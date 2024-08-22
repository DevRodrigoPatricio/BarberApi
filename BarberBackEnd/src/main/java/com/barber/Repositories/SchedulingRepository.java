package com.barber.Repositories;

import com.barber.Entities.Scheduling;
import com.barber.Entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulingRepository extends JpaRepository<Scheduling, Integer> {

}
