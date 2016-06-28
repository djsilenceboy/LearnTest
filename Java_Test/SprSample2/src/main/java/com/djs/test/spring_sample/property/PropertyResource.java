
package com.djs.test.spring_sample.property;

import org.springframework.core.io.Resource;

public class PropertyResource
{
	private Resource resourceText;
	private Resource resourceUrl;

	public Resource getResourceText()
	{
		return resourceText;
	}

	public void setResourceText( Resource resourceText )
	{
		this.resourceText = resourceText;
	}

	public Resource getResourceUrl()
	{
		return resourceUrl;
	}

	public void setResourceUrl( Resource resourceUrl )
	{
		this.resourceUrl = resourceUrl;
	}
}
