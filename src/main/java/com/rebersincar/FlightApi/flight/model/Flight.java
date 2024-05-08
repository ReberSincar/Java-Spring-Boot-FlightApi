package com.rebersincar.FlightApi.flight.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rebersincar.FlightApi.ticket.model.Ticket;
import com.rebersincar.FlightApi.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "flights")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String company;

    @Column(nullable = false)
    String flightNumber;

    @Column(nullable = false)
    String airplaneModel;

    @Column(nullable = false)
    String origin;

    @Column(nullable = false)
    String destination;

    @Column(nullable = false)
    Date departureTime;

    @Column(nullable = false)
    Date arrivalTime;

    @Column(nullable = false)
    float price;

    @CreationTimestamp
    Date createdAt;

    @UpdateTimestamp
    Date updatedAt;

    /*@ManyToMany(mappedBy = "flights", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<User> passengers;*/

    @OneToMany(mappedBy = "flight")
    private Set<Ticket> tickets = new HashSet<>();
}
