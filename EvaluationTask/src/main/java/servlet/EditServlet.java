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
	String button = request.getParameter("button");
	
	int price = 0;
	int error = 0;
	
	 if (name == null ||name.trim().isEmpty()) {
         request.setAttribute("error1", "※何か入力してください。");
         error = 1;
     }
	 
	 if (kananame == null || kananame.trim().isEmpty()) {
         request.setAttribute("error2", "※何か入力してください。");
         error = 1;
     }
	 
	 if (price1 == null || price1.trim().isEmpty()|| !price1.matches("\\d+") || price1.length() < 1 || price1.length() > 11) {
         request.setAttribute("error3", "※1文字以上11文字以内の数字で記入してください");
         error = 1;
     }else {
    	 price = Integer.parseInt(price1);
     }
	
	 if(error == 1) {
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/edit.jsp");
		 dispatcher.forward(request, response);
	 }else {
		 
			HttpSession session = request.getSession();
			BookDAO bDao = new BookDAO();
	try {
		if ("edit".equals(button)) {
			long update = bDao.editEvaluationTask1(name, kananame, price, jancd);
			if(update == 1) 
			response.sendRedirect("ListServlet");
		} else if ("delete".equals(button)) {
			long count = bDao.delete(jancd);
			if(count == 1) 
			session.setAttribute("delete", jancd + "を削除しました。");
			response.sendRedirect("ListServlet?delete=true");
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
}