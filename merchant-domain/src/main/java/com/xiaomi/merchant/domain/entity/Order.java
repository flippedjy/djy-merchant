package com.xiaomi.merchant.domain.entity;


import com.xiaomi.merchant.domain.client.CashierDeskClient;
import com.xiaomi.merchant.domain.constant.OrderStatus;
import com.xiaomi.merchant.domain.constant.PayStatus;
import com.xiaomi.merchant.domain.repository.GoodsRepository;
import com.xiaomi.merchant.domain.repository.OrderRepository;
import com.xiaomi.merchant.domain.vo.Goods;
import com.xiaomi.merchant.domain.vo.OrderLine;
import com.xiaomi.merchant.domain.vo.PayReqParam;
import com.xiaomi.merchant.domain.vo.PayResp;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.Assert;


import java.util.*;
import java.util.stream.Collectors;

public class Order {


    //订单Id
    private String orderId;
    //订单状态
    private OrderStatus orderStatus;
    //订单商品详情
    private List<OrderLine> goodsDetail;
    //订单所属用户ID
    private String ownerId;
    //订单下单时间
    private Date orderPlaceDate;
    //订单过期时间
    private Date orderExpireDate;
    //配送地址
    private String address;
    //总价
    private long totalPrice;
    //是否已下单
    private boolean placed;



    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderLine> getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(List<OrderLine> goodsDetail) {
        this.goodsDetail = goodsDetail;
        this.totalPrice = goodsDetail.stream()
                .collect(Collectors.summarizingLong(
                        line->line.getGoodsPrice() * line.getGoodsCount()))
                .getSum();
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Date getOrderPlaceDate() {
        return orderPlaceDate;
    }

    public void setOrderPlaceDate(Date orderPlaceDate) {
        this.orderPlaceDate = orderPlaceDate;
    }

    public Date getOrderExpireDate() {
        return orderExpireDate;
    }

    public void setOrderExpireDate(Date orderExpireDate) {
        this.orderExpireDate = orderExpireDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    /**
     * 从资源库加载订单
     * @param repository
     * @param orderId
     * @return
     */
    public static  Order load(OrderRepository repository,String orderId){
        Order order = repository.find(orderId);
        if(order == null){
            return null;
        }
        order.placed = true;
        return order;
    }

    /**
     * 下单
     * 1.需要计算账单金额
     * @param orderRepository
     * @param goodsRepository
     */
    public void place(OrderRepository orderRepository, GoodsRepository goodsRepository){
        Assert.isTrue(!placed,"已下单，不能重复下单");
        Assert.notEmpty(goodsDetail,"订单详情不能为空");
        Assert.notNull(ownerId,"订单归属人不能为空");
        this.orderPlaceDate = new Date();
        this.orderExpireDate = DateUtils.addDays(orderPlaceDate,15);
        this.orderStatus = OrderStatus.NOT_PAY;
        setGoodsDetailPrice(goodsRepository,goodsDetail);
        long totalPrice = goodsDetail.stream().collect(Collectors.summingLong(line->line.getGoodsPrice()*line.getGoodsCount()));
        this.totalPrice = totalPrice;
        orderRepository.save(this);
    }


    /**
     * 查找商品价格
     * @param goodsRepository
     * @param goodsDetail
     */
    private void setGoodsDetailPrice(GoodsRepository goodsRepository, List<OrderLine> goodsDetail) {
       
        List<String> goodsIds = goodsDetail.stream().map(OrderLine::getGoodsId).collect(Collectors.toList());
        List<Goods> goodsList = goodsRepository.findAll(goodsIds);
        Map<String, Goods> goodsMap = goodsList.stream().collect(Collectors.toMap(Goods::getGoodsId, (goods) -> goods));
        goodsDetail.forEach(goodsLine->{
            goodsLine.setGoodsPrice(goodsMap.get(goodsLine.getGoodsId()).getPrice().getPrice());
        });
    }


}
