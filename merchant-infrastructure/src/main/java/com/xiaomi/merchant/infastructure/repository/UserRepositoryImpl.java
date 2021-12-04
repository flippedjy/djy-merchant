package com.xiaomi.merchant.infastructure.repository;

import com.xiaomi.merchant.domain.entity.User;
import com.xiaomi.merchant.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {



    @Override
    public User find(String userId) {
        return null;
    }
}
