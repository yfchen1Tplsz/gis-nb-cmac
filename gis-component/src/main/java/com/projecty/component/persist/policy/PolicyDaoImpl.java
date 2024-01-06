package com.projecty.component.persist.policy;

import cn.hutool.core.collection.CollectionUtil;
import com.projecty.model.base.repository.policy.CommentInfoRepository;
import com.projecty.model.base.repository.policy.PolicyDateInfoRepository;
import com.projecty.model.base.repository.policy.PolicyEntityRepository;
import com.projecty.model.base.repository.policy.UnderwritingRepository;
import com.projecty.model.domain.policy.aggregate.PolicyAggregate;
import com.projecty.model.domain.policy.persist.PolicyDao;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author 陈宇锋
 * @date 2024/1/5
 */
@Service
public class PolicyDaoImpl implements PolicyDao {
    @Resource
    private PolicyEntityRepository policyEntityRepository;
    @Resource
    private PolicyDateInfoRepository policyDateInfoRepository;
    @Resource
    private CommentInfoRepository commentInfoRepository;
    @Resource
    private UnderwritingRepository underwritingRepository;
    @Override
    public void persist(PolicyAggregate policyAggregate) {
        policyEntityRepository.save(policyAggregate.getPolicyEntity());
        if(policyAggregate.getPolicyDateInfo() != null) {
            policyDateInfoRepository.save(policyAggregate.getPolicyDateInfo());
        }
        if(policyAggregate.getPolicyUnderwriting() != null){
            underwritingRepository.save(policyAggregate.getPolicyUnderwriting());
        }
        if(CollectionUtil.isNotEmpty(policyAggregate.getPolicyCommentList())){
            commentInfoRepository.saveAll(policyAggregate.getPolicyCommentList());
        }
    }
}
