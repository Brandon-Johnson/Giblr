<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Giblr | Artist</title>
    <link href="style.css" rel="stylesheet" type="text/css">
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
    <header>
        <a href="artistSearch.jsp" id="home" class="fa fa-home fa-2x"></a>
        <h1>Giblr</h1>
    </header>
    <div id="main-wrap">
        <h2>${name}</h2>
        <c:forEach items="${sd}" var="s">
	        <div class="album">
	            <div class="album-info">
	                <img src="${s.image}" width="200" class="album-art">
	                <div class="album-name">${s.name}</div>
	            </div>
	            <iframe src="https://embed.spotify.com/?uri=spotify:album:${s.id}" width="700" height="500" frameborder="0" allowtransparency="true"></iframe>
	            <img class="album-art">
	        </div>
        </c:forEach>
    </div>
</body>
</html>