package ca.mcgill.ecse321.cooperator.dto;

import java.util.Collections;
import java.util.List;

public class TermInstructorDto extends UserEntityDto {
    private List<CoopPositionDto> coopPosition;

    public TermInstructorDto() {

    }

    public TermInstructorDto(String email) {
        this(null, null, null, email, Collections.EMPTY_LIST);
    }

    @SuppressWarnings("unchecked")
    public TermInstructorDto(String first, String last, String pass, String e_mail) {
        this(first, last, pass, e_mail, Collections.EMPTY_LIST);
    }

    public TermInstructorDto(String first, String last, String pass, String e_mail, List<CoopPositionDto> cPosition) {
        super(first, last, pass, e_mail);
        this.coopPosition = cPosition;
    }

    public List<CoopPositionDto> getCoopPosition() {
        return coopPosition;
    }

    public void setCoopPosition(List<CoopPositionDto> coopPositions) {
        this.coopPosition = coopPositions;
    }

    public void addCoopPostion(CoopPositionDto cpDto) {
        coopPosition.add(cpDto);
    }
}
