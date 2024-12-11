package com.practice.repository;

import com.practice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <pre>
 * com.practice.repository
 *   |_ UserRepository
 * </pre>
 * 
 * @Author  : subin1
 * @Date    : 12/11/24 10:23 AM
 * @description : UserRepository
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    Boolean existsByUsername(String username);
}
