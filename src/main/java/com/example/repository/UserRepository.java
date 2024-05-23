package com.example.repository;

import com.example.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserName (String username);

    @Transactional
    void deleteByUserName(String username);

    List<User> findByRoleDescriptionIgnoreCase(String description);

}
