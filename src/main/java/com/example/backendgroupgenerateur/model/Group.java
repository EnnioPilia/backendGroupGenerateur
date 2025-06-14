package com.example.backendgroupgenerateur.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "`groups`")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Many Group -> One GroupHistory
    @ManyToOne
    @JoinColumn(name = "group_Draws_id", nullable = false)
    private GroupDraws groupDraws;

    // Many Group <-> Many Person via GroupPerson
    @ManyToMany
    @JoinTable(
        name = "group_person",
        joinColumns = @JoinColumn(name = "group_id"),
        inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> persons;

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public GroupDraws getGroupHistory() {
        return groupDraws;
    }
    public void setGroupHistory(GroupDraws groupHistory) {
        this.groupDraws = groupHistory;
    }
    public List<Person> getPersons() {
        return persons;
    }
    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
