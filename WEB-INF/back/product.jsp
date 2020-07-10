<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<script type="text/javascript">
        if ("${del}" != "") {
            alert("${del}");
        }
        
        function addpro(){
        	
        	window.location.href="${pageContext.request.contextPath}/addproduct";//get
        	
        }
    </script>
<c:remove var="del"></c:remove>

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
			<p>商品管理>商品列表</p>
		</div>
		<br>
		<div id="table">
			<!--显示分页后的商品-->
			<div id="middle">
				<table class="table table-bordered table-striped">
					<tr>
						<th>商品名称</th>
						<th>商品介绍</th>
						<th>定价（元）</th>
						<th>商品图片</th>
						<th>商品数量</th>
						<th>日期</th>
						<th>商品类型</th>
					</tr>
					<c:forEach items="${pb.list}" var="p">
						<tr>
							<td>${p.name}</td>
							<td>${p.content}</td>
							<td>${p.price}</td>
							<td><img width="55px" height="45px"
								src="${pageContext.request.contextPath}/resources/image_big/${p.image}"></td>
							<td>${p.number}</td>
							<td>${p.date}</td>
							<td>${p.typename}</td>
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
									href="${pageContext.request.contextPath}/getprobypage?page=${pb.page-1}">
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
										href="${pageContext.request.contextPath}/getprobypage?page=${index}">${index}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${pb.page eq pb.pages}">
								<li class="last"><a href="javascript:void(0)">下一页</a></li>
							</c:when>
							<c:otherwise>
								<li class="last"><a
									href="${pageContext.request.contextPath}/getprobypage?page=${pb.page+1}">
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