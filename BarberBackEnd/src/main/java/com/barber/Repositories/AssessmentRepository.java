package com.barber.Repositories;

import com.barber.Entities.Assessment;
import com.barber.Entities.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssessmentRepository extends JpaRepository<Assessment, Integer> {

}
