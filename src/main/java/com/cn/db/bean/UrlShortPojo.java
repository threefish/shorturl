package com.cn.db.bean;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created with IntelliJ IDEA.
 * User: 黄川
 * Date Time: 2015/9/2316:34
 */
@Table("tb_url_short")
public class UrlShortPojo {
    @Id
    protected int id;

    @Column("surl")
    protected String surl;

    @Column("lurl")
    @ColDefine(width=500)
    protected String lurl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }

    public String getLurl() {
        return lurl;
    }

    public void setLurl(String lurl) {
        this.lurl = lurl;
    }
}
