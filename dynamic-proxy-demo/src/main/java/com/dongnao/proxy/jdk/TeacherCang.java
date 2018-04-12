package com.dongnao.proxy.jdk;

public class TeacherCang implements Girl {

	public boolean dating(float length) {
		if (length >= 1.7f) {
			System.out.println("身高可以，可以约！");
			return true;
		} else {
			System.out.println("身高不够，不可以约！");
		}
		return false;
	}

}
