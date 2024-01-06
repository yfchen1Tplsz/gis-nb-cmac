package com.projecty.model.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@MappedSuperclass
public class SoftDeletedBaseEntity extends AbstractAuditingEntity{
    @Column(name="deleted")
    private boolean deleted;
}
