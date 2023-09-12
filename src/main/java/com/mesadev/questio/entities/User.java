package com.mesadev.questio.entities;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name ="user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
}
