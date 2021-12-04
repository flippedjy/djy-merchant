package com.xiaomi.merchant.infastructure;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.List;


public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext ctx;

    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    public static Object getBean(String name){
        return ctx.getBean(name);
    }


}
