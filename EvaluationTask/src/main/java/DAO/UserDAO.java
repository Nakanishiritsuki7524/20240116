package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import connection.DBConnection;
import model.Bean.InformationBean;
import model.Bean.UserBean;

public class UserDAO {
		//新規登録用
		public int registerUser(String userName, String email, String hashpass) throws ClassNotFoundException {
			int count = 0;
			String sql = "INSERT INTO user (user_name, email, password) VALUES (?, ?, ?)";
			try(Connection con = DBConnection.getConnection();
					PreparedStatement stmt = con.prepareStatement(sql)){
				stmt.setString(1, userName);
				stmt.setString(2, email);
				stmt.setString(3, hashpass);
				count = stmt.executeUpdate();
			}catch (SQLException e) {
		        e.printStackTrace(); 
		    }
		    
		    return count;
		}
		//ログイン用
		public UserBean loginUser(String email,String password) throws ClassNotFoundException, SQLException {
			UserBean uBean = new UserBean();
			String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
			try(Connection con = DBConnection.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)){
				stmt.setString(1, email);
				stmt.setString(2, password);
				ResultSet res = stmt.executeQuery();
				while(res.next()){
					uBean = new UserBean();
					uBean.setUserId(res.getInt("user_id"));
					uBean.setUserName(res.getString("user_name"));
					uBean.setEmail(res.getString("email"));
					uBean.setPasswoed(res.getString("password"));
				}
			}catch (SQLException e) {
				System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
                        ", SQLステート: " + e.getSQLState() + 
                        ", エラーコード: " + e.getErrorCode());
			} catch (Exception e) {
				System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
                        ", メッセージ: " + e.getMessage() + 
                        ", スタックトレース: " + Arrays.toString(e.getStackTrace()));
			}
			return uBean;
		}
	//ユーザーのIDを取得
		public int getUserId(String email) throws ClassNotFoundException, SQLException {
			int userId = -1;
			String sql = "SELECT user_id FROM user WHERE email = ?";
			try(Connection con = DBConnection.getConnection();
					PreparedStatement stmt = con.prepareStatement(sql)){
				stmt.setString(1, email);
				ResultSet res = stmt.executeQuery();
				if(res.next()) {
				   userId = res.getInt("user_id");
				}
			}catch (SQLException e) {
				System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
                        ", SQLステート: " + e.getSQLState() + 
                        ", エラーコード: " + e.getErrorCode());
			} catch (Exception e) {
				System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
                        ", メッセージ: " + e.getMessage() + 
                        ", スタックトレース: " + Arrays.toString(e.getStackTrace()));
			}
			return userId;
		}
		//ユーザーのタイトルやコンテンツを取得←いらない気がするListでどうせ取るから
		public InformationBean getUserInformationId(int userId) throws ClassNotFoundException, SQLException {
			String sql = "SELECT * FROM information WHERE user_id = ?";
			InformationBean information = null;//ここはnullじゃないといけいないのか
			try(Connection con = DBConnection.getConnection();
					PreparedStatement stmt = con.prepareStatement(sql)){
				stmt.setInt(1, userId);
				ResultSet res = stmt.executeQuery();
				while(res.next()) {
					information = new InformationBean();
					information.setInformationId(res.getInt("information_id"));
					information.setUserId(res.getInt("user_id"));
					information.setTitel(res.getString("titel"));
					information.setContent(res.getString("content"));
			}
			}catch (SQLException e) {
					System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
	                        ", SQLステート: " + e.getSQLState() + 
	                        ", エラーコード: " + e.getErrorCode());
				} catch (Exception e) {
					System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
	                        ", メッセージ: " + e.getMessage() + 
	                        ", スタックトレース: " + Arrays.toString(e.getStackTrace()));
				}
				return information;
		}
		//ユーザーの情報一覧を取得
		public List<InformationBean> getAllUserInformation(int userId) {
	        List<InformationBean> informationList = new ArrayList<>();
	        String sql = "SELECT * FROM information WHERE user_id = ?";
	        try(Connection con = DBConnection.getConnection();
					PreparedStatement stmt = con.prepareStatement(sql)){
	             stmt.setInt(1, userId);
	             ResultSet res = stmt.executeQuery();
	             
	            while (res.next()) {
	                InformationBean information = new InformationBean();
	                information.setInformationId(res.getInt("information_id"));
	                information.setTitel(res.getString("titel"));
	                information.setContent(res.getString("content"));
	                informationList.add(information);
	            }
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace(); // エラーハンドリングは適切に行うべき
	        }

	        return informationList;
	    }
		
		//新規作成
		public int registerList(int userId,String titel,String content) throws ClassNotFoundException {
			int count = 0;
			String sql = "INSERT INTO information (user_id, titel, content) VALUES (?, ?, ?)";
			try(Connection con = DBConnection.getConnection();
					PreparedStatement stmt = con.prepareStatement(sql)){
				stmt.setInt(1, userId);
				stmt.setString(2, titel);
				stmt.setString(3, content);
				count = stmt.executeUpdate();
			}catch (SQLException e) {
		        e.printStackTrace(); 
		    }
		    
		    return count;
		}
		//タイトルとコンテンツを取得
		public InformationBean getInformation(int informationId) {
			InformationBean infor = new InformationBean();
		String sql = "SELECT information_id, titel, content FROM information WHERE information_id = ?";
		try 
			(Connection con = DBConnection.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setInt(1, informationId);
			ResultSet res = stmt.executeQuery();
			while(res.next()) {
			infor.setInformationId(res.getInt("information_id"));
			infor.setTitel(res.getString("titel"));
			infor.setContent(res.getString("content"));
			}
		}catch (SQLException e) {
				System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
                    ", SQLステート: " + e.getSQLState() + 
                    ", エラーコード: " + e.getErrorCode());
		}catch (Exception e) {
				System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
                    ", メッセージ: " + e.getMessage() + 
                    ", スタックトレース: " + Arrays.toString(e.getStackTrace()));
		}
		return infor;
	}
		//データを消すときに必要なメソッド
		public int informationDelete(int informationId) throws ClassNotFoundException, SQLException {
			int rowsAffected = 0;
		String sql = "DELETE FROM information WHERE information_id = ?";
		try (Connection con = DBConnection.getConnection();
		     PreparedStatement stmt = con.prepareStatement(sql)) {
		     stmt.setInt(1, informationId);
		     rowsAffected = stmt.executeUpdate();
		    }
	     return rowsAffected;
		}
		public int updateInformation(String titel,String content,int informationId) {
			int cont = 0;
			String sql = "UPDATE information SET titel = ?, content = ? WHERE information_id = ?";
		}
}