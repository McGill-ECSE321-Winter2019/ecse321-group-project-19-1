package ca.mcgill.ecse321.cooperator.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TermInstructor extends UserEntity {
    private Set<CoopPosition> coopPosition = new HashSet<>();

    @ManyToMany(mappedBy = "termInstructor")
    public Set<CoopPosition> getCoopPosition() {
        return this.coopPosition;
    }

    public void setCoopPosition(Set<CoopPosition> coopPositions) {
        this.coopPosition = coopPositions;
    }

    @Override
    public String toString() {
        return "TermInstructor(email= "+getEmail()+")";
    }
}
