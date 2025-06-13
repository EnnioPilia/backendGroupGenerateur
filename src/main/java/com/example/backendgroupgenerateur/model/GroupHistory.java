package com.example.backendgroupgenerateur.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "group_histories")
public class GroupHistory {

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
    @OneToMany(mappedBy = "groupHistory", cascade = CascadeType.ALL, orphanRemoval = true)
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
