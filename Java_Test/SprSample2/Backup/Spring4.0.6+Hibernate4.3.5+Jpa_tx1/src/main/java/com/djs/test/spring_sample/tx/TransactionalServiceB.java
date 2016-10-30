
package com.djs.learn.spring_sample.tx;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

public class TransactionalServiceB
{
	protected static final Logger log = Logger.getLogger( TransactionalServiceB.class );

	@Transactional("order")
	public void doOrder() throws Exception
	{
		log.debug( "Do order." );
	}

	@Transactional("account")
	public void doAccount() throws Exception
	{
		log.debug( "Do account." );
	}
}
