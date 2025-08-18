package com.Newnop.Book_Rental_Management_System.entity;

import com.Newnop.Book_Rental_Management_System.entity.persisted_object.PersistedObject;
import com.Newnop.Book_Rental_Management_System.enums.AvailabilityStatus;
import com.Newnop.Book_Rental_Management_System.enums.BookQuality;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book extends PersistedObject {
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String summary;
    @Column(name = "book_code", unique = true, nullable = false)
    private String bookCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;
    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availabilityStatus;
    private String language;
    @Column(name = "published_year")
    private String publishedYear;
    @Column(name = "book_quality")
    @Enumerated(EnumType.STRING)
    private BookQuality bookQuality;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rental> rentals;
}
