package com.example.backendgroupgenerateur.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.backendgroupgenerateur.model.Person;
import com.example.backendgroupgenerateur.model.PersonList;
import com.example.backendgroupgenerateur.service.PersonListService;
import com.example.backendgroupgenerateur.service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;
    private final PersonListService personListService;

    public PersonController(PersonService personService, PersonListService personListService) {
        this.personService = personService;
        this.personListService = personListService;
    }

    // 🔹 Ajouter une personne à une liste
    @PostMapping("/{personListId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@PathVariable Long personListId, @RequestBody Person person) {
        PersonList personList = personListService.findById(personListId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Liste non trouvée"));

        person.setPersonList(personList); // Associe la personne à la liste
        return personService.create(person);
    }

    // 🔹 Récupérer toutes les personnes d’une liste
    @GetMapping("/list/{personListId}")
    public List<Person> getPersonsByList(@PathVariable Long personListId) {
        return personService.findByPersonListId(personListId);
    }

    // 🔹 Supprimer une personne
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable Long id) {
        personService.delete(id);
    }
}
