<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addBook.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/ajaxfileupload.js"></script>
	</head>
    
	<body>
	<!--取出上一个页面上带来的page的值-->
		<div id="addAll">
			<div id="nav">
				<p>公告管理>新增通知</p>
			</div>
			<div id="table">
				<form action="${pageContext.request.contextPath}/addnote"  method="post" id="myform">
					<table>
						<tr>
							<td class="one">标题</td>
							<td><input type="text" name="title" class="two"></td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span id="pnameerr"></span></td>
						</tr>
						<tr>
							<td class="one">通知内容</td>
							<td><input type="text" name="content" class="two"></td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span id="pcontenterr"></span></td>
						</tr>
						<tr>
							<td class="one">作者</td>
							<td><input type="text" name="publisher" class="two"  value="${sessionScope.user.uname}" readonly></td>
						</tr>
					
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span></span></td>
						</tr>

						<tr>
							<td>
								<input type="submit" value="提交" class="btn btn-success">
							</td>
							<td>
								<input type="reset" value="取消" class="btn btn-default" onclick="myclose(${param.page})">
								<script type="text/javascript">
									function myclose(ispage) {
										window.location="${pageContext.request.contextPath}/getnotebypage";
									}
								</script>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

	</body>

</html>