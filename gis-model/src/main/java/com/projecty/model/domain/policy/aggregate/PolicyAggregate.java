package com.projecty.model.domain.policy.aggregate;

import com.projecty.core.input.policy.PolicySaveInput;
import com.projecty.core.input.policy.PolicyUwAnnotationInput;
import com.projecty.infrastucture.enums.PolicyCurrencyType;
import com.projecty.infrastucture.enums.PolicyStatusType;
import com.projecty.infrastucture.enums.YesOrNoType;
import com.projecty.infrastucture.utils.SecurityUtils;
import com.projecty.model.domain.policy.entity.CommentInfo;
import com.projecty.model.domain.policy.entity.PolicyDateInfo;
import com.projecty.model.domain.policy.entity.PolicyEntity;
import com.projecty.model.domain.policy.entity.Underwriting;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.projecty.model.domain.policy.mapper.PolicyMapper.POLICY_MAPPER;

/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
@Data
@Builder
@AllArgsConstructor
public class PolicyAggregate {
    private PolicyEntity policyEntity;
    private PolicyDateInfo policyDateInfo;
    private Underwriting policyUnderwriting;
    private List<CommentInfo> policyCommentList;

    @QueryProjection
    public PolicyAggregate(PolicyEntity policyEntity, PolicyDateInfo policyDateInfo, Underwriting policyUnderwriting) {
        this.policyEntity = policyEntity;
        this.policyDateInfo = policyDateInfo;
        this.policyUnderwriting = policyUnderwriting;
    }

    public String getId(){
        return this.policyEntity.getId();
    }
    /*
    * @description: 实体之间建立id联系，凝聚聚合根
    * */
    public void coagulateAggregate(){
        String entityUUID = StringUtils.hasText(this.policyEntity.getId())? this.policyEntity.getId():UUID.randomUUID().toString();
        policyEntity.setId(entityUUID);

        String policyDateInfoId = StringUtils.hasText(this.policyDateInfo.getId())? this.policyDateInfo.getId():UUID.randomUUID().toString();
        policyDateInfo.setId(policyDateInfoId);
        policyDateInfo.setPolicyId(entityUUID);

        String underwritingId = StringUtils.hasText(this.policyUnderwriting.getId())? this.policyUnderwriting.getId():UUID.randomUUID().toString();
        policyUnderwriting.setId(underwritingId);
        policyUnderwriting.setPolicyId(entityUUID);

        if(!CollectionUtils.isEmpty(policyCommentList)){
            for (CommentInfo commentInfo : policyCommentList) {
                String commentId = StringUtils.hasText(commentInfo.getId())? commentInfo.getId():UUID.randomUUID().toString();
                commentInfo.setId(commentId);
                commentInfo.setPolicyId(entityUUID);
            }
        }
    }

    /*
     * @description: 变更保单状态为 policyStatusType
     * */
    public void changePolicyStatusTo(PolicyStatusType policyStatusType){
        this.policyEntity.setPolicyStatus(policyStatusType.code());
    }

    /*
     * @description: 变更保单作业用户和作业日期
     * */
    public void updateWorkTimeAndUser(){
        this.policyEntity.setUserId(SecurityUtils.getAuthenticationUser());
        this.policyEntity.setWorkDate(LocalDate.now());
    }

    /*
     * @description: 为保单添加一条批注
     * */
    public void addPolicyComment(PolicyUwAnnotationInput input, boolean isTemporarySave){
        if(isTemporarySave){
            this.addTemporaryPolicyComment(input);
        }else {
            this.addPermanentPolicyComment(input);
        }
    }

