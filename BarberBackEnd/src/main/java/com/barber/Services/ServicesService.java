package com.barber.Services;

import com.barber.Entities.Dtos.ServiceDTO;
import com.barber.Entities.Services;
import com.barber.Repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicesService {
    @Autowired
    private ServiceRepository repository;

    public ServiceDTO create(ServiceDTO serviceDTO){
        Services service = new Services(serviceDTO);
        repository.save(service);
        return new ServiceDTO(service);

    }
}
