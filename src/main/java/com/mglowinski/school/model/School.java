package com.mglowinski.school.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class School implements Serializable {

    private static final long serialVersionUID = -4989942092209420575L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull(message = "School name must be provided")
    private String name;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Address address;
}