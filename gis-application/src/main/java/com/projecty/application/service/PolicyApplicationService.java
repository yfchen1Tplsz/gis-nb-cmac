package com.projecty.application.service;

import com.projecty.core.application.PolicyApplication;
import com.projecty.core.input.policy.GeneralEnquiryPolicyListInput;
import com.projecty.core.input.policy.PolicyListInput;
import com.projecty.core.input.policy.PolicySaveInput;
import com.projecty.core.output.policy.*;
import com.projecty.model.domain.policy.aggregate.PolicyAggregate;
import com.projecty.model.domain.policy.factory.PolicyFactory;
import com.projecty.model.domain.policy.persist.PolicyDao;
import com.projecty.model.domain.policy.service.PolicyService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
@Service
public class PolicyApplicationService implements PolicyApplication {
    @Resource
    private PolicyFactory policyFactory;
    @Resource
    private PolicyDao policyDao;
    @Resource
    private PolicyService policyService;
    @Override
    @Transactional
    public PolicyOutput createNbPolicy(String policyNo) {
        PolicyAggregate policyAggregate = policyFactory.createNbPolicy(policyNo);
        policyDao.persist(policyAggregate);
        return policyService.queryPolicyOutput(policyAggregate.getId());
    }

    @Override
    public PolicyDetailOutput queryPolicyDetail(String id) {
        return null;
    }

    @Override
    public PolicyListOutput queryPolicyList(PolicyListInput policyListInput) {
        return null;
    }

    @Override
    public String queryAttribute(String id) {
        return null;
    }

    @Override
    public OrganizationOutput queryOrganizationTree(String id) {
        return null;
    }

    @Override
    public PolicyOutput backToUw(String id) {
        return null;
    }

    @Override
    public PolicyOutput workSave(String id, PolicySaveInput policySaveInput) {
        return null;
    }

    @Override
    public GeneralEnquiryPolicyListOutput generalQueryPolicyList(GeneralEnquiryPolicyListInput generalEnquiryPolicyListInput) {
        return null;
    }

    @Override
    public PolicyOutput updatePolicyStatusNbToUw(String id) {
        return null;
    }

    @Override
    public PolicyRecordOutput queryPolicyRecord(String id) {
        return null;
    }
}
