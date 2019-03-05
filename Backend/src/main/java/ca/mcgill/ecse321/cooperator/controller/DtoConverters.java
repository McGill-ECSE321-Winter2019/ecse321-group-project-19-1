package ca.mcgill.ecse321.cooperator.controller;

import ca.mcgill.ecse321.cooperator.dto.*;
import ca.mcgill.ecse321.cooperator.model.*;
import ca.mcgill.ecse321.cooperator.services.CoopPositionService;
import ca.mcgill.ecse321.cooperator.services.EmployerService;
import ca.mcgill.ecse321.cooperator.services.RequiredDocumentService;
import ca.mcgill.ecse321.cooperator.services.StudentService;

import java.util.ArrayList;
import java.util.List;

public class DtoConverters {
    /**
     * Check if an object is null and throw IllegalArgumentException in that case.
     *
     * @param o the object to check
     */
    static void CheckArg(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Trying to convert using a null " + o.getClass().getSimpleName());
        }
    }

    /**
     * Convert a required document to RequiredDocumentDto
     *
     * @param rd the required document to convert
     * @return the converted RequiredDocumentDto
     */
    public static RequiredDocumentDto convertToDto(RequiredDocument rd) {
        CheckArg(rd);

        if (rd instanceof Form) {
            return convertToDto((Form) rd);
        } else if (rd instanceof Report) {
            return convertToDto((Report) rd);
        } else if (rd instanceof EmployerContract) {
            return convertToDto((EmployerContract) rd);
        }

        throw new IllegalArgumentException("This Document is not a instance of any subclass!");
    }

    /**
     * Convert a report to ReportDto
     *
     * @param r the report to convert
     * @return the converted ReportDto
     */
    public static ReportDto convertToDto(Report r) {
        CheckArg(r);
        return new ReportDto(r.getReportType(), r.getDocumentId(), r.getName(), r.getDueDate(), r.getSubmitted(),
                r.getAccepted(), r.getCoopPosition().getCoopId());
    }

    /**
     * Convert a form to FormDto
     *
     * @param f the form to convert
     * @return the converted FormDto
     */
    public static FormDto convertToDto(Form f) {
        CheckArg(f);
        return new FormDto(f.getDocumentId(), f.getName(), f.getDueDate(), f.getSubmitted(), f.getAccepted(),
                f.getCoopPosition().getCoopId());
    }

    /**
     * Convert an EmployerContract to EmployerContractDto
     *
     * @param ec the EmployerContract to convert
     * @return the converted EmployerContractDto
     */
    public static EmployerContractDto convertToDto(EmployerContract ec) {
        CheckArg(ec);
        return new EmployerContractDto(ec.getDocumentId(), ec.getName(), ec.getDueDate(), ec.getSubmitted(),
                ec.getAccepted(), ec.getCoopPosition().getCoopId(), ec.getEmployer().getEmployerID());
    }

    /**
     * Convert an Employer to EmployerDto
     *
     * @param e the Employer to convert
     * @return the converted EmployerDto
     */
    public static EmployerDto convertToDto(Employer e) {
        CheckArg(e);
        return new EmployerDto(e.getEmployerID());
    }

    /**
     * Convert a Course to CourseDto
     *
     * @param c the Course to convert
     * @return the converted CourseDto
     */
    public static CourseDto convertToDto(Course c) {
        CheckArg(c);
        return new CourseDto(c.getCourseId(), c.getCourseName());
    }

    /**
     * Convert an Student to StudentDto
     *
     * @param s the Student to convert
     * @return the converted StudentDto
     */
    public static StudentDto convertToDto(Student s) {
        CheckArg(s);
        List<Integer> coops = new ArrayList<>();
        for (CoopPosition cp : s.getCoopPosition())
            coops.add(new Integer(cp.getCoopId()));
        StudentDto st = new StudentDto(s.getStudentID(), s.getFirstName(), s.getLastName(), s.getProblematic(), coops);
        return st;
    }

    /**
     * Convert an TermInstructor to TermInstructorDto
     *
     * @param ti the TermInstructor to convert
     * @return the converted TermInstructorDto
     */
    public static TermInstructorDto convertToDto(TermInstructor ti) {
        CheckArg(ti);
        return new TermInstructorDto(ti.getFirstName(), ti.getLastName(), ti.getPassword(), ti.getEmail());
    }

    /**
     * Convert an ProgramManager to ProgramManagerDto
     *
     * @param pm the ProgramManager to convert
     * @return the converted ProgramManagerDto
     */
    public static ProgramManagerDto convertToDto(ProgramManager pm) {
        CheckArg(pm);
        return new ProgramManagerDto(pm.getFirstName(), pm.getLastName(), pm.getPassword(), pm.getEmail());
    }

    /**
     * Convert an CoopPosition to CoopPositionDto
     *
     * @param cp the CoopPosition to convert
     * @return the converted CoopPositionDto
     */
    public static CoopPositionDto convertToDto(CoopPosition cp) {
        CheckArg(cp);
        List<TermInstructor> instructors = new ArrayList<>(cp.getTermInstructor());
        List<TermInstructorDto> tiDtos = new ArrayList<>();
        for (TermInstructor instructor : instructors) {
            if (instructor != null)
                tiDtos.add(convertToDto(instructor));
        }
        CoopPositionDto cpDto = new CoopPositionDto(cp.getCoopId(), cp.getDescription(), cp.getStartDate(),
                cp.getEndDate(), cp.getLocation(), cp.getTerm(), cp.getStudent().getStudentID(), tiDtos);
        return cpDto;
    }

    /**
     * Convert an UserEntity to UserEntityDto
     *
     * @param ue the UserEntity to convert
     * @return the converted UserEntityDto
     */
    public static UserEntityDto convertToDto(UserEntity ue) {
        CheckArg(ue);
        if (ue instanceof TermInstructor) {
            return convertToDto((TermInstructor) ue);
        } else if (ue instanceof ProgramManager) {
            return convertToDto((ProgramManager) ue);
        }
        throw new IllegalArgumentException("User not term instructor or program manager!");
    }

    /**
     * Convert a CoopPositionDto to CoopPosition
     *
     * @param cpDto               the CoopPositionDto to convert
     * @param coopPositionService the service used for conversion
     * @return A coopPosition corresponding to cpDto
     */
    public static CoopPosition convertToDomainObject(CoopPositionDto cpDto, CoopPositionService coopPositionService) {
        CheckArg(cpDto);
        CheckArg(coopPositionService);
        return coopPositionService.getById(cpDto.getCoopID());
    }

    /**
     * Convert a RequiredDocumentDto to RequiredDocument
     *
     * @param rdDto                   the RequiredDocumentDto to convert
     * @param requiredDocumentService the service used for conversion
     * @return A RequiredDocument corresponding to rdDto
     */
    public static RequiredDocument convertToDomainObject(RequiredDocumentDto rdDto, RequiredDocumentService requiredDocumentService) {
        CheckArg(rdDto);
        CheckArg(requiredDocumentService);
        return requiredDocumentService.getRequiredDocumentById(rdDto.getDocumentId());
    }

    /**
     * Convert a EmployerDto to Employer
     *
     * @param eDto            the EmployerDto to convert
     * @param employerService the service used for conversion
     * @return An Employer corresponding to eDto
     */
    public static Employer convertToDomainObject(EmployerDto eDto, EmployerService employerService) {
        CheckArg(eDto);
        CheckArg(employerService);
        return employerService.getEmployerById(eDto.getEmployerId());
    }

    /**
     * Convert a StudentDto to Student
     *
     * @param sDto           the StudentDto to convert
     * @param studentService the service used for conversion
     * @return A Student corresponding to sDto
     */
    public static Student convertToDomainObject(StudentDto sDto, StudentService studentService) {
        CheckArg(sDto);
        CheckArg(studentService);
        return studentService.getStudentById(sDto.getStudentId());
    }
}
