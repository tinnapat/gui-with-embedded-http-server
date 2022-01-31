package tinnapat.demo.gui;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Used to communicate between HTTP end point and UI
 */
public class CommandQueue {
	
	private static Queue<String> queue = new ArrayBlockingQueue<String>(10);
	
	public static void add(String s) {
		queue.add(s);
	}
	
	public static String poll() {
		return queue.poll();
	}
	
	public static boolean isEmpty() {
		return queue.isEmpty();
	}

}
