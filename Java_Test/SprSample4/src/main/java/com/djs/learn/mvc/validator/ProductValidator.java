
package com.djs.learn.mvc.validator;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.djs.learn.mvc.domain.Product;

public class ProductValidator implements Validator
{
	private static final Logger logger = Logger.getLogger(ProductValidator.class);

	@Autowired
	private javax.validation.Validator beanValidator;

	private Set<Validator> springValidators;

	public ProductValidator(){
		logger.info("[ProductValidator]");

		springValidators = new HashSet<Validator>();
	}

	public void setSpringValidators(Set<Validator> springValidators){
		logger.info("[setSpringValidators]");

		this.springValidators = springValidators;
	}

	public boolean supports(Class<?> clazz){
		logger.info("[supports]");

		return Product.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors){
		logger.info("[validate]");

		Set<ConstraintViolation<Object>> constraintViolations = beanValidator.validate(target);

		for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
			String propertyPath = constraintViolation.getPropertyPath().toString();
			String message = constraintViolation.getMessage();
			errors.rejectValue(propertyPath, "", message);
		}

		for (Validator validator : springValidators) {
			validator.validate(target, errors);
		}
	}
}
