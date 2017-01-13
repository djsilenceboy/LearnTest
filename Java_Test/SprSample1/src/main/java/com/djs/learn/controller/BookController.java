
package com.djs.learn.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.djs.learn.domain.Book;
import com.djs.learn.domain.Category;
import com.djs.learn.service.BookService;

@Controller
public class BookController
{
	@Autowired
	private BookService bookService;

	private static final Logger logger = Logger.getLogger(BookController.class);

	@RequestMapping(value = "/book_input")
	public String inputBook(Model model){
		List<Category> categories = bookService.getAllCategories();
		Book book = new Book();

		model.addAttribute("categories", categories);
		model.addAttribute("book", book);

		logger.info("inputBook: categories = " + categories);
		logger.info("inputBook: book = " + book);
		logger.info("inputBook: View = <BookAddForm>");

		return "BookAddForm";
	}

	@RequestMapping(value = "/book_edit/{id}")
	public String editBook(Model model, @PathVariable long id){
		List<Category> categories = bookService.getAllCategories();
		Book book = bookService.get(id);

		logger.info("editBook: id = " + id);
		logger.info("editBook: categories = " + categories);
		logger.info("editBook: book = " + book);
		logger.info("editBook: View = <BookEditForm>");

		model.addAttribute("categories", categories);
		model.addAttribute("book", book);

		return "BookEditForm";
	}

	@RequestMapping(value = "/book_save")
	public String saveBook(@ModelAttribute Book book){
		Category category = bookService.getCategory(book.getCategory().getId());

		logger.info("saveBook: book (before) = " + book);

		book.setCategory(category);
		bookService.save(book);

		logger.info("saveBook: book (after) = " + book);
		logger.info("saveBook: View = <redirect:/book_list>");

		return "redirect:/book_list";
	}

	@RequestMapping(value = "/book_update")
	public String updateBook(@ModelAttribute Book book){
		Category category = bookService.getCategory(book.getCategory().getId());

		logger.info("updateBook: book (before) = " + book);

		book.setCategory(category);
		bookService.update(book);

		logger.info("updateBook: book (after) = " + book);
		logger.info("updateBook: View = <redirect:/book_list>");

		return "redirect:/book_list";
	}

	@RequestMapping(value = "/book_list")
	public String listBooks(Model model){
		List<Book> books = bookService.getAllBooks();

		logger.info("listBooks: books = " + books);
		logger.info("listBooks: View = <BookList>");

		model.addAttribute("books", books);

		return "BookList";
	}
}
