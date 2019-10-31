<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 引入自定义标签 -->   
<%@ taglib prefix="shop" uri="MyPager-tag"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	           <li><a href="<c:url value="/articleType/getAll.action"/>">商品类型管理</a></li>
	            <li class="active"><a href="<c:url value="/order/getAll.action"/>">订单管理</a></li>
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
 <div class="container">
				<div class="table-responsive">
				<table class="table table-hover table-bordered table-striped">
				
				<!-- 遍历订单 -->
				<c:forEach items="${orders}" var="order">
				
				     <thead>
						<tr style="background-color: #eeccff">
							<th style="width: 15%">订单号</th>
							<th style="width: 40%">下单时间</th>
							<th style="width: 15%">发货时间</th>
							<th style="width: 10%">订单状态</th>
							<th style="width: 10%">订单总金额</th>
							<th style="width: 10%">操作</th>
						</tr>
					</thead>
					
					<tbody>
					    <tr>
								<td>
									 ${order.orderCode}
								</td>
								<td>
								   <fmt:formatDate value="${order.createDate}" pattern="yyyy年MM月dd日 HH:mm:ss"/> 
								</td>
								<td>
									<fmt:formatDate value="${order.sendDate}" pattern="yyyy年MM月dd日 HH:mm:ss"/> 
								</td>
								<td>
								   <c:choose>
								      <c:when test="${order.status eq '0'}">未发货</c:when>
								      <c:otherwise>已发货</c:otherwise>
								   </c:choose>
								</td>
								<td>
									${order.amount}
								</td>
								<td>
									<c:choose>
									   <c:when test="${order.status eq '0'}">
									      <a href="${pageContext.request.contextPath}/order/checkOrder.action?id=${order.id}&status=1">确认发货</a>
									   </c:when>
									   <c:otherwise>
									      <a href="${pageContext.request.contextPath}/order/checkOrder.action?id=${order.id}&status=0">取消发货</a>  
									   </c:otherwise>
									</c:choose>
								</td>
							</tr>
					
					</tbody>
					
					
					<!-- 获取订单中的详情信息   order.items:List<OrderItem>    item:OrderItem -->
					<c:forEach items="${order.items}" var="item">
					      <thead>
						<tr>
							<th style="width: 15%">图片</th>
							<th style="width: 40%">名称</th>
							<th style="width: 15%">单价</th>
							<th style="width: 10%">购买数量</th>
							<th style="width: 10%">小计</th>
							<th style="width: 10%">库存</th>
						</tr>
					</thead>
					<tbody>
					
						
					 
							<tr>
								<td>
									<img alt="商品图片" src="${pageContext.request.contextPath}/image/article/${item.article.image}">
								</td>
								<td>
                                    ${item.article.title}
								</td>
								<td>
									<span class="price">${item.article.price}</span>&nbsp;&nbsp;<font color="red">${item.article.discount * item.article.price}</font>	
								</td>
								<td>
								     ${item.orderNum}
								</td>
								<td>
									<fmt:formatNumber value="${item.article.discount * item.article.price * item.orderNum}" pattern="0.00"></fmt:formatNumber>
								</td>
								
								<td>
									${item.article.storage}
								</td>
							</tr>
						
					</tbody> 
					</c:forEach>
				   
				</c:forEach>

				</table>
				
			</div>
				 
            <shop:pager
			     pageIndex="${pageModel.pageIndex}" 
			     pageSize="${pageModel.pageSize}" 
			     totalNum="${pageModel.totalNum}" 
			     submitUrl="${pageContext.request.contextPath}/order/getAll.action?pageIndex={0}" 
			     pageStyle="yellow"></shop:pager>

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