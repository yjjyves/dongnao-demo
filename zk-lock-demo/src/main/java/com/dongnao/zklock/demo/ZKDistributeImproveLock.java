package com.dongnao.zklock.demo;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

public class ZKDistributeImproveLock implements Lock {

	// 创建一个zk客户端
	private ZkClient client = new ZkClient("localhost:2181");

	{
		client.setZkSerializer(new MyZkSerializer());
	}

	private static String LOCK_PATH = "/TTTTT";

	private ThreadLocal<String> currentPath = new ThreadLocal<>();

	private ThreadLocal<String> beforePath = new ThreadLocal<>();

	public ZKDistributeImproveLock() {
		super();
		// 创建父节点
		try {
			this.client.createPersistent(LOCK_PATH);
		} catch (Exception e) {
		}
	}

	/*
	 * 1、给自己注册一个临时顺序节点 2、获取所有的子节点，判断自己的那个节点是否是最小的 是，获到锁，
	 * 否，注册前一个节点的删除watcher,阻塞自己,
	 */
	@Override
	public boolean tryLock() {
		// 不能重复注册临时顺序节点
		if (currentPath.get() == null) {
			// 为自己创建一个临时顺序节点
			currentPath.set(this.client
					.createEphemeralSequential(LOCK_PATH + "/", "aaa"));
		}

		List<String> children = this.client.getChildren(LOCK_PATH);

		// 排序
		Collections.sort(children);
		if (currentPath.get().equals(LOCK_PATH + "/" + children.get(0))) {
			return true;
		}

		// 当前节点的索引号
		int cx = Collections.binarySearch(children,
				this.currentPath.get().substring(LOCK_PATH.length() + 1));

		// 得到前一个节点
		beforePath.set(LOCK_PATH + "/" + children.get(cx - 1));

		return false;
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
		this.client.subscribeDataChanges(this.beforePath.get(), listener);

		// 阻塞
		if (client.exists(this.beforePath.get())) {
			try {
				cdl.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 移除订阅
		this.client.unsubscribeDataChanges(this.beforePath.get(), listener);
	}

	@Override
	public void unlock() {

		// 删除节点
		this.client.delete(this.currentPath.get());

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
