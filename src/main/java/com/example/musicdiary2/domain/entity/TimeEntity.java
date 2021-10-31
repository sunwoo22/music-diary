package com.example.musicdiary2.domain.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeEntity {

    @CreatedDate
    @Column(columnDefinition = "date default now()", updatable = false)
    private LocalDate createdDate;

//    @LastModifiedDate
//    @Column(columnDefinition = "datetime default now()")
//    private LocalDate modifiedDate;

//    public TimeEntity(LocalDateTime createdDate) {
//        this.createdDate = createdDate;
//    }
}
