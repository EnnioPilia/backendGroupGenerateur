package com.example.backendgroupgenerateur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backendgroupgenerateur.model.PersonList;

@Repository
public interface PersonListRepository extends JpaRepository<PersonList, Long> {
    // Tu peux ajouter des méthodes spécifiques ici si besoin, par exemple :
    // List<PersonList> findByUserId(Long userId);
}
