package com.example.demo.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Curso;

public interface  CursoRepo extends JpaRepository<Curso, String> {
    List<Curso> findByCodes(String code);
}
