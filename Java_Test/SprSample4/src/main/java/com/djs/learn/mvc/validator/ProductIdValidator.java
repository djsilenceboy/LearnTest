
package com.djs.learn.mvc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.djs.learn.mvc.domain.Product;
import com.djs.learn.mvc.exception.ProductNotFoundException;
import com.djs.learn.mvc.service.ProductService;

// The real validator class for ProductId annotation for customized JSR-303/Bean Validation.
// Used for ProductId field of Product class.
// Test: http://localhost:8080/SprSample4/market/products/add

public class ProductIdValidator implements ConstraintValidator<ProductId, String>
{
	private static final Logger logger = Logger.getLogger(ProductIdValidator.class);

	@Autowired
	private ProductService productService;

	public void initialize(ProductId constraintAnnotation){
		//  intentionally left blank; this is the place to initialize the constraint annotation for any sensible default values.
	}

	public boolean isValid(String value, ConstraintValidatorContext context){
		logger.info("[isValid]");

		// It is tried to add a new product.
		// So, it is invalid if found existing one.

		boolean isValid = true;
		Product product;

		logger.info("[isValid] value = " + value);

		try {
			product = productService.getProductById(value);

			if (product != null) {
				isValid = false;
			}
		} catch (ProductNotFoundException e) {
		}

		logger.info("[isValid] isValid = " + isValid);

		return isValid;
	}
}
