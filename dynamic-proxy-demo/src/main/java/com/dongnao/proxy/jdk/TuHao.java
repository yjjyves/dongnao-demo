package com.dongnao.proxy.jdk;

public class TuHao {

	private float length;

	public TuHao(float length) {
		super();
		this.length = length;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public void dating(Girl g) {
		g.dating(length);
	}

}
