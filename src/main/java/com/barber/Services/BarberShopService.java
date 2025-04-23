package com.barber.Services;

import com.barber.Entities.BarberShop;
import com.barber.Entities.Dtos.CreateBarberShopDTO;
import com.barber.Entities.Dtos.OperationDTO;
import com.barber.Entities.Operation;
import com.barber.Repositories.BarberShopRepository;
import com.barber.Repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BarberShopService {
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private BarberShopRepository barberShopRepository;


    public List<CreateBarberShopDTO> getAllBarberShops() {
        List<BarberShop> barberShops = barberShopRepository.findAll();

        return barberShops.stream()
                .map(barberShop -> new CreateBarberShopDTO(
                        barberShop.getName(),
                        barberShop.getCnpj(),
                        barberShop.getTelephone(),
                        barberShop.getEmail(),
                        barberShop.getAddress(),
                        barberShop.getCity(),
                        barberShop.getState(),
                        barberShop.getCnpj(),
                        barberShop.getAtiva(),
                        barberShop.getOperations().stream()
                                .map(op -> new OperationDTO(
                                        op.getDayOfTheWeek(),
                                        op.getOpeningHours(),
                                        op.getClosingTime()
                                ))
                                .toList()
                ))
                .toList();
    }

    public void createBarberShop(CreateBarberShopDTO dto) {

        List<Operation> operations = dto.operations()
                .stream()
                .map(Operation::new)
                .collect(Collectors.toList());

        List<Operation> savedOperations = operationRepository.saveAll(operations);

        BarberShop barberShop = new BarberShop(dto);
        barberShop.setOperations(savedOperations);

        barberShopRepository.save(barberShop);
    }
}
