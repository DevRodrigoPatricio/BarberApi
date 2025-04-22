package com.barber.Repositories;

import com.barber.Entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
}
