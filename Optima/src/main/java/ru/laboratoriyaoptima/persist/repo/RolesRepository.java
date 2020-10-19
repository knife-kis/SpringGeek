package ru.laboratoriyaoptima.persist.repo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.laboratoriyaoptima.persist.entity.Role;

@Repository
public interface RolesRepository extends CrudRepository<Role, Long> {
	Role findOneByName(String name);
}
