<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%-- <%@page import="com.oracle.xiaomi.pojo.*" %> --%>
<%@page import="java.util.*"%>
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
		<div id="condition" style="text-align: center">
			<form action="${pageContext.request.contextPath}/getprobypage"
				id="myform">
				商品名称：<input name="pname" id="pname" value="${pname }">&nbsp;&nbsp;&nbsp;
				商品类型：<select name="typeid" id="typeid">
					<option value="-1">请选择</option>
					<c:forEach items="${ptlist}" var="type">
						<option value="${type.typeId}">${type.typeName}</option>
					</c:forEach>
				</select> &nbsp;&nbsp;&nbsp; 
				价格：<input name="minpprice" id="minpprice"
					value="${minpprice }">-<input name="maxpprice"
					id="maxpprice" value="${maxpprice }"> <input type="submit"
					value="查询">
			</form>
		</div>
		<br>

		<div id="table">
			<div id="top">
				<input type="button" class="btn btn-warning" id="btn1" value="新增商品"
					onclick="addpro()">

			</div>
			<!--显示分页后的商品-->
			<div id="middle">
				<table class="table table-bordered table-striped">
					<tr>
						<th>商品名称</th>
						<th>商品介绍</th>
						<th>定价（元）</th>
						<th>商品图片</th>
						<th>商品数量</th>
						<th>管理操作</th>
					</tr>

					<c:forEach items="${pb.list}" var="p">
						<tr>
							<td>${p.pname}</td>
							<td>${p.pcontent}</td>
							<td>${p.pprice}</td>
							<td><img width="55px" height="45px"
								src="${pageContext.request.contextPath}/resources/image_big/${p.pimage}"></td>
							<td>${p.pnumber}</td>
							<td>
								<button type="button" class="btn btn-info myupdate"
									onclick="pmodify(${p.pid})">修改</button>
								<button type="button" class="btn btn-warning" id="mydel"
									onclick="pdel(${p.pid})">删除</button>
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
									href="${pageContext.request.contextPath}/getprobypage?page=${pb.page-1}&pname=${pname}&typeid=${typeid}&minpprice=${minpprice}&maxpprice=${maxpprice}">
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
										href="${pageContext.request.contextPath}/getprobypage?page=${index}&pname=${pname}&typeid=${typeid}&minpprice=${minpprice}&maxpprice=${maxpprice}">${index}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${pb.page eq pb.pages}">
								<li class="last"><a href="javascript:void(0)">下一页</a></li>
							</c:when>
							<c:otherwise>
								<li class="last"><a
									href="${pageContext.request.contextPath}/getprobypage?page=${pb.page+1}&pname=${pname}&typeid=${typeid}&minpprice=${minpprice}&maxpprice=${maxpprice}">
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
								<td class="one">商品名称</td>
								<td><input type="text" name="pname" class="two"
									class="form-control"></td>
							</tr>
							<!--错误提示-->
							<tr class="three">
								<td class="four"></td>
								<td><span id="pnameerr"></span></td>
							</tr>
							<tr>
								<td class="one">商品介绍</td>
								<td><input type="text" name="pcontent" class="two"
									class="form-control"></td>
							</tr>
							<!--错误提示-->
							<tr class="three">
								<td class="four"></td>
								<td><span id="pcontenterr"></span></td>
							</tr>
							<tr>
								<td class="one">定价</td>
								<td><input type="number" name="pprice" class="two"
									class="form-control"></td>
							</tr>
							<!--错误提示-->
							<tr class="three">
								<td class="four"></td>
								<td><span id="priceerr"></span></td>
							</tr>

							<tr>
								<td class="one">图片介绍</td>
								<td><input type="file" name="pimage" class="form-control"></td>
							</tr>
							<tr class="three">
								<td class="four"></td>
								<td><span></span></td>
							</tr>

							<tr>
								<td class="one">总数量</td>
								<td><input type="number" name="pnumber" class="two"
									class="form-control"></td>
							</tr>
							<!--错误提示-->
							<tr class="three">
								<td class="four"></td>
								<td><span id="numerr"></span></td>
							</tr>


							<tr>
								<td class="one">类别</td>
								<td><select name="typeid" class="form-control">
										<c:forEach items="${typeList}" var="type">
											<option value="${type.typeId}">${type.typeName}</option>
										</c:forEach>
								</select></td>
							</tr>
							<!--错误提示-->
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
    function pdel(pid) {
        if (confirm("确定删除吗")) {
            location.href = "${pageContext.request.contextPath}/delproduct?pid="+pid;
        }
    }

    function pmodify(pid) {
        location.href = "${pageContext.request.contextPath}/getproductbyid?pid="+pid;
    }
