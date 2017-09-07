<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
</style>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid" style="margin-top:7px;">
	<table id="cart" class="table table-hover table-condensed">
    				<thead>
						<tr>
							<th style="width:50%">Product</th>
							<th style="width:10%">Price</th>
							<th style="width:8%">Quantity</th>
							<th style="width:22%" class="text-center">Subtotal</th>
							<th style="width:10%"></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td data-th="Product">
								<div class="row">
									<div class="col-sm-2 hidden-xs"><img src="<c:url value='/images/Items/oneplus5_1.jpeg'/>" alt="..." class="img-responsive"/></div>
									<div class="col-sm-10">
										<h4 class="nomargin">OnePlus 5 6GB Black</h4>
										<p>Quis aute iure reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Lorem ipsum dolor sit amet.</p>
									</div>
								</div>
							</td>
							<td data-th="Price"><input type="hidden" id="price" value="32999"/><p id="setprice">Rs 32,999</p></td>
							<td data-th="Quantity">
							<form>
								<input type="number" id="cartid" class="form-control text-center" onblur="calc()" value="1"/>
							</form>
							</td>
							<td data-th="Subtotal" class="text-center"><input type="hidden" id="subprice" value="32999"/><p id="setsubprice">Rs 32,999</p></td>
							<td class="actions" data-th="">
								<button class="btn btn-info btn-sm"><i class="fa fa-refresh"></i></button>
								<button class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i></button>								
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr class="visible-xs">
							<td class="text-center"><strong>Total Rs 32,999</strong></td>
						</tr>
						<tr>
							<td><a href="Landing.jsp" class="btn btn-warning"><i class="fa fa-angle-left"></i> Continue Shopping</a></td>
							<td colspan="2" class="hidden-xs"></td>
							<td class="hidden-xs text-center"><strong><input type="hidden" id="totalprice" value="32999"/><p id="settotalprice">Total Rs 32,999</p></strong></td>
							<td><a href="#" class="btn btn-success btn-block">Checkout <i class="fa fa-angle-right"></i></a></td>
						</tr>
					</tfoot>
				</table>
</div>
<script>
function calc()
{
	var quantity = document.getElementById("cartid").value;
	var price = document.getElementById("price").value;
	var val;
	if(quantity>0)
		{
			val = quantity*price;;
			document.getElementById("setsubprice").innerHTML=val;
			document.getElementById("settotalprice").innerHTML=val;
		}
}
</script>

<jsp:include page="footer.jsp"/>
</body>
</html>