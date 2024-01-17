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
<input type="text" name="jancd" placeholder="1~13文字以内の数字" value="${jancd}"><div>${error1}</div><br>
<strong><label>ISBN_CD：</label></strong>
<input type="text" name="isbncd" placeholder="1~13文字以内の数字" value="${isbncd}"><div>${error2}</div><br>
<strong><label>BOOK_NAME：</label></strong>
<input type="text" name="bookname" placeholder="名前を入力してください" value="${bookname}"><div>${error3}</div><br>
<strong><label>BOOK_KANA：</label></strong>
<input type="text" name="bookkana" placeholder="フリガナを入力してください" value="${bookkana}"><div>${error4}</div><br>
<strong><label>PRICE：</label></strong>
<input type="text" name="price" placeholder="1~11文字以内の数字" value="${priceString}"><div>${error5}</div><br>
<button type="submit">登録</button>
</form>
<a href="ListServlet">一覧に戻る</a>
</body>
</html>