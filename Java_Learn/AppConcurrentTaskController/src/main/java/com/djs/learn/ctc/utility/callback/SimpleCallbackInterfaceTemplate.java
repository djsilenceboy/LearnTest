
package com.djs.learn.ctc.utility.callback;

/**
 * Simple callback interface template.
 */
public interface SimpleCallbackInterfaceTemplate<T, R>
{
	/**
	 * Invoke callback.
	 *
	 * @param param
	 *        T. Can be null, can be ignored.
	 * @return R - Can be null, can be ignored.
	 * @throws Exception
	 */
	public R invoke(T param) throws Exception;
}
