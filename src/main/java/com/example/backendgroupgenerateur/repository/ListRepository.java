package com.example.backendgroupgenerateur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backendgroupgenerateur.model.PersonList;

@Repository
public interface ListRepository extends JpaRepository<PersonList, Long> {
    // Ajoute ici des méthodes personnalisées si besoin
}
