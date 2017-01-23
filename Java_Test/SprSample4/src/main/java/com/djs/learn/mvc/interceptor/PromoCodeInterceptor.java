
package com.djs.learn.mvc.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// HandlerInterceptorAdapter is a simple implementation of HandlerInterceptor.
public class PromoCodeInterceptor extends HandlerInterceptorAdapter
{
	private static final Logger logger = Logger.getLogger(PromoCodeInterceptor.class);

	private String promoCode;
	private String errorRedirect;
	private String offerRedirect;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException{
		logger.info(this.getClass().getName() + ":preHandle");

		String givenPromoCode = request.getParameter("promo");

		if (promoCode.equals(givenPromoCode)) {
			response.sendRedirect(request.getContextPath() + "/" + offerRedirect);
		} else {
			response.sendRedirect(errorRedirect);
		}

		return false;
	}

	public void setPromoCode(String promoCode){
		this.promoCode = promoCode;
	}

	public void setErrorRedirect(String errorRedirect){
		this.errorRedirect = errorRedirect;
	}

	public void setOfferRedirect(String offerRedirect){
		this.offerRedirect = offerRedirect;
	}
}
