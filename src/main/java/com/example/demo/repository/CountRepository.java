package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Count;

public interface CountRepository extends JpaRepository<Count, Integer> {
}
