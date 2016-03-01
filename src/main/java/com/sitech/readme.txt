----分库
		模拟下单：
			post=>http://localhost:8080/v1/res/ordersave
			将订单保存进A库\B库
			
		模拟订单流转：
			post=>http://localhost:8080/v1/res/orderget?orderId=123a
			根据订单号取A库\B库中的数据
----多库
模拟合并API：
	post=>http://localhost:8080/v1/res/multds
	可以合并两库的LIST	

----切库
模拟用户库挂了(仅适用系统菜单类)：
	post=>http://localhost:8080/v1/res/dberror
	能切换为A库。如果A库挂了或应用重启才会重试用户库。		



模拟SSM：
	请见核心包工程	
	
/////////////////////////////////////////////////////////
	后续将该些代码迁移进IMPL中.只需要迁移com.sitech.ext下的即可。