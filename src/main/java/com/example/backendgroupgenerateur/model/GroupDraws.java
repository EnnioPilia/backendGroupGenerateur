package com.example.backendgroupgenerateur.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "group_Draws")
public class GroupDraws {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt = LocalDateTime.now();

    private boolean archived;

    private int numberOfGroups;

    // Many GroupHistory -> One PersonList
    @ManyToOne
    @JoinColumn(name = "person_list_id", nullable = false)
    private PersonList personList;

    // One GroupHistory -> Many Group
    @OneToMany(mappedBy = "groupDraws", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Group> groups;

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public boolean isArchived() {
        return archived;
    }
    public void setArchived(boolean archived) {
        this.archived = archived;
    }
    public int getNumberOfGroups() {
        return numberOfGroups;
    }
    public void setNumberOfGroups(int numberOfGroups) {
        this.numberOfGroups = numberOfGroups;
    }
    public PersonList getPersonList() {
        return personList;
    }
    public void setPersonList(PersonList personList) {
        this.personList = personList;
    }
    public List<Group> getGroups() {
        return groups;
    }
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
