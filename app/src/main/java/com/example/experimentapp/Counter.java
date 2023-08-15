package com.example.experimentapp;

import android.util.Log;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Counter {
    private int counter = 0;
   private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void increment() {
        try {
            readWriteLock.writeLock().lock();
            counter = counter + 1;
            Thread.sleep(1000);
            Log.d("Farman","Writing "+counter + " : "+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }


    public int getValue(){
        try {
            readWriteLock.readLock().lock();
            Thread.sleep(5000);
            return counter;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }


}
