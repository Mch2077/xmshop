<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/reset.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/base.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/list.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bright.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/addBook.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<title></title>
</head>

<body>
	<div id="brall">
		<div id="nav">
			<p>商品订单管理>商品订单列表</p>
		</div>
		<br>
		<div id="table">
			<!--显示分页后的商品订单-->
			<div id="middle">
				<table class="table table-bordered table-striped">
					<tr>
						<th>订单号</th>
						<th>客户</th>
						<th>地址</th>
						<th>总价格</th>
						<th>备注</th>
						<th>状态</th>
						<th>订单时间</th>
					</tr>
					<c:forEach items="${pb.list}" var="xmorder">
						<tr>
							<td>${xmorder.oid}</td>
							<td>${xmorder.realname}</td>
							<td>${xmorder.address}</td>
							<td>${xmorder.totalprice}</td>
							<td>${xmorder.remarks}</td>
							<td>${xmorder.status}</td>
							<td><fmt:formatDate value="${xmorder.odate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
						</tr>
					</c:forEach>
				</table>
				<!--分页栏-->
				<div class="footNum">
					<ul>
						<c:choose>
							<c:when test="${pb.page eq 1 }">
								<li class="pre"><a href="javascript:void(0)">上一页</a></li>
							</c:when>
							<c:otherwise>
								<li class="pre"><a
									href="${pageContext.request.contextPath}/getorderbypage?page=${pb.page-1}">
										上一页</a></li>
							</c:otherwise>
						</c:choose>
						<c:forEach begin="1" end="${pb.pages}" step="1" var="index">
							<c:choose>
								<c:when test="${pb.page eq index}">
									<li class="num current"><a href="javascript:void(0)">${index}</a></li>
								</c:when>
								<c:otherwise>
									<li class="num"><a
										href="${pageContext.request.contextPath}/getorderbypage?page=${index}">${index}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${pb.page eq pb.pages}">
								<li class="last"><a href="javascript:void(0)">下一页</a></li>
							</c:when>
							<c:otherwise>
								<li class="last"><a
									href="${pageContext.request.contextPath}/getorderbypage?page=${pb.page+1}">
										下一页</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>