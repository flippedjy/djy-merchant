package com.xiaomi.merchant.domain.entity;


import com.xiaomi.merchant.domain.client.CashierDeskClient;
import com.xiaomi.merchant.domain.constant.OrderStatus;
import com.xiaomi.merchant.domain.constant.PayStatus;
import com.xiaomi.merchant.domain.repository.GoodsRepository;
import com.xiaomi.merchant.domain.repository.OrderRepository;
import com.xiaomi.merchant.domain.vo.PayReqParam;
import com.xiaomi.merchant.domain.vo.PayResp;

import com.xiaomi.merchant.infastructure.SerialNumGenerator;
import com.xiaomi.merchant.type.PriceFen;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SimpleIdGenerator;

import java.util.*;
import java.util.stream.Collectors;

public class Order {

    //订单Id
    private String orderId;
    //订单状态
    private OrderStatus orderStatus;
    //订单商品详情
    private List<OrderLine> goodsDetail;
    //已支付订单
    private PayOrder paidOrder;
    //支付金额
    private long payAmount;
    //订单所属用户ID
    private String ownerId;
    //订单下单时间
    private Date orderPlaceDate;
    //订单过期时间
    private Date orderExpireDate;
    //配送地址
    private String address;
    //支付订单
    private List<PayOrder> payOrders;

    public List<PayOrder> getPayOrders() {
        return payOrders;
    }

    public String getAddress() {
        return address;
    }

    public Date getOrderPlaceDate() {
        return orderPlaceDate;
    }

    public Date getOrderExpireDate() {
        return orderExpireDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public PayOrder getPaidOrder() {
        return paidOrder;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<OrderLine> getGoodsDetail() {
        return goodsDetail;
    }

    public PayOrder payApply(OrderRepository repository, PayReqParam payReqParam,
                             CashierDeskClient cashierDeskClient){
        Assert.isTrue(orderStatus.equals(OrderStatus.NOT_PAY),"the order had paid or is closed");
        String payOrderId = SerialNumGenerator.getNextSerialNum("payOrder");
        PayOrder payOrder = new PayOrder(payOrderId,payReqParam,payAmount);
        this.payOrders = Arrays.asList(payOrder);
        repository.savePayOrder(this);
        cashierDeskClient.payApply(this);
        payOrder.setPayStatus(PayStatus.IN_PROCESS);
        this.orderStatus = OrderStatus.IN_PROCESS;
        repository.save(this);
        return payOrder;
    }







    private Order(){

    }

    public void place(OrderRepository orderRepository,GoodsRepository goodsRepository) {
        Assert.isNull(this.orderStatus,"请勿重复下单");
        this.orderStatus = OrderStatus.NOT_PAY;
        this.orderPlaceDate = new Date();
        this.orderExpireDate = DateUtils.addMinutes(orderPlaceDate,30);
        setDetailPriceSnap(goodsRepository);
        orderRepository.save(this);

    }

    /**
     * 设置订单详情价格快照
     * @param goodsRepository
     */
    private void setDetailPriceSnap(GoodsRepository goodsRepository){
        this.goodsDetail.forEach(line->{
            Goods goods = goodsRepository.find(line.goodsId);
            Assert.notNull(goods,"invaild goods id");
            line.unitPrice = goods.getPrice();
        });
    }

    public void syncPayStatus(CashierDeskClient cashierDeskClient) {
        Assert.isTrue(this.orderStatus.equals(OrderStatus.IN_PROCESS));
        Assert.isTrue(!CollectionUtils.isEmpty(this.payOrders));
        PayResp payResp = cashierDeskClient.queryOrderPayStatus(this.orderId);
        if(PayStatus.IN_PROCESS.equals(payResp.getPayStatus())){
            return ;
        }

    }

    private static class OrderLine{
        private String goodsId;

        private int count;

        private PriceFen unitPrice;

    }

    public static OrderBuilder newBuilder(){
        return new OrderBuilder();
    }


    public static class OrderBuilder{

        private OrderBuilder() {
        }

        private String orderId;

        public OrderBuilder owner(User user) {
            this.owner = user;
            return this;
        }

        private User owner;

        public OrderBuilder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        //订单商品详情
        private Map<String,Integer> goodsDetail;

        public OrderBuilder addGoods(Map<String,Integer> details){
            if(CollectionUtils.isEmpty(details)){
                return this;
            }
            if(this.goodsDetail == null){
                goodsDetail = details;
                return this;
            }
            details.entrySet().forEach((entry)->{
                details.merge(entry.getKey(), entry.getValue(), Integer::sum);
            });

            return this;
        }

        public OrderBuilder addGoods(String goodsId,Integer count){
            Assert.hasText(goodsId,"invalid goodsId");
            Assert.isTrue((count != null && count > 0),"goods count must be a postive number");
            if(goodsDetail == null){
                goodsDetail = new HashMap<>();
            }
            goodsDetail.merge(goodsId,count,Integer::sum);
            return this;
        }

        public Order build(){
            Assert.notNull(this.owner,"order owner must not be null ");
            Order order = new Order();
            order.orderId = this.orderId;
            order.ownerId = this.owner.getUserId();
            Assert.isTrue(CollectionUtils.isEmpty(this.goodsDetail),"build order fail,order does not contain any goods");
            goodsDetail.entrySet().stream().map(entry->{
                OrderLine orderLine = new OrderLine();
                orderLine.goodsId = entry.getKey();
                orderLine.count = entry.getValue();
                return orderLine;
            }).collect(Collectors.toList());
            return order;
        }

    }


}
