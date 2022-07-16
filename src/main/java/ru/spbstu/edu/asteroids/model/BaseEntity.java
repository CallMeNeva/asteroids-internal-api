package ru.spbstu.edu.asteroids.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseEntity<I extends Serializable> {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private I id;

    @Column(name = "created_on", nullable = false)
    private LocalDateTime creationDateTime;

    // TODO: equals and hashCode
}
