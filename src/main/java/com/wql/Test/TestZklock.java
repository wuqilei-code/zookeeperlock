package com.wql.Test;

import com.wql.util.NumCreate;
import com.wql.zklock.ZkLock;
import com.wql.zklock.ZkLockSon;

public class TestZklock {

    public static void main(String[] args) {
        for (int i = 1;i<21;i++) {
            final  int temp = i;
            new Thread(() -> {

                try{
                new NumCreate().CreateNum();

                }catch (Exception e){

                }

            },String.valueOf(temp) ).start();


        }
    }
}
