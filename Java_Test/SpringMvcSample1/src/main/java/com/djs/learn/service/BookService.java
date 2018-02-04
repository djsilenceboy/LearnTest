
package com.djs.learn.service;

import java.util.List;

import com.djs.learn.domain.Book;
import com.djs.learn.domain.Category;

public interface BookService
{
	List<Category> getAllCategories();

	Category getCategory(int id);

	List<Book> getAllBooks();

	Book save(Book book);

	Book update(Book book);

	Book get(long id);

	long getNextId();
}
