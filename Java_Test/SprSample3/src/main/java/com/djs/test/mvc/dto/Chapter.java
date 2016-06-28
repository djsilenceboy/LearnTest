
package com.djs.test.mvc.dto;

import org.apache.log4j.Logger;

public class Chapter
{
	private int number;
	private Title title;
	private String content;

	private final Logger log = Logger.getLogger(Chapter.class);

	public Chapter(){
	}

	public Chapter(int number, Title title, String content){
		this.number = number;
		this.title = title;
		this.content = content;

		log.info("():Number = " + number);
		log.info("():Title = " + title);
		log.info("():Content = " + content);
	}

	public int getNumber(){
		return number;
	}

	public void setNumber(int number){
		this.number = number;

		log.info("Number = " + number);
	}

	public Title getTitle(){
		return title;
	}

	public void setTitle(Title title){
		this.title = title;

		log.info("Title = " + title);
	}

	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content = content;

		log.info("Content = " + content);
	}

	@Override
	public String toString(){
		return "Chapter [number=" + number + ", title=" + title + ", content=" + content + "]";
	}
}
