package com.example.student_management.services;

import com.example.student_management.entity.Student;
import com.example.student_management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Méthode pour sauvegarder un étudiant
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    // Méthode pour supprimer un étudiant par ID
    public boolean delete(int id) {
        // Utilisation correcte d'Optional sans encapsulation redondante
        Optional<Student> studentOptional = Optional.ofNullable(studentRepository.findById(id));
        if (studentOptional.isPresent()) {
            studentRepository.delete(studentOptional.get());
            return true;
        } else {
            return false;
        }
    }

    // Méthode pour récupérer tous les étudiants
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    // Méthode pour compter le nombre d'étudiants
    public long countStudents() {
        return studentRepository.count(); // Pas besoin d'Optional ici
    }

    // Méthode pour récupérer le nombre d'étudiants par année de naissance
    public Collection<?> findNbrStudentByYear() {
        return studentRepository.findNbrStudentByYear();
    }
}
