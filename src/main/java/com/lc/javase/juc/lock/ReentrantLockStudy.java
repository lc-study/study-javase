package com.lc.javase.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantLockStudy {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock(false);
        ReadWriteLock rwLock = new ReentrantReadWriteLock(false);
        Condition condition1 = lock.newCondition();
        lock.lock();
        try {
            lock.tryLock(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            try {
                condition1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        } finally {
            lock.unlock();
        }
        condition1.signal();
        Condition condition2 = lock.newCondition();
        try {
            condition2.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        condition2.signal();
    }
}