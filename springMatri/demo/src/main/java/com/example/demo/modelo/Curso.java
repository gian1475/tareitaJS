package com.example.demo.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="curso")
public class Curso {
    @Id
    @Column(name="codcur")
    String codcur;
    @Column(name="codesp")
    String codes;
    @Column(name="nomcur")
    String nomcur;
    @Column(name="costo")
    double costo;
    @Column(name="nrovac")
    int nrovac;

    public String getCodcur() {
        return codcur;
    }

    public void setCodcur(String codcur) {
        this.codcur = codcur;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getNomcur() {
        return nomcur;
    }

    public void setNomcur(String nomcur) {
        this.nomcur = nomcur;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getNrovac() {
        return nrovac;
    }

    public void setNrovac(int nrovac) {
        this.nrovac = nrovac;
    }
    
    
  
}
