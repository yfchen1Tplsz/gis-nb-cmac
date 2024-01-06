package com.projecty.model.domain.policy.entity;

import com.projecty.model.ModelModule;
import com.projecty.model.base.entity.SoftDeletedBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.time.LocalDate;

import static com.projecty.model.ModelModule.TableConstants.AUD_TABLE_POLICY_DATE;
import static com.projecty.model.ModelModule.TableConstants.TABLE_POLICY_DATE;

/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@DynamicInsert
@DynamicUpdate
@Entity
@Comment(value = "保单日期信息表")
@Table(name = TABLE_POLICY_DATE, schema = ModelModule.SCHEMA_NB)
//@Audited
//@AuditTable(schema = ModelModule.SCHEMA_AUD, value = AUD_TABLE_POLICY_DATE)
public class PolicyDateInfo extends SoftDeletedBaseEntity {

    @Id
    @Comment(value = "id")
    @Column(nullable = false, length = 36)
    private String id;

    @Comment(value = "保单id")
    @Column(nullable = false, length = 36)
    private String policyId;

    @Comment(value = "申请日期")
    @Column(name = "proposal_date")
    private LocalDate applicationDate;

    @Comment(value = "保单生效日期")
    @Column(name = "commencement_date")
    private LocalDate policyEffectDate;

    @Comment(value = "保障生效日期")
    @Column(name = "benefit_effect_date")
    private LocalDate ensureEffectDate;

    @Comment(value = "保障终止日期")
    @Column(name = "termination_date")
    private LocalDate ensureExpireDate;

    @Comment(value = "保单通过日期")
    @Column(name = "acceptance_date")
    private LocalDate policyAdoptionDate;

    @Comment(value = "下個保费到期日")
    @Column(name = "next_payment_date")
    private LocalDate ndd;

}
