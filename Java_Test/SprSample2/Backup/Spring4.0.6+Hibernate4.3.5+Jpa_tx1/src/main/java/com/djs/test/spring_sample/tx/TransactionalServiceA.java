
package com.djs.learn.spring_sample.tx;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TransactionalServiceA
{
	protected static final Logger log = Logger.getLogger( TransactionalServiceA.class );

	public void doOrder() throws Exception
	{
		log.debug( "Do order." );
	}

	public void doAccount() throws Exception
	{
		log.debug( "Do account." );
	}
}
