<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Emp Page</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Welcome to Employment Page</h1>
		<a href="add">添加员工</a>
		<table class="table table-bordered">
			<thead>
				<tr>
					<td>#</td>
					<td>姓名</td>
					<td>工作</td>
					<td>上级编号</td>
					<td>受雇日期</td>
					<td>月薪资</td>
					<td>部门编号</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${rs.pageData}" var="emp">
					<tr>
						<td>${emp.empno}</td>
						<td>${emp.ename}</td>
						<td>${emp.job}</td>
						<td>${emp.mgr}</td>
						<td><p>
								<fmt:formatDate type="date" value="${emp.hiredate}" />
							</p></td>
						<td>${emp.sal}</td>
						<td>${emp.deptno}</td>
						<td><a href="update/${emp.empno}">修改</a>&nbsp;
						 <a href="delete/${emp.empno}"
							onclick="return confirm ('确定要删除${emp.ename}吗？')">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<nav aria-label="...">
			<ul class="pagination" id="page">
				<li class="page-item disabled" id="page_pre"><a
					class="page-link"
					href="list?pageIndex=${rs.pagination.pageIndex-1}&pageSize=${rs.pagination.pageSize}"
					tabindex="-1" aria-disabled="true">Previous</a></li>

				<li class="page-item" id="page_next"><a class="page-link"
					href="list?pageIndex=${rs.pagination.pageIndex+1}&pageSize=${rs.pagination.pageSize}">Next</a></li>
			</ul>
		</nav>
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
	<script type="text/javascript">
		window.onload = function() {//页面加载后调用
			var totalCountPage = ${rs.pagination.totalCountPage}
			;
			//alert("总页数"+totalCountPage);
			var pageIndex = ${rs.pagination.pageIndex}
			;
			var pageSize = ${rs.pagination.pageSize};
			var page_num = ""
			for (var i = 1; i <= totalCountPage; i++) {
				if (i == pageIndex) {
					page_num = page_num + '<li class="page-item active" aria-current="page"><a class="page-link" href="list?pageIndex='+i+'&pageSize=' + pageSize + '">'+i+'<span class="sr-only">(current)</span></a></li>'

				} else {
					page_num = page_num +'<li class="page-item"><a class="page-link" href="list?pageIndex='+i+'&pageSize='+pageSize+'">'+i+'</a></li>'
				}
			}
			$("#page_pre").after(page_num);

			if (pageIndex == 1) {//上一页置灰
				$("#page_pre").addClass("disabled");
			} else {
				$("#page_pre").removeClass("disabled");
			}
			if (pageIndex == totalCountPage) {
				$("#page_next").addClass("disabled");
			} else {
				$("#page_next").removeClass("disabled");
			}

		}
	</script>
</body>
</html>