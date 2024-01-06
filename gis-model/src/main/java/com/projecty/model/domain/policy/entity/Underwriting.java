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

import java.time.LocalDate;

import static com.projecty.model.ModelModule.TableConstants.AUD_TABLE_UW;
import static com.projecty.model.ModelModule.TableConstants.TABLE_UW;

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
@Comment(value = "核保记录表")
@Table(name = TABLE_UW, schema = ModelModule.SCHEMA_NB)
//@Audited
//@AuditTable(schema = ModelModule.SCHEMA_AUD, value = AUD_TABLE_UW)
public class Underwriting extends SoftDeletedBaseEntity {

    @Id
    @Comment(value = "id")
    @Column(nullable = false,length = 36)
    private String id;

    @Comment(value = "保单id")
    @Column(nullable = false,length = 36)
    private String policyId;

    @Comment(value = "当事人id")
    @Column(length = 36)
    private String partyId;

    @Comment(value = "核保决定")
    @Column(name = "underwriting_result", length = 128)
    private String uwResult;

    @Comment(value = "加费项")
    @Column(name = "extra_option", length = 256)
    private String extraInfo;

    @Comment(value = "不保事项")
    @Column(name = "disagree_detail", length = 256)
    private String exclusionsInfo;

    @Comment(value = "IMP")
    @Column(name = "imp", length = 50)
    private String imp;

    @Comment(value = "核保日期")
    @Column(name = "underwriting_date")
    private LocalDate uwDate;

    @Comment(value = "问题件标识状态")
    @Column(length = 1)
    private String prpInd;

}
