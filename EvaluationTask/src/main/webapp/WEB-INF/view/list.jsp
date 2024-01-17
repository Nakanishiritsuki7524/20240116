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
<% String delete = request.getParameter("delete"); %>
<% String clear = request.getParameter("clear"); %>
<% if ("true".equals(clear)) { %>
    <p><%= session.getAttribute("clear") %></p>
<% } else if("true".equals(delete)){%>
    <p><%= session.getAttribute("delete") %></p>
<% } %>

<table border="1">
        <thead>
            <tr>
                <th>EvaluationTask ID</th>
                <th>BookName</th>
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
                    <td colspan="4">Empty</td>
                </tr>
            <% } %>
        </tbody>
    </table> 
 <a href="RegisterServlet">新規作成</a>
</body>
</html>