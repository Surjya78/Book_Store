package com.bookStore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bookStore.controller.BookController;
import com.bookStore.controller.MyBookListController;
import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.repository.BookRepository;
import com.bookStore.repository.MyBookRepository;

@SpringBootTest
class BookStoreApplicationTests {

	@Autowired
	private BookController controller;
	
	@Autowired
	private MyBookListController controller2;
	
	@MockBean
	private BookRepository repository;
	
	@MockBean
	private MyBookRepository repository2;
	
	@Test
	public void addBookTest() {
		Book book = new Book(1,"Math","Aurobindo","450");
		when(repository.save(book)).thenReturn(book);
		assertEquals(book, controller.addBook(book));
	}
	
	@Test
	public void getMyBooksTest() {
		when(repository2.findAll()).thenReturn((List<MyBookList>) Stream.of(new MyBookList(10,"Math","Aurobindo","450"),
				new MyBookList(8,"Odia","Kumar","500")).collect(Collectors.toList()));
		assertEquals(2, controller2.getMyBooks().size());
		
	}

}
