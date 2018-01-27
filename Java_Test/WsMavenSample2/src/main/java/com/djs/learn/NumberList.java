
package com.djs.learn;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "numberList")
public class NumberList
{
	@XmlElement(name = "numbers")
	List<Long> numbers;

	public List<Long> getNumbers(){
		return numbers;
	}

	public void setNumbers(List<Long> numbers){
		this.numbers = numbers;
	}
}
