
package com.djs.learn.queuemanage;

import java.util.LinkedList;

/**
 * <b>Task queue</b>.
 * <p>
 * Update log: (date / author : comments)
 * <ul>
 * <li>2009-05-07 / Du Jiang : Creation
 * </ul>
 * 
 * @version 1.0.0.0
 */
public class TaskQueue<TaskType>
{
	/**
	 * Linked list as queue.
	 */
	private LinkedList<TaskType> taskQueue = new LinkedList<TaskType>();

	/**
	 * Add task to head of queue.
	 * 
	 * @param tt
	 *        TaskType.
	 */
	public void addTaskToHead(TaskType tt){
		synchronized (this) {
			if (tt != null) {
				taskQueue.addFirst(tt);
			}
		}
	}

	/**
	 * Add tasks to head of queue.
	 * 
	 * @param ttayTask
	 *        TaskType [].
	 */
	public void addTasksToHead(TaskType[] ttayTask){
		synchronized (this) {
			if ((ttayTask != null) && (ttayTask.length > 0)) {
				for (int i = 0; i < ttayTask.length; i++) {
					taskQueue.addFirst(ttayTask[i]);
				}
			}
		}
	}

	/**
	 * Add task to tail of queue.
	 * 
	 * @param tt
	 *        TaskType.
	 */
	public void addTaskToTail(TaskType tt){
		synchronized (this) {
			if (tt != null) {
				taskQueue.addLast(tt);
			}
		}
	}

	/**
	 * Add tasks to tail of queue.
	 * 
	 * @param ttayTask
	 *        TaskType [].
	 */
	public void addTasksToTail(TaskType[] ttayTask){
		synchronized (this) {
			if ((ttayTask != null) && (ttayTask.length > 0)) {
				for (int i = 0; i < ttayTask.length; i++) {
					taskQueue.addLast(ttayTask[i]);
				}
			}
		}
	}

	/**
	 * Remove task from head of queue.
	 * 
	 * @return TaskType
	 */
	public TaskType removeTaskFromHead(){
		synchronized (this) {
			if (taskQueue.isEmpty()) {
				return null;
			} else {
				return taskQueue.removeFirst();
			}
		}
	}

	/**
	 * Remove tasks from head of queue.
	 * 
	 * @param iTaskNumbers
	 *        Expected tasks number. But returned may be less than that.
	 * @return Object[] - TaskType [].
	 */
	public Object[] removeTasksFromHead(int iTasksNumber){
		synchronized (this) {
			if (taskQueue.isEmpty()) {
				return null;
			} else {
				if (iTasksNumber > taskQueue.size()) {
					iTasksNumber = taskQueue.size();
				}

				Object[] objayTask = new Object[iTasksNumber];

				for (int i = 0; i < objayTask.length; i++) {
					objayTask[i] = taskQueue.removeFirst();
				}

				return objayTask;
			}
		}
	}

	/**
	 * Remove task from tail of queue.
	 * 
	 * @return TaskType
	 */
	public TaskType removeTaskFromTail(){
		synchronized (this) {
			if (taskQueue.isEmpty()) {
				return null;
			} else {
				return taskQueue.removeLast();
			}
		}
	}

	/**
	 * Remove tasks from tail of queue.
	 * 
	 * @param iTaskNumbers
	 *        Expected tasks number. But returned may be less than that.
	 * @return Object[] - TaskType [].
	 */
	public Object[] removeTasksFromTail(int iTasksNumber){
		synchronized (this) {
			if (taskQueue.isEmpty()) {
				return null;
			} else {
				if (iTasksNumber > taskQueue.size()) {
					iTasksNumber = taskQueue.size();
				}

				Object[] objayTask = new Object[iTasksNumber];

				for (int i = 0; i < objayTask.length; i++) {
					objayTask[i] = taskQueue.removeLast();
				}

				return objayTask;
			}
		}
	}

	/**
	 * Check if queue is empty.
	 * 
	 * @return boolean
	 */
	public boolean isEmpty(){
		synchronized (this) {
			return taskQueue.isEmpty();
		}
	}

	/**
	 * Get size of queue.
	 * 
	 * @return int
	 */
	public int getSize(){
		synchronized (this) {
			return taskQueue.size();
		}
	}

	/**
	 * Clear queue.
	 * <p>
	 * Remove all tasks.
	 */
	public void clearQueue(){
		synchronized (this) {
			taskQueue.clear();
		}
	}
}
