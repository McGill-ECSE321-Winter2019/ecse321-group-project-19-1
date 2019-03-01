package ca.mcgill.ecse321.cooperator.controller;

import ca.mcgill.ecse321.cooperator.dto.*;
import ca.mcgill.ecse321.cooperator.model.*;
import ca.mcgill.ecse321.cooperator.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DtoConverters {

    static void CheckArg(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Trying to convert using a null "+o.getClass().getSimpleName());
        }
    }

    static RequiredDocumentDto convertToDto(RequiredDocument rd) {
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

    static ReportDto convertToDto(Report r) {
        CheckArg(r);
        return new ReportDto(r.getReportType(), r.getDocumentId(), r.getName(), r.getDueDate());
    }

    static FormDto convertToDto(Form f) {
        CheckArg(f);
        return new FormDto(f.getDocumentId(), f.getName(), f.getDueDate());
    }

    static EmployerContractDto convertToDto(EmployerContract ec) {
        CheckArg(ec);
        return new EmployerContractDto(ec.getDocumentId(), ec.getName(), ec.getDueDate());
    }

    static EmployerDto convertToDto(Employer e) {
        CheckArg(e);
        return new EmployerDto(e.getEmployerID());
    }

    static CourseDto convertToDto(Course c) {
        CheckArg(c);
        return new CourseDto(c.getCourseId(), c.getCourseName());
    }

    static StudentDto convertToDto(Student s) {
        CheckArg(s);
        return new StudentDto(s.getStudentID());
    }

    static TermInstructorDto convertToDto(TermInstructor ti) {
        CheckArg(ti);
        return new TermInstructorDto(ti.getFirstName(), ti.getLastName(), ti.getPassword(), ti.getEmail());
    }

    static ProgramManagerDto convertToDto(ProgramManager pm) {
        CheckArg(pm);
        return new ProgramManagerDto(pm.getFirstName(), pm.getLastName(), pm.getPassword(), pm.getEmail());
    }

    static CoopPositionDto convertToDto(CoopPosition cp) {
        CheckArg(cp);
        StudentDto sDto = convertToDto(cp.getStudent());
        if (cp.getTermInstructor() == null || cp.getTermInstructor().isEmpty()) {
            CoopPositionDto cpDto = new CoopPositionDto(cp.getCoopId(), cp.getDescription(), cp.getStartDate(),
                    cp.getEndDate(), cp.getLocation(), cp.getTerm(), sDto);
            return cpDto;
        } else {
            TermInstructor termInstructor = getLast(cp.getTermInstructor());
            TermInstructorDto tiDto = convertToDto(termInstructor);
            CoopPositionDto cpDto = new CoopPositionDto(cp.getCoopId(), cp.getDescription(), cp.getStartDate(),
                    cp.getEndDate(), cp.getLocation(), cp.getTerm(), sDto, tiDto);
            return cpDto;
        }
    }

    static UserEntityDto convertToDto(UserEntity ue) {
        CheckArg(ue);
        if (ue instanceof TermInstructor) {
            return convertToDto((TermInstructor) ue);
        } else if (ue instanceof ProgramManager) {
            return convertToDto((ProgramManager) ue);
        }
        throw new IllegalArgumentException("User not term instructor or program manager!");
    }

    static CoopPosition convertToDomainObject(CoopPositionDto cpDto, CoopPositionService coopPositionService) {
        CheckArg(cpDto);
        CheckArg(coopPositionService);
        return coopPositionService.getCoopPositionById(cpDto.getCoopID());
    }

    static RequiredDocument convertToDomainObject(RequiredDocumentDto rdDto, RequiredDocumentService requiredDocumentService) {
        CheckArg(rdDto);
        CheckArg(requiredDocumentService);
        return requiredDocumentService.getRequiredDocumentById(rdDto.getDocumentId());
    }

    static Employer convertToDomainObject(EmployerDto eDto, EmployerService employerService) {
        CheckArg(eDto);
        CheckArg(employerService);
        return employerService.getEmployerById(eDto.getEmployerId());
    }

    static Student convertToDomainObject(StudentDto sDto, StudentService studentService) {
        CheckArg(sDto);
        CheckArg(studentService);
        return studentService.getStudentById(sDto.getStudentId());
    }

    static TermInstructor getLast(Set<TermInstructor> termInstructor) {
        List<TermInstructor> newList = new ArrayList<TermInstructor>(termInstructor);
        return newList.get(newList.size() - 1);
    }

}
