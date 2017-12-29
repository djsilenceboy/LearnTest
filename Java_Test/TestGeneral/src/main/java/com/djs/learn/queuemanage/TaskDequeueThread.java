
package com.djs.learn.queuemanage;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * <b>Task dequeue thread</b>.
 * <p>
 * Update log: (date / author : comments)
 * <ul>
 * <li>2009-05-07 / Du Jiang : Creation
 * </ul>
 * 
 * @author Du Jiang
 * @version 1.0.0.0
 */
public class TaskDequeueThread extends Thread
{
	private static Logger log = Logger.getLogger(TaskDequeueThread.class);

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
	public TaskDequeueThread(TaskQueue<TaskInfo> tq){
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
			int iTotalTasks = 0;
			long lSleepInternal = 100;

			while (true) {
				TaskInfo ti = tq.removeTaskFromHead();

				if (ti != null) {
					if (log.isTraceEnabled()) {
						log.trace("Remove task ID = " + ti.getId());
					}

					iTotalTasks++;
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
