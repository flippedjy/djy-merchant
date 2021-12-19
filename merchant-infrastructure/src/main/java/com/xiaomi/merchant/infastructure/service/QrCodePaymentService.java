package com.xiaomi.merchant.infastructure.service;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.gson.JsonObject;
import com.xiaomi.merchant.domain.entity.Order;
import com.xiaomi.merchant.domain.entity.PayOrder;
import com.xiaomi.merchant.domain.service.PaymentService;
import com.xiaomi.merchant.domain.vo.PayInfo;
import com.xiaomi.merchant.domain.vo.PayResp;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sun.misc.IOUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class QrCodePaymentService implements PaymentService {


    @Override
    public String getPayType() {
        return "1";
    }

    @Override
    public PayResp payApply(PayOrder payOrder) {


        PayResp payResp = new PayResp();
        PayInfo payInfo = new PayInfo();
        //payInfo.setUrl("http://www.baidu.com?orderId="+order.getCurrentPayOrder().getPayOrderId());

        payResp.setPayInfo(payInfo);
        return payResp;
    }

    static String url = "http://staging.api.ucashier.mipay.com/jsapi/trade/create";

    static String privateKey = 
            "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDjHT85F+W5/sdc" +
            "w4Zjhri3RSPWsv+gqZl08qVmgxeBcmYEPzfF8uS7URrAh/rQQkE+2S/D+wv9mDyx" +
            "P4evTuEAQRwbaoTtVhQ2X7a8DByh8dkVSdUbIT1R5atNAPhDf18X+iFmTe8BoOj7" +
            "A46JXOTU2BZvLCKqcYOxqDpp7fdHZnVi3czi8fjFKTPUeTRHgQ/BnZWe6D2TQ52+" +
            "ClXnUf7CWQhhRUbOaAqtWKWNkzAIx47JEvzyr33ShplXHAaIjIcgPrzl59VJAPop" +
            "qQHx4eUT5MjyRUDQaVpasPaehGvhs29Qpbyg32HuFHrc3n7PS9mBzPUBemkQb8Me" +
            "MXWQb799AgMBAAECggEBAJdPsgKIwF4Vt58/CTcf18sKfmEZaqQQupOm4bmVl0CA" +
            "BiXINJFmTSvbUjZ5ognESpHjwyYQCSIQnkb865pJtDAmMOK3Kg6xrc+4HxfT/fOL" +
            "HLVJNnjXwXHh0SoBfmYB40onNL9jJtxv5yjtarZrtG119iRlREdIT5Q6X4iZqYjg" +
            "IXe3d3tlTBeSX1fx071uMXw6im5GKCZjiQKS/96PJyOXUlAVSbkWEJZXdHB0Ss4B" +
            "/724zUpHbqVk8qHLv8dxl0JAo9xGQ2dFbTOPj9rg9RCFg0ekrF9jFDSNkAZcwWxY" +
            "MnXf9w/0eCEONiw0W6+2imCstiRxf4VO/8QuCdZ1GsECgYEA/5VtJtXGE4filhcN" +
            "IX+0vWVdKE2pT9p0uAaAVheSs5EbnA51IPjSjZ10/kASXiqJ2cP5v56lBSezrsLb" +
            "BM4CiywzPWTM43fKlVXfEfyND+BgOq7eYoQPqvwVB3L99ZE3FtMcZYyUeirboLjd" +
            "Vk8MBcM6Uefms3u97WBNbr18nHECgYEA43vzCWP7TKE7n5W+4q2dLVgeN8hYWrUo" +
            "Rxupt0mnBbXcOzY+nYhtTIlpymBTRhih96ZRj3C7CecgzSpexTDvn2BFscThvwct" +
            "ZajxWLknW+ojS0tPuIWiy1UxwZc/wSx4k6wFvNboqDWh41Neik1/QmQv2dw4zWXY" +
            "deQVgtHOic0CgYBnsVs4f1C+icnvNOj551MP6DkUafXAVRQ3uUOGKkg1nDDEbsUs" +
            "nGboV7KomJwbIk0NUcpY6OdwoPvwclPjriUwC726MdRCkU5aqh03sewVPeYsyNem" +
            "xoeIyn9Jp2jLoZ/lHb+On6t5qTu0CGycYLOy/tMBegGKXjDfRswDmtMcsQKBgQCK" +
            "M3hQYSx/vohhiHECd6VAsgMRsqZy3RIOOTEfSCCvaG/WjTr3K7XzRJw/tcuBj19+" +
            "+80bDsqi1obn/fC7beJ1+d/9gvX+vxaGZ1/QnhPaVGGC0csp+A4R570ltoSmECVk" +
            "2aJWul14K7UhTTnnXfONWx5zBcaAino6DQpAgsQulQKBgGeKzJjPPmiDBXMHTnGG" +
            "iGP9bimQWSE5hRp4yoVDdM8Ff+aJ4Q5o3Tot7VKbmRVzTjqMviM3F/BxiUbVNB4l" +
            "o6GkjX186ICZ4ndn+MSFyUAVsgz7y33k6X+eaQzvp7ELu4bDnUn2xSiqFWvg1bX5" +
            "bWboqUigc0tbQbqPJd8rFen8";

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException, IOException {
        JsonObject requestJson = new JsonObject();

        JsonObject order = new JsonObject();
        order.addProperty("service","createDirectPayByUser");
        order.addProperty("partnerId","1000016728");
        order.addProperty("notifyUrl","https://www.baidu.com");
        order.addProperty("returnUrl","https://www.baidu.com");
        order.addProperty("totalFee","10");
        order.addProperty("orderDesc","测试订单");
        order.addProperty("outOrderId","20210906154212000000000013993596");
        order.addProperty("payMethod","directPay");
        order.addProperty("order",""+System.currentTimeMillis()/1000);

        TreeMap<String, Object> param = new TreeMap<>();
        Map<String, Object> collect = order.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        param.putAll(collect);
        String context = Joiner.on("&").withKeyValueSeparator("=").join(param);

        Signature sign = Signature.getInstance("SHA1WithRSA");
        KeySpec key = new PKCS8EncodedKeySpec(new BASE64Decoder().decodeBuffer(privateKey));
        PrivateKey pk = KeyFactory.getInstance("RSA").generatePrivate(key);
        sign.initSign(pk);
        sign.update(context.getBytes(StandardCharsets.UTF_8));
        byte[] sign1 = sign.sign();
        String signString = new BASE64Encoder().encode(sign1);

        order.addProperty("payType","5");//不签名
        order.addProperty("sign",signString);
        requestJson.addProperty("order",order.toString());
        URLConnection conn = new URL(url).openConnection();
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        PrintWriter printWriter = new PrintWriter(conn.getOutputStream());
        printWriter.write(requestJson.toString());
        printWriter.flush();



        conn.connect();
        InputStream inputStream = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while((line = br.readLine()) != null){
            System.out.println(line);
        }



    }
}
