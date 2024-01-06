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
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.projecty.model.ModelModule.TableConstants.AUD_TABLE_POLICY;
import static com.projecty.model.ModelModule.TableConstants.TABLE_POLICY;

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
@Comment(value = "保单表")
@Table(name = TABLE_POLICY, schema = ModelModule.SCHEMA_NB)
//@Audited
//@AuditTable(schema = ModelModule.SCHEMA_AUD, value = AUD_TABLE_POLICY)
public class PolicyEntity extends SoftDeletedBaseEntity {

    @Id
    @Comment(value = "id")
    @Column(nullable = false, length = 36)
    private String id;

    @Comment(value = "保单号码")
    @Column(length = 50, nullable = false)
    private String policyNo;

    @Comment(value = "保单状态")
    @Column(length = 20, nullable = false)
    private String policyStatus;

    @Comment(value = "中介人")
    @Column(name = "intermediary", length = 64)
    private String middleMan;

    @Comment(value = "渠道")
    @Column(name = "channel", length = 32)
    private String channel;

    @Comment(value = "保单币种")
    @Column(name = "currency", length = 20)
    private String policyCurrency;

    @Comment(value = "总年度保费")
    @Column(name = "annualized_premium", precision = 20, scale = 2)
    private BigDecimal yearPremium;

    @Comment(value = "缴费频率")
    @Column(name = "payment_frequency", length = 20)
    private String paymentFrequency;

    @Comment(value = "行政方式")
    @Column(name = "administration_type", length = 20)
    private String administrationType;

    @Comment(value = "报价单号")
    @Column(name = "proposal_no", length = 50)
    private String proposalNo;

    @Comment(value = "保单备注")
    @Column(name = "remark", length = 4096)
    private String policyRemark;

    @Comment(value = "用户id")
    @Column(name = "user_id", length = 500)
    private String userId;

    @Comment(value = "工作日期")
    @Column(name = "work_date")
    private LocalDate workDate;

}
