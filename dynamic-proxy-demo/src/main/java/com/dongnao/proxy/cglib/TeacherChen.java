package com.dongnao.proxy.cglib;

public class TeacherChen {

	public boolean dating(char cup) {
		if (cup == 'E') {
			System.out.println("这个女老板品德不错，可以约");
			return true;
		} else {
			System.out.println("这个女老板品德不性，不可以约");
			return false;
		}
	}

	public void show() {
		System.out.println("进入拍摄状态.......");
	}
}
