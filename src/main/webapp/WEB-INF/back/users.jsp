<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%-- <%@page import="com.oracle.xiaomi.pojo.*" %> --%>
<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<script type="text/javascript"> 
        function adduser(){
        	window.location.href="${pageContext.request.contextPath}/adduser";//get
        	
        }
    </script>

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
			<p>员工管理>员工列表</p>
		</div>
		<br>
		<div id="table">
			<div id="top">
				<input type="button" class="btn btn-warning" id="btn1" value="新增员工"
					onclick="adduser()">

			</div>
			<!--显示分页后的商品-->
			<div id="middle">
				<table class="table table-bordered table-striped">
					<tr>
						<th>用户头像</th>
						<th>用户名</th>
						<th>性别</th>
						<th>年龄</th>
						<th>真实姓名</th>
						<th>管理操作</th>
					</tr>

					<c:forEach items="${pb.list}" var="u">
						<tr>
							<td><img width="55px" height="45px" src="${pageContext.request.contextPath}/resources/image_user/${u.uimage}"></td>
							<td>${u.uname}</td>
							<td>${u.usex}</td>
							<td>${u.uage}</td>
							<td>${u.realname}</td>
							<td>
								<button type="button" class="btn btn-info myupdate"
									onclick="umodify(${u.uid})">修改</button>
								<button type="button" class="btn btn-warning" id="mydel"
									onclick="udel(${u.uid})">删除</button>
							</td>
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
									href="${pageContext.request.contextPath}/getusersbypage?page=${pb.page-1}">
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
										href="${pageContext.request.contextPath}/getusersbypage?page=${index}">${index}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${pb.page eq pb.pages}">
								<li class="last"><a href="javascript:void(0)">下一页</a></li>
							</c:when>
							<c:otherwise>
								<li class="last"><a
									href="${pageContext.request.contextPath}/getusersbypage?page=${pb.page+1}">
										下一页</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript">
    function udel(uid) {
        if (confirm("确定删除吗")) {
            location.href = "${pageContext.request.contextPath}/deluser?uid="+uid;
        }
    }
    function umodify(uid) {
        location.href = "${pageContext.request.contextPath}/getuserbyid?uid="+uid;
    }
</script>

</html>