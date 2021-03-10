package com.mit.api.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role", schema = "public")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_seq")
    @SequenceGenerator(name = "role_id_seq", sequenceName = "role_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String role;
}
