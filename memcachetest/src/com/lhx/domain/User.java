package com.lhx.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lhx on 15-8-31 下午4:31
 *
 * @Description
 */
public class User implements Serializable {

    private int id ;
    private String name ;
    private boolean sex ;
    private Date birthday ;

    public User() {
    }

    public User(int id, String name, boolean sex, Date birthday) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "id:" + id + " name:" + name + " sex:" + sex + " birthday:" + birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
