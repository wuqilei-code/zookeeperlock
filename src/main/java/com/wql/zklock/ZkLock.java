package com.wql.zklock;

public interface ZkLock {
    //加锁
    public void getLock();
    //解除锁
    public void getUnLock();
}
