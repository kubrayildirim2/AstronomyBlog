package com.kubrabayrakci.AstronomyBlog.repository;

import com.kubrabayrakci.AstronomyBlog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUserName(String username);
}
