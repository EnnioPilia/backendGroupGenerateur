package com.example.backendgroupgenerateur.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.backendgroupgenerateur.model.PersonList;
import com.example.backendgroupgenerateur.repository.PersonListRepository;

@RestController
@RequestMapping("/lists")
public class ListController {

    private final PersonListRepository personListRepository;

    public ListController(PersonListRepository personListRepository) {
        this.personListRepository = personListRepository;
    }

    @GetMapping
    public List<PersonList> getAllLists() {
        return personListRepository.findAll();
    }

    @PostMapping
    public PersonList createList(@RequestBody PersonList list) {
        return personListRepository.save(list);
    }
}
