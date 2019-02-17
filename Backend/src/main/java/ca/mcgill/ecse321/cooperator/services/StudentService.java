package ca.mcgill.ecse321.cooperator.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.model.CooperatorManager;
import ca.mcgill.ecse321.cooperator.model.Student;

@Service
public class StudentService {

	 @Autowired
	    StudentRepository studentRepository;
	 
	    public Student createStudent(CooperatorManager sys) {
	    	if(sys == null)
	            throw new IllegalArgumentException("Cannot add a student with empty system");
	        Student student = new Student();
	        student.setCooperatorManager(sys);
	        studentRepository.save(student);
	        return student;
	    }

	 	@Transactional
	 	public Student getStudent(int id) {
	 		Student student = studentRepository.findStudentByStudentID(id);
	 		return student;
	 	}
	 	
	    @Transactional
	    public List<Student> getAllStudents(){
	        return (List<Student>)studentRepository.findAll();
	    }

}
