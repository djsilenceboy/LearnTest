
package com.djs.learn.ctc.thread;

import java.io.Serializable;

/**
 * Request quit signal DTO.
 */
public class RequestQuitSignalDto implements Cloneable, Serializable
{
	private static final long serialVersionUID = 5715542007201784151L;

	/**
	 * Internal ID.
	 * <p>
	 * Unique.
	 */
	protected String internalId = null;

	/**
	 * Flag: request quit signal.
	 * <p>
	 * Default value is "false".<br>
	 * Getter/Setter are synchronized.
	 */
	protected boolean requestQuitSignal = false;

	@Override
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// This should never happen.
			throw new InternalError(e.toString());
		}
	}

	public String getInternalId(){
		return internalId;
	}

	public void setInternalId(String internalId){
		this.internalId = internalId;
	}

	public synchronized boolean isRequestQuitSignal(){
		return requestQuitSignal;
	}

	public synchronized void setRequestQuitSignal(boolean requestQuitSignal){
		this.requestQuitSignal = requestQuitSignal;
	}
}
