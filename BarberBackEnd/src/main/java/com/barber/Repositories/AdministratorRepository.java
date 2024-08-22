package com.barber.Repositories;

import com.barber.Entities.Administrator;
import com.barber.Entities.Barber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

}
