<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404 에러 처리 페이지</title>
</head>
<body>

<h2>해당 페이지를 찾을 수 없습니다.</h2>
실패된 URI : ${pageContext.errorData.requestURI}<br>

</body>
</html>