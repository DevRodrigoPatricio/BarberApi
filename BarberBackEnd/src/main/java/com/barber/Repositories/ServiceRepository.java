package com.barber.Repositories;

import com.barber.Entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Services, Integer> {

    List<Services> findByName(String name);
}
