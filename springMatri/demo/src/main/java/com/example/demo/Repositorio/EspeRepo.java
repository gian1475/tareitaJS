package com.example.demo.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Espe;

public interface EspeRepo extends JpaRepository<Espe, String> {
    
}
