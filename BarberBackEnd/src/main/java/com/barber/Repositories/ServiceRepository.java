package com.barber.Repositories;

import com.barber.Entities.Administrator;
import com.barber.Entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {

}
