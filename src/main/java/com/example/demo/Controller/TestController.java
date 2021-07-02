package com.example.demo.Controller;

import com.example.demo.model.NoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@RestController
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @GetMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> add(@RequestParam("firstNo") Integer firstNo, @RequestParam("secondNo") Integer secondNo) {
        Integer result = Math.addExact(firstNo, secondNo);
        NoModel noModel = new NoModel();
        noModel.setFirstNo(firstNo);
        noModel.setSecondNo(secondNo);
        noModel.setResult(result);
        noModel.setOperation("Add");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("firstNo", noModel.getFirstNo());
        mapSqlParameterSource.addValue("secondNo", noModel.getSecondNo());
        mapSqlParameterSource.addValue("result", noModel.getResult());
        mapSqlParameterSource.addValue("operation", noModel.getOperation());
        namedParameterJdbcTemplate.update("Insert into events(FIRSTNO, SECONDNO, RESULT, OPERATION) values (:firstNo, :secondNo, :result, :operation)", mapSqlParameterSource);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/subtract", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> subtract(@RequestParam("firstNo") Integer firstNo, @RequestParam("secondNo") Integer secondNo) {
        Integer result = Math.subtractExact(firstNo, secondNo);
        NoModel noModel = new NoModel();
        noModel.setFirstNo(firstNo);
        noModel.setSecondNo(secondNo);
        noModel.setResult(result);
        noModel.setOperation("Subtract");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("firstNo", noModel.getFirstNo());
        mapSqlParameterSource.addValue("secondNo", noModel.getSecondNo());
        mapSqlParameterSource.addValue("result", noModel.getResult());
        mapSqlParameterSource.addValue("operation", noModel.getOperation());
        namedParameterJdbcTemplate.update("Insert into events(FIRSTNO, SECONDNO, RESULT, OPERATION) values (:firstNo, :secondNo, :result, :operation)", mapSqlParameterSource);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/multiply", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> mutliply(@RequestParam("firstNo") Integer firstNo, @RequestParam("secondNo") Integer secondNo) {
        Integer result = Math.multiplyExact(firstNo, secondNo);
        NoModel noModel = new NoModel();
        noModel.setFirstNo(firstNo);
        noModel.setSecondNo(secondNo);
        noModel.setResult(result);
        noModel.setOperation("Multiply");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("firstNo", noModel.getFirstNo());
        mapSqlParameterSource.addValue("secondNo", noModel.getSecondNo());
        mapSqlParameterSource.addValue("result", noModel.getResult());
        mapSqlParameterSource.addValue("operation", noModel.getOperation());
        namedParameterJdbcTemplate.update("Insert into events(FIRSTNO, SECONDNO, RESULT, OPERATION) values (:firstNo, :secondNo, :result, :operation)", mapSqlParameterSource);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/divide", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> divide(@RequestParam("firstNo") Integer firstNo, @RequestParam("secondNo") Integer secondNo) {
        Integer result = Math.floorDiv(firstNo, secondNo);
        NoModel noModel = new NoModel();
        noModel.setFirstNo(firstNo);
        noModel.setSecondNo(secondNo);
        noModel.setResult(result);
        noModel.setOperation("Divide");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("firstNo", noModel.getFirstNo());
        mapSqlParameterSource.addValue("secondNo", noModel.getSecondNo());
        mapSqlParameterSource.addValue("result", noModel.getResult());
        mapSqlParameterSource.addValue("operation", noModel.getOperation());
        namedParameterJdbcTemplate.update("Insert into events(FIRSTNO, SECONDNO, RESULT, OPERATION) values (:firstNo, :secondNo, :result, :operation)", mapSqlParameterSource);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/results", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NoModel>> results() {
        List<NoModel> noModels = namedParameterJdbcTemplate.query("Select * from events", new ModelMapper());
        return ResponseEntity.status(HttpStatus.OK).body(noModels);
    }

    class ModelMapper implements RowMapper<NoModel> {

        @Override
        public NoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            NoModel model = new NoModel();
            model.setFirstNo(rs.getInt("FIRSTNO"));
            model.setSecondNo(rs.getInt("SECONDNO"));
            model.setResult(rs.getInt("RESULT"));
            model.setOperation(rs.getString("OPERATION"));
            return model;
        }

    }
}
