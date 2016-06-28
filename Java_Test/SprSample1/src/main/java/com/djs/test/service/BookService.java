
package com.djs.test.service;

import java.util.List;

import com.djs.test.domain.Book;
import com.djs.test.domain.Category;

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
