package com.ws.repository;

import com.ws.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

   UserEntity findByUserAndPasswordAndStatusIsTrue(String username, String password);


   Optional<UserEntity> findByUserAndEmployee_Headquarters_Company_Id(String user,Long id);

}
