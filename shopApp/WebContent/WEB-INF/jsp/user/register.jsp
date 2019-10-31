<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登陆</title>
    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" rel="stylesheet" />

    <link href="${pageContext.request.contextPath }/resources/css/taobao.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath }/resources/jquery/jquery.js"></script>
    <script type="text/javascript">
        $(function(){
            // 按了回车键也可以登录
            $(document).keydown(function(event){
                if(event.keyCode==13){
                    $("#loginBtn").trigger("click");
                }
            })

            // 给登录按钮绑定点击事件
            $("#btn_submit").click(function(){
            	//获取账号
                var loginName = $("#loginName").val();
            	//获取密码
                var passWord = $("#password").val() ;
            	//获取确认密码
            	var okPass = $("#okPassword").val();

                // 定义一个变量存储校验信息
                var msg  = "" ;
                if(!/^\w{5,}$/.test(loginName)){
                    msg = "登录名必须5个字符以上";
                }else if(!/^\w{6,}$/.test(passWord)){
                    msg = "请输入6位以上合法的密码";
                }else if(passWord != okPass){
                	 msg = "两次输入密码不一致，请核实";
                }

                if(msg!=""){
                    // 触发弹出框
                  alert(msg);
                }else{
                	 // 提交表单
                    $(".form-horizontal").submit();
                }
               
            });
        })
        
        
        //校验账号是否存在
        function validName(obj){
        	if(obj.value!=null && obj.value !=""){
        		
        		$.ajax({
         		   type: "POST",
         		   url: "${pageContext.request.contextPath}/user/validName",
         		   data: "loginName="+obj.value,
         		   success: function(msg){
         		     if(msg == "exist"){
         		    	 alert("账号已存在，请重新输入！");
         		    	 obj.value = "";
         		     }
         		   }
         		});
        	}
        	
        	
        }
    </script>

</head>

<body>
<%-- 	<jsp:include page="/WEB-INF/jsp/commons/banner.jsp"></jsp:include> --%>
<nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="<c:url value="/article/getAll.action"/>">电商系统,卖家系统-商品管理</a>
        </div>
        
         <div id="navbar" class="collapse navbar-collapse">
        	<ul class="nav navbar-nav">
        	   <li><a href="<c:url value="/article/getAll.action"/>">商品管理</a></li>
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

    <!--/.container-->
	<div class="container">
		<div class="row info-content">
			<form id="registerForm" class="form-horizontal" method="post"
				action="${pageContext.request.contextPath}/user/userRegister" style="margin-top: 20px;">
				<div class="form-group">
					<label class="col-sm-2 control-label">登录名称</label>
					<div class="col-sm-3">
						<input type="text" value="" id="loginName" name="loginName" onblur="validName(this)"
							class="form-control" placeholder="请输入您的登陆名称">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">真实姓名</label>
					<div class="col-sm-3">
						<input type="text" id="name" value="" name="name"
							class="form-control" placeholder="请输入您的真实姓名">
					</div>

				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">登陆密码</label>
					<div class="col-sm-3">
						<input type="password" id="password" name="password"
							class="form-control" placeholder="请输入您的密码">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">确认密码</label>
					<div class="col-sm-3">
						<input type="password" id="okPassword" name="okpassword"
							class="form-control" placeholder="请输入您的确认密码">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">性别</label>
					<div class="col-sm-3">
						<label>男:</label><input name="sex" value="1" type="radio"
							checked="checked"> <label>女:</label><input 
							name="sex" value="2" type="radio">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">联系电话</label>
					<div class="col-sm-3">
						<input type="text" id="phone" value="" name="phone"
							class="form-control" placeholder="请输入您的电话">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">地址</label>
					<div class="col-sm-8">
						<textarea id="address" name="address" rows="4" cols="30"
							class="form-control" placeholder="请输入您的地址信息"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">电子邮箱:</label>
					<div class="col-sm-3">
						<input type="text" id="email" value="" name="email"
							class="form-control" placeholder="请输入您的邮箱">
					</div>
					<div class="col-sm-3">
						<span style="color: red;"></span>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label"></label>
					<div class="col-sm-3">
						<button type="button" id="btn_submit" class="btn btn-success">
							<span class="glyphicon glyphicon-user">注册
						</button>
						<button type="reset" class="btn btn-info">
							<span class="glyphicon glyphicon-edit">重置
						</button>
					</div>
				</div>
			</form>
		</div>
		<footer>
			<p>&copy; 版权所有，欢迎借鉴</p>
		</footer>

		<script>
			/*  $('#registerForm').bootstrapValidator({
				//       live: 'disabled',
				message : 'This value is not valid',
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					"user.name" : {
						validators : {
							notEmpty : {
								message : '真实姓名不能为空'
							}
						}
					},
					"user.loginName" : {
						message : '用户名验证失败',
						validators : {
							notEmpty : {
								message : '用户名不能为空'
							},
							stringLength : {
								min : 5,
								max : 30,
								message : '用户名长度5到30位'
							}
						}
					},
					"user.email" : {
						validators : {
							notEmpty : {
								message : '邮箱不能为空'
							},
							emailAddress : {
								message : '邮箱格式不正确'
							}
						}
					},
					"user.phone" : {
						validators : {
							notEmpty : {
								message : '电话不能为空'
							},
							phone : {
								message : '电话号码错误',
								country : 'CN'
							}
						}
					},
					"user.password" : {
						validators : {
							notEmpty : {
								message : '密码不能为空'
							},
							different : {
								field : 'user.loginName',
								message : '用户名和密码不能相同'
							}
						}
					},
					"user.okpassword" : {
						validators : {
							notEmpty : {
								message : '确认密码不能为空'
							},
							identical : {
								field : 'user.password',
								message : '两次密码不相同'
							},
							different : {
								field : 'user.loginName',
								message : '用户名和密码不能相同'
							}
						}
					},
					"user.address" : {
						validators : {
							notEmpty : {
								message : '地址不能为空'
							}
						}
					}
				}
			}); 

			$("#btn_submit").click(function() {
				alert(1);
				$('#registerForm').bootstrapValidator('validate');
			});
			 */
			
		</script>
	</div>
<!--  横幅下方的主体结束 -->

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->

<script src="${pageContext.request.contextPath }/resources/bootstrap/js/bootstrap.js"></script>

<script src="${pageContext.request.contextPath }/resources/js/taobao.js"></script>
</body>
</html>