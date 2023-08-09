package com.capgemini.jingxi_demo.domain.repository;


import com.capgemini.jingxi_demo.domain.entity.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserItem, String> {
    @Query(value = "SELECT * FROM useritem", nativeQuery = true)
    List<UserItem> GetAllUserData();

    @Query(value = "select * from useritem where name = :name", nativeQuery = true)
    List<UserItem> GetUserByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query(value="update useritem set name = :name where id = :id", nativeQuery = true)
    void ModifyUserByidWithName(@Param("name") String name, @Param("id") int id);

}
