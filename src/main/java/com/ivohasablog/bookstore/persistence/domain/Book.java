package com.ivohasablog.bookstore.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Id;

/**
 * Created by Ivo on 18.11.2017 г..
 */
@Data
@NoArgsConstructor
@ToString()
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private String publishedDate;

    public Book(String title, String author, String publishedDate) {
        this.title = title;
        this.author = author;
        this.publishedDate = publishedDate;
    }
}
