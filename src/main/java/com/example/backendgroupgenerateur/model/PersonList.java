package com.example.backendgroupgenerateur.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "person_lists")
public class PersonList {  // <== Changement de nom ici

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "personList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Person> personnes;

    // Constructeur vide
    public PersonList() {}

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<Person> getPersonnes() { return personnes; }
    public void setPersonnes(List<Person> personnes) { this.personnes = personnes; }
}
