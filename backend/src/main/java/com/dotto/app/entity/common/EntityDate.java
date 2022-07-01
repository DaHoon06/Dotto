package com.dotto.app.entity.common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class EntityDate {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;


    @LastModifiedDate
    @Column(nullable = false, updatable = false)
    private Timestamp modifiedAt;

}
