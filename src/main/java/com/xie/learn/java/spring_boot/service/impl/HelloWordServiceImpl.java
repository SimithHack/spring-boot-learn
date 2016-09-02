package com.xie.learn.java.spring_boot.service.impl;

import com.xie.learn.java.spring_boot.service.HelloWordService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by xfq on 16/8/28.
 */
@Service
public class HelloWordServiceImpl implements HelloWordService {
    @Value("${JAVA_HOME}")
    private String commandParam;
    @Value("${xfq.name}")
    private String name;
    @Override
    public String autoScanTest() {
        return "This is autoScanTest";
    }
    //测试访问commandline参数
    public String commandParameter(){
        return commandParam;
    }
    //测试获取application.properties的配置
    public String applicationProp(){
        return name;
    }

}
