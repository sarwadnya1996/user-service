package com.example.user_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditableEntity {
    @Column(name ="CREATED_BY")
    @Builder.Default
    private String createdBy="SYSTEM";
    @Column(name="CREATED_AT")
    @Builder.Default
    private LocalDateTime createAt= LocalDateTime.now();
    @Column(name="UPDATED_BY")
    private String updatedBy;
    @Column(name="UPDATED_AT")
    private String updatedAt;



}
