package com.org.learn.geode.client.web;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Mainclass {

	public static void main(String[] args) {
		Queue<String> q = new LinkedBlockingQueue<>();

		q.add("one");
		q.add("two");
		System.out.println(q.peek());
		System.out.println(q.isEmpty());
		System.out.println(q.peek());
		System.out.println(q.isEmpty());
	}

}
