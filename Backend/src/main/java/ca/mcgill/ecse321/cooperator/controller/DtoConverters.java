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

    static void CheckArg(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Trying to convert using a null " + o.getClass().getSimpleName());
        }
    }

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

    public static ReportDto convertToDto(Report r) {
        CheckArg(r);
        return new ReportDto(r.getReportType(), r.getDocumentId(), r.getName(), r.getDueDate(), r.getSubmitted(),
                r.getAccepted(), r.getCoopPosition().getCoopId());
    }

    public static FormDto convertToDto(Form f) {
        CheckArg(f);
        return new FormDto(f.getDocumentId(), f.getName(), f.getDueDate(), f.getSubmitted(), f.getAccepted(),
                f.getCoopPosition().getCoopId());
    }

    public static EmployerContractDto convertToDto(EmployerContract ec) {
        CheckArg(ec);
        return new EmployerContractDto(ec.getDocumentId(), ec.getName(), ec.getDueDate(), ec.getSubmitted(),
                ec.getAccepted(), ec.getCoopPosition().getCoopId(), ec.getEmployer().getEmployerID());
    }

    public static EmployerDto convertToDto(Employer e) {
        CheckArg(e);
        return new EmployerDto(e.getEmployerID());
    }

    public static CourseDto convertToDto(Course c) {
        CheckArg(c);
        return new CourseDto(c.getCourseId(), c.getCourseName());
    }

    public static StudentDto convertToDto(Student s) {
        CheckArg(s);
        List<Integer> coops = new ArrayList<>();
        for (CoopPosition cp : s.getCoopPosition())
            coops.add(new Integer(cp.getCoopId()));
        StudentDto st = new StudentDto(s.getStudentID(), s.getFirstName(), s.getLastName(), s.getProblematic(), coops);
        return st;
    }

    public static TermInstructorDto convertToDto(TermInstructor ti) {
        CheckArg(ti);
        return new TermInstructorDto(ti.getFirstName(), ti.getLastName(), ti.getPassword(), ti.getEmail());
    }

    public static ProgramManagerDto convertToDto(ProgramManager pm) {
        CheckArg(pm);
        return new ProgramManagerDto(pm.getFirstName(), pm.getLastName(), pm.getPassword(), pm.getEmail());
    }

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

    public static UserEntityDto convertToDto(UserEntity ue) {
        CheckArg(ue);
        if (ue instanceof TermInstructor) {
            return convertToDto((TermInstructor) ue);
        } else if (ue instanceof ProgramManager) {
            return convertToDto((ProgramManager) ue);
        }
        throw new IllegalArgumentException("User not term instructor or program manager!");
    }

    public static CoopPosition convertToDomainObject(CoopPositionDto cpDto, CoopPositionService coopPositionService) {
        CheckArg(cpDto);
        CheckArg(coopPositionService);
        return coopPositionService.getById(cpDto.getCoopID());
    }

    public static RequiredDocument convertToDomainObject(RequiredDocumentDto rdDto, RequiredDocumentService requiredDocumentService) {
        CheckArg(rdDto);
        CheckArg(requiredDocumentService);
        return requiredDocumentService.getRequiredDocumentById(rdDto.getDocumentId());
    }

    public static Employer convertToDomainObject(EmployerDto eDto, EmployerService employerService) {
        CheckArg(eDto);
        CheckArg(employerService);
        return employerService.getEmployerById(eDto.getEmployerId());
    }

    public static Student convertToDomainObject(StudentDto sDto, StudentService studentService) {
        CheckArg(sDto);
        CheckArg(studentService);
        return studentService.getStudentById(sDto.getStudentId());
    }
}
