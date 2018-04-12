package com.dongnao.proxy.jdk;

public class TeacherChen implements Boy {

	public boolean dating(char size) {
		if (size == 'E') {
			System.out.println("这个女老板品德不错，可以约！");
			return true;
		} else {
			System.out.println("这个女老板品德不行，不可以约！");
		}
		return false;
	}

	public void show() {
		System.out.println("进入拍摄状态..............");
	}

}
