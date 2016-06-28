
package dj.test.commonlog.sub;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AnotherOne
{
	protected Log log = LogFactory.getLog(AnotherOne.class);

	// Called repeatedly to process a particular argument value
	// which you want logged if debugging is enabled
	public void process_debug(String value){
		// Do the string concatenation only if logging is enabled
		// if (log.isDebugEnabled())
		{
			log.debug("[AnotherOne_DEBUG] " + value);
		}
	}

	public void process_info(String value){
		// Do the string concatenation only if logging is enabled
		// if (log.isInfoEnabled())
		{
			log.info("[AnotherOne_INFO] " + value);
		}
	}
}
