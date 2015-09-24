package com.cn.db.service;

import com.cn.db.bean.UrlShortPojo;
import com.cn.db.dao.HucDao;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;

/**
 * Created with IntelliJ IDEA.
 * User: 黄川
 * Date Time: 2015/9/2316:53
 */

public class UrlShortServiceImpl implements UrlShortService {

    private Dao dao = HucDao.getInstance();

    @Override
    public UrlShortPojo add(String surl, String lurl) {
        UrlShortPojo bean = new UrlShortPojo();
        bean.setLurl(lurl);
        bean.setSurl(surl);
        return dao.insert(bean);
    }

    @Override
    public String fetchLurl(String surl) {
        UrlShortPojo bean=dao.fetch(UrlShortPojo.class, Cnd.where("surl", "=", surl));
        return bean.getLurl();
    }

    @Override
    public String fetchSurl(String lurl) {
        UrlShortPojo bean = dao.fetch(UrlShortPojo.class, Cnd.where("lurl", "=", lurl));
        return bean.getSurl();
    }

    @Override
    public int maxId() {
        return dao.getMaxId(UrlShortPojo.class);
    }
}
