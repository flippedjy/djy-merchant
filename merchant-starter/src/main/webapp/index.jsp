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
    <title>小米商城</title>
</head>
<body>
    <form>
        <input name="goodsIds" value="1" hidden>
    <div class="figure figure-img">
        <img width="160" height="160" alt="Redmi Note 11 5G"
                                        src="//cdn.cnbj1.fds.api.mi-img.com/mi-mall/50da95e9e4496dcac8704da2deb94f6e.jpg?thumb=1&amp;w=200&amp;h=200&amp;f=webp&amp;q=90"
                                        lazy="loaded"></div>
    <h3 class="title">
        Redmi Note 11 5G
    </h3>
    <p class="desc">5000mAh大电量</p>
    <p class="price"><span class="num">1199</span>元<span>起</span><!----></p></a>
    <button formaction="/createOrder">加入购物车</button>
    &nbsp;&nbsp;&nbsp;
    <button formaction="/createOrder">立刻购买</button>
    </form>
</body>
</html>
