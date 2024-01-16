package servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
		String isbncd = request.getParameter("isbancd");
		String bookname = request.getParameter("bookname");
		String bookkana = request.getParameter("bookkana");
		String priceString = request.getParameter("price");
		int price = Integer.parseInt(priceString);
		
		BookDAO bDao = new BookDAO();
		try {
			int count = bDao.register(jancd, isbncd, bookname, bookkana, price);
			if(count == 1) {
			response.sendRedirect("ListServlet");
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

