package com.beed.repository;

import com.beed.model.dto.AppUserDto;
import com.beed.model.entity.AppUser;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    @Query(value = "SELECT u.rate FROM AppUser u WHERE u.id = :id")
    Optional<Double> getRateById(@Param("id") Long id);

    AppUser findAppUserById(Long id);
    Optional<AppUser> findAppUserByUsername(String username);
    @Modifying
    @Query(value = "UPDATE AppUser u SET  u.username = :username, u.name = :name," +
            " u.surname = :surname, u.mail = :mail, u.phone = :phone" +
            " where u.id = :id")
    void updateAppUser(@Param("name") String name,
                       @Param("surname") String surname,
                       @Param("mail") String mail,
                       @Param("phone") String phone,
                       @Param("username") String username,
                       @Param("id") Long id);
    @Modifying
    @Query(value = "UPDATE AppUser u SET  u.profilePhotoUrl = :photo" +
            " where u.id = :id")
    void updateAppUserPhoto(@Param("photo") String photo,
                            @Param("id") Long id);
    @Modifying
    @Query(value = "UPDATE AppUser u SET  u.rate = :rate" +
            " where u.id = :id")
    void updateAppUserRate(@Param("rate") Double rate,
                           @Param("id") Long id);

    @Query(value = "UPDATE AppUser SET role = :role where id = :id", nativeQuery = true)
    void updateAppUser(@Param("role") String role,
                       @Param("id") Long id);

    @Query("SELECT u.id from AppUser u WHERE u.username = :username")
    Long getUserIdByUsername(@Param("username") String username);

    @Query("SELECT u.deviceToken from AppUser u WHERE u.id = :id")
    String getUserDeviceTokenById(@Param("id") Long id);


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
