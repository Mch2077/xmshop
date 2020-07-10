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
        
        function addnote(){
        	
        	window.location.href="${pageContext.request.contextPath}/addnote";//get 到控制器里去跳转
        	
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
			<p>公告管理>公告列表</p>
		</div>
		<div id="condition" style="text-align: center">
			<form action="${pageContext.request.contextPath}/getnotebypage"
				id="myform">
				标题名称：<input name="title" id="title" value="${title }">&nbsp;&nbsp;&nbsp;
				内容 <input name="content" id="content" value="${content}">&nbsp;&nbsp;&nbsp;
				作者 <input name="publisher" id="publisher" value="${publisher}">&nbsp;&nbsp;&nbsp;
				<input type="submit" value="查询">
			</form>
		</div>
		<br>

		<div id="table">
			<div id="top">

				<input type="button" class="btn btn-warning" id="btn1" value="新增通知"
					onclick="addnote()">

			</div>
			<!--显示分页后的商品-->
			<div id="middle">
				<table class="table table-bordered table-striped">
					<tr>
						<th>标题</th>
						<th>内容</th>
						<th>发布者</th>
						<th>发布时间</th>
						<th>操作</th>
					</tr>

					<c:forEach items="${note.list}" var="note">
						<tr>
							<td>${note.title}</td>
							<div title="${note.content}" class="title">
								<td>${note.content}</td>
							</div>
							<td>${note.publisher}</td>
							<td><fmt:formatDate value="${note.publishdate}"
									pattern="yyyy年MM月dd日HH点mm分ss秒" /></td>
							<td>
								<button type="button" class="btn btn-info myupdate"
									onclick="modify(${note.id})">编辑</button>
								<button type="button" class="btn btn-warning" id="mydel"
									onclick="del(${note.id})">删除</button>
							</td>
						</tr>
					</c:forEach>
				</table>

				<!--分页栏-->

				<div class="footNum">
					<ul>
						<c:choose>
							<c:when test="${note.page eq 1 }">
								<li class="pre"><a href="javascript:void(0)">上一页</a></li>
							</c:when>
							<c:otherwise>
								<li class="pre"><a
									href="${pageContext.request.contextPath}/getnotebypage?page=${note.page-1}&title=${title}&content=${content}&publisher=${publisher}">
										上一页</a></li>
							</c:otherwise>
						</c:choose>
						<c:forEach begin="1" end="${note.pages}" step="1" var="index">
							<c:choose>
								<c:when test="${note.page eq index}">
									<li class="num current"><a href="javascript:void(0)">${index}</a></li>
								</c:when>
								<c:otherwise>
									<li class="num"><a
										href="${pageContext.request.contextPath}/getnotebypage?page=${index}&title=${title}&content=${content}&publisher=${publisher}">${index}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${note.page eq note.pages}">
								<li class="last"><a href="javascript:void(0)">下一页</a></li>
							</c:when>
							<c:otherwise>
								<li class="last"><a
									href="${pageContext.request.contextPath}/getnotebypage?page=${note.page+1}&title=${title}&content=${content}&publisher=${publisher}">
										下一页</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>

			</div>
		</div>
	</div>

	<!--编辑的模式对话框-->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">新增商品</h4>
				</div>
				<div class="modal-body" id="addTD">
					<form
						action="${pageContext.request.contextPath}/admin/product?flag=save"
						enctype="multipart/form-data" method="post" id="myform">
						<table>
							<tr>
								<td class="one">标题</td>
								<td><input type="text" name="title" class="two"
									class="form-control"></td>
							</tr>
							<!--错误提示-->
							<tr class="three">
								<td class="four"></td>
								<td><span id="pnameerr"></span></td>
							</tr>
							<tr>
								<td class="one">通知内容</td>
								<td><input type="text" name="content" class="two"
									class="form-control"></td>
							</tr>
							<!--错误提示-->
							<tr class="three">
								<td class="four"></td>
								<td><span id="pcontenterr"></span></td>
							</tr>
							<tr>
								<td class="one">发布人</td>
								<td><input type="text" name="publisher" class="two"
									class="form-control"></td>
							</tr>
							<!--错误提示-->
							<tr class="three">
								<td class="four"></td>
								<td><span id="priceerr"></span></td>
							</tr>

							<tr>
								<td class="one">发布时间</td>
								<td><input type="text" name="publishdate"
									class="form-control"></td>
							</tr>
							<tr class="three">
								<td class="four"></td>
								<td><span></span></td>
							</tr>


							<tr>
								<td><input type="submit" class="btn btn-success" value="提交"
									class="btn btn-success"></td>
								<td>
									<button type="button" class="btn btn-info" data-dismiss="modal">取消</button>
								</td>
							</tr>
						</table>
					</form>

				</div>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
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
    function del(id) {
        if (confirm("确定删除吗")) {
            location.href = "${pageContext.request.contextPath}/delnote?id="+id;
        }
    }

    function modify(id) {
        location.href = "${pageContext.request.contextPath}/getnotebyid?id="+id;
    }
</script>
<!--分页的AJAX实现-->
<script type="text/javascript">
    function split(page) {

        //取出所有的条件
        var pname=$("#title").val();
        var typeid=$("#content").val();
        var lprice=$("#publisher").val();
        var hprice=$("#publishdate").val();

        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/prod/ajaxSplit.action",
            data: { "page": page,"pname":pname,"typeid":typeid,"lprice":lprice,"hprice":hprice},
            success: function () {
                $("#table").load("http://localhost:8080/admin/product.jsp #table")
            },
            error: function (e) {
                alert(e.message);
            }
        });
    }
</script>

</html>