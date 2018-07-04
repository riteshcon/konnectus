package com.authjwt.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.authjwt.user.ApplicationUser;
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);
}