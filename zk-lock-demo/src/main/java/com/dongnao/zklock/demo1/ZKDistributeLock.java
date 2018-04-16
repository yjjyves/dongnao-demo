package com.dongnao.zklock.demo1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

public class ZKDistributeLock implements Lock {

	/*
	 * 利用zookeeper的同父子节点不可重名的特点来实现分布式锁
	 * 加锁：去创建指定名称的节点，如果能创建成功，则获得锁（加锁成功），如果节点已存在，就标识锁被别人获取了， 你就得阻塞，等待
	 * 释放锁：删除指定名称的节点
	 */
	private String LockPath;

	private ZkClient client;

	public ZKDistributeLock(String lockPath) {
		super();
		LockPath = lockPath;
		client = new ZkClient("localhost:2181");
		client.setZkSerializer(new MyZkSerializer());
	}

	@Override
	public boolean tryLock() {
		try {
			this.client.createPersistent(LockPath);
		} catch (ZkNodeExistsException e) {
			return false;
		}
		return true;
	}

	@Override
	public void lock() {
		if (!tryLock()) {
			// 阻塞等待
			waitForLock();
			// 再次尝试加锁
			lock();
		}
	}

	private void waitForLock() {
		// 怎么让自己阻塞
		CountDownLatch cdl = new CountDownLatch(1);

		// 注册watcher
		IZkDataListener listener = new IZkDataListener() {

			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
				System.out.println("-----监听到节点被删除");
				cdl.countDown();
			}

			@Override
			public void handleDataChange(String dataPath, Object data)
					throws Exception {

			}
		};

		client.subscribeDataChanges(LockPath, listener);

		if (this.client.exists(LockPath)) {
			try {
				cdl.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		client.unsubscribeDataChanges(LockPath, listener);
	}

	@Override
	public void unlock() {
		// 删除节点
		this.client.delete(this.LockPath);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean tryLock(long time, TimeUnit unit)
			throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

}
