
package com.djs.learn.javalang.locale;

import java.util.ListResourceBundle;

public class NumberBundle_fr_FR extends ListResourceBundle
{
	@Override
	public Object[][] getContents(){
		return contents;
	}

	private Object[][] contents = {{"A", new Integer(45600)}, {"B", new Float(0.456)}};
}
