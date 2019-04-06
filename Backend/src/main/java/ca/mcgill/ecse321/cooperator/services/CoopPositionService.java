package ca.mcgill.ecse321.cooperator.services;

import ca.mcgill.ecse321.cooperator.Utilities;
import ca.mcgill.ecse321.cooperator.dao.CoopPositionRepository;
import ca.mcgill.ecse321.cooperator.dao.RequiredDocumentRepository;
import ca.mcgill.ecse321.cooperator.dao.StudentRepository;
import ca.mcgill.ecse321.cooperator.model.*;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CoopPositionService {
    private boolean EXTRACT_DATA = false;
    @Autowired
    CoopPositionRepository coopPositionRepository;

    @Autowired
    RequiredDocumentRepository requiredDocumentRepository;

    @Autowired
    StudentRepository studentRepository;


    /**
     * Create a new coop position in the database.
     *
     * @param startDate   The start date of the coop.
     * @param endDate     The end date of the coop.
     * @param description Textual description of the coop position
     * @param term        As string representing the term when the coop is happening.
     * @param studentId   The id of the student participating in this coop
     * @return A CoopPositionDto representing the newly added coop
     */

    @Transactional
    public CoopPosition createCoopPosition(Date startDate, Date endDate, String description, String location, String term, Student student) {
        if (!Utilities.checkNotEmpty(description))
            throw new IllegalArgumentException("Cannot add a coop position with empty description");
        if (!Utilities.checkNotEmpty(location))
            throw new IllegalArgumentException("Cannot add a coop position with empty location");
        if (!Utilities.checkNotEmpty(term))
            throw new IllegalArgumentException("Cannot add a coop position with empty term");
        if (startDate == null)
            throw new IllegalArgumentException("Cannot add a coop position with empty startDate");
        if (endDate == null)
            throw new IllegalArgumentException("Cannot add a coop position with empty endDate");
        CoopPosition cp = new CoopPosition();
        cp.setDescription(description);
        cp.setLocation(location);
        cp.setStartDate(startDate);
        cp.setEndDate(endDate);
        cp.setTerm(term);
        cp.setStudent(student);
        coopPositionRepository.save(cp);
        return cp;
    }


     /**
     * Get all coop positions with a specific status
     *
     * @param status presenting the status of the coop to find
     * @return A list of coop positions with the specific status
     */


    @Transactional
    public List<CoopPosition> getCoopPositionsByStatus(Status status) {
        List<CoopPosition> coopPositionsByStatus = new ArrayList<>();
        for (CoopPosition cp : coopPositionRepository.findCoopPositionByStatus(status)) {
            coopPositionsByStatus.add(cp);
        }
        return coopPositionsByStatus;
    }

    /**
     * Get coop positions with a specific id
     *
     * @param id of a coop
     * @return A coop position with the specific id
     */

    @Transactional
    public CoopPosition getById(Integer id) {
        return coopPositionRepository.findByCoopId(id);
    }

    /**
     * get coop position assigned to a student
     * 
     * @param student assigned to a coop
     * @return A coop position assigned to this student
     */
    @Transactional
    public List<CoopPosition> getCoopPositionsByStudent(Student student) {
        List<CoopPosition> coopPositionsByStudent = new ArrayList<>();
        for (CoopPosition cp : coopPositionRepository.findCoopPositionByStudent(student)) {
            coopPositionsByStudent.add(cp);
        }
        return coopPositionsByStudent;
    }

    /**
     * Get coop position by term
     * 
     * @param term to filter coop position with
     * @return a list of coop positions assigned to this term
     */

    @Transactional
    public List<CoopPosition> getCoopPositionsByTerm(String term) {
        List<CoopPosition> coopPositionsByTerm = new ArrayList<>();
        for (CoopPosition cp : coopPositionRepository.findCoopPositionByTerm(term)) {
            coopPositionsByTerm.add(cp);
        }
        return coopPositionsByTerm;
    }

    /**
     * Get coop position by term instructor
     * 
     * @param termInstructor assigned to a coop position
     * @return a list of coop positions assigned to this term instructor
     */
    @Transactional
    public List<CoopPosition> getCoopPositionsByTermInstructor(TermInstructor termInstructor) {
        List<CoopPosition> coopPositionsByTermInstructor = new ArrayList<>();
        for (CoopPosition cp : coopPositionRepository.findCoopPositionByTermInstructor(termInstructor)) {
            coopPositionsByTermInstructor.add(cp);
        }
        return coopPositionsByTermInstructor;
    }

    /**
     * Get all coop positions in the system.
     *
     * @return a list of CoopPositionDto representing all coop positions in the system.
     */

    @Transactional
    public List<CoopPosition> getAllCoopPositions() {
        return (List<CoopPosition>) coopPositionRepository.findAll();
    }

     /**
     * Add a required document to coop position
     * 
     * @param cpId the coop id to add the document to
     * @param rdId the the id of the document to add
     * 
     * @return A boolean signaling the success of the operation
     */

    @Transactional
    public boolean addRequiredDocumentToCoopPosition(int cpId, int rdId) {
        RequiredDocument rd = requiredDocumentRepository.findById(rdId);
        CoopPosition cp = coopPositionRepository.findByCoopId(cpId);
        if (rd == null || cp == null)
            return false;
        cp.addRequiredDocument(rd);
        coopPositionRepository.save(cp);
        return true;
    }

    /**
     * Set a coop position status
     * 
     * @param cp The coop position for the status change
     * @param status to set on the coop position
     * @return A coop position with the change status
     */

    @Transactional
    public CoopPosition setCoopPostionStatus(CoopPosition cp, Status status) {
        cp.setStatus(status);
        coopPositionRepository.save(cp);
        return cp;
    }

    /**
     * delete coop position with the specific id
     * 
     * @param id of the coop position
     * @return boolean signaling success of failure
     */

    @Transactional
    public boolean deleteCoopPosition(int cpId) {
        CoopPosition cp = coopPositionRepository.findByCoopId(cpId);
        if (cp == null) {
            throw new NullPointerException("No such coop position.");
        }
        coopPositionRepository.deleteById(cpId);
        return true;
    }
    
    /**
     * Add term instructor to a specific coop position
     * 
     * @param cp coop position to add the term instructor to
     * @param ti term instructor to add to the coop position
     */
    @Transactional
    public void addTermInstructor(CoopPosition cp, TermInstructor ti) {
    	if (ti == null) {
    		throw new NullPointerException("No such Term Instructor.");
    	}
    	if (cp == null) {
    		throw new NullPointerException("No such Coop Position.");
    	}
    	cp.addTermInstructor(ti);
    	coopPositionRepository.save(cp);
    }

}
