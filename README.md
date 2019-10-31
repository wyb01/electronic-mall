# electronic-mall


#该项目分为前后端：
  
   1、ShoppingApp：前端   顾客可以下单购买商品
 
   2、shopApp：后端       管理员维护商品


    (1)、需要把 lib 下 jar包 add to build 

    (2)、配置tomcat，Mudules - path : "/"

    (3)、前端ShoppingApp需要  Java Build Path - Libraries - Add Library - Server Runtime 添加tomcat 	
             以使项目依赖tomcat的 servel-api.jar (导入HttpSession类)
 

