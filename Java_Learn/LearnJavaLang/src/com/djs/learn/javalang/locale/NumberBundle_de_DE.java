
package com.djs.learn.javalang.locale;

import java.util.ListResourceBundle;

public class NumberBundle_de_DE extends ListResourceBundle
{
	@Override
	public Object[][] getContents(){
		return contents;
	}

	static final Object[][] contents = {{"A", new Integer(78900)}, {"B", new Float(0.789)}};
}
