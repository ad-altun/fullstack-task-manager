package org.example.fullstacktodoapp.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdService {
        public String generateId() {
        return UUID.randomUUID().toString();
    }
}