</script>
<!--分页的AJAX实现-->
<script type="text/javascript">
    function split(page) {

        //取出所有的条件
        var pname=$("#pname").val();
        var typeid=$("#typeid").val();
        var lprice=$("#lprice").val();
        var hprice=$("#hprice").val();

        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/prod/ajaxSplit.action",
            data: { "page": page,"pname":pname,"typeid":typeid,"lprice":lprice,"hprice":hprice},
            success: function () {

                <%--//将传回来的JSON对象中的数据挂在表格上显示--%>
                <%--var before = "<table class=\"table table-bordered table-striped\">\n" +--%>
                    <%--"                <tr>\n" +--%>
                    <%--"\n" +--%>
                    <%--"                    <th>商品名</th>\n" +--%>
                    <%--"                    <th>商品介绍</th>\n" +--%>
                    <%--"                    <th>定价（元）</th>\n" +--%>
                    <%--"                    <th>商品图片</th>\n" +--%>
                    <%--"                    <th>商品数量</th>\n" +--%>

                    <%--"                    <th>操作</th>\n" +--%>
                    <%--"                </tr>\n" +--%>
                    <%--"\n";--%>
                <%--var middle = "";--%>
                <%--for (var i = 0; i < pageinfo.size; i++) {--%>
                    <%--var p = pageinfo.list[i];--%>
                    <%--middle += "       <tr>\n" +--%>
                        <%--"                        <td>" + p.pName + "</td>\n" +--%>
                        <%--"                        <td>" + p.pContent + "</td>\n" +--%>
                        <%--"                        <td>" + p.pPrice + "</td>\n" +--%>
                        <%--"                        <td><img width=\"55px\" height=\"45px\"\n" +--%>
                        <%--"                                 src=\"${pageContext.request.contextPath}/image_big/" + p.pImage + "\"></td>\n" +--%>
                        <%--"                        <td>" + p.pNumber + "</td>\n" +--%>
                       <%--"\n<td>\n" +--%>
                        <%--"                            <button type=\"button\" class=\"btn btn-info myupdate\"\n" +--%>
                        <%--"                                    onclick=\"one(" + p.pId + "," + page + ")\">编辑\n" +--%>
                        <%--"                            </button>\n" +--%>
                        <%--"                            <button type=\"button\" class=\"btn btn-warning\" id=\"mydel\"\n" +--%>
                        <%--"                                    onclick=\"del(" + p.pId + "," + page + ")\">删除\n" +--%>
                        <%--"                            </button>\n" +--%>
                        <%--"                        </td>\n" +--%>
                        <%--"                    </tr>\n";--%>
                <%--}--%>


                <%--var bottom = "\n" + "            </table>\n" +--%>
                    <%--"\n" +--%>
                    <%--"            <!--分页栏-->\n" +--%>
                    <%--"            <div id=\"bottom\">\n" +--%>
                    <%--"                <div>\n" +--%>
                    <%--"                    <nav aria-label=\"...\" style=\"text-align:center;\">\n" +--%>
                    <%--"                        <ul class=\"pagination\">\n" +--%>
                    <%--"                            <li>\n" +--%>
                    <%--"                                <a href=\"javascript:split("+pageinfo.prePage+","+pageinfo.pages+")\" aria-label=\"Previous\"><span aria-hidden=\"true\">«</span></a>\n" +--%>
                    <%--"                            </li>\n";--%>
                <%--var bottom1 = "";--%>
                <%--for (var j = 1; j <= pageinfo.pages; j++) {--%>
                    <%--if (j == page) {--%>
                        <%--bottom1 += "<li><a style=\"background-color:lightslategray;color: #fff;\"\n" +--%>
                            <%--"                                           href=\"javascript:split(" + j + "," +pageinfo.pages + ")\">" + j + "</a></li>";--%>
                    <%--} else {--%>
                        <%--bottom1 += "<li><a href=\"javascript:split(" + j + "," + pageinfo.pages + ")\">" + j + "</a></li>";--%>
                    <%--}--%>
                <%--}--%>


                <%--var bottom2 = "\n" +--%>
                    <%--"                            <li>\n" +--%>
                    <%--"                                <a href=\"javascript:split("+pageinfo.nextPage+","+pageinfo.pages+")\" aria-label=\"Next\"><span aria-hidden=\"true\">»</span></a>\n" +--%>
                    <%--"                            </li>\n" +--%>
                    <%--"                            <li style=\" margin-left:150px;color: #0e90d2;height: 35px; line-height: 35px;\">总共&nbsp;&nbsp;&nbsp;<font\n" +--%>
                    <%--"                                    style=\"color:orange;\">" + pageinfo.pages + "</font>&nbsp;&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前&nbsp;&nbsp;&nbsp;<font\n" +--%>
                    <%--"                                    style=\"color:orange;\">" + pageinfo.pageNum + "</font>&nbsp;&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +--%>
                    <%--"                            </li>\n" +--%>
                    <%--"                        </ul>\n" +--%>
                    <%--"                    </nav>\n" +--%>
                    <%--"                </div>\n" +--%>
                    <%--"            </div>";--%>


                <%--$("#middle").html(before + middle + bottom + bottom1 + bottom2);--%>

                $("#table").load("http://localhost:8080/admin/product.jsp #table")
            },
            error: function (e) {
                alert(e.message);
            }
        });
    }
</script>

</html>