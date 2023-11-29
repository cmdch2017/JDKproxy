package com.example.proxy.strong;

/**
 * 事务增强
 *
 * @author lst
 * @date 2023年11月29日 9:28
 */
public class DaoTransaction {
    public void startTransaction() {
        System.out.println("开启事务");
    }

    public void endTransaction() {
        System.out.println("关闭事务");
    }
}
