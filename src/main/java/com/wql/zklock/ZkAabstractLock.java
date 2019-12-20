package com.wql.zklock;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

public abstract class ZkAabstractLock implements ZkLock {

    private final static String  zkServers="192.168.21.131:2181";

    private final static int connectionTimeout=45*1000;
    protected ZkClient client = new ZkClient(zkServers);
     public  String lockPath="/lockPath";
     public CountDownLatch count = null;

    @Override
    public void getLock() {

        //加锁的方法， 如果tryLock()返回true说明获取所成功
        if(tryLock()){
            System.out.println("成功获取锁");
        }else {
            //反之就说明已经被别人获取锁，进行等待
           waitLock();
           getLock();
        }
    }

    //模板设计模式，交由它的子类去进行实现
    public abstract void waitLock() ;


    public abstract  boolean tryLock();

    @Override
    public void getUnLock() {

        if(client != null){
            //关闭客户端，就会清楚掉zookeeper的临时节点
            client.close();
            System.out.println("成功释放锁！");
        }
    }
}
