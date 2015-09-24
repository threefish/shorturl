package com.cn.test;

import com.cn.db.dao.HucDao;
import com.cn.url.service.SequenceBaseShortUrlService;
import com.cn.url.service.ShortUrlService;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;

/**
 * Created with IntelliJ IDEA.
 * User: 黄川
 * Date Time: 2015/9/2410:12
 */

public class TestMain {
    public static void main(String[] args) {
        Dao dao = HucDao.getInstance();
        if (dao != null) {
            Daos.createTablesInPackage(dao, "com.huc.app.core.db", false);
            System.out.println("connect db is done");
        } else {
            System.out.println("connect db is error");
        }
        ShortUrlService service = new SequenceBaseShortUrlService();
        for (int i = 0; i <= 100000; i++) {
            System.out.println(i + " : " + service.convertShort("http://www.cnbeta.com/articles/43280" + i + ".htm"));
        }
    }
}
