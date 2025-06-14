package com.example.backendgroupgenerateur.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backendgroupgenerateur.model.GroupDraws;
import com.example.backendgroupgenerateur.service.GroupDrawsService;

@RestController
@RequestMapping("/groupdraws")
public class GroupDrawsController {

    private final GroupDrawsService groupDrawsService;

    @Autowired
    public GroupDrawsController(GroupDrawsService groupDrawsService) {
        this.groupDrawsService = groupDrawsService;
    }

    // Créer un nouveau GroupDraws
    @PostMapping
    public ResponseEntity<GroupDraws> createGroupDraw(@RequestBody GroupDraws groupDraw) {
        GroupDraws savedGroupDraw = groupDrawsService.create(groupDraw);
        return ResponseEntity.ok(savedGroupDraw);
    }

    // Récupérer un GroupDraws par son ID
    @GetMapping("/{id}")
    public ResponseEntity<GroupDraws> getGroupDrawById(@PathVariable Long id) {
        Optional<GroupDraws> groupDrawOpt = groupDrawsService.findById(id);
        return groupDrawOpt
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Tu peux ajouter d'autres endpoints (update, delete, etc.) ici
}
