package com.barber.Entities.Dtos;

import java.util.List;

public record CreateBarberShopDTO(
        String name,
        String cnpj,
        String telephone,
        String email,
        String address,
        String city,
        String state,
        String cep,
        Boolean ativa,
        List<OperationDTO> operations
) {}
