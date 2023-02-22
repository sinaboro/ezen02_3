<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var = "user" value="${SPRING_SECURITY_CONTEXT.authentication.principal} "/>
<c:set var = "auth" value="${SPRING_SECURITY_CONTEXT.authentication.authorities}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Admin Page</h1>
	<p>principal : <sec:authentication property="principal"/></p>
	<p>MemberVO : <sec:authentication property="principal.member"/>
	<p>사용자이름 :  <sec:authentication property="principal.member.userName"/></p>
	<p>사용자 권한리스트 : <sec:authentication property="principal.member.authList"/></p>
	<a href="/customLogout">Logout</a>
</body>
</html>