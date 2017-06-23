<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello world Example</title>
</head>
	 <body>
	 	<jsp:include page="addHeadLogin.jsp" />
	     <h1>Hello ${name.size() == 0}, How are youš?</h1>
	     <br/>
	     
	     <c:if test="${name.size() == 0}">It's false!</c:if>
	     <p>JSP demo, works!</p>
	 </body>
</html>