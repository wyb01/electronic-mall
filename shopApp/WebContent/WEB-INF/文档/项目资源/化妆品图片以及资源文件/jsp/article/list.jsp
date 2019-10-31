<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 引入自定义标签 -->   
<%@ taglib prefix="shop" uri="/shop/pager"%>
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
    <!-- 引入分页标签样式 -->
    <link href="${pageContext.request.contextPath }/resources/css/pager.css" rel="stylesheet" />

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
        		<li class="active"><a href="<c:url value="/article/getAll.action"/>">商品管理</a></li>
	            <li><a href="<c:url value="/articleType/getAll.action"/>">商品类型管理</a></li>
	            <li><a href="<c:url value="/order/getAll.action"/>">订单管理</a></li>
          	</ul>
          	
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

<!--  横幅下方的主体开始 -->
<div class="container">

    <div class="row row-offcanvas row-offcanvas-right">

        <!-- 侧边导航开始 -->
        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
            <div class="list-group">
                <c:forEach items="${requestScope.firstArticleTypes }" var="type">
                    <a href="<c:url value="/article/getAll.action?typeCode=${type.code }"/>" class="list-group-item <c:if test="${type.code eq param.typeCode or top.code eq fn:substring(param.typeCode, 0, 4) }">active</c:if>">${type.name }</a>
                </c:forEach>
            </div>
        </div>
        <!--  侧边导航结束 -->
        <!-- 内容主体开始 -->
        <div class="col-xs-12 col-sm-9">
            <p class="pull-right visible-xs">
                <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">显示导航条</button>
            </p>


            <div class="alert alert-info" role="alert">
                <div>
                    <form action="<c:url value="/article/getAll.action"/>" method="post">
                        <table class="table-condensed">
                            <tbody>
                            <tr>
                           
                                <td>
                                <!-- 如果当前选择了商品的类型，仅在该类型下面进行搜索 -->
                                <input type="hidden" name="typeCode" value="${typeCode }" />
                                <div class="input-group">
                                    <input type="text" name="title" value="${title}"
                                           class="form-control" placeholder="搜索商品的标题" />
                                    <div class="input-group-btn">
                                        <button class="btn" type="submit">
                                            <span class="glyphicon glyphicon-search"></span>
                                        </button>
                                    </div>
                                </div>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="添加商品">添加商品</button>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
            

            <!-- 展示商品数据 -->
            <div class="row">
                <c:forEach items="${articles }" var="a">
                    <div class="col-xs-6 col-lg-4">
            	<span class="thumbnail">
                    <a class="label label-danger" href="<c:url value="/article/removeArticle.action?id=${a.id }&typeCode=${typeCode}&secondType=${secondType}&title=${title}"/>">下架</a>
                    <a class="label label-success" href="<c:url value="/article/showUpdate.action?id=${a.id }&typeCode=${typeCode}&secondType=${secondType}&title=${title}"/>">修改</a>
				    <a href="<c:url value="/article/preArticle.action?id=${a.id }"/>">
 				      <img src="<c:url value="/image/article/${a.image }"/>" alt="...">

		              <p style="height: 20px; overflow: hidden;">${a.title }</p>
				    </a>
		              <p>
		              	<span class="price">${a.price }</span>
		              	<c:if test="${not empty a.discount }">
                            <span class="discountPrice">惊爆价: <fmt:formatNumber value="${a.price * a.discount / 10 }" pattern="0.00"></fmt:formatNumber>(${a.discount }折)</span>
                        </c:if>
		              </p>
              </span>
                    </div>
                </c:forEach>
            </div>
		<!-- 自定义分页标签 
		     pageIndex:当前页码
		     pageSize：每页显示的记录数
		     recordCount：总记录数
		     submitUrl：提交地址
		     stylecss：分页样式
		-->
		  <shop:pager
		     pageIndex="${pageModel.pageIndex}" 
		     pageSize="${pageModel.pageSize}" 
		     totalNum="${pageModel.totalNum}" 
		     submitUrl="${pageContext.request.contextPath}/article/getAll.action?pageIndex={0}&title=${title}&secondType=${secondType}&typeCode=${typeCode}" 
		     pageStyle="yellow"></shop:pager>
		 </div>
    </div>

    <hr>

    <footer>
        <p>&copy; 版权所有，欢迎借鉴</p>
    </footer>
</div>

<!-- 弹出框 -->
<div class="modal fade" id="exampleModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">添加物品</h4>
            </div>
            <div class="modal-body ">
                <div align="center">
                    <span style="color:red;"></span>
                    <form name="articleform" class="form-horizontal" action="<c:url value="/article/addArticle.action"/>" enctype="multipart/form-data" method="post">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">类型编号：</label>
                            <div class="col-sm-4">
                                <select class="form-control" name="code" id="addTypeCode">
                                    <c:forEach items="${articleTypes}" var="type">
                                        <option value="${type.code}">${type.name}</option>
                                    </c:forEach>
                                </select> 
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">标题：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="title" size="50">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">供应商：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="supplier" size="50">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">地区：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="locality" size="50">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">价格：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="price" size="50">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">库存数量：</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="storage" size="50">
                            </div>
                        </div>

                       <!--  <div class="form-group">
                            <label class="col-sm-3 control-label">上架日期：</label>
                            <div class="col-sm-4">
                                <input type=text class="form-control" name="putawayDate" id="putawayDate" size="50">
                            </div>
                        </div> -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">物品封面：</label>
                            <div class="col-sm-4">
                                <input type="file" class="form-control" name="file" size="40" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">书面描述：</label>
                            <div class="col-sm-6">
                                <textarea rows="5" cols="60" class="form-control" name="description"></textarea>
                            </div>
                        </div>
                        <table>
                            <tr>
                                <td><input type="submit" class="btn btn-success btn-sm" value="提交" /></td>
                                <td>&nbsp;&nbsp;<input type="button" class="btn btn-danger btn-sm" data-dismiss="modal" value="取消" /></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<!--  横幅下方的主体结束 -->
<!-- 一般来讲，css必须在head里面引入，因为页面加载完成，就需要显示正确的样式 -->
<!-- js一般在页面最后面加载，等页面布局都完成以后，再来处理js文件！ -->
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath }/resources/jquery/jquery.js"></script>
<script src="${pageContext.request.contextPath }/resources/bootstrap/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/taobao.js"></script>
<script type="text/javascript">
    $(function(){
       /*  $("#secondType").val("${secondType}");
        $("#addTypeCode").val("${typeCode}"); */
    })

</script>
</body>
</html>