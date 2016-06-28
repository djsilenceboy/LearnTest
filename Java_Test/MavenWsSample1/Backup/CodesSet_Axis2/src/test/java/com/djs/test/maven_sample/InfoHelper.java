
package com.djs.test.maven_sample;

import java.util.Iterator;

import javax.xml.soap.SOAPElement;

import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;

/**
 * Exception info.
 * <p>
 * Update log: (date / author : comments)
 * <ul>
 * <li>2008-08-20 / Du Jiang : Creation
 * <li>2010-04-28 / Du Jiang : Impl.
 * </ul>
 */
public class InfoHelper
{
	/**
	 * Show exception.
	 * 
	 * @param log
	 *        Logger.
	 * @param paddingSpace
	 *        String.
	 * @param t
	 *        Throwable.
	 */
	public static void showException( Logger log, String paddingSpace, Throwable t )
	{
		int i;

		log.error( paddingSpace + "failed: class: " + t.getClass().getName() );

		/*
		if (t instanceof ServiceException)
		{
			String [] vars = ((ServiceException)t).getVariables();

			log.error( paddingSpace + "failed: message id: " + ((ServiceException)t).getMessageId() );
			log.error( paddingSpace + "failed: text      : " + ((ServiceException)t).getText() );

			if (vars != null)
			{
				for (i = 0; i < vars.length; i++)
				{
					log.error( paddingSpace + "failed: vars [" + i + "]  : " + vars[i] );
				}
			}
		}
		else if (t instanceof PolicyException)
		{
			String [] vars = ((PolicyException)t).getVariables();

			log.error( paddingSpace + "failed: message id: " + ((PolicyException)t).getMessageId() );
			log.error( paddingSpace + "failed: text      : " + ((PolicyException)t).getText() );

			if (vars != null)
			{
				for (i = 0; i < vars.length; i++)
				{
					log.error( paddingSpace + "failed: vars [" + i + "]  : " + vars[i] );
				}
			}
		}
		*/
		if (t instanceof AxisFault)
		{
			log.error( paddingSpace + "failed: fault code: " + ((AxisFault)t).getFaultCode() );
			log.error( paddingSpace + "failed: fault string: " + ((AxisFault)t).getReason() );
		}
		else
		{
			log.error( paddingSpace + "failed: message: " + t.getMessage() );
		}

		if (t.getCause() != null)
		{
			showException( log, paddingSpace + "  ", t.getCause() );
		}
	}

	/**
	 * Show SOAPElement contents.
	 * 
	 * @param log
	 *        Logger.
	 * @param paddingSpace
	 *        String.
	 * @param elem
	 *        SOAPElement.
	 */
	public static void showSoapElement( Logger log, String paddingSpace, SOAPElement elem )
	{
		// log.debug( paddingSpace + "Node name : " + elem.getNodeName() );
		// log.debug( paddingSpace + "Local name: " + elem.getLocalName() );
		// log.debug( paddingSpace + "Value     : " + elem.getValue() );

		log.debug( paddingSpace + " <" + elem.getNodeName() + "> = " + elem.getValue() );

		for (Iterator item = elem.getChildElements(); item.hasNext();)
		{
			Object obj = item.next();

			if (obj instanceof SOAPElement)
			{
				showSoapElement( log, paddingSpace + "-", (SOAPElement)obj );
			}
		}
	}
}
