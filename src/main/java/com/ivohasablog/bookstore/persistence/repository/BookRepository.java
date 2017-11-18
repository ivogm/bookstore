package com.ivohasablog.bookstore.persistence.repository;

import com.ivohasablog.bookstore.persistence.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Ivo on 18.11.2017 г..
 */
public interface BookRepository extends MongoRepository<Book, String> {
}
