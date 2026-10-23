package com.example.demo.servicio;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.Matricula;

@Service
public class ProceServicio {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void ejecutarSpadicion(Matricula alu) {
        String procedimiento = "CALL spadicion(?, ?, ?, ?)";

        jdbcTemplate.update(procedimiento, alu.getCodcur(), alu.getApenom(), alu.getTipo(), alu.getPago());
    }

    public void ejecutaBorra(String id) {
        String procedimiento = "CALL spanula(?)";

        jdbcTemplate.update(procedimiento, id);
    }

    // mismo calculo que usa spadicion para generar el codigo
    public String siguienteNromat() {
        return jdbcTemplate.queryForObject(
                "SELECT LPAD(IFNULL(MAX(nromat), 0) + 1, 6, '0') FROM alumno", String.class);
    }

    // cantidad de alumnos por curso de una especialidad
    public List<Map<String, Object>> reportePorEspecialidad(String codesp) {
        String sql = "SELECT c.codcur, c.nomcur, COUNT(a.nromat) AS cantidad "
                + "FROM curso c LEFT JOIN alumno a ON a.codcur = c.codcur "
                + "WHERE c.codesp = ? "
                + "GROUP BY c.codcur, c.nomcur ORDER BY c.codcur";

        List<Map<String, Object>> filas = jdbcTemplate.queryForList(sql, codesp);
        for (Map<String, Object> fila : filas) {
            long cantidad = ((Number) fila.get("cantidad")).longValue();
            fila.put("estado", cantidad > 5 ? "Iniciar" : "No inicia");
        }
        return filas;
    }

}
