package com.xie.learn.java.spring_boot;

import com.xie.learn.java.spring_boot.domain.ConnectInfo;
import com.xie.learn.java.spring_boot.domain.ThirdPartBean;
import com.xie.learn.java.spring_boot.service.HelloWordService;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xfq on 16/8/23.
 */
//具有Controller和@ResponseBody的双重作用
@RestController
//表示根据添加的依赖，自动猜测应用类型，然后自动配置
@EnableAutoConfiguration
//会自动扫描spring-boot package下边的包
@ComponentScan
public class HelloWorld {
    @Value("${jdbc.connection.url}")
    private String url;
    @Autowired
    private  ConnectInfo info;
    @Autowired
    private HelloWordService helloWordService;
    @RequestMapping("/scan")
    public String autoScanTest(){
        return helloWordService.autoScanTest();
    }
    @RequestMapping("/")
    public String name(){
        return "<div style='color:red;'>Hello Wolrd XFQ</div>";
    }
    @RequestMapping("/command")
    public String command(){
        return helloWordService.commandParameter();
    }
    @RequestMapping("/application")
    public String getApplicationProp(){
        return helloWordService.applicationProp();
    }
    public static void main(String[] args){
        //spring-boot的SpirngApplication负责启动应用程序，它将会自动配置tomcat服务器。
        System.out.println(args);
        SpringApplication.run(HelloWorld.class,args);
    }
    @RequestMapping("connect_info")
    public ConnectInfo getConnectInfo(){
        return info;
    }
    @RequestMapping("url")
    public String url(){
        return url;
    }
    /**绑定第三方配置**/
    @Autowired
    private ThirdPartBean tpb;
    @RequestMapping("third")
    public ThirdPartBean getTPB(){
        return tpb;
    }
    @Bean
    @ConfigurationProperties(prefix = "jdbc.connection")
    public ThirdPartBean getThirdPartBean(){
        return new ThirdPartBean();
    }
}
