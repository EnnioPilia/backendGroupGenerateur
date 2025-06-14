package com.example.backendgroupgenerateur.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backendgroupgenerateur.model.GroupDraws;

public interface GroupDrawsRepository extends JpaRepository<GroupDraws, Long> {
    List<GroupDraws> findByPersonListId(Long personListId);
}
