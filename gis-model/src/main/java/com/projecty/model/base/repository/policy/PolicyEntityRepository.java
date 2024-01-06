package com.projecty.model.base.repository.policy;

import com.projecty.model.domain.policy.entity.PolicyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
@Repository
public interface PolicyEntityRepository extends JpaRepository<PolicyEntity,String> {

    boolean existsByPolicyNo(String policyNo);
}
