<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Spring Mvc WebFlow Demo</title>
		<link href="style.css" rel="stylesheet" type="text/css" />
	</head>
 
	<body>
		<div class="content">
			<fieldset>
				<legend>Confirm Details</legend>
				<!-- for triggering webflow events using links,
					 the eventId to be triggered is given in "href" attribute as:
				 -->
				<sf:form modelAttribute="user">
					<sf:label path="ufname">First Name:</sf:label>${user.ufname}
					<br /><br />
					<sf:label path="ulname">Last Name:</sf:label>${user.ulname}
					<br /><br />
					<sf:label path="uemail">Email Id:</sf:label>${user.uemail}
					<br /><br />
					<sf:label path="password">Password:</sf:label>${user.password}
					<br /><br />
					<sf:label path="gender">Gender:</sf:label>${user.gender}
					<br /><br />
					<sf:label path="contact">Contact:</sf:label>${user.contact}
					<br /><br />
					<sf:label path="address">Address:</sf:label>${user.address}
					<br /><br />
					<sf:label path="pincode">PinCode:</sf:label>${user.pincode}
					<br /><br />
					<sf:label path="state">State:</sf:label>${user.state}
					<br /><br />
					<sf:label path="country">Country:</sf:label>${user.country}
					<br /><br />
					<!-- for triggering webflow events using form submission,
					 the eventId to be triggered is given in "name" attribute as:
					-->
					<input name="_eventId_edit" type="submit" value="Edit" /> 
					<input name="_eventId_submit" type="submit" value="Confirm Details" /><br />
				</sf:form>
			</fieldset>
		</div>
	</body>
</html>
