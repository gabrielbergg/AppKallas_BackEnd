package com.example.Kallas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Kallas.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
