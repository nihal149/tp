package com.example.student_management.controllers;

import com.example.student_management.entity.Student;
import com.example.student_management.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    // Constructeur pour l'injection de dépendances
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Méthode pour enregistrer un étudiant
    @Operation(summary = "Enregistrer un étudiant", description = "Enregistre un nouvel étudiant dans la base de données.")
    @PostMapping("/save")
    public ResponseEntity<Student> save(@RequestBody Student student) {
        Student savedStudent = studentService.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    // Méthode pour supprimer un étudiant par ID
    @Operation(summary = "Supprimer un étudiant", description = "Supprime un étudiant par son ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        boolean deleted = studentService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Méthode pour obtenir tous les étudiants
    @Operation(summary = "Obtenir tous les étudiants", description = "Récupère la liste de tous les étudiants enregistrés.")
    @GetMapping("/all")
    public ResponseEntity<List<Student>> findAll() {
        List<Student> students = studentService.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // Méthode pour compter le nombre total d'étudiants
    @Operation(summary = "Compter le nombre d'étudiants", description = "Récupère le nombre total d'étudiants dans la base de données.")
    @GetMapping("/count")
    public ResponseEntity<Long> countStudent() {
        long count = studentService.countStudents();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    // Méthode pour obtenir le nombre d'étudiants par année de naissance
    @Operation(summary = "Obtenir le nombre d'étudiants par année", description = "Récupère le nombre d'étudiants par année de naissance.")
    @GetMapping("/byYear")
    public ResponseEntity<Collection<?>> findByYear() {
        Collection<?> studentsByYear = studentService.findNbrStudentByYear();
        return new ResponseEntity<>(studentsByYear, HttpStatus.OK);
    }
}
