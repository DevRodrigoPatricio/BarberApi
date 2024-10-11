package com.barber.Services;

import com.barber.Entities.Barber;
import com.barber.Entities.Dtos.SchedulingDTO;
import com.barber.Entities.Scheduling;
import com.barber.Entities.Services;
import com.barber.Entities.User;
import com.barber.Exceptions.ObjectnotFoundException;
import com.barber.Repositories.BarberRepository;
import com.barber.Repositories.SchedulingRepository;
import com.barber.Repositories.ServiceRepository;
import com.barber.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SchedulingService {
    @Autowired
    private SchedulingRepository repository;

    @Autowired
    private BarberRepository barberRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServicesService  servicesService;

    public List<SchedulingDTO> findAll() {
        List<Scheduling> schedulings = repository.findAll();
        return schedulings.stream().map(SchedulingDTO::new).collect(Collectors.toList());
    }

    public Scheduling findById(Integer id) {
        Optional<Scheduling> scheduling = repository.findById(id);
        return scheduling.orElseThrow(() -> new ObjectnotFoundException("agendamento não localizado " + id));
    }

    public SchedulingDTO create(SchedulingDTO schedulingDTO) {

        User user = userRepository.findById(schedulingDTO.getUser())
                .orElseThrow(() -> new ObjectnotFoundException("usuário não encontrado!"));

        Barber barber = barberRepository.findById(schedulingDTO.getBarber())
                .orElseThrow(() -> new ObjectnotFoundException("Barberiro não encontrado!"));

        Set<Services> services = new HashSet<Services>();

        for (Integer idService : schedulingDTO.getServices()){
            Services service =  servicesService.findById(idService);
            services.add(service);
        }

        Scheduling scheduling = new Scheduling(schedulingDTO, user, barber,services);
        repository.save(scheduling);
        return new SchedulingDTO(scheduling);
    }


}
