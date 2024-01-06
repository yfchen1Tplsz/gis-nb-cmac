package com.projecty.core.application;

import com.projecty.core.input.policy.GeneralEnquiryPolicyListInput;
import com.projecty.core.input.policy.PolicyListInput;
import com.projecty.core.input.policy.PolicySaveInput;
import com.projecty.core.output.policy.*;


/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
public interface PolicyApplication {
    PolicyOutput createNbPolicy(String policyNo);

    PolicyDetailOutput queryPolicyDetail(String id);

    PolicyListOutput queryPolicyList(PolicyListInput policyListInput);

    String queryAttribute(String id);

    OrganizationOutput queryOrganizationTree(String id);

    PolicyOutput backToUw(String id);

    PolicyOutput workSave(String id, PolicySaveInput policySaveInput);

    GeneralEnquiryPolicyListOutput generalQueryPolicyList(GeneralEnquiryPolicyListInput generalEnquiryPolicyListInput);

    PolicyOutput updatePolicyStatusNbToUw(String id);

    PolicyRecordOutput queryPolicyRecord(String id);
}
