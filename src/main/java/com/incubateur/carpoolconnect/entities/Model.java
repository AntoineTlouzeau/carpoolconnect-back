package com.incubateur.carpoolconnect.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Model implements Serializable {

    @Serial
    private static final long serialVersionUID = -499034391084718969L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    private Brand brand;
    @OneToMany(mappedBy = "model")
    @Column(unique = true)
    private List<Car> cars;

}
