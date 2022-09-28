package com.example.demo.api.user;

import com.example.demo.api.Business.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepo extends JpaRepository<AppUser,Long> {

    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByUsername(String username);

    Optional<AppUser> findByPhoneNumber(String phoneNumber);
    Optional<List<AppUser>>findAllByRoles(Role role);
    Optional<List<AppUser>>findAllByBusiness(Business id);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a "+"SET a.enabled= TRUE WHERE a.email=?1")
    void enableAppUser(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a "+"SET a.business= :business, a.modifiedDate= :date WHERE a.username=:username")
    int userBusiness(@Param("username")String username, @Param("business") Business business, @Param("date") Date date);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a "+"SET a.roles= :role, a.modifiedDate= :date WHERE a.username=:username")
    int setUserRole(@Param("username")String username,@Param("role") Role role,@Param("date") Date date);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a "+"SET a.password= :password,a.modifiedDate= :date WHERE a.username=:username")
    int setNewPassword(@Param("username")String username,@Param("password")String password,@Param("date") Date date);


}
