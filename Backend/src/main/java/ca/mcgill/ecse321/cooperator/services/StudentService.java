package ca.mcgill.ecse321.cooperator.services;

import ca.mcgill.ecse321.cooperator.Utilities;
import ca.mcgill.ecse321.cooperator.dao.CoopPositionRepository;
import ca.mcgill.ecse321.cooperator.dao.RequiredDocumentRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.model.CoopPosition;
import ca.mcgill.ecse321.cooperator.model.RequiredDocument;
import ca.mcgill.ecse321.cooperator.model.Student;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    private boolean EXTRACT_DATA = false;
    private String ALL_STUDENT_PATH = "/external/students";
    private int REMOTE_CALL_DELAY_MS = 3000;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CoopPositionRepository coopPositionRepository;

    @Autowired
    RequiredDocumentRepository requiredDocumentRepository;

    StudentService() {
        if (EXTRACT_DATA) {
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(REMOTE_CALL_DELAY_MS);
                        // Query remote thread
                        JSONArray jaResponse = Utilities.sendRequestArray("GET", Utilities.BASE_URL_STUDENTVIEW,
                                ALL_STUDENT_PATH);
                        System.out.println();
                        if (jaResponse != null) {
                            for (int i = 0; i < jaResponse.length(); i++) {
                                JSONObject obj = jaResponse.getJSONObject(i);
                                if (obj != null) {
                                    createStudent(obj.getInt("studentID"), obj.getString("firstName"),
                                            obj.getString("lastName"));
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Student Extractor thread failed");
                    }
                }
            }).start();
        }
    }

    /**
     * Create a new student in the system
     *
     * @param The       id of the new student
     * @param firstName the first name of the new student
     * @param lastName  the last name of the new student
     * @return a Student representing the new student
     */
    @Transactional
    public Student createStudent(int id, String firstName, String lastName) {
        Student student = new Student(firstName, lastName);
        studentRepository.save(student);
        student.setStudentID(id);
        studentRepository.save(student);
        return student;
    }

    /**
     * Create a new student in the system
     *
     * @param firstName the first name of the new student
     * @param lastName  the last name of the new student
     * @return a Student representing the new student
     */
    @Transactional
    public Student createStudent(String firstName, String lastName) {
        Student student = new Student(firstName, lastName);
        studentRepository.save(student);
        return student;
    }

    /**
     * Get one student of the system
     *
     * @param id of the student to find
     * @return one Studentrepresenting the specified student in the system
     */
    @Transactional
    public Student getStudentById(int id) {
        Student student = studentRepository.findById(id);
        return student;
    }

    /**
     * Get all students of the system
     * 
     * @return A list of all the Students in the system
     */
    @Transactional
    public List<Student> getAllStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    /**
     * Get all problematic students of the system
     * 
     * @return A list of all the problematic students in the system
     */
    @Transactional
    public List<Student> getAllProblematicStudents() {
        List<Student> possiblyProblematic = studentRepository.findStudentByProblematic(true);
        return possiblyProblematic;
    }

    /**
     * Submits a required document to a coop
     * 
     * @param studentId the id of the student
     * @param coopId    the id of the coop
     * @param docId     the id of the document to submit
     * @return The submitted required document
     */
    @Transactional
    public RequiredDocument submitRequiredDocumentToCoop(int studnetId, int coopId, int docId) {
        Student student = studentRepository.findById(studnetId);
        CoopPosition coop = coopPositionRepository.findByCoopId(coopId);
        RequiredDocument doc = requiredDocumentRepository.findById(docId);
        if (student == null || coop == null || doc == null) {
            System.err.println("Trying to submit a document(id= " + docId + ") to coop(id= " + coopId
                    + ") by student(id= " + studnetId + ") failed!");
            throw new NullPointerException("No such student or coop or doc.");
        }
        if (student.submitDocument(coop, doc)) {
            requiredDocumentRepository.save(doc);
            return doc;
        }
        throw new NullPointerException("Document not submitted.");
    }

    /**
     * Offer coop position to a student
     * 
     * @param studentId the id of the student
     * @param cpId      the id of the coop position
     * @return The student assigned to the coop position
     */
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

    /**
     * Deleting a student
     * 
     * @param studentId: student id
     * @return true = success
     */
    @Transactional
    public boolean deleteStudent(int studentId) {
        Student s = studentRepository.findById(studentId);
        if (s == null) {
            throw new NullPointerException("No such student.");
        }
        studentRepository.deleteById(studentId);
        return true;
    }
}
