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

import java.time.LocalDateTime;

import static com.projecty.model.ModelModule.TableConstants.AUD_TABLE_COMMENT;
import static com.projecty.model.ModelModule.TableConstants.TABLE_COMMENT;

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
@Comment(value = "批注表")
@Table(name = TABLE_COMMENT, schema = ModelModule.SCHEMA_NB)
//@Audited
//@AuditTable(schema = ModelModule.SCHEMA_AUD, value = AUD_TABLE_COMMENT)
public class CommentInfo extends SoftDeletedBaseEntity {

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

    @Comment(value = "批注生成日期")
    @Column(name = "comment_date")
    private LocalDateTime commentDate;

    @Comment(value = "批注生成用户")
    @Column(name = "user_id", length = 50)
    private String userId;

    @Comment(value = "批注内容")
    @Column(name = "comment_content", columnDefinition = "text")
    private String commentContent;

    @Comment(value = "是否暂存标识")
    @Column(name = "save_ind", nullable = false)
    private String saveInd;

}
