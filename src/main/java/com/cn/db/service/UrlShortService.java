package com.cn.db.service;


import com.cn.db.bean.UrlShortPojo;

/**
 * Created with IntelliJ IDEA.
 * User: 黄川
 * Date Time: 2015/9/2316:51
 */

public interface UrlShortService {

    public UrlShortPojo add(String surl, String lurl);

    public String fetchLurl(String surl);
    public String fetchSurl(String lurl);

    public int  maxId();
}
