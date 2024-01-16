<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規作成</title>
</head>
<body>
<h1>新規作成</h1>
<form action="RegisterServlet" method="post">
<strong><label>JAN_CD：</label></strong>
<input type="text" name="jancd"><br>
<strong><label>ISBN_CD：</label></strong>
<input type="text" name="isbncd"><br>
<strong><label>BOOK_NM：</label></strong>
<input type="text" name="bookname"><br>
<strong><label>BOOK_KANA：</label></strong>
<input type="text" name="bookkana"><br>
<strong><label>PRICE：</label></strong>
<input type="text" name="price"><br>
<button type="submit">登録</button>
</form>
<a href="ListServlet">一覧に戻る</a>
</body>
</html>