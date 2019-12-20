package com.wql.zklock;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

public class ZkLockSon extends ZkAabstractLock
{
    @Override
    public void waitLock() {

        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }
            //节点被删除
            @Override
            public void handleDataDeleted(String s) throws Exception {

                //如果节点被删除了，就让countdownlatch-1
                if(client != null){
                    //进行减一操作
                    count.countDown();
                }
            }
        };
        //监听事件通知
        client.subscribeDataChanges(lockPath,listener);
        //如果有这个节点
        if(client.exists(lockPath)){
            //进行等待
          count = new CountDownLatch(1);

                try {
                    count.await();
                    //当为0 的时候进行执行！在监听器里面进行这个判断
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
        //后面代码继续执行
        //为了不影响程序的执行 建议删除该事件监听 监听完了就删除掉
        client.unsubscribeDataChanges(lockPath,listener);
    }

    //进行加锁
    @Override
    public boolean tryLock(){
        try {
            //创建节点就返回true
            client.createEphemeral(lockPath);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
