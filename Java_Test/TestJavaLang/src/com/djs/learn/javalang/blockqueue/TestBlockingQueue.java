
package com.djs.learn.javalang.blockqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TestBlockingQueue
{
	static class Product
	{
		private int productId;
		private int sellerId;

		public int getProductId(){
			return productId;
		}

		public void setProductId(int productId){
			this.productId = productId;
		}

		public int getSellerId(){
			return sellerId;
		}

		public void setSellerId(int sellerId){
			this.sellerId = sellerId;
		}

		@Override
		public String toString(){
			return "Product " + productId + " (Seller " + sellerId + ")";
		}
	}

	static class Seller implements Runnable
	{
		private int id;
		private BlockingQueue<Product> queue;
		private String logId;

		public Seller(int id, BlockingQueue<Product> queue){
			this.id = id;
			this.queue = queue;

			logId = "Seller <" + id + "> ";
		}

		public void run(){
			System.out.println(logId + "Start.");
			int productId = 0;

			while (true) {
				Product product = new Product();

				product.setProductId(productId++);
				product.setSellerId(id);

				System.out.println(logId + product);

				queue.add(product);

				try {
					Thread.sleep(20);
				} catch (Exception e) {
				}
			}
		}
	}

	static class Buyer implements Runnable
	{
		private int id;
		private BlockingQueue<Product> queue;
		private String logId;

		public Buyer(int id, BlockingQueue<Product> queue){
			this.id = id;
			this.queue = queue;

			logId = "Buyer <" + id + "> ";
		}

		public void run(){
			System.out.println(logId + "Start.");

			while (true) {
				try {
					Product product = queue.take();

					System.out.println(logId + product);

					Thread.sleep(20);
				} catch (Exception e) {
				}
			}
		}
	}

	public static void main(String[] args){
		BlockingQueue<Product> queue = new LinkedBlockingQueue<Product>();

		for (int i = 1; i <= 3; i++) {
			Thread thread = new Thread(new Buyer(i, queue));
			thread.setDaemon(true);
			thread.start();
		}

		for (int i = 1; i <= 2; i++) {
			Thread thread = new Thread(new Seller(i, queue));
			thread.setDaemon(true);
			thread.start();
		}

		try {
			Thread.sleep(100);
		} catch (Exception e) {
		}
	}
}
