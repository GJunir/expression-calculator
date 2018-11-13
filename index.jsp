<%--
  Created by IntelliJ IDEA.
  User: Gabidullin
  Date: 13.06.2018
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>OnlineCalculator</title>

</head>

<body>

<context reloadble="true"></context>

<%
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader ("Expires", -1);
%>
<p class="center">Введите числа и операции:</p>
<form action="${pageContext.request.contextPath}/Servlets" method="post">
  <input class="center" name="expressionField" type="text" size="15" onkeyup="return check(this);" onchange="return check(this);" />
  <button class="center" name="submit" value="submit">Calculate</button>
</form>
<script type="text/javascript">
    function check(input) {
        input.value = input.value.replace(/[^\d.+*\( \) \/-]/g, '');
    }

</script>
<p></p>
<p></p>
<p><%= new java.util.Date() %></p>
</body>
</html>