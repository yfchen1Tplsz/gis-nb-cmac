package com.projecty.model.domain.policy.persist;

import com.projecty.model.domain.policy.aggregate.PolicyAggregate;

/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
public interface PolicyDao {
    void persist(PolicyAggregate policyAggregate);
}
