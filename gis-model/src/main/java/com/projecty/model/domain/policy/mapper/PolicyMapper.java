package com.projecty.model.domain.policy.mapper;

import com.projecty.core.input.policy.PolicySaveInput;
import com.projecty.model.domain.policy.entity.PolicyDateInfo;
import com.projecty.model.domain.policy.entity.PolicyEntity;
import com.projecty.model.domain.policy.entity.Underwriting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
@Mapper
public interface PolicyMapper {
    PolicyMapper POLICY_MAPPER = Mappers.getMapper(PolicyMapper.class);

    @Mapping(target = "policyStatus", ignore = true)
    void updateEntityFromPolicySaveInput(@MappingTarget PolicyEntity policyEntity, PolicySaveInput policySaveInput);


    void updatePolicyDateInfo(@MappingTarget PolicyDateInfo policyDateInfo,PolicySaveInput policySaveInput);

    @Mapping(target = "prpInd", source = "policyPrp")
    void updateUnderwriting(@MappingTarget Underwriting policyUnderwriting, PolicySaveInput policySaveInput);
}
