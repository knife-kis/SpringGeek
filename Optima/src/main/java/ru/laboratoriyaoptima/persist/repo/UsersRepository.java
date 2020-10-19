package ru.laboratoriyaoptima.persist.repo;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.laboratoriyaoptima.persist.entity.User;

import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    Optional<User> findOneByPhone(String phone);
    boolean existsByPhone(String phone);
}