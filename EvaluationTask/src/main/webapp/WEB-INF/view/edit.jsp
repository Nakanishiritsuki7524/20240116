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
 <% EvaluationTask1Bean EvaluationTask1Bean = (EvaluationTask1Bean)request.getAttribute("EvaluationTask1Bean"); %>
<form action="EditServlet" method="post">
<input type="hidden" name="jancd" value="<%=EvaluationTask1Bean.getJANCD() %>">
<strong><label>名　前：</label></strong>
<input type="text" name="name" value="<%=EvaluationTask1Bean.getBOOKNM() %>"><br>
<strong><label>カナ名：</label></strong>
<input type="text" name="kananame" value="<%=EvaluationTask1Bean.getBOOKKANA() %>"><br>
<strong><label>価　格：</label></strong>
<input type="text" name="price" value="<%=EvaluationTask1Bean.getPRICE() %>"><br>
<button type="submit">編集する</button>
</form>
<a href="ListServlet">一覧に戻る</a><!-- jdなおjフォjn青jnファジャのj -->
</body>
</html>