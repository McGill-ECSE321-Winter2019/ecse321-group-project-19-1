package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.cooperator.model.CooperatorManager;

public interface CooperatorManagerRepository extends CrudRepository<CooperatorManager, Integer>{
	CooperatorManager findCooperatorManagerBySystemName(String name);   
}
