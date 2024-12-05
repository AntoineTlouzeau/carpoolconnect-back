package com.incubateur.carpoolconnect.entities;

import com.incubateur.carpoolconnect.audit.AbstractAuditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment extends AbstractAuditable implements Serializable {

    @Serial
    private static final long serialVersionUID = 6325461662262183190L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String body;

    private boolean isFlagged;

    @ManyToOne(optional = false)
    private User commenter;

    @ManyToOne(optional = false)
    private Route route;

}
