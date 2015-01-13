<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html manifest="">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta charset="UTF-8">

	<title>YesdoApp</title>

	<script src="resources/common.js" type="text/javascript"></script>
	<script type="text/javascript">
		provide('App.config').loggedIn = ${loggedIn};

		<c:if test="${not empty id}">
		provide('App.config').id = ${id};
		</c:if>

		<c:if test="${not empty login}">
		provide('App.config').login = '${login}';
		</c:if>

		provide('App.config').permissions = [];
		<c:forEach items="${permissions}" var="item">
		provide('App.config').permissions.push('${item}');
		</c:forEach>

	</script>
	<!-- The line below must be kept intact for Sencha Cmd to build your application -->
	<script id="microloader" type="text/javascript" src="bootstrap.js"></script>

</head>
<body></body>
</html>