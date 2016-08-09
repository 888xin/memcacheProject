package com.lhx.memcached;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.lhx.domain.User;
import org.omg.CORBA._PolicyStub;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by lhx on 15-8-31 下午4:00
 *
 * @Description
 */
public class MyCache {
    public static void main(String[] args) {

        MemCachedClient client = new MemCachedClient();
        String[] addr = {"127.0.0.1:11211"};
        Integer[] weights = {3};
        SockIOPool pool = SockIOPool.getInstance();
        pool.setServers(addr);
        pool.setWeights(weights);
        pool.setInitConn(5);
        pool.setMinConn(5);
        pool.setMaxConn(200);
        pool.setMaxIdle(1000*30*30);
        pool.setMaintSleep(30);
        pool.setNagle(false);
        pool.setSocketTO(30);
        pool.setSocketConnectTO(0);
        pool.initialize();

//        client.setCompressEnable(true);
//        client.setCompressThreshold(1000*1024);

        //将数据放入缓存
        client.set("test2","test2");
        java.util.Map<java.lang.String,java.util.Map<java.lang.String,java.lang.String>> map = client.stats();

        //将数据放入缓存,并设置失效时间
//        Date date = new Date(2000000);
//        client.set("test1","test1-value",date);

        //删除缓存数据
        //client.delete("test1");

//        User user = new User(1,"李新新",true, Calendar.getInstance().getTime());
//        Date date = new Date(1000*20);
//        client.set("user1", user, date);

//        List<User> list = new ArrayList<User>();
//        User user = new User(1, "李新新", true, Calendar.getInstance().getTime());
//        Date date = new Date(1000 * 20);
//        client.set("user1", user, date);
//        list.add(user);
//        client.set("user_list",list);


        //获取缓存数据
        String str = (String) client.get("test2");
        User user1 = (User) client.get("user1");
        System.out.println(str);
        System.out.println(user1);
//        client.delete("user_list");
//        List<User> list2 = (List<User>) client.get("user_list");
//        System.out.println(list2.size());

    }
}
