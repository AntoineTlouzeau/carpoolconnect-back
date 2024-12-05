package com.incubateur.carpoolconnect.entities;

import com.incubateur.carpoolconnect.audit.AbstractAuditable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "routes")
public class Route extends AbstractAuditable implements Serializable {

    @Serial
    private static final long serialVersionUID = -1017049620466797949L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDateTime departureDate;

    @Column(nullable = false)
    private int seats;

    @Column(name = "number_small_baggage", nullable = false)
    private int smallBaggage;

    @Column(name = "number_large_baggage", nullable = false)
    private int largeBaggage;

    @Column(nullable = false)
    private boolean silence;

    private String musicGenre;

    @ManyToOne
    private User driver;

    @ManyToOne
    private Car car;

    @OneToMany(mappedBy = "route")
    private List<Rating> ratings;

    @OneToMany(mappedBy = "route")
    private List<Reservation> reservations;

    @ManyToOne(optional = false)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Address departure;

    @ManyToOne(optional = false)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Address destination;

    @ManyToMany
    @JoinColumn(name = "intermediate_addresses")
    private List<Address> intermediateAddresses;
}
