package com.lhx.memcached;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.lhx.domain.User;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by lhx on 15-8-31 下午4:00
 *
 * @Description
 */
public class MyCache2 {
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


        //将数据放入缓存
        //client.set("li","hello");
        java.util.Map<String,java.util.Map<String,String>> map = client.statsItems();

        java.util.Map<java.lang.String,java.util.Map<java.lang.String,java.lang.String>> mapMap = client.statsCacheDump(1,1);


        //获取数据
        for (String s : map.keySet()) {
            System.out.println(s);
            Map<String,String> map1 = map.get(s);
            for (String s1 : map1.keySet()) {
                System.out.println(s1 + "------" + map1.get(s1));
            }
        }
        System.out.println(client.get("li"));
    }


//    public static Map<String,KeysBean> getKeysForMap() throws UnsupportedEncodingException {
//
//        MemCachedClient client = new MemCachedClient();
//        String[] addr = {"127.0.0.1:11211"};
//        Integer[] weights = {3};
//        SockIOPool pool = SockIOPool.getInstance();
//        pool.setServers(addr);
//        pool.setWeights(weights);
//        pool.setInitConn(5);
//        pool.setMinConn(5);
//        pool.setMaxConn(200);
//        pool.setMaxIdle(1000*30*30);
//        pool.setMaintSleep(30);
//        pool.setNagle(false);
//        pool.setSocketTO(30);
//        pool.setSocketConnectTO(0);
//        pool.initialize();
//
//
//
//        Map<String,KeysBean> keylist=new HashMap<String,KeysBean>();
//        //遍历statsItems 获取items:2:number=14
//        Map<String,Map<String,String>> statsItems=client.statsItems();
//        Map<String,String> statsItems_sub=null;
//        String statsItems_sub_key=null;
//        int items_number=0;
//        String server=null;
//        //根据items:2:number=14，调用statsCacheDump，获取每个item中的key
//        Map<String,Map<String,String>> statsCacheDump=null;
//        Map<String,String> statsCacheDump_sub=null;
//        String statsCacheDumpsub_key=null;
//        String statsCacheDumpsub_key_value=null;
//
//        for (Iterator iterator=statsItems.keySet().iterator();iterator.hasNext();) {
//            server=(String) iterator.next();
//            statsItems_sub=statsItems.get(server);
//            //System.out.println(server+"==="+statsItems_sub);
//            for (Iterator iterator_item=statsItems_sub.keySet().iterator();iterator_item.hasNext();) {
//                statsItems_sub_key=(String) iterator_item.next();
//                //System.out.println(statsItems_sub_key+":=:"+bb);
//                //items:2:number=14
//                if (statsItems_sub_key.toUpperCase().startsWith("items:".toUpperCase()) && statsItems_sub_key.toUpperCase().endsWith(":number".toUpperCase())){
//                    items_number=Integer.parseInt(statsItems_sub.get(statsItems_sub_key).trim());
//                    //System.out.println(statsItems_sub_key+":=:"+items_number);
//                    statsCacheDump=client.statsCacheDump(new String[]{server},Integer.parseInt(statsItems_sub_key.split(":")[1].trim()), items_number);
//
//                    for (Iterator statsCacheDump_iterator=statsCacheDump.keySet().iterator();statsCacheDump_iterator.hasNext();) {
//                        statsCacheDump_sub=statsCacheDump.get(statsCacheDump_iterator.next());
//                        //System.out.println(statsCacheDump_sub);
//                        for (Iterator iterator_keys=statsCacheDump_sub.keySet().iterator();iterator_keys.hasNext();) {
//                            statsCacheDumpsub_key=(String) iterator_keys.next();
//                            statsCacheDumpsub_key_value=statsCacheDump_sub.get(statsCacheDumpsub_key);
//                            //System.out.println(statsCacheDumpsub_key);//key是中文被编码了,是客户端在set之前编码的，服务端中文key存的是密文
//                            //System.out.println(statsCacheDumpsub_key_value);
//
//                            keylist.put(URLDecoder.decode(statsCacheDumpsub_key, "UTF-8"), new KeysBean(server,Long.parseLong(statsCacheDumpsub_key_value.substring(1, statsCacheDumpsub_key_value.indexOf("b;")-1).trim()),Long.parseLong(statsCacheDumpsub_key_value.substring(statsCacheDumpsub_key_value.indexOf("b;")+2,statsCacheDumpsub_key_value.indexOf("s]")-1).trim())));
//                        }
//                    }
//                }
//
//            }
//        }
//        return keylist;
//    }
//
//    class KeysBean{
//        private int id;
//        private String name;
//    }


}
