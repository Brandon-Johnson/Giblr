<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Giblr | Home</title>
    <link href="style.css" rel="stylesheet" type="text/css">
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
    <header>
        <a href="artistList.jsp" id="home" class="fa fa-home fa-2x"></a>
        <h1>Giblr</h1>
    </header>
    <div id="main-wrap">
	    <div id="search-wrap">
	    	<label>Search for Artist:</label>
	        <input type="text" />
	        <input class="submit" type="submit" value="Search" />
        </div>
    </div>
</body>
</html>