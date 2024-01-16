<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="java.util.* ,model.bean.* " %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List</title>
</head>
<body>
<h1>一覧</h1>
<table border="1">
        <thead>
            <tr>
                <th>EvaluationTask1 ID</th>
                <th>Title</th>
                <th>Price</th>
            </tr>
        </thead>
        <tbody>
            <% List<EvaluationTask1Bean> EvaluationTask1List = (List<EvaluationTask1Bean>) session.getAttribute("EvaluationTask1List"); %>
            
            <% if (EvaluationTask1List != null && !EvaluationTask1List.isEmpty()) { %>
           
            <% for(EvaluationTask1Bean EvaluationTask1 : EvaluationTask1List){ %>
                    <tr>
                        <td><a href="EditServlet?jANCD=<%=EvaluationTask1.getJANCD() %>"><%= EvaluationTask1.getJANCD() %></a></td>
                        <td><%= EvaluationTask1.getBOOKNM() %></td>
                        <td>¥<%= EvaluationTask1.getPRICE() %></td>
                    </tr>
                <% } %>
            <% } else { %>
                <tr>
                    <td colspan="4">No　</td>
                </tr>
            <% } %>
        </tbody>
    </table> 
 <!--    <a href="RegisterServlet">新規作成</a> -->
</body>
</html>