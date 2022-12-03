package me.ronygomes.reference.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.ronygomes.reference.springboot.domain.Task;

@Repository
public interface TaskDao extends JpaRepository<Task, Integer> {
}
