package com.capgemini.jingxi_demo.infrastructure.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "useritem")
public class UserItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
}
