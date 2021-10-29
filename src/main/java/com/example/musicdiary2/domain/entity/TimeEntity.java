package com.example.musicdiary2.domain.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeEntity {

    @CreatedDate
    @Column(columnDefinition = "datetime default now()", updatable = false)
    private LocalDateTime createdDate;

//    @LastModifiedDate
//    @Column(columnDefinition = "datetime default now()")
//    private LocalDate modifiedDate;

//    public TimeEntity(LocalDateTime createdDate) {
//        this.createdDate = createdDate;
//    }
}
