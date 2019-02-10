package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.cooperator.model.CoopPosition;

import java.util.Date;
import java.util.List;

public interface CoopPositionRepository extends CrudRepository<CoopPosition, Integer>{

    List<CoopPosition> findByLocation(String location);
    List<CoopPosition> findByStartDate(Date startDate);

}