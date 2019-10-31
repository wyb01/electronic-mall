<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 引入自定义标签 -->   
<%@ taglib prefix="shop" uri="MyPager-tag"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>商品类型管理</title>
    <!-- Bootstrap core CSS -->
    <%-- c:url 标签的作用：
    	1.自动在URL的前面加上context path
    	2.如果客户端禁用了Cookie，自动使用URL重写技术，把jsessionid放到url的分号后面
    	/taobao/resources/bootstrap/css/bootstrap.css;jsessionid=xxxxx
     --%>
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" rel="stylesheet" />
    <link href="${pageContext.request.contextPath }/resources/css/taobao.css" rel="stylesheet" />
    <!-- 引入分页标签样式 -->
    <link href="${pageContext.request.contextPath }/resources/css/pager.css" rel="stylesheet" />

	<style type="text/css">
		.panel-heading .btn
		{
			margin-top: -5px;
		}
		.panel-body
		{
			padding: 0;
		}
	</style>
</head>

<body>

<!-- 横幅导航条开始 -->
<nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a  class="navbar-brand " style="color: red" href="<c:url value="/article/getAll.action"/>">电商系统,卖家系统-商品管理</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
        	<ul class="nav navbar-nav">
        	   <li><a href="<c:url value="/article/getAll.action"/>">商品管理</a></li>
	           <li class="active"><a href="<c:url value="/articleType/getAll.action"/>">商品类型管理</a></li>
	           <li><a href="<c:url value="/order/getAll.action"/>">订单管理</a></li>
          	</ul>
        
            <ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${not empty user_session}">
						  	<li><a href="#"> <span style='color: red;'></span>
							</a></li>
							<li><a href="#">欢迎[<font color="red">${user_session.name}</font>]访问!</a></li>
							<li><a href="${pageContext.request.contextPath}/user/logout">退出</a></li>
							<li><a href="${pageContext.request.contextPath}/user/showRegisterPage">免费注册</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="#"> <span style='color: red;'></span>
							</a></li>
							<li><a href="${pageContext.request.contextPath}/user/showLoginPage">登录</a></li>
							<li><a href="${pageContext.request.contextPath}/user/showRegisterPage">免费注册</a></li>
						</c:otherwise>
					</c:choose>
			</ul>
				
        </div>

    </div>
</nav>
<!-- 横幅导航条结束 -->
 <font color="red">${message}</font>
<!--  主体内容开始 -->
<div class="container">
	<div class="panel panel-default">
	    <div class="panel-heading">
	    	<span>商品类型列表</span>
	    	<span class="pull-right">
	    		<a href="<c:url value="/articleType/showAdd.action"/>" class="btn btn-sm btn-success">
	    			<i class="glyphicon glyphicon-plus"></i>
	    		</a>
	    	</span>
	    </div>
	    <div class="panel-body">
		    <table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>CODE</th>
						<th>类型名称</th>
						<th>备注</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list }" var="t">
						<tr>
							<td>${t.code }</td>
							<td>${t.name }</td>
							<td>${t.remark }</td>
							<td>
								<span  class="label label-info"><a href="${pageContext.request.contextPath}/articleType/showUpdate.action?code=${t.code}" style="color: white;">修改</a></span>
								<span class="label label-danger"><a href="${pageContext.request.contextPath}/articleType/deleteType.action?code=${t.code}" style="color: white;">删除</a></span>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			 <shop:pager
		     pageIndex="${pageModel.pageIndex}" 
		     pageSize="${pageModel.pageSize}" 
		     totalNum="${pageModel.totalNum}" 
		     submitUrl="${pageContext.request.contextPath}/articleType/getAll.action?pageIndex={0}" 
		     pageStyle="yellow"></shop:pager> 
	    </div>
	</div>
    <hr>

    <footer>
        <p>&copy; 版权所有，欢迎借鉴</p>
    </footer>
</div>
<!--  主体内容结束 -->

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath }/resources/jquery/jquery.js"></script>
<script src="${pageContext.request.contextPath }/resources/bootstrap/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/taobao.js"></script>
<script type="text/javascript">
    $(function(){
        $("#secondType").val("${secondType}");
        $("#addTypeCode").val("${typeCode}");
    })

</script>
</body>
</html>