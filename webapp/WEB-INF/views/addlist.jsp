<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="/guestbook3/add" method="get">
		<table border="1" width="500px">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan="4"><textarea name="content" cols=60 rows=5></textarea></td>
			</tr>
			
			<tr>
				<td colspan='4'><button type="submit">확인</button></td>
			</tr>
		
		</table>
	</form>
	<br/>

		
	<c:forEach items = "${guestbookList }" var = "guestVo"  >
			<table border="1"  width="500px">
		
				<tr>
					<td>[${guestVo.no }]</td>
					<td>${guestVo.name }</td>
					<td>${guestVo.regDate }</td>
					
				<td><a href="/guestbook3/deleteform?no=${guestVo.no}">삭제</a></td>

				</tr>
				<tr>
					<td colspan=4>${guestVo.content }</td>
				</tr>
			</table>
		
	    <br/>
	</c:forEach>
</body>
</html>