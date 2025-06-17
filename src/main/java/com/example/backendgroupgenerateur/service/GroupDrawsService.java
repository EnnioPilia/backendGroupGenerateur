package com.example.backendgroupgenerateur.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backendgroupgenerateur.model.GroupDraws;
import com.example.backendgroupgenerateur.repository.GroupDrawsRepository;

@Service
public class GroupDrawsService {

    private final GroupDrawsRepository groupDrawsRepository;

    @Autowired
    public GroupDrawsService(GroupDrawsRepository groupDrawsRepository) {
        this.groupDrawsRepository = groupDrawsRepository;
    }

   public GroupDraws create(GroupDraws groupDraws) {
    if (groupDraws.getGroups() != null) {
        groupDraws.getGroups().forEach(group -> {
            group.setGroupDraws(groupDraws); // 🔥 Lien vers le parent
        });
    }
    return groupDrawsRepository.save(groupDraws); // Hibernate gère la cascade
}


    public Optional<GroupDraws> findById(Long id) {
        return groupDrawsRepository.findById(id);
    }

    
    public void deleteById(Long id) {
        groupDrawsRepository.deleteById(id);
    }
}
