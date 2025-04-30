package com.barber.Services;

import com.barber.Entities.Barber;
import com.barber.Entities.BarberShop;
import com.barber.Entities.Dtos.ServiceDTO;
import com.barber.Entities.Services;
import com.barber.Exceptions.ObjectnotFoundException;
import com.barber.Repositories.BarberRepository;
import com.barber.Repositories.BarberShopRepository;
import com.barber.Repositories.ServiceRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicesService {
    @Autowired
    private ServiceRepository repository;

    @Autowired
    private BarberRepository barberRepository;
    @Autowired
    private BarberShopRepository barberShopRepository;


    public List<ServiceDTO> findAll() {
        List<Services> services = repository.findAll();
        return services.stream().map(ServiceDTO::new).collect(Collectors.toList());
    }

    public Services findById(Integer id) {
        Optional<Services> service = repository.findById(id);
        return service.orElseThrow(() -> new ObjectnotFoundException("serviço não econtrado " + id));
    }

    public List<ServiceDTO> findByName(String name) {
        List<Services> services = repository.findByNameContaining(name);
        return services.stream().map(ServiceDTO::new).collect(Collectors.toList());
    }

    public ServiceDTO create(ServiceDTO serviceDTO) {
        Barber barber = barberRepository.findById(serviceDTO.getBarber())
                .orElseThrow(() -> new ObjectnotFoundException("Barbeiro não encontrado!"));

        BarberShop barberShop = barberShopRepository.findById(serviceDTO.getBarberShop())
                .orElseThrow(() -> new ObjectnotFoundException("Barbearia não encontrada!"));

        Services service = new Services(serviceDTO, barber, barberShop); // precisa desse construtor
        repository.save(service);
        return new ServiceDTO(service);
    }


    public void update(Integer id, @Valid ServiceDTO serviceDTO) {
        Services oldservice = findById(id);
        Barber barber = barberRepository.findById(serviceDTO.getBarber())
                .orElseThrow(() -> new ObjectnotFoundException("Barberiro não encontrado!"));
        BarberShop barberShop = barberShopRepository.findById(serviceDTO.getBarberShop())
                .orElseThrow(() -> new ObjectnotFoundException("Barbearia não encontrada!"));
        oldservice = new Services(serviceDTO, barber, barberShop);
        oldservice.setId(id);
        repository.save(oldservice);
    }
}
