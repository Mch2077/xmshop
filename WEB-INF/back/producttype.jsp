<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%-- <%@page import="com.oracle.xiaomi.pojo.*" %> --%>
<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<script type="text/javascript">
        if ("${del}" != "") {
            alert("${del}");
        }
        
        function addprotype(){
        	window.location.href="${pageContext.request.contextPath}/addprotype";//get
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
<style type="text/css">
.title {
	width: 400px;
	overflow: hidden; /* 超出部分隐藏 */
	text-overflow: ellipsis; /* 超出部分加省略号 */
	/* white-space: nowrap; /* 强制在1行内显示 */
	display: -webkit-box;
	-webkit-line-clamp: 1; /* 限制在一个块元素显示的文本的行数 */
	-webkit-box-orient: vertical; /* 垂直排列 */
	word-break: break-all; /* 内容自动换行 */
}
</style>
</head>

<body>
	<div id="brall">
		<div id="nav">
			<p>商品类型管理>商品类型列表</p>
		</div>
		<br>
		<div id="table">
			<div id="top">

				<input type="button" class="btn btn-warning" id="btn1" value="新增商品类型"
					onclick="addprotype()">

			</div>
			<!--显示分页后的商品-->
			<div id="middle">
				<table class="table table-bordered table-striped">
					<tr>
						<th>商品类型名称</th>
						<th>操作</th>
					</tr>

					<c:forEach items="${pb.list}" var="pt">
						<tr>
							<td>${pt.name}</td>
							<td>
								<button type="button" class="btn btn-info myupdate"
									onclick="modify(${pt.id})">编辑</button>
								<button type="button" class="btn btn-warning" id="mydel"
									onclick="del(${pt.id})">删除</button>
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
									href="${pageContext.request.contextPath}/getprotypebypage?page=${pb.page-1}">
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
										href="${pageContext.request.contextPath}/getprotypebypage?page=${index}">${index}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${pb.page eq pb.pages}">
								<li class="last"><a href="javascript:void(0)">下一页</a></li>
							</c:when>
							<c:otherwise>
								<li class="last"><a
									href="${pageContext.request.contextPath}/getprotypebypage?page=${pb.page+1}">
										下一页</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>

			</div>
		</div>
	</div>
</body>
<!--弹出新增模式对话框-->
<script type="text/javascript">
    $(function () {
            $(".btn-info").on("click", function () {
                //浏览不关，第二次打开时要清空
                $("#myModal").modal("hide");

            });
            //新增学生非空判断
            $(".btn-success").on("click", function () {
                $("#myModal").modal("hide");
            });
});
</script>
<script type="text/javascript">
    function mysubmit() {
        $("#myform").submit();
    }
    
    //删除函数
    function del(id) {
        if (confirm("确定删除吗")) {
            location.href = "${pageContext.request.contextPath}/delprotype?id="+id;
        }
    }

    //修改函数
    function modify(id) {
        location.href = "${pageContext.request.contextPath}/toupdateprotypepage?id="+id;//get
    }
</script>


</html>