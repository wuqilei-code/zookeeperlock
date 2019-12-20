package com.wql.util;

import com.wql.zklock.ZkLockSon;

public class NumCreate {

    private static int Num ;
    private ZkLockSon son = new ZkLockSon();

    //每次调用创建一个数返回
    public void CreateNum(){
       son.getLock();
        Num++;
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"------>"+Num);
        son.getUnLock();

    }

}
