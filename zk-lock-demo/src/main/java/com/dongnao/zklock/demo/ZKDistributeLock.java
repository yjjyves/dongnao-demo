package com.dongnao.zklock.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

public class ZKDistributeLock implements Lock {

	// 创建一个zk客户端
	private ZkClient client = new ZkClient("localhost:2181");

	{
		client.setZkSerializer(new MyZkSerializer());
	}

	private static String LOCK_PATH = "/LOCK";

	/*
	 * 获取锁： 所有要同步的线程，都去创建同名的节点， 如果创建成功，表示获得锁， 如果创建不成功，则阻塞自己。
	 * 
	 * 释放锁： 删除创建的那个节点。
	 */
	@Override
	public boolean tryLock() {
		try {
			client.createPersistent(LOCK_PATH);
		} catch (ZkNodeExistsException e) {
			return false;
		}
		return true;
	}

	@Override
	public void lock() {
		if (!tryLock()) {
			waitForLock();
			lock();
		}
	}

	private void waitForLock() {
		// 阻塞自己
		CountDownLatch cdl = new CountDownLatch(1);

		// 注册一个watcher
		IZkDataListener listener = new IZkDataListener() {

			@Override
			public void handleDataChange(String dataPath, Object data)
					throws Exception {
				// TODO Auto-generated method stub

			}

			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
				// 收到了节点被删除的通知，就应该唤醒等待的线程
				cdl.countDown();
				System.out.println("--------------收到删除通知 ——————————————————");
			}

		};

		// 订阅
		this.client.subscribeDataChanges(LOCK_PATH, listener);

		// 阻塞
		if (client.exists(LOCK_PATH)) {
			try {
				cdl.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 移除订阅
		this.client.unsubscribeDataChanges(LOCK_PATH, listener);
	}

	@Override
	public void unlock() {

		// 删除节点
		this.client.delete(LOCK_PATH);

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
