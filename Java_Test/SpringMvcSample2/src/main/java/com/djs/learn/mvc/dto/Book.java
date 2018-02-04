
package com.djs.learn.mvc.dto;

import java.util.List;

import org.apache.log4j.Logger;

public class Book
{
	private int isbn;
	private String author;
	private Title title;
	private List<Chapter> chapters;

	private final Logger log = Logger.getLogger(Book.class);

	public Book(){
	}

	public Book(int isbn, String author, Title title, List<Chapter> chapters){
		this.isbn = isbn;
		this.author = author;
		this.title = title;
		this.chapters = chapters;

		log.info("():ISBN = " + isbn);
		log.info("():Author = " + author);
		log.info("():Title = " + title);
		log.info("():Chapters = " + chapters);
	}

	public int getIsbn(){
		return isbn;
	}

	public void setIsbn(int isbn){
		this.isbn = isbn;

		log.info("ISBN = " + isbn);
	}

	public String getAuthor(){
		return author;
	}

	public void setAuthor(String author){
		this.author = author;

		log.info("Author = " + author);
	}

	public Title getTitle(){
		return title;
	}

	public void setTitle(Title title){
		this.title = title;

		log.info("Title = " + title);
	}

	public List<Chapter> getChapters(){
		return chapters;
	}

	public void setChapters(List<Chapter> chapters){
		this.chapters = chapters;

		log.info("Chapters = " + chapters);
	}

	@Override
	public String toString(){
		return "Book [isbn=" + isbn + ", author=" + author + ", title=" + title + ", chapters=" + chapters + "]";
	}
}
