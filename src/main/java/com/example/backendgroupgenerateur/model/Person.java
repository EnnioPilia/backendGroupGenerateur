package com.example.backendgroupgenerateur.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false)
    private String genre; // "masculin", "féminin", "ne se prononce pas"

    @Column(nullable = false)
    private int aisanceFrancais; // 1 à 4

    @Column(nullable = false)
    private boolean ancienDWWM;

    @Column(nullable = false)
    private int niveauTechnique; // 1 à 4

    @Column(nullable = false)
    private String profil; // "timide", "réservé", "à l’aise"

    @Column(nullable = false)
    private int age;

    @ManyToOne
    @JoinColumn(name = "person_list_id", nullable = false)
    private PersonList personList;

    public Person() {}

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAisanceFrancais() {
        return aisanceFrancais;
    }

    public void setAisanceFrancais(int aisanceFrancais) {
        this.aisanceFrancais = aisanceFrancais;
    }

    public boolean isAncienDWWM() {
        return ancienDWWM;
    }

    public void setAncienDWWM(boolean ancienDWWM) {
        this.ancienDWWM = ancienDWWM;
    }

    public int getNiveauTechnique() {
        return niveauTechnique;
    }

    public void setNiveauTechnique(int niveauTechnique) {
        this.niveauTechnique = niveauTechnique;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PersonList getPersonList() {
        return personList;
    }

    public void setPersonList(PersonList personList) {
        this.personList = personList;
    }

    @Override
    public String toString() {
        return "Person{" +
            "id=" + id +
            ", nom='" + nom + '\'' +
            ", genre='" + genre + '\'' +
            ", aisanceFrancais=" + aisanceFrancais +
            ", ancienDWWM=" + ancienDWWM +
            ", niveauTechnique=" + niveauTechnique +
            ", profil='" + profil + '\'' +
            ", age=" + age +
            '}';
    }
}
