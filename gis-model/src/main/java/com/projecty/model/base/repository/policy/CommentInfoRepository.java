package com.projecty.model.base.repository.policy;

import com.projecty.model.domain.policy.entity.CommentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentInfoRepository extends JpaRepository<CommentInfo,String> {
}
