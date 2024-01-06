package com.projecty.model.domain.policy.service;

import com.projecty.core.output.policy.PolicyOutput;
import com.projecty.core.output.policy.QPolicyOutput;
import com.projecty.infrastucture.exception.BusinessException;
import com.projecty.model.base.repository.policy.PolicyEntityRepository;
import com.projecty.model.domain.policy.entity.QPolicyDateInfo;
import com.projecty.model.domain.policy.entity.QPolicyEntity;
import com.projecty.model.domain.policy.entity.QUnderwriting;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 * @author 陈宇锋
 * @date 2024/1/4
 */

@Service
public class PolicyService{
    @Resource
    private PolicyEntityRepository policyEntityRepository;
    @Resource
    private JPAQueryFactory jpaQueryFactory;

    public void validatePolicyNoIsUsed(String policyNo) {
        boolean policyNoIsExists = policyEntityRepository.existsByPolicyNo(policyNo);
        if(policyNoIsExists){
            throw new BusinessException("保單號已經存在","");
        }
    }

    /*
    * @Description 根据保单id 查询保单输出信息
    * */
    public PolicyOutput queryPolicyOutput(String policyId){
        QPolicyEntity policyEntity = QPolicyEntity.policyEntity;
        QPolicyDateInfo policyDateInfo = QPolicyDateInfo.policyDateInfo;
        QUnderwriting underwriting = QUnderwriting.underwriting;
        return jpaQueryFactory.select(
                        new QPolicyOutput(
                                policyEntity.id,
                                policyEntity.policyNo,
                                policyEntity.policyStatus,
                                policyEntity.middleMan,
                                policyEntity.channel,
                                policyEntity.policyCurrency,
                                policyEntity.yearPremium,
                                policyEntity.paymentFrequency,
                                policyEntity.administrationType,
                                policyDateInfo.applicationDate,
                                policyDateInfo.policyEffectDate,
                                policyDateInfo.ensureEffectDate,
                                policyDateInfo.ensureExpireDate,
                                policyDateInfo.policyAdoptionDate,
                                policyEntity.proposalNo,
                                policyEntity.policyRemark,
                                underwriting.prpInd,
                                policyDateInfo.ndd
                        )
                )
                .from(policyEntity)
                .leftJoin(policyDateInfo)
                .on(policyEntity.id.eq(policyDateInfo.policyId).and(policyDateInfo.deleted.isFalse()))
                .leftJoin(underwriting)
                .on(policyEntity.id.eq(underwriting.policyId).and(underwriting.partyId.isNull()).and(underwriting.deleted.isFalse()))
                .where(policyEntity.id.eq(policyId).and(policyEntity.deleted.isFalse()))
                .fetchOne();
    }
}
