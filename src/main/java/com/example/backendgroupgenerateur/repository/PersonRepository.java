package com.example.backendgroupgenerateur.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backendgroupgenerateur.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByPersonListId(Long personListId);
}
