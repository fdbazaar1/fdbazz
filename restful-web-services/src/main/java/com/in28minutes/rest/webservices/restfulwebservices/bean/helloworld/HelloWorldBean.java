package com.in28minutes.rest.webservices.restfulwebservices.bean.helloworld;

public class HelloWorldBean {

	private String msg;

	@Override
	public String toString() {
		return "HelloWorldBean [msg=" + msg + "]";
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public HelloWorldBean() {
	}

	public HelloWorldBean(String message) {

		msg = message;
	}

}
