package com.projecty.model.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@ToString(callSuper = true)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditingEntity {
    @Version
    @Column(name="version")
    private Long version;

    @CreatedDate
    @Column(name="created_time")
    private Instant createdTime;

    @CreatedBy
    @Column(name="created_by")
    private String createdBy;

    @LastModifiedDate
    @Column(name="updated_time")
    private Instant updatedTime;

    @LastModifiedBy
    @Column(name="updated_by")
    private String updatedBy;
}
