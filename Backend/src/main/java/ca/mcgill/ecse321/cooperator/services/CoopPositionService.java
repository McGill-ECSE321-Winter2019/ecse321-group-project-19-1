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

    @Transactional
    public List<CoopPosition> getCoopPositionsByStatus(Status status) {
        List<CoopPosition> coopPositionsByStatus = new ArrayList<>();
        for (CoopPosition cp : coopPositionRepository.findCoopPositionByStatus(status)) {
            coopPositionsByStatus.add(cp);
        }
        return coopPositionsByStatus;
    }

    @Transactional
    public CoopPosition getById(Integer id) {
        return coopPositionRepository.findByCoopId(id);
    }

    @Transactional
    public List<CoopPosition> getCoopPositionsByStudent(Student student) {
        List<CoopPosition> coopPositionsByStudent = new ArrayList<>();
        for (CoopPosition cp : coopPositionRepository.findCoopPositionByStudent(student)) {
            coopPositionsByStudent.add(cp);
        }
        return coopPositionsByStudent;
    }

    @Transactional
    public List<CoopPosition> getCoopPositionsByTerm(String term) {
        List<CoopPosition> coopPositionsByTerm = new ArrayList<>();
        for (CoopPosition cp : coopPositionRepository.findCoopPositionByTerm(term)) {
            coopPositionsByTerm.add(cp);
        }
        return coopPositionsByTerm;
    }

    @Transactional
    public List<CoopPosition> getCoopPositionsByTermInstructor(TermInstructor termInstructor) {
        List<CoopPosition> coopPositionsByTermInstructor = new ArrayList<>();
        for (CoopPosition cp : coopPositionRepository.findCoopPositionByTermInstructor(termInstructor)) {
            coopPositionsByTermInstructor.add(cp);
        }
        return coopPositionsByTermInstructor;
    }

    @Transactional
    public List<CoopPosition> getAllCoopPositions() {
        return (List<CoopPosition>) coopPositionRepository.findAll();
    }

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

    @Transactional
    public CoopPosition setCoopPostionStatus(CoopPosition cp, Status status) {
        cp.setStatus(status);
        coopPositionRepository.save(cp);
        return cp;
    }

    @Transactional
    public boolean deleteCoopPosition(int cpId) {
        CoopPosition cp = coopPositionRepository.findByCoopId(cpId);
        if (cp == null) {
            throw new NullPointerException("No such coop position.");
        }
        coopPositionRepository.deleteById(cpId);
        return true;
    }
    
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
