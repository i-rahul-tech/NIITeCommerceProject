<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Welcome</title>
<style>

</style>
</head>
<body>
<jsp:include page="header.jsp"/>
<!-- Carousel Start -->
<div class="container-fluid"> 
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">
      <div class="item active">
        <img src="<c:url value='/images/carousel1.PNG'/>" alt="Unable To Load Item1" style="width:100%;height:300px;">
      </div>

      <div class="item">
        <img src="<c:url value='/images/carousel2.jpg'/>" alt="Unable To Load Item2" style="width:100%;height:300px;">
      </div>
    
      <div class="item">
        <img src="<c:url value='/images/carousel3.jpg'/>" alt="Unable To Load Item3" style="width:100%;height:300px;">
      </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</div>
<!-- Carousel End -->

<div class="container-fluid" style="margin:15px 2% 5px 2%">
<h3>Trending</h3>
<div class="row">
<div class="col-sm-3">
<table>
<tr>
<td>
<a href="#"><img src="<c:url value='/images/Items/oneplus5_1.jpeg'/>" class="img-thumbnail" alt="Unable to Load" style="width:250px;height:220px;"/>
</td>
</tr>
<tr><td><p class="text-center" style="font-size:16px;"><b>OnePlus 5</b></p></td></tr>
<tr><td><p class="text-center" style="color:#1a66ff;font-size:15px;">Price: &#x20B9; 32,999</p></td></tr>
</table>
</div>
<div class="col-sm-3">
<table>
<tr>
<td>
<a href="#"><img src="<c:url value='/images/Items/Nike FlyEase.jpg'/>"  class="img-thumbnail" alt="Unable to Load" style="width:250px;height:220px;"/></a>
<tr><td><p class="text-center" style="font-size:16px;"><b>Nike FlyEase</b></p></td></tr>
<tr><td><p class="text-center" style="color:#1a66ff;font-size:15px;">Price: &#x20B9; 14,999</p></td></tr>
</table>
</div>
<div class="col-sm-3">
<table>
<tr>
<td>
<a href="#"><img src="<c:url value='/images/Items/samsung_S8_1.jpg'/>" class="img-thumbnail" alt="Unable to Load" style="width:250px;height:220px;"/></a>
<tr><td><p class="text-center" style="font-size:16px;"><b>Samsung S8</b></p></td></tr>
<tr><td><p class="text-center" style="color:#1a66ff;font-size:15px;">Price: &#x20B9; 49,999</p></td></tr>
</table>
</div>
<div class="col-sm-3">
<table>
<tr>
<td>
<a href="#"><img src="<c:url value='/images/Items/Polo_1.jpg'/>" class="img-thumbnail" alt="Unable to Load" style="width:250px;height:220px;"/></a>
<tr><td><p class="text-center" style="font-size:16px;"><b>Polo T-Shirt</b></p></td></tr>
<tr><td><p class="text-center" style="color:#1a66ff;font-size:15px;">Price: &#x20B9; 999</p></td></tr>
</table>
</div>
</div>
<div class="row">
<div class="col-sm-3">
<table>
<tr>
<td>
<a href="#"><img src="<c:url value='/images/Items/Adidas_Neo_1.jpg'/>" class="img-thumbnail" alt="Unable to Load" style="width:250px;height:220px;"/></a>
</td>
</tr>
<tr><td><p class="text-center" style="font-size:16px;"><b>Adidas Neo Cloudfoam</b></p></td></tr>
<tr><td><p class="text-center" style="color:#1a66ff;font-size:15px;">Price: &#x20B9; 3,999-4,999</p></td></tr>
</table>
</div>
<div class="col-sm-3">
<table>
<tr>
<td>
<a href="#"><img src="<c:url value='/images/Items/iphone_7+_1.png'/>"  class="img-thumbnail" alt="Unable to Load" style="width:250px;height:220px;"/></a>
<tr><td><p class="text-center" style="font-size:16px;"><b>Iphone 7 Plus</b></p></td></tr>
<tr><td><p class="text-center" style="color:#1a66ff;font-size:15px;">Price: &#x20B9; 52,994</p></td></tr>
</table>
</div>
<div class="col-sm-3">
<table>
<tr>
<td>
<a href="#"><img src="<c:url value='/images/Items/jblt100_1.jpg'/>" class="img-thumbnail" alt="Unable to Load" style="width:250px;height:220px;"/></a>
<tr><td><p class="text-center" style="font-size:16px;"><b>JBL T100A</b></p></td></tr>
<tr><td><p class="text-center" style="color:#1a66ff;font-size:15px;">Price: &#x20B9; 1,150</p></td></tr>
</table>
</div>
<div class="col-sm-3">
<table>
<tr>
<td>
<a href="#"><img src="<c:url value='/images/Items/Samsung_LED_1.jpg'/>" class="img-thumbnail" alt="Unable to Load" style="width:250px;height:220px;"/></a>
<tr><td><p class="text-center" style="font-size:16px;"><b>Samsung Smart 43 Inch LED</b></p></td></tr>
<tr><td><p class="text-center" style="color:#1a66ff;font-size:15px;">Price: &#x20B9; 52,999</p></td></tr>
</table>
</div>
</div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>