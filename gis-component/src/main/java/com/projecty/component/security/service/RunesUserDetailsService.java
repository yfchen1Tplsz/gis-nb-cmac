package com.projecty.component.security.service;

import com.projecty.component.security.entity.QRelationWithUserAndRole;
import com.projecty.component.security.entity.QRoleEntity;
import com.projecty.component.security.entity.QUserEntity;
import com.projecty.component.security.entity.UserEntity;
import com.projecty.component.security.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("runesUserDetailsService")
@RequiredArgsConstructor
public class RunesUserDetailsService implements UserDetailsService {
    private final JPAQueryFactory jpaQueryFactory;
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                    ()->new UsernameNotFoundException("用户名或密码错误")
                );
        QUserEntity userEntity = QUserEntity.userEntity;
        QRoleEntity roleEntity = QRoleEntity.roleEntity;
        QRelationWithUserAndRole relationWithUserAndRole = QRelationWithUserAndRole.relationWithUserAndRole;
        List<String> roleList = jpaQueryFactory.select(roleEntity.role)
                .from(userEntity)
                .innerJoin(relationWithUserAndRole)
                .on(relationWithUserAndRole.userId.eq(userEntity.id))
                .innerJoin(roleEntity)
                .on(relationWithUserAndRole.roleId.eq(roleEntity.id))
                .where(userEntity.username.eq(username))
                .fetch();
        return RunesUserDetails.builder()
                .userEntity(user)
                .roleList(roleList)
                .build();
    }
}
