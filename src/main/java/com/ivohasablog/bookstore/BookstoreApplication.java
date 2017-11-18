package com.ivohasablog.bookstore;

import com.ivohasablog.bookstore.persistence.domain.Book;
import com.ivohasablog.bookstore.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner{

	@Autowired
	private BookRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		repository.deleteAll();

		// save a couple of customers
		repository.save(new Book("It", "Stephen King", "September 15, 1986"));
		repository.save(new Book("Carrie", "Stephen King", "April 5, 1974"));
		repository.save(new Book("The Shining", "Stephen King", "January 28, 1977"));
		repository.save(new Book("The Green Mile", "Stephen King", "August 29, 1996"));
		repository.save(new Book("Clean Code", "Robert Cecil Martin", "July 17, 2008"));
		repository.save(new Book("The Clean Coder: A Code of Conduct for Professional Programmers", "Robert Cecil Martin", "May 23rd 2011"));
		repository.save(new Book("Clean Architecture: A Craftsman's Guide to Software Structure and Design", "Robert Cecil Martin", "September 10, 2017"));

		for(int i=0; i<10000; i++) {
			String title = "Book " + i;
			String author = "System";
			String date = "February 13, 2017";
			repository.save(new Book(title, author, date));
		}

		// fetch all customers
		System.out.println("Books found with findAll():");
		System.out.println("-------------------------------");
		for (Book book : repository.findAll()) {
			System.out.println(book.toString());
		}
		System.out.println();
	}
}
