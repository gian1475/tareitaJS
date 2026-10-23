package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Repositorio.AluRepo;
import com.example.demo.Repositorio.CursoRepo;
import com.example.demo.Repositorio.EspeRepo;
import com.example.demo.modelo.Matricula;
import com.example.demo.servicio.ProceServicio;

@Controller

public class AluControl {
  @Autowired
  private AluRepo seralu;
  @Autowired
  private CursoRepo sercur;
  @Autowired
  private EspeRepo seresp;
  @Autowired
  private ProceServicio serpro;

  @GetMapping("/especial")
  public String mostrarpag(Model modelo, @RequestParam(value = "codesp", required = false) String codesp) {
    modelo.addAttribute("lisesp", seresp.findAll());

    if (codesp != null) {
      System.out.println("codigo seleccionado " + codesp);
      modelo.addAttribute("elegido", codesp);
      modelo.addAttribute("cursos", sercur.findByCodes(codesp));

    } else {
      modelo.addAttribute("elegido", "nada");
      modelo.addAttribute("cursos", List.of());
    }

    return "pagina1";
  }

  @GetMapping("/alumnos/{id}")
  public String mostrarAlumnos(@PathVariable("id") String codcur, Model modelo) {
    // Buscar alumnos por código de curso
    modelo.addAttribute("listaAlumnos", seralu.findByCodcur(codcur));
    // También puedes mandar el código para mostrarlo en la vista
    modelo.addAttribute("curso", sercur.findById(codcur).get());
    Matricula alumno = new Matricula();

    alumno.setCodcur(codcur);
    modelo.addAttribute("alumno", alumno);
    return "pagina2";
  }

  @PostMapping("/adiciona")
  public String ejecutarProcedimiento(@ModelAttribute Matricula alumno, Model modelo) {
    try {
      System.out.println("codigo de  curso " + alumno.getCodcur() + "    pagp   " + alumno.getPago());
      serpro.ejecutarSpadicion(alumno);
      modelo.addAttribute("mensaje", "Procedimiento ejecutado correctamente.");
    } catch (Exception e) {
      modelo.addAttribute("mensaje", "Error al ejecutar el procedimiento: " + e.getMessage());
    }
    return "redirect:/alumnos/" + alumno.getCodcur();
  }

  @GetMapping("/borra/{id}")
  public String borra(@PathVariable("id") String id) {

    // 1. Obtener el codcur del alumno antes de borrar
    String codcur = seralu.findById(id).get().getCodcur();

    // 2. Ejecutar el procedimiento para borrar
    serpro.ejecutaBorra(id);

    // 3. Redirigir a pagina2: /alumnos/{codcur}
    return "redirect:/alumnos/" + codcur;

  }

}
