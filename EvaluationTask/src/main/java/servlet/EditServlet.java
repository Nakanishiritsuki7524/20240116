package servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.EvaluationTask1Bean;
import model.dao.BookDAO;


@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		long jANCD = Long.parseLong(request.getParameter("jANCD"));
		
		EvaluationTask1Bean EvaluationTask1Bean = new EvaluationTask1Bean();
		BookDAO bDao = new BookDAO();
		
		try {
			EvaluationTask1Bean = bDao.getEvaluationTask1(jANCD);
			request.setAttribute("EvaluationTask1Bean", EvaluationTask1Bean);
		}catch(Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("errorMessage", "申し訳ありませんが、システムエラーが発生しました。"
					+ "もう一度お試しいただくか、お問い合わせより管理者にお問い合わせください。");
			response.sendRedirect("error.jsp");
			return;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/edit.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	String jancd1 = request.getParameter("jancd");
	long jancd = Long.parseLong(jancd1);
	String name = request.getParameter("name");
	String kananame = request.getParameter("kananame");
	String price1 = request.getParameter("price");
	int price = Integer.parseInt(price1);
	String button = request.getParameter("button");
	
	BookDAO bDao = new BookDAO();
	
	try {
		if ("edit".equals(button)) {
			long update = bDao.editEvaluationTask1(name, kananame, price, jancd);
			if(update == 1) 
			response.sendRedirect("ListServlet");
		} else if ("delete".equals(button)) {
			long count = bDao.delete(jancd);
			if(count == 1) 
			response.sendRedirect("ListServlet");
		}
	}catch(ClassNotFoundException e) {
		e.printStackTrace();
		request.getSession().setAttribute("errorMessage", "内部の設定エラーが発生しました。"
				+ "お問い合わせよ管理者に連絡して、解決の支援を受けてください。");
        response.sendRedirect("errorToAdmin.jsp");
	} catch(SQLException e) {
		e.printStackTrace();
		request.getSession().setAttribute("errorMessage", "現在データベースにアクセスできません。後ほど再度お試しください。"
				+ "問題が続く場合は、お問い合わせより管理者にご連絡ください。");
		response.sendRedirect("errorToAdmin.jsp");
	}
	catch(Exception e) {
		e.printStackTrace();
		request.getSession().setAttribute("errorMessage", "申し訳ありませんが、システムエラーが発生しました。"
				+ "もう一度お試しいただくか、お問い合わせより管理者にお問い合わせください。");
		response.sendRedirect("errorToadmin.jsp");
	}
	}

}
