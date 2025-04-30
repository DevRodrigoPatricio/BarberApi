package com.barber.Repositories;

import com.barber.Entities.Barber;
import com.barber.Entities.BarberShop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarberRepository extends JpaRepository<Barber, Integer> {

    List<Barber> findBybarberShop(BarberShop barber);
}
