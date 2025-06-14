package com.example.backendgroupgenerateur.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backendgroupgenerateur.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
List<Group> findByGroupDrawsId(Long groupDrawsId);
}
