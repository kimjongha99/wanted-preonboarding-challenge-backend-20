package com.example.wantedchallenge.infrastructure.user;

import com.example.wantedchallenge.domain.user.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {
//    private final JPAQueryFactory queryFactory;


    //todo: 추후에 QueryDSL 추가
    @Override
    public List<User> findCustomUsers() {
        // QueryDSL을 사용한 예제 쿼리
//        QUser user = QUser.user;
//        return queryFactory.selectFrom(user)
//                .where(user.email.contains("example"))
//                .fetch();
        return null;
    }
}