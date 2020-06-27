<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Player Page</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
	<!-- 引入 ECharts 文件 -->
    <script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
</head>

<body>
	<div class="container">
		<h1>Welcome to Player Page</h1>
	
		<table class="table table-bordered">
			<thead>
				<tr>
					<td>#</td>
					<td>球员ID</td>
					<td>Name</td>
					<td>Age</td>
					<td>Overall</td>
					<td>国籍</td>
					<td>俱乐部</td>
				</tr>
			</thead>
			<tbody id="player_tbody">
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
		 <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
		 <div class="row">
		 <div id="chart_1" class="col" style="height:400px;"></div>
		 <div id="chart_3" class="col"  style="height:400px;"></div>
		 </div>
		  <div class="row">
		 <div id="chart_2" class="col" style="height:400px;"></div>
		 </div>
    	
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
					var totalCountPage= result.pagination.totalCountPage;
					initPage(pageIndex,pageSize,totalCountPage);
					//1.获取模板
					jsRenderTpl = $.templates('#player_table');
					//2.模板与数据融合
					finalTpl = jsRenderTpl(result);
					//3.加载到HTML里
					$("#player_tbody").html(finalTpl);
				}
				
			});
			
			//初始化图表
			initChart();	
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
	
	<script type="text/x-jsrender" id ="player_table">
		{{for pageData}}
			<tr>
				<td>{{:rowId}}</td>
				<td>{{:pid}}</td>
				<td>{{:pname}}</td>
				<td>{{:age}}</td>
				<td>{{:overall}}</td>
				<td>{{:nation.nation}}</td>
				<td>{{:club.cname}}</td>
			</tr>
		{{/for}}
	</script>
	
	<script type="text/javascript">
	function initChart(){
		//初始花echarts实例
		var myChart = echarts.init(document.getElementById('chart_1'));
			
			$.ajax({
				url : "chartofage",
				success : function(result){
					option = {
							title: {
								text:"球员年龄分布",
								subtext:"数据来源FIFA",
								left:"center"
							},
							
							series:[
								{
									name:"年龄段",
									type:"pie",
									redius:"55%",
									center:["50%", "60%"],
									data:result
								}
							]
							
					};
					//TODO显示图表
					myChart.setOption(option);
					
				}
			});
			var  myChart2 = echarts.init(document.getElementById('chart_2'));
			
			$.ajax({
				url : "chartofoveralltoage",
				success : function(result2){
					option2 = {
							title: {
								text:"不同年龄球员的平均能力值折线图",
								subtext:"数据来源FIFA",
								left:"center"
							},
						    xAxis: {
						        type: 'category',
						        boundaryGap: false
						    },
						    yAxis: {
						        type: 'value',
						        boundaryGap: false
						    
						    },
						  
						    series: [
						        {
						            type: 'line',
						            smooth: 0.6,
						            symbol: 'none',
						            lineStyle: {
						                color: 'green',
						                width: 5
						            },
						            areaStyle: {},
						            data:result2
						            
						            
						        }
						    ]
						};


					//TODO显示图表
					myChart2.setOption(option2);
				}
			});
var myChart3 = echarts.init(document.getElementById('chart_3'));
			
			$.ajax({
				url : "chartofoverall",
				success : function(result3){
					option3 =  {
							title: {
								text:"俱乐部平均能力值",
								subtext:"数据来源FIFA",
								left:"center"
							},
						    color: ['#3398DB'],
						    tooltip: {
						        trigger: 'axis',
						        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
						            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
						        }
						    },
						    grid: {
						        left: '3%',
						        right: '4%',
						        bottom: '3%',
						        containLabel: true
						    },
						    xAxis: [
						        {
						            type: 'category',
						            data: result3.xAxis,
						            axisTick: {
						                alignWithLabel: true
						            }
						        }
						    ],
						    yAxis: [
						        {
						            type: 'value',
						        	min:80,
						        	max:100
						        }
						    ],
						    series: [
						        {
						            name: '直接访问',
						            type: 'bar',
						            barWidth: '60%',
						            data: result3.series,
						        }
						    ]
						};
					//TODO显示图表
					myChart3.setOption(option3);
					
				}
			});
	}
	</script>
	

	
</body>
</html>