package com.ssafy.harufilm.repository.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.harufilm.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserid(String userid);

    User findByUserpid(int userpid);
    
    List<User> findByUsernameContaining(String keyword);

}
