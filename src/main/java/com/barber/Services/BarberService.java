package com.barber.Services;

import com.barber.Entities.Barber;
import com.barber.Entities.BarberShop;
import com.barber.Entities.Dtos.BarberDTO;
import com.barber.Exceptions.ObjectnotFoundException;
import com.barber.Repositories.BarberRepository;
import com.barber.Repositories.BarberShopRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BarberService {
    @Autowired
    private BarberRepository repository;

    @Autowired
    private BarberShopRepository barberShopRepository;

    public List<BarberDTO> findAll() {
        List<Barber> barbers = repository.findAll();
        return barbers.stream().map(BarberDTO::new).collect(Collectors.toList());
    }

    public List<BarberDTO> findByBarberShopid(Integer idBarberShop) {
        BarberShop barberShop = barberShopRepository.findById(idBarberShop).orElse(null);
        List<Barber> barbers = repository.findBybarberShop(barberShop);
        return barbers.stream().map(BarberDTO::new).collect(Collectors.toList());
    }

    public Barber findById(Integer id) {
        Optional<Barber> barber = repository.findById(id);
        return barber.orElseThrow(() -> new ObjectnotFoundException("serviço não econtrado " + id));
    }

    public BarberDTO create(BarberDTO barberDTO) {
        BarberShop barberShop = barberShopRepository.findById(barberDTO.getBarberShop()).get();
        Barber barber = new Barber(barberDTO);
        barber.setBarberShop(barberShop);
        repository.save(barber);
        return new BarberDTO(barber);
    }

    public void update(Integer id, @Valid BarberDTO barberDTO) {
        Barber oldBarber = findById(id);
        oldBarber = new Barber(barberDTO);
        oldBarber.setId(id);
        repository.save(oldBarber);
    }
}
