package dev.mahfuz.yayjobs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import dev.mahfuz.yayjobs.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends MongoRepository<User,String> {
    Optional<User> findByUsername(String username);

}