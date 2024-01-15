package com.assignment.assignment.Repo;

import com.assignment.assignment.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
