package ca.mcgill.ecse321.cooperator.services;

import ca.mcgill.ecse321.cooperator.dao.CoopPositionRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.model.CoopPosition;
import ca.mcgill.ecse321.cooperator.model.RequiredDocument;
import ca.mcgill.ecse321.cooperator.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CoopPositionRepository coopPositionRepository;

    public Student createStudent(String firstName,String lastName) {
        Student student = new Student(firstName,lastName);
        studentRepository.save(student);
        return student;
    }

    @Transactional
    public Student getStudentById(int id) {
        Student student = studentRepository.findById(id);
        return student;
    }

    @Transactional
    public List<Student> getAllStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    @Transactional
    public List<Student> getAllProblematicStudents() {
        List<Student> possiblyProblematic = studentRepository.findStudentByProblematic(true);
        return possiblyProblematic;
    }

    @Transactional
    public Boolean offerCoopPostionToStudent(int studentId, int cpId) {
        Student s = studentRepository.findById(studentId);
        CoopPosition cp = coopPositionRepository.findByCoopId(cpId);
        if (s == null || cp == null)
            return false;
        s.offerCoopPostion(cp);
        studentRepository.save(s);
        return true;
    }
}
