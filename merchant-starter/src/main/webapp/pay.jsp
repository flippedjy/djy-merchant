<%--
  Created by IntelliJ IDEA.
  User: djy
  Date: 2021/11/21
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>小米商城-支付</title>
</head>
<body>
    <form action="/doPay">
        <input hidden name="orderId" value="${orderId}"><br>
        <input hidden name="amount" value="${totalPrice}"><br>
    <span>总价:${totalPrice/100}元</span><br>
    <input type="checkbox" value="1" name="payType" >扫码支付<br>
    <input type="submit" value="去支付">
    </form>
</body>
</html>
