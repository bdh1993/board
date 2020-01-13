<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="bootstrap.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Detail</title>
</head>
<body>
<h2>게시글 상세</h2>

<div class="container">
	<form action="/insertProc" method="post">
		<div class="form-group">
			<label>제목</label>
			<p>${detail.subject}</p>
		</div>
		<div class="form-group">
			<label>작성자</label>
			<p>${detail.writer}</p>
		</div>
		<div class="form-group">
			<label>날짜</label>
			<fmt:formatDate value="${detail.reg_date}" pattern="yyyy.MM.dd HH:mm:ss"/>
		</div>
		<div class="form-group">
			<label>내용</label>
			<p>${detail.content}</p>
		</div>
	</form>
		<div class="btn-group btn-group-sm" role="group" style="float:right;">
			<button type="button" class="btn btn-default" onclick="location.href='/delete/${detail.bno}'">삭제</button>
         	<button type="button" class="btn btn-default" onclick="location.href='/update/${detail.bno}'">수정</button>
          	<button type="button" class="btn btn-default" onclick="location.href='/list'"> 목록 </button>
		</div>
	
	
<!-- 댓글 -->
	<div class="container">
		<label for="content">comment</label>
		<form name="commentInsertForm">
			<div class="input-group">
				<input type="hidden" name="bno" value="${detail.bno}"/>
				<input type="text" class="form-control" id="content" name="content" placeholder="내용을 입력하세요.">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button" name="commentInsertBtn">등록</button>
				</span>
			</div>
		</form>
	</div>
	<div class="container">
		<div class="commentList"></div>
	</div>
</div>

<%@ include file="commentS.jsp" %>

</body>
</html>