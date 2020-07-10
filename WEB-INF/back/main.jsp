<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/index.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<title></title>
<style type="text/css">
#top {
	margin-top: 20px;
}
</style>
</head>

<body>
	<!--整体部分-->
	<div id="all">
		<!--上部分-->
		<div id="top">
			<div id="top1">
				<span>商品管理系统</span>
			</div>
			<div id="top2"></div>
			<div id="top3">
				<span>欢迎您：${user.uname}</span>
				<span><a href="${pageContext.request.contextPath}/logout">退出</a></span>
			</div>
		</div>
		<!--下部分-->
		<div id="bottom">
			<!--下部分左边-->
			<div id="bleft">
				<div id="ltop">
					<div id="lts">
						<img
							src="${pageContext.request.contextPath}/resources/image_user/${user.uimage}" /><br />
						<p style="text-align: center;">${user.realname}</p>
					</div>
				</div>
				<div id="lbottom">
					<ul>
						<!-- 获取商品类型的分页数据 -->
						<a href="${pageContext.request.contextPath}/getprotypebypage"
							target="myright">
							<li class="two"><span class="glyphicon glyphicon-book"
								style="color: white;"></span>
								&nbsp;&nbsp;&nbsp;&nbsp;商品类型管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<span class="glyphicon glyphicon-play" style="color: white;"></span>
						</li>
						</a>
					
						<!-- 获取商品@RequestMapping("/getprobypage") -->
						<a href="${pageContext.request.contextPath}/getprobypage"
							target="myright">
							<li class="two"><span class="glyphicon glyphicon-book"
								style="color: white;"></span>
								&nbsp;&nbsp;&nbsp;&nbsp;商品管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<span class="glyphicon glyphicon-play" style="color: white;"></span>
						</li>
						</a>
						<a href="${pageContext.request.contextPath}/admin/err.jsp"
							target="myright">
							<li class="one"><span class="glyphicon glyphicon-sort"
								style="color: white;"></span>&nbsp;&nbsp;&nbsp;&nbsp;订单管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
								class="glyphicon glyphicon-play" style="color: white;"></span></li>
						</a>
						<!-- 获取商品@RequestMapping("/getusers") -->
						<!-- 实现权限的控制 9为超级管理员，才能看到员工管理菜单  -->
						<c:if test="${sessionScope.user.roleid == 9 }">
						<a href="${pageContext.request.contextPath}/getusersbypage"
							target="myright">
							<li class="one"><span class="glyphicon glyphicon-user"
								style="color: white;"></span>&nbsp;&nbsp;&nbsp;&nbsp;员工管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
								class="glyphicon glyphicon-play" style="color: white;"></span></li>
						</a>
						</c:if>
						<a href="${pageContext.request.contextPath}/getnotebypage"
							target="myright">
							<li class="one"><span class="glyphicon glyphicon-bullhorn"
								style="color: white"></span>&nbsp;&nbsp;&nbsp;&nbsp;通知公告&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
								class="glyphicon glyphicon-play" style="color: white;"></span></li>
						</a>
						<a href="${pageContext.request.contextPath}/updateupass"
							target="myright">
							<li class="one"><span class="glyphicon glyphicon-user"
								style="color: white;"></span>&nbsp;&nbsp;&nbsp;&nbsp;修改密码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
								class="glyphicon glyphicon-play" style="color: white;"></span></li>
						</a>
					</ul>
				</div>
			</div>
			<!--下部分右边-->
			<div id="bright">
				<iframe frameborder="0" scrolling="no" name="myright" width="1235px"
					height="700px"></iframe>
			</div>
		</div>
	</div>
</body>

</html>