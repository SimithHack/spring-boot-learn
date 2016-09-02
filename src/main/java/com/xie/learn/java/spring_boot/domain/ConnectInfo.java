package com.xie.learn.java.spring_boot.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by xfq on 16/9/2.
 * 它会自动使用application.properties的jdbc.connection为前缀的属性来为此类设置值
 */

@Component//将此类注册到容器，可以避免
@ConfigurationProperties(prefix = "jdbc.connection")
public class ConnectInfo {
    private String url;
    private String driver;
    private int port;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    /*public String getUrl() {
        return "192.168.1.1";
    }

    public int getPort() {
        return 22;
    }

    public String getDriver() {
        return "jdbc.driver";
    }*/

}
