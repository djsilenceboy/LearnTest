
package com.djs.test.mvc.dto;

import org.apache.log4j.Logger;

public class Title
{
	private String titleValue;

	private final Logger log = Logger.getLogger(Title.class);

	public Title(){
	}

	public Title(String titleValue){
		this.titleValue = titleValue;

		log.info("():TitleValue = " + titleValue);
	}

	public String getTitleValue(){
		return titleValue;
	}

	public void setTitleValue(String titleValue){
		this.titleValue = titleValue;

		log.info("TitleValue = " + titleValue);
	}

	@Override
	public String toString(){
		return "Title [titleValue=" + titleValue + "]";
	}
}
