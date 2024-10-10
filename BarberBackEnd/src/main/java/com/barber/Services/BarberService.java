package com.barber.Services;

import com.barber.Entities.Barber;
import com.barber.Entities.Dtos.BarberDTO;
import com.barber.Exceptions.ObjectnotFoundException;
import com.barber.Repositories.BarberRepository;
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

    public List<BarberDTO> findAll() {
        List<Barber> barbers = repository.findAll();
        return barbers.stream().map(BarberDTO::new).collect(Collectors.toList());
    }

    public Barber findById(Integer id) {
        Optional<Barber> barber = repository.findById(id);
        return barber.orElseThrow(() -> new ObjectnotFoundException("serviço não econtrado " + id));
    }

    public BarberDTO create(BarberDTO barberDTO) {
        Barber barber = new Barber(barberDTO);
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
