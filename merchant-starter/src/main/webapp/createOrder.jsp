<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: djy
  Date: 2021/11/21
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单确认</title>
</head>
<body>
<h1>订单确认</h1>
<form action="/place" method="get">
    <table>
        <tbody>
    <c:forEach items="${goods}" var="item">
        <input name="price" value="${item.price.price}" hidden/>
        <tr>
            <td>
        <span style="border: black">
            <input name="goodsId" value="${item.goodsId}" hidden></span></td>
            <td><span><h5>${item.goodsName}</h5></span></td>
            <td><span><h5>${item.price.price / 100}元</h5></span></td>
        </tr>
    </c:forEach>
    </tbody>
    </table>
    <span style="size: B5">总计:</span><span id="totalPriceSpan" style="font-style: italic"></span><span>元</span>
    <input name="totalPrice" id="totalPrice" hidden>
    <br>
    <span>地址:</span><input name="address" />
    <br>
    <input type="submit"  value="立即付款"/>
</form>
</body>
<script type="text/javascript">
    var totalPrice = 0;
    document.getElementsByName("price").forEach((e)=>{totalPrice = totalPrice + Number.parseInt(e.value)});
    document.getElementById("totalPriceSpan").innerHTML = (totalPrice/100);
    document.getElementById("totalPrice").value = totalPrice;
</script>
</html>
