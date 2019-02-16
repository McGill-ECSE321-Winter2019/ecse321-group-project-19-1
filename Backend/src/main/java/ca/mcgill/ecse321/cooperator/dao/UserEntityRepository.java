package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.cooperator.model.UserEntity;

public interface UserEntityRepository extends CrudRepository<UserEntity, String>{
    UserEntity findUserEntityByEmail(String email);
}