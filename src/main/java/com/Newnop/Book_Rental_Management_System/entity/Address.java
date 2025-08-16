package com.Newnop.Book_Rental_Management_System.entity;

import com.Newnop.Book_Rental_Management_System.entity.persisted_object.PersistedObject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
public class Address extends PersistedObject {
    @Column(name = "address_line_1")
    private String addressLine1;
    @Column(name = "address_line_2")
    private String addressLine2;
    @Column(name = "address_line_3")
    private String addressLine3;
    private String city;
    @Column(name = "postal_code")
    private String postalCode;
    @OneToOne(mappedBy = "address")
    private User user;
}
