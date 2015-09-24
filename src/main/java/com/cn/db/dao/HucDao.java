package com.cn.db.dao;

import com.alibaba.druid.pool.DruidDataSource;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;

import java.sql.SQLException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: 黄川
 * Date Time: 2015/9/2315:47
 */

public class HucDao {

    private static Dao dao;

    private static DruidDataSource getDataSource() {
        DruidDataSource dds = new DruidDataSource();
        try {
            HucDBPros dbPros = new HucDBPros().loadToFile();
            dds.setUrl(dbPros.getUrl());
            dds.setUsername(dbPros.getUsername());
            dds.setPassword(dbPros.getPassword());
            dds.setValidationQuery(dbPros.getValidationQuery());
            dds.setMaxActive(dbPros.getMaxActive());
            dds.setMaxWait(dbPros.getMaxWait());
            dds.setFilters(dbPros.getFilters());
            dds.setTestWhileIdle(dbPros.isTestWhileIdle());

            Properties properties = new Properties();
            String[] property = dbPros.getConnectionProperties().split("=");
            properties.setProperty(property[0], property[1]);
            dds.setConnectProperties(properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dds;
    }
    /**
     * 创建新链接
     * @return
     */
    private static Dao newDao() {
        return new NutDao(getDataSource());
    }

    /**
     * 取得链接
     * @return
     */
    public static Dao getInstance() {
        if (dao == null) {
            return new NutDao(getDataSource());
        } else {
            return dao;
        }
    }

}
