package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repositorio.AluRepo;
import com.example.demo.Repositorio.CursoRepo;
import com.example.demo.Repositorio.EspeRepo;
import com.example.demo.modelo.Curso;
import com.example.demo.modelo.Espe;
import com.example.demo.modelo.Matricula;
import com.example.demo.servicio.ProceServicio;

@RestController
@RequestMapping("/api/v1")
public class AluApi {

    @Autowired
    private EspeRepo seresp;

    @Autowired
    private CursoRepo sercur;

    @Autowired
    private AluRepo seralu;

    @Autowired
    private ProceServicio serpro;

    // --------------------------------------------
    // 1. LISTA DE ESPECIALIDADES
    // --------------------------------------------
    @GetMapping("/especialidades")
    public List<Espe> listarEspecialidades() {
        return seresp.findAll();
    }

    // --------------------------------------------
    // 2. LISTA DE CURSOS POR ESPECIALIDAD
    // --------------------------------------------
    @GetMapping("/cursos/{codesp}")
    public List<Curso> listarCursos(@PathVariable String codesp) {
        return sercur.findByCodes(codesp);
    }

    // --------------------------------------------
    // 3. LISTA DE ALUMNOS POR CURSO
    // --------------------------------------------
    @GetMapping("/alumnos/{codcur}")
    public List<Matricula> listarAlumnos(@PathVariable String codcur) {
        return seralu.findByCodcur(codcur);
        /*
         * return seralu.findAll()
         * .stream()
         * .filter(a -> a.getCodcur().trim().equalsIgnoreCase(codcur.trim()))
         * .toList();
         */
    }

    // --------------------------------------------
    // 4. ADICIONAR MATRICULA (EJECUTAR PROCEDIMIENTO)
    // --------------------------------------------
    @PostMapping("/adiciona")
    public Map<String, Object> adicionar(@RequestBody Matricula alumno) {

        Map<String, Object> resp = new HashMap<>();

        Curso curso = sercur.findById(alumno.getCodcur()).orElse(null);
        if (curso == null || curso.getNrovac() <= 0) {
            resp.put("ok", false);
            resp.put("mensaje", "El curso no tiene vacantes disponibles");
            return resp;
        }

        try {
            serpro.ejecutarSpadicion(alumno);
            resp.put("ok", true);
            resp.put("mensaje", "Matrícula ingresada correctamente");
        } catch (Exception e) {
            resp.put("ok", false);
            resp.put("mensaje", "Error: " + e.getMessage());
        }

        return resp;
    }

    // --------------------------------------------
    // 5. BORRAR MATRICULA POR ID
    // --------------------------------------------
    @DeleteMapping("/borra/{id}")
    public Map<String, Object> borrar(@PathVariable String id) {

        Map<String, Object> resp = new HashMap<>();

        try {
            serpro.ejecutaBorra(id);
            resp.put("ok", true);
            resp.put("mensaje", "Alumno eliminado correctamente");
        } catch (Exception e) {
            resp.put("ok", false);
            resp.put("mensaje", "Error: " + e.getMessage());
        }

        return resp;
    }

    @GetMapping("/curso/{codcur}")
    public Curso getCurso(@PathVariable String codcur) {
        return sercur.findById(codcur).orElse(null);
    }

    @GetMapping("/siguiente")
    public Map<String, Object> siguienteNromat() {
        return Map.of("nromat", serpro.siguienteNromat());
    }

    // --------------------------------------------
    // 6. REPORTE: CANTIDAD DE ALUMNOS POR CURSO
    // --------------------------------------------
    @GetMapping("/reporte/{codesp}")
    public List<Map<String, Object>> reporte(@PathVariable String codesp) {
        return serpro.reportePorEspecialidad(codesp);
    }

    // --------------------------------------------
    // 7. MANTENIMIENTO DE CURSO
    // --------------------------------------------
    @GetMapping("/curso")
    public List<Curso> listarTodosCursos() {
        return sercur.findAll();
    }

    @PostMapping("/curso")
    public Map<String, Object> crearCurso(@RequestBody Curso curso) {
        Map<String, Object> resp = new HashMap<>();
        if (sercur.existsById(curso.getCodcur())) {
            resp.put("ok", false);
            resp.put("mensaje", "Ya existe un curso con código " + curso.getCodcur());
            return resp;
        }
        try {
            sercur.save(curso);
            resp.put("ok", true);
            resp.put("mensaje", "Curso registrado correctamente");
        } catch (DataIntegrityViolationException e) {
            // ej: nombre de mas de 15 caracteres
            resp.put("ok", false);
            resp.put("mensaje", "Error: " + e.getMostSpecificCause().getMessage());
        }
        return resp;
    }

    @PutMapping("/curso/{codcur}")
    public Map<String, Object> editarCurso(@PathVariable String codcur, @RequestBody Curso curso) {
        Map<String, Object> resp = new HashMap<>();
        if (!sercur.existsById(codcur)) {
            resp.put("ok", false);
            resp.put("mensaje", "No existe el curso " + codcur);
            return resp;
        }
        curso.setCodcur(codcur);
        try {
            sercur.save(curso);
            resp.put("ok", true);
            resp.put("mensaje", "Curso actualizado correctamente");
        } catch (DataIntegrityViolationException e) {
            resp.put("ok", false);
            resp.put("mensaje", "Error: " + e.getMostSpecificCause().getMessage());
        }
        return resp;
    }

    @DeleteMapping("/curso/{codcur}")
    public Map<String, Object> borrarCurso(@PathVariable String codcur) {
        Map<String, Object> resp = new HashMap<>();
        if (!seralu.findByCodcur(codcur).isEmpty()) {
            resp.put("ok", false);
            resp.put("mensaje", "No se puede eliminar: el curso tiene alumnos matriculados");
            return resp;
        }
        sercur.deleteById(codcur);
        resp.put("ok", true);
        resp.put("mensaje", "Curso eliminado correctamente");
        return resp;
    }
}
