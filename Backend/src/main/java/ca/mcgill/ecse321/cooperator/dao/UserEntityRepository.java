package ca.mcgill.ecse321.cooperator.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.cooperator.model.UserEntity;

//@RepositoryRestResource(collectionResourceRel = "participants", path = "participants")
public interface UserEntityRepository extends CrudRepository<UserEntity, String>{
    UserEntity findUserEntityByEmail(String email);
}