package com.example.user_service.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ContactDetails extends AuditableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phoneId;
    private String mobileNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id",nullable= false)
    private User user;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

}
