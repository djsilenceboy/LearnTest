
package com.djs.learn.spring_sample.validation;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PersonValidator implements Validator
{
	private final Logger log = Logger.getLogger( PersonValidator.class );

	@Override
	public boolean supports( Class clazz )
	{
		return Person.class.equals( clazz );
	}

	@Override
	public void validate( Object obj, Errors e )
	{
		Person p = (Person)obj;

		if (log.isInfoEnabled())
		{
			log.info( "Name = " + p.getName() );
			log.info( "Age = " + p.getAge() );
			log.info( "Errors = " + e );
		}

		ValidationUtils.rejectIfEmpty( e, "name", "value.empty", "Name should not be null or empty." );

		if (p.getAge() < 0)
		{
			e.rejectValue( "age", "value.negative", "Age should be >= 0." );
		}
		else if (p.getAge() > 100)
		{
			e.rejectValue( "age", "value.too.large", "Age should be <= 100." );
		}
	}
}
