package ca.mcgill.ecse321.cooperator.services;

import ca.mcgill.ecse321.cooperator.dao.CoopPositionRepository;
import ca.mcgill.ecse321.cooperator.dao.RequiredDocumentRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.model.CoopPosition;
import ca.mcgill.ecse321.cooperator.model.RequiredDocument;
import ca.mcgill.ecse321.cooperator.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CoopPositionRepository coopPositionRepository;

    @Autowired
    RequiredDocumentRepository requiredDocumentRepository;

    @Transactional
    public Student createStudent(String firstName, String lastName) {
        Student student = new Student(firstName, lastName);
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
    public RequiredDocument submitRequiredDocumentToCoop(int studnetId, int coopId, int docId) {
        Student student = studentRepository.findById(studnetId);
        CoopPosition coop = coopPositionRepository.findByCoopId(coopId);
        RequiredDocument doc = requiredDocumentRepository.findById(docId);
        if (student == null || coop == null || doc == null) {
            System.err.println("Trying to submit a document(id= " + docId + ") to coop(id= " + coopId + ") by student(id= "
                    + studnetId + ") failed!");
            throw new NullPointerException("No such student or coop or doc.");
        }
        if (student.submitDocument(coop, doc)) {
            requiredDocumentRepository.save(doc);
            return doc;
        }
        throw new NullPointerException("Document not submitted.");
    }

    @Transactional
    public Student offerCoopPostionToStudent(int studentId, int cpId) {
        Student s = studentRepository.findById(studentId);
        CoopPosition cp = coopPositionRepository.findByCoopId(cpId);
        if (s == null || cp == null)
        	 throw new NullPointerException("No such student or coop.");
        s.offerCoopPostion(cp);
        studentRepository.save(s);
        return s;
    }
    
    @Transactional
    public void deleteStudent(int studentId) {
    	Student s = studentRepository.findById(studentId);
    	if(s==null) {
    		throw new NullPointerException("No such student.");
    	} 
    	studentRepository.deleteById(studentId);
    }
}
