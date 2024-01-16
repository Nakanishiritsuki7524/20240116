package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import connection.DBConnection;
import model.bean.EvaluationTask1Bean;

public class BookDAO {
	
	//ユーザーの情報一覧を取得
			public List<EvaluationTask1Bean> getAllEvaluationTask1() throws ClassNotFoundException {
		        List<EvaluationTask1Bean> EvaluationTask1List = new ArrayList<>();
		        String sql = "SELECT * FROM BOOK";
		        try(Connection con = DBConnection.getConnection();
					PreparedStatement stmt = con.prepareStatement(sql)){
		            ResultSet res = stmt.executeQuery();
		             
		            while (res.next()) {
		            	EvaluationTask1Bean EvaluationTask1 = new EvaluationTask1Bean();
		            	EvaluationTask1.setJANCD(res.getString("JAN_CD"));
		            	EvaluationTask1.setISBNCD(res.getString("ISBN_CD"));
		            	EvaluationTask1.setBOOKNM(res.getString("BOOK_NM"));
		            	EvaluationTask1.setBOOKKANA(res.getString("BOOK_KANA"));
		            	EvaluationTask1.setPRICE(res.getInt("PRICE"));
		            	EvaluationTask1.setISSUEDATE(res.getString("ISSUE_DATE"));
		            	EvaluationTask1.setCREATEDATE(res.getString("CREATE_DATETIME"));
		            	EvaluationTask1.setUPDATEDATE(res.getString("UPDATE_DATETIME"));
		                EvaluationTask1List.add(EvaluationTask1);
		            }
		        } catch (SQLException e) {
		            e.printStackTrace(); // エラーハンドリングは適切に行うべき
		        }

		        return EvaluationTask1List;
		    }
			//JADCDごとに表示する
			public EvaluationTask1Bean getEvaluationTask1(long JANCD) throws ClassNotFoundException, SQLException {
				String sql = "SELECT * FROM BOOK WHERE JAN_CD = ?";
				EvaluationTask1Bean EvaluationTask1Bean = null;//ここはnullじゃないといけいないのか
				try(Connection con = DBConnection.getConnection();
						PreparedStatement stmt = con.prepareStatement(sql)){
					stmt.setLong(1, JANCD);
					ResultSet res = stmt.executeQuery();
					while(res.next()) {
						EvaluationTask1Bean = new EvaluationTask1Bean();
						EvaluationTask1Bean.setJANCD(res.getString("JAN_CD"));
						EvaluationTask1Bean.setBOOKNM(res.getString("BOOK_NM"));
						EvaluationTask1Bean.setBOOKKANA(res.getString("BOOK_KANA"));
						EvaluationTask1Bean.setPRICE(res.getInt("PRICE"));
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
					return EvaluationTask1Bean;
			}
			//
			public int editEvaluationTask1(String name, String kanaName, int price, long jancd)
					throws SQLException, ClassNotFoundException {
				int row = 0;
				String sql = "UPDATE BOOK SET BOOK_NM = ?, BOOK_KANA = ?, PRICE = ?  WHERE JAN_CD = ?";
				try (Connection con = DBConnection.getConnection();
			    		PreparedStatement pstmt = con.prepareStatement(sql)) {
			        pstmt.setString(1, name);
			        pstmt.setString(2, kanaName);
			        pstmt.setInt(3, price);
			        pstmt.setLong(4, jancd);
			        row = pstmt.executeUpdate();
			    } catch (SQLException e) {
			    	System.err.println("SQLエラーが発生しました。エラーメッセージ: " + e.getMessage() + 
		                               ", SQLステート: " + e.getSQLState() + 
		                               ", エラーコード: " + e.getErrorCode());
			    } catch (Exception e) {
			    	System.err.println("予期せぬ例外が発生しました。エラーの種類: " + e.getClass().getName() + 
		                               ", メッセージ: " + e.getMessage() + 
		                               ", スタックトレース: " + Arrays.toString(e.getStackTrace()));
			    }
			    return row;
			}
}
