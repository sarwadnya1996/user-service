package com.example.user_service.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.sql.results.graph.Fetch;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode(callSuper = true)
public class Address extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "IS_DEFAULT_SHIPPING")
    private Boolean isDefaultShippingAddress;
    @Column(name ="IS_DEFAULT_BILLING")
    private Boolean isDefaultBillingAddress;
    @Column(name="STREET")
    private String streetAddress;
    @Column(name="CITY")
    private String city;
    @Column(name = "POSTL_CD")
    private String postalCode;
    @Column(name = "ADDRESS_TYPE")
    private AddressType type;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

}
