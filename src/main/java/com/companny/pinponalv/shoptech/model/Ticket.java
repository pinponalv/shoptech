package com.companny.pinponalv.shoptech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "tickets")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String code;
    @OneToOne
    @Column(unique = true)
    private Orders order;
    @Column(nullable = false)
    private BigDecimal total;
    //Uso instant para saber en que momento sucede algo independientemente de la zona horaria
    //y con localdatetime no puedo ver informacion de zona horaria
    //entonces para pagos y logs es bueno saber esto
    @Column(nullable = false)
    private Instant issuedAt;
    @Column(nullable = false)
    private String pdfPath;
}
