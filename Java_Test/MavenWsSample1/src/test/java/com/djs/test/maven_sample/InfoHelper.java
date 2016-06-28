
package com.djs.test.maven_sample;

import java.util.Iterator;

import javax.xml.soap.SOAPElement;

import org.apache.log4j.Logger;
import org.csapi.schema.parlayx.common.v2_1.PolicyException;
import org.csapi.schema.parlayx.common.v2_1.ServiceException;

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
	 *        Object.
	 */
	public static void showException( Logger log, String paddingSpace, Object t )
	{
		int i;

		log.error( paddingSpace + "failed: class: " + t.getClass().getName() );

		if (t instanceof ServiceException)
		{
			String [] vars = ((ServiceException)t).getVariables().toArray( new String [0] );

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
			String [] vars = ((PolicyException)t).getVariables().toArray( new String [0] );

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
		else if (t instanceof Throwable)
		{
			log.error( paddingSpace + "failed: message: " + ((Throwable)t).getMessage() );

			if (((Throwable)t).getCause() != null)
			{
				showException( log, paddingSpace + "  ", ((Throwable)t).getCause() );
			}
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
