package com.parshuram.springmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parshuram.springmvc.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer>{

}
