package com.projecty.model.domain.policy.factory;

import com.projecty.infrastucture.enums.PolicyCurrencyType;
import com.projecty.infrastucture.enums.PolicyStatusType;
import com.projecty.infrastucture.utils.SecurityUtils;
import com.projecty.model.domain.policy.aggregate.PolicyAggregate;
import com.projecty.model.domain.policy.entity.PolicyDateInfo;
import com.projecty.model.domain.policy.entity.PolicyEntity;
import com.projecty.model.domain.policy.entity.Underwriting;
import com.projecty.model.domain.policy.service.PolicyService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
@Service
public class PolicyFactory {
    @Resource
    private PolicyService policyService;

    public PolicyAggregate createNbPolicy(String policyNo){
        policyService.validatePolicyNoIsUsed(policyNo);
        PolicyEntity policyEntity = PolicyEntity.builder()
                .policyNo(policyNo)
                .policyStatus(PolicyStatusType.NB.code())
                .policyCurrency(PolicyCurrencyType.HKD.code())
                .workDate(LocalDate.now())
                .userId(SecurityUtils.getAuthenticationUser())
                .build();
        PolicyAggregate policyAggregate = PolicyAggregate.builder()
                .policyEntity(policyEntity)
                .policyDateInfo(new PolicyDateInfo())
                .policyUnderwriting(new Underwriting())
                .build();
        policyAggregate.coagulateAggregate();
        return policyAggregate;
    }

}
