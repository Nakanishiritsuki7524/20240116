package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.EvaluationTask1Bean;
import model.dao.BookDAO;

@WebServlet("/ListServlet")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String clear = (String) session.getAttribute("clear");
		String delete = (String) session.getAttribute("delete");
		session.removeAttribute("clear"); // 一度取得したらクリアする
		session.removeAttribute("delete");
		
		BookDAO bDao = new BookDAO();
		List<EvaluationTask1Bean> EvaluationTask1List = new ArrayList<>();
		
		try {
			session.setAttribute("clear", clear);
			session.setAttribute("delete", delete);
			EvaluationTask1List = bDao.getAllEvaluationTask1();
			session.setAttribute("EvaluationTask1List", EvaluationTask1List);
		}catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage","");
			response.sendRedirect("error.jsp");
			return;
	}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/list.jsp");
		dispatcher.forward(request, response);



}
}