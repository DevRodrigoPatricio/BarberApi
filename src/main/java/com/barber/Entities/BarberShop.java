package com.barber.Entities;

import com.barber.Entities.Dtos.CreateBarberShopDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class BarberShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200,nullable = false)
    private String name;

    @Column(length = 20,nullable = false)
    private String cnpj;

    private String telephone;
    private String email;

    private String address;
    private String city;
    private String state;
    private String cep;

    @ManyToMany
    @JoinTable(
            name = "barbershop_operation",
            joinColumns = @JoinColumn(name = "barbershop_id"),
            inverseJoinColumns = @JoinColumn(name = "operation_id")
    )
    private List<Operation> operations;
    private Boolean ativa;


   public BarberShop(CreateBarberShopDTO barberShop) {
       this.name = barberShop.name();
       this.cnpj = barberShop.cnpj();
       this.telephone = barberShop.telephone();
       this.email = barberShop.email();
       this.address = barberShop.address();
       this.city = barberShop.city();
       this.state = barberShop.state();
       this.cep = barberShop.cep();
       this.operations = barberShop.operations().stream().map(Operation::new).collect(Collectors.toList());
       this.ativa = barberShop.ativa();

   }


}
