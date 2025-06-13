package com.example.backendgroupgenerateur.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backendgroupgenerateur.model.GroupHistory;
import com.example.backendgroupgenerateur.repository.GroupHistoryRepository;

@Service
public class GroupHistoryService {

    private final GroupHistoryRepository groupHistoryRepository;

    @Autowired
    public GroupHistoryService(GroupHistoryRepository groupHistoryRepository) {
        this.groupHistoryRepository = groupHistoryRepository;
    }

    public GroupHistory create(GroupHistory groupHistory) {
        return groupHistoryRepository.save(groupHistory);
    }

    public Optional<GroupHistory> findById(Long id) {
        return groupHistoryRepository.findById(id);
    }
}
