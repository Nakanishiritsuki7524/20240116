package servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.BookDAO;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/register2.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jancd = request.getParameter("jancd");
		String isbncd = request.getParameter("isbncd");
		String bookname = request.getParameter("bookname");
		String bookkana = request.getParameter("bookkana");
		String priceString = request.getParameter("price");
		int price = 0;
		
		int error = 0;

		if (jancd == null || jancd.trim().isEmpty() || !jancd.matches("\\d+") || jancd.length() < 1 || jancd.length() > 13) {
		    request.setAttribute("error1", "※数字のみで1文字以上13文字以下で入力してください。");
		    error = 1;
		}

        if (isbncd == null || isbncd.trim().isEmpty()|| isbncd.length() < 1 || !isbncd.matches("\\d+") || isbncd.length() >13) {
		    request.setAttribute("error2", "※数字のみで1文字以上13文字以下で入力してください。");
		    error = 1;
        }
        
        if (bookname == null || bookname.trim().isEmpty()) {
            request.setAttribute("error3", "※名前を入力してください。");
            error = 1;
        }

        if (bookkana == null || bookkana.trim().isEmpty()) {
            request.setAttribute("error4", "※フリガナを入力してください。");
            error = 1;
        }
        
        if (priceString == null|| priceString.trim().isEmpty()|| !priceString.matches("\\d+") || priceString.length() < 1 || priceString.length() > 11) {
            request.setAttribute("error5", "※数字のみで1文字以上11文字以内の数字で入力してください");
            error = 1;
        }else {
        	 price = Integer.parseInt(priceString);
        }

        // エラーがある場合
        if (error == 1) {
            // 入力データをリクエストスコープにセット（適宜、入力データを再表示するため）
            request.setAttribute("jancd", jancd);
            request.setAttribute("isbncd", isbncd);
            request.setAttribute("bookname", bookname);
            request.setAttribute("bookkana", bookkana);
            request.setAttribute("priceString", priceString);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/register2.jsp");
            dispatcher.forward(request, response);
            
        } else {
		
		BookDAO bDao = new BookDAO();
		try {
			int count = bDao.register(jancd, isbncd, bookname, bookkana, price);
			if(count == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("clear", jancd + "を登録しました。");
			response.sendRedirect("ListServlet?clear=true");
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "システムエラーが発生しました");
	        response.sendRedirect("errorToAdmin.jsp");
		} catch(SQLException e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "データベースエラーが発生しました。しばらくしてから再開してください");
	        response.sendRedirect("errorToAdmin.jsp");
		} catch(Exception e) {
			  e.printStackTrace();
			  request.getSession().setAttribute("errorMessageToAdmin", "システムエラーが発生しました。早急に対応してください。");
			  response.sendRedirect("errorToAdmin.jsp");
			  return;
	}

	}
}
}
