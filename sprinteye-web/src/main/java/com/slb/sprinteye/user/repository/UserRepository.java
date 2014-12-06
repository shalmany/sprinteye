package com.slb.sprinteye.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.slb.sprinteye.user.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);
    
    @Query("select count(u) from User u join u.roles r where u.id = :id and r.name  = (:role)")
    public int countByIdAndRolesLike(@Param("id") Long id,@Param("role")String role);
}
