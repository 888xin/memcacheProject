package com.lhx.memcached;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.lhx.domain.User;

/**
 * Created by lhx on 15-8-31 下午4:00
 *
 * @Description
 */
public class MyCach4 {
    public static void main(String[] args) {

        MemCachedClient client = new MemCachedClient();
        String[] addr = {"192.168.2.3:20001","192.168.2.3:20002"};
//        Integer[] weights = {3};
        SockIOPool pool = SockIOPool.getInstance();
        pool.setServers(addr);
//        pool.setWeights(weights);
//        pool.setInitConn(5);
//        pool.setMinConn(5);
//        pool.setMaxConn(200);
//        pool.setMaxIdle(1000*30*30);
//        pool.setMaintSleep(30);
//        pool.setNagle(false);
//        pool.setSocketTO(30);
//        pool.setSocketConnectTO(0);
        pool.initialize();

        //将数据放入缓存
        boolean flag = client.set("test2","test2222222");
        System.out.println(flag);

        //获取缓存数据
        String str = (String) client.get("test2");
//        User user1 = (User) client.get("user1");
        System.out.println(str);
//        client.delete("test2");
//        System.out.println(client.get("test2"));
//        System.out.println(user1);
//        client.delete("user_list");
//        List<User> list2 = (List<User>) client.get("user_list");
//        System.out.println(list2.size());

    }
}
