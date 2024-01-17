<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="model.bean.* " %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>編集</title>
</head>
<body>
<h1>詳細</h1>
 <% EvaluationTask1Bean EvaluationTask1Bean = (EvaluationTask1Bean)request.getAttribute("EvaluationTask1Bean"); %>
<form action="EditServlet" method="post">
<input type="hidden" name="jancd" value="<%=EvaluationTask1Bean.getJANCD() %>">
<strong><label>BOOK_NAME：</label></strong>
<input type="text" name="name" value="<%=EvaluationTask1Bean.getBOOKNM() %>"><div>${error1}</div><br>
<strong><label>BOOK_KANA：</label></strong>
<input type="text" name="kananame" value="<%=EvaluationTask1Bean.getBOOKKANA() %>"><div>${error2}</div><br>
<strong><label>P R I C E：</label></strong>
<input type="text" name="price" value="<%=EvaluationTask1Bean.getPRICE() %>"><div>${error3}</div><br>
<button type="submit" name="button" value="edit">編集する</button>
<button type="submit" name="button" value="delete">削除する</button>
</form>
<a href="ListServlet">一覧に戻る</a>
</body>
</html>