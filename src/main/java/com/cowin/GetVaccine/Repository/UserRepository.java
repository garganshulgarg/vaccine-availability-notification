package com.cowin.GetVaccine.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cowin.GetVaccine.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

	@Query("SELECT u FROM User u WHERE u.active=1")
    public List<User> findAllTheActiveAndEligibleUsers();
}
