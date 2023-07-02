package com.ems.entities;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Bonus")
public class Bonus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bid;
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "eid")
    private Employee employee;
}