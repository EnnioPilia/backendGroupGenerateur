package com.example.backendgroupgenerateur.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backendgroupgenerateur.model.GroupHistory;

public interface GroupHistoryRepository extends JpaRepository<GroupHistory, Long> {
    List<GroupHistory> findByPersonListId(Long personListId);
}
