
package com.djs.learn.ctc.utility.callback;

import java.util.List;
import java.util.Set;

/**
 * Simple callback group interface template.
 */
public interface SimpleCallbackGroupInterfaceTemplate<T, R>
{
	public Set<SimpleCallbackInterfaceTemplate<T, R>> getCallbacks();

	/**
	 * Clear all callbacks from set.
	 */
	public void clearCallbacks();

	/**
	 * Register callback handle.
	 * <p>
	 * If already exist, no effect.
	 *
	 * @param callback
	 *        CallbackInterface. If null, ignored.
	 * @param refreshWithMostRecentCopy
	 *        boolean. true = after register callback, immediately call back with most recent copy of T.<br>
	 *        Normally, set it as false.<br>
	 *        But it is better set as true, if callback is from reloading properties file only when file has been modified.
	 * @throws Exception
	 */
	public void registerCallback(SimpleCallbackInterfaceTemplate<T, R> callback, boolean refreshWithMostRecentCopy) throws Exception;

	/**
	 * Unregister callback handle.
	 * <p>
	 * If not exist, no effect.
	 *
	 * @param callback
	 *        CallbackInterface. If null, ignored.
	 * @throws Exception
	 */
	public void unregisterCallback(SimpleCallbackInterfaceTemplate<T, R> callback) throws Exception;

	/**
	 * Invoke callbacks.
	 *
	 * @param param
	 *        T. Can be null, can be ignored.
	 * @return List<R> - R from each of callbacks.<br>
	 *         In List order, suggest LinkedList.<br>
	 *         If List is null or empty, means no registered callback.<br>
	 *         If element of List is null, means no R from that callback.
	 * @throws Exception
	 */
	public List<R> invoke(T param) throws Exception;
}
