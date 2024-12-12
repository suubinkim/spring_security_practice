package com.practice.repository;

import com.practice.entity.Refresh;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <pre>
 * com.practice.repository
 *   |_ RefreshRepository
 * </pre>
 *
 * @Author : subin1
 * @Date : 12/12/24 1:48 PM
 * @description : RefreshRepository
 */
public interface RefreshRepository extends JpaRepository<Refresh, Long> {

    Boolean existsByRefresh(String refresh);

    @Transactional
    void deleteByRefresh(String refresh);
}
