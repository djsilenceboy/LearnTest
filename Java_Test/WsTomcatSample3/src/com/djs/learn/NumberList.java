
package com.djs.learn;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "numberList")
public class NumberList
{
	@XmlElement(name = "numbers")
	private List<Long> numbers;

	public List<Long> getNumbers(){
		return numbers;
	}
}
