package com.staj.bengisu.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)

public abstract class Auditable {

    @CreatedDate
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdOn;

    @CreatedBy
    @Column(name = "CREATED_BY", length = 50)
    private String createdBy;


    @LastModifiedDate
    @Column(name = "MODIFIED_DATE")
    private LocalDateTime updatedOn;

    @LastModifiedBy
    @Column(name = "MODIFIED_BY", length = 50)
    private String updatedBy;

}
