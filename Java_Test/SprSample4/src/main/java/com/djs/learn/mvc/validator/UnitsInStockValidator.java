
package com.djs.learn.mvc.validator;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.djs.learn.mvc.domain.Product;

@Component
public class UnitsInStockValidator implements Validator
{
	private static final Logger logger = Logger.getLogger(UnitsInStockValidator.class);

	public boolean supports(Class<?> clazz){
		logger.info("[supports]");

		return Product.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors){
		logger.info("[validate]");

		Product product = (Product)target;

		if (product.getUnitPrice() != null && new BigDecimal(1000).compareTo(product.getUnitPrice()) <= 0 && product.getUnitsInStock() > 99) {
			errors.rejectValue("unitsInStock", "com.djs.learn.mvc.validator.UnitsInStockValidator.message");
		}
	}
}
