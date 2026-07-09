package com.example.user_service.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID")
    private Long user_id;

    @Column(name = "USER_NAME", unique = true, nullable = false)
    private String userName;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Column(name = "EMAIL_ID")
    private String emailId;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "IS_ACTIVE")
    @Builder.Default
    private boolean isActive = true;
    @Column(name ="ROLE")
    private String role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;

    @OneToMany(mappedBy ="user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ContactDetails> contactDetails;

        public void addAddress(Address userAddress) {
        if(addresses == null) {
            addresses = new ArrayList<>();
        }
        addresses.add(userAddress);
        userAddress.setUser(this);
    }

    public void removeContactDetails(ContactDetails contactDetails) {
        this.getContactDetails().stream().filter(contact -> contact.equals(contactDetails))
                .findAny().get().setIsActive(false);
    }
    public void addContactDetail(ContactDetails  contactDetail) {
        if(contactDetails == null) {
            contactDetails = new ArrayList<>();
        }
        contactDetails.add(contactDetail);
        contactDetail.setUser(this);
    }

    public void removeAddress(Address userAddress) {
        this.getAddresses().stream().filter(userAddress1 -> userAddress1.equals(userAddress))
                .findAny().get().setIsActive(false);
    }
}
