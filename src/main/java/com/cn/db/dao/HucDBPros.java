package com.cn.db.dao;


import com.cn.util.HucFileReader;
import com.google.gson.Gson;

/**
 * Created with IntelliJ IDEA.
 * User: 黄川
 * Date Time: 2015/9/2315:59
 */

public class HucDBPros {
    private String url="";
    private String username="";
    private String password="";
    private boolean testWhileIdle=false;
    private String validationQuery="";
    private int maxActive=100;
    private String filters="";
    private String connectionProperties="";
    private int maxWait=10000;

    /**
     * 从配置文件中取得
     * @return this
     */
    public HucDBPros loadToFile() {
        String appjson = HucFileReader.readeStringForPath(HucFileReader.getFilePath("db.json"));
        return new Gson().fromJson(appjson, HucDBPros.class);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public String getConnectionProperties() {
        return connectionProperties;
    }

    public void setConnectionProperties(String connectionProperties) {
        this.connectionProperties = connectionProperties;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }
}
