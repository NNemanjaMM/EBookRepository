<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="fill-width">
	<div class="contentHolder">
		<c:if test="${sessionUser == null}">
			<nav class="navbar-ribbon navbar navbar-default navbar-fixed-top">
				<div class="container">
			        <div class="navbar-collapse collapse">				
			        	<p class="nav navbar-nav navbar-title"><i>You are currently not registered. Please log in or register to get full expirience.</i></p>	
			          	<div class="nav navbar-nav navbar-right">
			          		<a href="/authentication"><button class="btn btn-default" style="width: 8em;">Sign In</button></a>
			          	</div>
			        </div>
			    </div>
			</nav>
		</c:if>	
		<c:if test="${sessionUser != null}">
			<nav class="navbar-ribbon navbar navbar-default navbar-fixed-top">
				<div class="container">
			        <div class="navbar-collapse collapse">				
			          	<div class="nav navbar-nav navbar-right">
			          		<i>Welcome, ${sessionUser.firstName} &nbsp;&nbsp;</i>
			          		<a href="/account"><button class="btn btn-default" style="width: 8em;">Edit Account</button></a>
			          		<a href="/logout"><button class="btn btn-default" style="width: 8em;">Sign Out</button></a>
			          	</div>
			        </div>
			    </div>
			</nav>
		</c:if>	

    	<div class="header">
			<div class="header-image">
				<img src="resources/static/images/el_icon.png"/>
			</div>
			<div class="header-menu">
				<h1> &nbsp;&nbsp;&nbsp;&nbsp; <span>e-Library</span> &nbsp;&nbsp; Online E-Books Repository</h1>
				
				<c:if test="${sessionUser != null}">
				<c:if test="${sessionUser.type == 'a'}">
					<ul class="nav nav-tabs">
						<li role="presentation" class="">
							<a href="/categorymanage">Update Categories</a>
						</li>
						<li role="presentation" class="dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" href="#"> Update Books <span class="caret"></span></a>
						    <ul class="dropdown-menu">
						     	<li><a href="/bookmanage">All Books</a></li>						     				
								<c:forEach items="${categories}" var="cat">
						     		<li class=""><a href="/category?categoryId=${cat.id}">${cat.name}</a></li>
								</c:forEach>
						    </ul>
						</li>		
						<li role="presentation" class="">
							<a href="/usermanage">Update Users</a>
						</li>
					</ul>
				</c:if>
				</c:if>
			</div>
		</div>
	</div>
</div>