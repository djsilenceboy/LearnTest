
package com.djs.learn.ctc.utility.callback;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Simple callback group helper template.
 */
public class SimpleCallbackGroupHelperTemplate<T, R> implements SimpleCallbackGroupInterfaceTemplate<T, R>
{
	private static final Logger log = LogManager.getLogger(SimpleCallbackGroupHelperTemplate.class);

	/**
	 * SimpleCallbackInterface set.
	 * <p>
	 * The set can be empty. Means no any callback.
	 */
	protected Set<SimpleCallbackInterfaceTemplate<T, R>> callbacks = new LinkedHashSet<SimpleCallbackInterfaceTemplate<T, R>>();
	/**
	 * A most recent copy of T.
	 * <p>
	 * Initially, it is null, means no such copy yet.<br>
	 * It is a copy of object's address, not object's clone.
	 */
	protected T mostRecentCopy = null;

	/**
	 * Constructor.
	 */
	public SimpleCallbackGroupHelperTemplate(){
		if (log.isTraceEnabled()) {
			log.trace("Enter...");
		}
	}

	public synchronized Set<SimpleCallbackInterfaceTemplate<T, R>> getCallbacks(){
		return callbacks;
	}

	public synchronized void clearCallbacks(){
		if (log.isDebugEnabled()) {
			log.debug("Clear set.");
		}

		callbacks.clear();
	}

	public synchronized void registerCallback(SimpleCallbackInterfaceTemplate<T, R> callback, boolean refreshWithMostRecentCopy) throws Exception{
		if (callback != null) {
			callbacks.add(callback);

			if (log.isDebugEnabled()) {
				log.debug("Register callback (+>" + callbacks.size() + ") = " + callback);
			}

			// If there is a most recent copy, and new callback needs it.
			if (refreshWithMostRecentCopy && (mostRecentCopy != null)) {
				if (log.isDebugEnabled()) {
					log.debug("Invoke with most recent copy.");
				}

				callback.invoke(mostRecentCopy);
			}
		}
	}

	public synchronized void unregisterCallback(SimpleCallbackInterfaceTemplate<T, R> callback) throws Exception{
		if (callback != null) {
			callbacks.remove(callback);

			if (log.isDebugEnabled()) {
				log.debug("Unregister callback (->" + callbacks.size() + ") = " + callback);
			}
		}
	}

	public synchronized List<R> invoke(T param) throws Exception{
		List<R> outputParams = null;

		// Keep a copy of address.
		mostRecentCopy = param;

		// Call each of callbacks.
		if (!callbacks.isEmpty()) {
			outputParams = new LinkedList<R>();
			int i = 0;

			for (SimpleCallbackInterfaceTemplate<T, R> callback : callbacks) {
				if (log.isDebugEnabled()) {
					log.debug("Invoke callback [" + i + "] = " + callback);
				}

				// There would be Exception.
				R outputParam = callback.invoke(param);
				outputParams.add(outputParam);

				i++;
			}
		}

		return outputParams;
	}
}
