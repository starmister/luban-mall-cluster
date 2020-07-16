package com.luban.mall.component.zklock;

public interface ZKLock {

    boolean lock(String lockpath);

    boolean unlock(String lockpath);

}
