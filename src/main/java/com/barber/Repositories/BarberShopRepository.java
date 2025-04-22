package com.barber.Repositories;

import com.barber.Entities.BarberShop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberShopRepository extends JpaRepository<BarberShop, Integer> {
}
