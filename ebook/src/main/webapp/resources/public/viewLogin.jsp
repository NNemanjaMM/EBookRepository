<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE>
<html>
<head>
	<jsp:include page="components/addHeadLogin.jsp" />
</head>
	 <body>
		<div class="jumbotron">
		    <div>
		        <span>
		            <h2>Welcome to online <b>e-Library</b>.</h2>
		            <h4><i>Please log in.</i></h4>
		        </span>
		    </div>
		</div>
		<div class="container">
		    <div class="row">
		        <div class="col-md-4 col-md-offset-4">
		            <div class="panel panel-login">
		                <div class="panel-heading">
		                    <div class="row">
		                        <div class="col">
		                            <a href="#" class="active" id="login-form-link">Log In</a>
		                        </div>
		                    </div>
		                    <hr>
		                </div>
		                <div class="panel-body">
		                    <div class="row">
		                        <div class="col-lg-12">
		                            <div>
		                            	<form id="login-form" method="post" action="login">
		                                    <div class="form-group">
		                                        <input type="text" name="username" id="email" tabindex="1" class="form-control" placeholder="Username" value="">
		                                    </div>
		                                    <div class="form-group">
		                                        <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password">
		                                    </div>
		                                    <div class="form-group">
		                                        <div class="row">
		                                            <div class="col-sm-6 col-sm-offset-3">
		                                                <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Log In!">
		                                            </div>
		                                        </div>
		                                    </div>
		                                </form>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
	 
	 </body>
</html>