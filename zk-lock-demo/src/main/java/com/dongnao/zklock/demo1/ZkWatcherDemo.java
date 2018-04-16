package com.dongnao.zklock.demo1;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

public class ZkWatcherDemo {

	public static void main(String[] args) {
		ZkClient client = new ZkClient("localhost:2181");
		client.setZkSerializer(new MyZkSerializer());

		client.subscribeDataChanges("/mike/c1", new IZkDataListener() {

			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
				System.out.println("-----监听到节点被删除");
			}

			@Override
			public void handleDataChange(String dataPath, Object data)
					throws Exception {
				System.out.println("---监听到数据变为：" + data);
			}
		});

		try {
			Thread.sleep(2 * 60 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
