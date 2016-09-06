
package com.djs.learn.queuemanage;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * <b>Task enqueue thread</b>.
 * <p>
 * Update log: (date / author : comments)
 * <ul>
 * <li>2009-05-07 / Du Jiang : Creation
 * </ul>
 * 
 * @author Du Jiang
 * @version 1.0.0.0
 */
public class TaskEnqueueThread extends Thread
{
	private static Logger log = Logger.getLogger(TaskEnqueueThread.class);

	/**
	 * Task queue.
	 */
	private TaskQueue<TaskInfo> tq = null;

	/**
	 * Constructor.
	 * 
	 * @param tq
	 *        TaskQueue<TaskInfo>.
	 */
	public TaskEnqueueThread(TaskQueue<TaskInfo> tq){
		if (log.isTraceEnabled()) {
			log.trace("Enter...");
		}

		this.tq = tq;
	}

	@Override
	public void run(){
		if (log.isTraceEnabled()) {
			log.trace("Processing...");
		}

		try {
			long lSleepInternal = 1000;
			int iMaxQueue = 20;
			int iMinQueue = 10;
			int iIdCount = 0;

			while (true) {
				int iQueueSize = tq.getSize();

				if (iQueueSize < iMinQueue) {
					if (log.isTraceEnabled()) {
						log.trace("Queue size = " + iQueueSize);
					}

					int iRequiredTasks = iMaxQueue - iQueueSize;
					int i;

					TaskInfo[] tiayTask = new TaskInfo[iRequiredTasks];

					for (i = 0; i < iRequiredTasks; i++) {
						tiayTask[i] = new TaskInfo();
						tiayTask[i].setId(++iIdCount);

						if (log.isTraceEnabled()) {
							log.trace("Add task ID = " + tiayTask[i].getId());
						}
					}

					tq.addTasksToTail(tiayTask);
				}

				sleep(lSleepInternal);
			}
		} catch (Exception e) {
			if (log.isEnabledFor(Level.ERROR)) {
				log.error("Exception = " + e, e);
			}
		}
	}
}
