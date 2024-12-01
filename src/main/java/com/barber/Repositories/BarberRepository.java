package com.barber.Repositories;

import com.barber.Entities.Barber;
import com.barber.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberRepository extends JpaRepository<Barber, Integer> {

}
