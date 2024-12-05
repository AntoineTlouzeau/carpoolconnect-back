package com.incubateur.carpoolconnect.entities;

import com.incubateur.carpoolconnect.audit.AbstractAuditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address extends AbstractAuditable implements Serializable {

    @Serial
    private static final long serialVersionUID = -6013395742351678005L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int number;

    private String street;

    private String city;

    private float latitude;

    private float longitude;

    private int zipcode;

    @OneToMany(mappedBy = "departure")
    private List<Route> routeDepart;

    @OneToMany(mappedBy = "destination")
    private List<Route> routeDestination;

    @ManyToMany(mappedBy = "intermediateAddresses")
    private List<Route> routes;
}
