<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE>
<html>
<head>
	<jsp:include page="components/addHeadMain.jsp" />
</head>
	<body>
	 
		<jsp:include page="components/addHeader.jsp" />
	 	
		
		<div class="middle contentHolder">
			<jsp:include page="components/addSideNavigation.jsp" />
		
			<div class="content">
    			<div class="center-content">
					
					<jsp:include page="components/addTableCategories.jsp" />
					
        		</div>
			</div>		
		</div>
	 	
		<jsp:include page="components/addFooter.jsp" />
	</body>
</html>