package com.Newnop.Book_Rental_Management_System.repository;

import com.Newnop.Book_Rental_Management_System.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
