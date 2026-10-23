package com.example.demo.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Matricula;

public interface  AluRepo extends JpaRepository<Matricula, String> {
    List<Matricula> findByCodcur(String cod);
}
