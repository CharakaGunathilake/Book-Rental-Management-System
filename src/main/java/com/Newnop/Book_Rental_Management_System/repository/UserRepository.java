package com.Newnop.Book_Rental_Management_System.repository;

import com.Newnop.Book_Rental_Management_System.entity.User;
import com.Newnop.Book_Rental_Management_System.enums.UserRole;
import com.Newnop.Book_Rental_Management_System.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findByUserStatus(UserStatus userStatus);

    List<User> findAllByUserRole(UserRole roleEnum);
}
