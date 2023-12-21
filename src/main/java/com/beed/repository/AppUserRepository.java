package com.beed.repository;

import com.beed.model.dto.AppUserDto;
import com.beed.model.entity.AppUser;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    @Query(value = "SELECT u.rate FROM AppUser u WHERE u.id = :id")
    Optional<Double> getRateById(@Param("id") Long id);

    AppUser findAppUserById(Long id);
    Optional<AppUser> findAppUserByUsername(String username);
    @Query(value = "UPDATE AppUser SET username = :username, name = :name, surname = :surname, rate = :rate \n"+
            ", mail = :mail, phone = :phone where id = :id", nativeQuery = true)
    void updateAppUser(@Param("username") String username,
                       @Param("name") String name,
                       @Param("surname") String surname,
                       @Param("rate") Double rate,
                       @Param("mail") String mail,
                       @Param("phone") String phone,
                       @Param("id") Long id);

    @Query(value = "UPDATE AppUser SET role = :role where id = :id", nativeQuery = true)
    void updateAppUser(@Param("role") String role,
                       @Param("id") Long id);

    @Query("SELECT u.id from AppUser u WHERE u.username = :username")
    Long getUserIdByUsername(@Param("username") String username);

    @Query("SELECT new com.beed.model.dto.AppUserDto(" +
            "a.id, a.username, a.name, a.surname, a.rate, a.mail, a.phone, a.profilePhotoUrl) " +
            "FROM AppUser a " +
            "WHERE a.username = :username")
    AppUserDto getAppUserDtoByUsername(@Param("username") String username);

    @Query("SELECT new com.beed.model.dto.AppUserDto(" +
            "a.id, a.username, a.name, a.surname, a.rate, a.mail, a.phone, a.profilePhotoUrl) " +
            "FROM AppUser a ")
    List<AppUserDto> getUsersInfos(Pageable pageable);
}
