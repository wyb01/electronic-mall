<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>商品首页</title>
    <!-- Bootstrap core CSS -->
    <%-- c:url 标签的作用：
    	1.自动在URL的前面加上context path
    	2.如果客户端禁用了Cookie，自动使用URL重写技术，把jsessionid放到url的分号后面
    	/taobao/resources/bootstrap/css/bootstrap.css;jsessionid=xxxxx
     --%>
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" rel="stylesheet" />
    <link href="${pageContext.request.contextPath }/resources/css/taobao.css" rel="stylesheet" />

</head>

<body>

<!-- 横幅导航条开始 -->
<nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
    <div class="container">
        <div class="navbar-header">
             <ul class="nav navbar-nav">
                <li class="active"><a href="<c:url value="/article/getAll.action"/>">商品管理</a></li>
                <li><a href="<c:url value="/articleType/getAll.action"/>">商品类型管理</a></li>
	            <li><a href="<c:url value="/order/getAll.action"/>">订单管理</a></li>
	            <li><a href="<c:url value="/user/getAll.action"/>">用户管理</a></li>
	          </ul>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${not empty user_session}">
						  	<li><a href="#"> <span style='color: red;'></span>
							</a></li>
							<li><a href="#">欢迎[<font color="red">${user_session.name}</font>]访问!</a></li>
							<li><a href="${pageContext.request.contextPath}/user/logout">退出</a></li>
							<li><a href="${pageContext.request.contextPath}/user/register">免费注册</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="#"> <span style='color: red;'></span>
							</a></li>
							<li><a href="${pageContext.request.contextPath}/user/login">登录</a></li>
							<li><a href="${pageContext.request.contextPath}/user/register">免费注册</a></li>
						</c:otherwise>
					</c:choose>
			</ul>
        </div>

    </div>
</nav>
<!-- 横幅导航条结束 -->

<div class="container">

    <div class="row row-offcanvas row-offcanvas-right">

        <!-- 内容主体开始 -->
        <div class="col-xs-12 col-sm-12">
            <div class="col-xs-12 col-sm-6">
                <img alt="商品的图片" src="${pageContext.request.contextPath}/image/article/${article.image}">
            </div>
            <div class="col-xs-12 col-sm-6">
                <p>${article.title}</p>
                <p>${article.supplier}</p>
                <p>
                    <span class="price">${article.price}</span>
                    <c:if test="${article.discount!=null}">
                        <span style="color: red">折扣价： (${article.discount})</span>
                    </c:if>

                </p>
                <p>
                    库存量：<span id="storage">${article.storage}</span>
                </p>
                <p>
                <form method="get" action="<c:url value="/article/getAll.action"/>">
                    <!-- 商品数量 -->
                    <button>
							<span class="glyphicon glyphicon-chevron-left"
                                  style="color: red;"></span>返回
                    </button>
                </form>
                </p>

            </div>
            <div class="col-xs-12">
                <fieldset>
                    <legend>介绍</legend>
                    ${article.description}
                </fieldset>
            </div>
        </div>
    </div>
    <hr>
    <footer>
        <p>&copy; 版权所有，欢迎借鉴</p>
    </footer>
</div>
<script src="${pageContext.request.contextPath }/resources/jquery/jquery.js"></script>
<script src="${pageContext.request.contextPath }/resources/bootstrap/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/taobao.js"></script>
</body>
</html>