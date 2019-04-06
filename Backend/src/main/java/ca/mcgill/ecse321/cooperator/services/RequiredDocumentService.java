package ca.mcgill.ecse321.cooperator.services;

import ca.mcgill.ecse321.cooperator.Utilities;
import ca.mcgill.ecse321.cooperator.dao.CoopPositionRepository;
import ca.mcgill.ecse321.cooperator.dao.RequiredDocumentRepository;
import ca.mcgill.ecse321.cooperator.model.*;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RequiredDocumentService {

    private enum RequiredDocumentType {
        REPORT, EMPLOYER_CONTRACT, FORM
    }

    @Autowired
    CoopPositionRepository coopPositionRepository;

    @Autowired
    RequiredDocumentRepository requiredDocumentRepository;

    /**
     * Create a new report in the system
     *
     * @param name    the name of the report
     * @param dueDate the date for which the report have to submitted before
     * @param cp      the coop position associated with this report
     * @param type    the type of the report
     * @return a Report representing the newly added Report
     */
    @Transactional
    public Report createReport(String name, Date dueDate, CoopPosition cp, ReportType type) {
        Report report = (Report) createRequiredDocument(name, dueDate, cp, RequiredDocumentType.REPORT);
        report.setReportType(type);
        return report;
    }

    /**
     * Create a new employer contract in the system
     *
     * @param name    the name of the employer contract
     * @param dueDate the date for which the employer contract have to submitted
     *                before
     * @param cpId    the Id of the coop position associated with this employer
     *                contract
     * @return a Employer contract representing the newly added employer contract
     * @throws IllegalArgumentException
     */
    @Transactional
    public EmployerContract createEmployerContract(String name, Date dueDate, CoopPosition cp, Employer em) {
        if (!Utilities.checkNotEmpty(name))
            throw new IllegalArgumentException("Cannot add a document with empty name.");

        EmployerContract rdoc = new EmployerContract();

        if (rdoc != null) {
            rdoc.setName(name);
            rdoc.setDueDate(dueDate);
            rdoc.setCoopPosition(cp);
            rdoc.setEmployer(em);
            rdoc.setSubmitted(false);
            rdoc.setAccepted(false);
            rdoc.setEvaluation("");
            requiredDocumentRepository.save(rdoc);
            return rdoc;
        }
        throw new IllegalArgumentException("[Internal error] Failed to create a new document.");
    }

    /**
     * Set the grade (accepted or not accepted) of a required document
     *
     * @param accepted indicating if the document is accepted or not by the
     *                 instructor
     * @param docId    the id of the graded document
     * @return the graded document, or null if the document is not found
     * @throws NullPointerException
     */

    @Transactional
    public RequiredDocument gradeDocument(int docId, boolean accepted) {
        RequiredDocument rdoc = requiredDocumentRepository.findById(docId);
        if (rdoc == null) {
            System.err.println("Document(id= " + docId + ") not found");
            throw new NullPointerException("Document not found");
        }
        rdoc.setAccepted(accepted);
        requiredDocumentRepository.save(rdoc);
        return rdoc;
    }

    /**
     * Create a new employerContract in the system
     *
     * @param name    the name of the employer contract
     * @param dueDate the date for which the employer contract have to submitted
     *                before
     * @param cpId    the Id of the coop position associated with this employer
     *                contract
     * @return a employer contract representing the newly added employer contract
     */

    @Transactional
    public EmployerContract createEmployerContract(String name, Date dueDate, CoopPosition cp) {
        return (EmployerContract) createRequiredDocument(name, dueDate, cp, RequiredDocumentType.EMPLOYER_CONTRACT);
    }

    /**
     * Create a new Form in the system
     *
     * @param name    the name of the Form
     * @param dueDate the date for which the Form have to submitted before
     * @param cp      the coop position associated with this Form
     * @return a Form representing the newly added Form
     */
    @Transactional
    public Form createForm(String name, Date dueDate, CoopPosition cp) {
        return (Form) createRequiredDocument(name, dueDate, cp, RequiredDocumentType.FORM);
    }

    /**
     * Get all the required documents associated to a coop position.
     *
     * @param cp the coop position we are targeting
     * @return a list of RequiredDocument representing the requested required
     *         documents.
     */
    @Transactional
    public List<RequiredDocument> getRequiredDocumentByCoopPosition(CoopPosition cp) {
        List<RequiredDocument> requiredDocumentByStudentAndCoopPosition = new ArrayList<>();
        for (RequiredDocument rdoc : requiredDocumentRepository.findRequiredDocumentByCoopPosition(cp)) {
            requiredDocumentByStudentAndCoopPosition.add(rdoc);
        }
        return requiredDocumentByStudentAndCoopPosition;
    }

    /**
     * Get all the required documents associated a due date.
     *
     * @param dueDate the due date we are targeting
     * @return a list of RequiredDocument representing the requested required
     *         documents.
     */
    @Transactional
    public List<RequiredDocument> getRequiredDocumentByDueDate(Date dueDate) {
        List<RequiredDocument> requiredDocumentByDueDate = new ArrayList<>();
        for (RequiredDocument rdoc : requiredDocumentRepository.findRequiredDocumentByDueDate(dueDate)) {
            requiredDocumentByDueDate.add(rdoc);
        }
        return requiredDocumentByDueDate;
    }

    /**
     * Get the required document associated with an id
     * 
     * @param id the id of the document
     * @return the document specific to an id
     */
    @Transactional
    public RequiredDocument getRequiredDocumentById(int id) {
        RequiredDocument rdoc = requiredDocumentRepository.findById(id);
        return rdoc;
    }

    /**
     * Set the grade (accepted or not accepted) of a required document
     *
     * @param answer indicating if the document is accepted or not by the instructor
     * @param id     the id of the graded document
     */
    @Transactional
    public void setAccepted(int id, Boolean answer) {
        RequiredDocument rd = requiredDocumentRepository.findById(id);
        rd.setAccepted(answer);

    }

    /**
     * Get all the required document in the system
     * 
     * @return A list of all required document in the system
     */
    @Transactional
    public List<RequiredDocument> getAllRequiredDocuments() {
        return (List<RequiredDocument>) requiredDocumentRepository.findAll();
    }

    /**
     * Set the evaluation of a employer contract
     * 
     * @param ec         the employer contract
     * @param e          the empoyer
     * @param evaluation the evaluation of the employer contract
     */
    @Transactional
    public EmployerContract setEvaluation(EmployerContract ec, Employer e, String evaluation) {
        if (ec.getEmployer() == null || !ec.getEmployer().equals(e))
            throw new NullPointerException("No such employer.");
        ec.setEvaluation(evaluation);
        requiredDocumentRepository.save(ec);
        return ec;
    }

    // =============================== Private methods
    // ===============================
    /**
     * Create a required document in the system
     *
     * @param name    the name of the Required document
     * @param dueDate the date for which the Required document have to submitted
     *                before
     * @param cpId    the Id of the coop position associated with this Required
     *                document
     * @param type    the type of the Required document
     * @return a Required document representing the newly added Required document
     * @throws IllegalArgumentException
     */
    private RequiredDocument createRequiredDocument(String name, Date dueDate, CoopPosition cp,
            RequiredDocumentType type) {
        if (!Utilities.checkNotEmpty(name))
            throw new IllegalArgumentException("Cannot add a document with empty name.");

        RequiredDocument rdoc = null;
        if (type == RequiredDocumentType.REPORT) {
            rdoc = new Report();
        } else if (type == RequiredDocumentType.EMPLOYER_CONTRACT) {
            rdoc = new EmployerContract();
        } else if (type == RequiredDocumentType.FORM) {
            rdoc = new Form();
        }

        if (rdoc != null) {
            rdoc.setName(name);
            rdoc.setDueDate(dueDate);
            rdoc.setCoopPosition(cp);
            rdoc.setSubmitted(false);
            rdoc.setAccepted(false);
            requiredDocumentRepository.save(rdoc);
            return rdoc;
        }
        throw new IllegalArgumentException("[Internal error] Failed to create a new document.");
    }

    /**
     * deleting a required document by id
     * 
     * @param docId document id
     * @return true = success
     * @throws NullPointerException
     */
    @Transactional
    public boolean deleteRequiredDocument(int docId) {
        RequiredDocument rd = requiredDocumentRepository.findById(docId);
        if (rd == null) {
            throw new NullPointerException("No such document.");
        }
        requiredDocumentRepository.deleteById(docId);
        return true;
    }

}
