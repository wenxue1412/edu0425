<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Population Page</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
	<script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
</head>
   
<body>
	<div class="container">
		<h1>Welcome to Population Page</h1>
		<table class="table table-bordered">
		<form action="search" method="post">
  			First name: <input type="text" name="fname"><br>
  			<input type="submit" value="提交">
		</form>
			<thead>
				<tr>
					<td>#</td>
					<td>市区町村</td>
					<td>Municipalities</td>
					<td>総人口</td>
					<td>15歳未満人口</td>
					<td>15～64歳人口</td>
					<td>65歳以上人口</td>
					<td>外国人人口</td>
					<td>人口集中地区人口</td>
					<td>出生数</td>
					<td>死亡数</td>
					<td>転入者数（外国人含む）</td>
					<td>転出者数（外国人含む）</td>
					<td>昼間人口</td>
					<td>総世帯数</td>
					<td>一般世帯数</td>
					<td>核家族世帯数</td>
					<td>単独世帯数</td>
					<td>65歳以上の世帯員のいる核家族世帯数</td>
					<td>高齢夫婦世帯数（高齢夫婦のみ）</td>
					<td>高齢単身世帯数</td>
					<td>婚姻件数</td>
					<td>離婚件数</td>
					<td>市区町村ｺｰﾄﾞ</td>
				</tr>
			</thead>
			<tbody id="popu_tbody">
				<!-- 我们要在这里用jsrender模板 -->
			</tbody>

		</table>
		<nav aria-label="...">
			<ul class="pagination" id="page">
				<li class="page-item disabled" id="page_pre"><a
					class="page-link"
					href="index?pageIndex=${pageIndex-1}&pageSize=${pageSize}"
					tabindex="-1" aria-disabled="true">Previous</a></li>

				<li class="page-item" id="page_next"><a class="page-link"
					href="index?pageIndex=${pageIndex+1}&pageSize=${pageSize}">Next</a></li>
			</ul>
		</nav>
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
	<!-- Load JsRender latest version, from www.jsviews.com: -->
	<script src="https://www.jsviews.com/download/jsrender.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
	<script type="text/x-jsrender" id ="popu_table">
		{{for pageData}}
			<tr>
				<td>{{:did}}</td>
				<td>{{:dname}}</td>
				<td>{{:ename}}</td>
				<td>{{:tpopu}}</td>
				<td>{{:ypopu}}</td>
				<td>{{:apopu}}</td>
				<td>{{:opopu}}</td>
				<td>{{:fpopu}}</td>
				<td>{{:ddpopu}}</td>
				<td>{{:lbir}}</td>
				<td>{{:death}}</td>
				<td>{{:inmnum}}</td>
				<td>{{:outmnum}}</td>
				<td>{{:dtpopu}}</td>
				<td>{{:hnum}}</td>
				<td>{{:phnum}}</td>
				<td>{{:nfnum}}</td>
				<td>{{:onehnum}}</td>
				<td>{{:onhnum}}</td>
				<td>{{:ochnum}}</td>
				<td>{{:oshnum}}</td>
				<td>{{:marriages}}</td>
				<td>{{:divorces}}</td>
				<td>{{:did2}}</td>
			</tr>
		{{/for}}
	</script>
	<script type="text/javascript">
	window.onload = function() {//页面加载后调用
		//var totalCountPage = ${rs.pagination.totalCountPage};
		//alert("总页数"+totalCountPage);
		var pageIndex = ${pageIndex};
		var pageSize = ${pageSize};
		
		
		//ajax可以去请求服务端口，并且接收返回值，修改页面值
		$.ajax({
			url : "page?pageIndex="+pageIndex+"&pageSize="+pageSize,
			success : function(result){//ajax成功返回result
				//alert(result.pagination.totalCountPage);
				var totalCountPage=result.pagination.totalCountPage;
				initPage(pageIndex,pageSize,totalCountPage);
				//1.获取模板
				jsRenderTpl = $.templates('#popu_table');
				//2.模板与数据融合
				finalTpl = jsRenderTpl(result);
				//3.加载到HTML里
				$("#popu_tbody").html(finalTpl);
			}
			
		});
		
		//初始化图表	
	}
	
	function initPage(pageIndex,pageSize,totalCountPage){
		var page_num = "";
		var i = 1;
		var j = totalCountPage;
		
		if(pageIndex>5){
			i = pageIndex - 4;
		}
		if(pageIndex + 4 < totalCountPage){
			$("#page_next").before("...")
			j = pageIndex + 5;
		}
		for (; i <= j; i++) {
			if (i == pageIndex) {
				page_num = page_num + '<li class="page-item active" aria-current="page"><a class="page-link" href="index?pageIndex='+i+'&pageSize=' + pageSize + '">'+i+'<span class="sr-only">(current)</span></a></li>'

			} else {
				page_num = page_num +'<li class="page-item"><a class="page-link" href="index?pageIndex='+i+'&pageSize='+pageSize+'">'+i+'</a></li>'
			}
		}
		$("#page_pre").after(page_num);
		
		if(pageIndex > 5){
			$("#page_pre").after("...");
		}

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