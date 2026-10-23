package com.example.demo.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "alumno")
public class Matricula {
    @Id
    @Column(name = "nromat")
    String nromat;
    @Column(name = "codcur")
    String codcur;
    @Column(name = "apenom")
    String apenom;
    @Column(name = "tipo")
    String tipo;
    @Column(name = "pago")
    double pago;

    @Transient
    public String getCtipo() {
        if (tipo == null)
            return "regular";
        String cad = "";
        if (tipo.equals("1"))
            cad = "1/2 Beca";
        else if (tipo.equals("2"))
            cad = "1/4 Beca";
        else
            cad = "Regular";
        return cad;

    }

    public String getNromat() {
        return nromat;
    }

    public void setNromat(String nromat) {
        this.nromat = nromat;
    }

    public String getCodcur() {
        return codcur;
    }

    public void setCodcur(String codcur) {
        this.codcur = codcur;
    }

    public String getApenom() {
        return apenom;
    }

    public void setApenom(String apenom) {
        this.apenom = apenom;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }

}
