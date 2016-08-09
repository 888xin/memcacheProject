package com.lhx.memcached;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by lhx on 15-8-31 下午4:00
 *
 * @Description
 */
public class MyCache3 {

    private final static Logger logger = LoggerFactory.getLogger(MyCache3.class);

    public static void main(String[] args) {

//        MemCachedClient client = new MemCachedClient();
//        String[] addr = {"127.0.0.1:11211"};
//        Integer[] weights = {3};
//        SockIOPool pool = SockIOPool.getInstance();
//        pool.setServers(addr);
//        pool.setWeights(weights);
//        pool.setInitConn(5);
//        pool.setMinConn(5);
//        pool.setMaxConn(200);
//        pool.setMaxIdle(1000 * 30 * 30);
//        pool.setMaintSleep(30);
//        pool.setNagle(false);
//        pool.setSocketTO(30);
//        pool.setSocketConnectTO(0);
//        pool.initialize();
//
//
//        List<String> list = getAllKeys(client);
//        for (String s : list) {
//            System.out.println(s);
//        }

        System.out.println(strMatch("dshs;fshfaie:PlayImpl:2:1","PlayImpl:"));
    }


    /**
     * 获取服务器上面所有的key
     */
    public static List<String> getAllKeys(MemCachedClient memCachedClient) {
        logger.info("开始获取没有挂掉服务器中所有的key.......");
        List<String> list = new ArrayList<String>();
        Map<String, Map<String, String>> items = memCachedClient.statsItems();
        for (Iterator<String> itemIt = items.keySet().iterator(); itemIt.hasNext(); ) {
            String itemKey = itemIt.next();
            Map<String, String> maps = items.get(itemKey);
            for (Iterator<String> mapsIt = maps.keySet().iterator(); mapsIt.hasNext(); ) {
                String mapsKey = mapsIt.next();
                String mapsValue = maps.get(mapsKey);
                if (mapsKey.endsWith("number")) {  //memcached key 类型  item_str:integer:number_str
                    String[] arr = mapsKey.split(":");
                    int slabNumber = Integer.valueOf(arr[1].trim());
                    int limit = Integer.valueOf(mapsValue.trim());
                    Map<String, Map<String, String>> dumpMaps = memCachedClient.statsCacheDump(slabNumber, limit);
                    for (Iterator<String> dumpIt = dumpMaps.keySet().iterator(); dumpIt.hasNext(); ) {
                        String dumpKey = dumpIt.next();
                        Map<String, String> allMap = dumpMaps.get(dumpKey);
                        for (Iterator<String> allIt = allMap.keySet().iterator(); allIt.hasNext(); ) {
                            String allKey = allIt.next();
                            list.add(allKey.trim());

                        }
                    }
                }
            }
        }
        logger.info("获取没有挂掉服务器中所有的key完成.......");
        return list;
    }


    private static boolean strMatch(String key, String keyWords) {
        return key.matches("\\S+"+keyWords+"\\S+");

    }
}
