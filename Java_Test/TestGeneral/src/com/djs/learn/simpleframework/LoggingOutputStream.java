
package com.djs.learn.simpleframework;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Logging wrapper.
 * <p>
 * Update log: (date / author : comments)
 * <ul>
 * <li>2008-04-02 / Du Jiang : Creation
 * </ul>
 * 
 * @author Du Jiang
 * @version 1.0.0.0
 */
public final class LoggingOutputStream extends OutputStream
{
	private Logger log = null;
	private StringBuffer szBuffer = null;

	/**
	 * @param log
	 *        Logger.
	 */
	public LoggingOutputStream(Logger log){
		this.log = log;
	}

	/**
	 * Writes the specified byte as a character.
	 * 
	 * @param b
	 *        The byte.
	 */
	@Override
	public void write(int b) throws IOException{
		char ch = (char)b;

		if (szBuffer == null) {
			szBuffer = new StringBuffer();
		}

		szBuffer.append(ch);

		if (String.valueOf(ch).equals("\r")) {
			log.log(Level.INFO, szBuffer.toString());

			szBuffer = null;
		}
	}
}
