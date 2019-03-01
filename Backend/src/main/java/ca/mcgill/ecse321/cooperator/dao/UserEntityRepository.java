package ca.mcgill.ecse321.cooperator.dao;

import ca.mcgill.ecse321.cooperator.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserEntityRepository extends CrudRepository<UserEntity, String> {
    UserEntity findUserEntityByEmail(String email);
}