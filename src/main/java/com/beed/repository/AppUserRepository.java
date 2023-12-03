package com.beed.repository;

import com.beed.model.entity.AppUser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    @Query("SELECT u.rate FROM AppUser u WHERE u.id = :id")
    Optional<Double> getRateById(@Param("id") Long id);

    Optional<AppUser> findAppUserByUsername(String username);

}