    /*
     * @description: 为保单添加一条提交批注
     * */
    public void addPermanentPolicyComment(PolicyUwAnnotationInput input) {
        CommentInfo comment = CommentInfo.builder()
                .id(UUID.randomUUID().toString())
                .policyId(this.getId())
                .commentDate(LocalDateTime.now())
                .commentContent(input.getAnnotationContent())
                .userId(SecurityUtils.getAuthenticationUser())
                .saveInd(YesOrNoType.NO.code())
                .build();
        if(CollectionUtils.isEmpty(this.policyCommentList)){
           this.policyCommentList = new ArrayList<>();
           this.policyCommentList.add(comment);
           return;
        }
        Optional<CommentInfo> preTemporaryComment = this.policyCommentList.stream().filter(c -> YesOrNoType.YES.code().equals(c.getSaveInd())).findFirst();
        if(preTemporaryComment.isPresent()){
            CommentInfo temporaryComment = preTemporaryComment.get();
            temporaryComment.setSaveInd(YesOrNoType.NO.code());
            temporaryComment.setCommentContent(input.getAnnotationContent());
            temporaryComment.setCommentDate(LocalDateTime.now());
            temporaryComment.setUserId(SecurityUtils.getAuthenticationUser());
            temporaryComment.setCreatedBy(SecurityUtils.getAuthenticationUser());
            temporaryComment.setCreatedTime(LocalDateTime.now().toInstant(ZoneOffset.UTC));
        }else{
            this.policyCommentList.add(comment);
        }
    }

    /*
     * @description: 为保单添加一条暂存批注
     * */
    public void addTemporaryPolicyComment(PolicyUwAnnotationInput input) {
        CommentInfo comment = CommentInfo.builder()
                .id(UUID.randomUUID().toString())
                .policyId(this.getId())
                .commentDate(LocalDateTime.now())
                .commentContent(input.getAnnotationContent())
                .userId(SecurityUtils.getAuthenticationUser())
                .saveInd(YesOrNoType.YES.code())
                .build();
        if(CollectionUtils.isEmpty(this.policyCommentList)){
            this.policyCommentList = new ArrayList<>();
            this.policyCommentList.add(comment);
            return;
        }
        Optional<CommentInfo> preTemporaryComment = this.policyCommentList.stream().filter(c -> YesOrNoType.YES.code().equals(c.getSaveInd())).findFirst();
        if(preTemporaryComment.isPresent()){
            CommentInfo temporaryComment = preTemporaryComment.get();
            temporaryComment.setCommentContent(input.getAnnotationContent());
            temporaryComment.setCommentDate(LocalDateTime.now());
            temporaryComment.setUserId(SecurityUtils.getAuthenticationUser());
            temporaryComment.setCreatedBy(SecurityUtils.getAuthenticationUser());
            temporaryComment.setCreatedTime(LocalDateTime.now().toInstant(ZoneOffset.UTC));
        }else{
            this.policyCommentList.add(comment);
        }
    }

    /*
     * @description: 根据PolicySaveInput 修改保单数据
     * */
    public void updateWithPolicySaveInput(PolicySaveInput policySaveInput){
        POLICY_MAPPER.updateEntityFromPolicySaveInput(this.policyEntity,policySaveInput);
        if(!StringUtils.hasText(policySaveInput.getPolicyCurrency())){
            this.policyEntity.setPolicyCurrency(PolicyCurrencyType.HKD.code());
        }
        POLICY_MAPPER.updatePolicyDateInfo(this.policyDateInfo,policySaveInput);
        // 核保决定发生改变，更新核保日期
        if ((!StringUtils.hasText(this.policyUnderwriting.getUwResult()) && StringUtils.hasText(policySaveInput.getUwResult()))
                || (StringUtils.hasText(this.policyUnderwriting.getUwResult()) && !this.policyUnderwriting.getUwResult().equals(policySaveInput.getUwResult()))) {
            this.policyUnderwriting.setUwDate(LocalDate.now());
        }
        POLICY_MAPPER.updateUnderwriting(this.policyUnderwriting,policySaveInput);
        this.policyEntity.setUserId(SecurityUtils.getAuthenticationUser());
    }

    /*
     * @description: 软删除该保单
     * */
    public void softDeletePolicy(){
        this.policyEntity.setDeleted(true);
        this.policyDateInfo.setDeleted(true);
        this.policyUnderwriting.setDeleted(true);
        if(!CollectionUtils.isEmpty(this.policyCommentList)){
            policyCommentList.forEach(k->k.setDeleted(true));
        }
    }
}
