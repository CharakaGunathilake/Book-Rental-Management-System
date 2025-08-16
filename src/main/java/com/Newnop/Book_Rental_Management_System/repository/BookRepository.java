package com.Newnop.Book_Rental_Management_System.repository;

import com.Newnop.Book_Rental_Management_System.entity.Book;
import com.Newnop.Book_Rental_Management_System.enums.AvailabilityStatus;
import com.Newnop.Book_Rental_Management_System.enums.BookQuality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleIgnoreCase(String bookName);

    List<Book> findAllByPublishedYear(Integer year);

    List<Book> findAllByBookQuality(BookQuality quality);

    List<Book> findAllByAvailabilityStatus(AvailabilityStatus status);

    List<Book> findAllByGenre_Id(Long genreId);

    List<Book> findAllByAuthor_Id(Long authorId);
}
