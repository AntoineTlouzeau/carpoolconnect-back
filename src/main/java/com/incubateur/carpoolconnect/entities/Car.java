package com.incubateur.carpoolconnect.entities;

import com.incubateur.carpoolconnect.audit.AbstractAuditable;
import com.incubateur.carpoolconnect.enums.Colors;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cars")
public class Car extends AbstractAuditable implements Serializable {

    @Serial
    private static final long serialVersionUID = -1585279113086471460L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Model model;

    @Enumerated(EnumType.STRING)
    private Colors color;

    @OneToMany(mappedBy = "car")
    private List<Route> routes;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "car")
    private List<Picture> pictures;

}
