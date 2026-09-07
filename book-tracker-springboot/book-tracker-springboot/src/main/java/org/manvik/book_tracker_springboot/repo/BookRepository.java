package org.manvik.book_tracker_springboot.repo;

import org.manvik.book_tracker_springboot.booktrackerspringboot.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {



}
