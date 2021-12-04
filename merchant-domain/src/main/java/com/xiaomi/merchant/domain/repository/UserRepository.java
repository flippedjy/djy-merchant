package com.xiaomi.merchant.domain.repository;

import com.xiaomi.merchant.domain.entity.User;

public interface UserRepository {

    User find(String userId);
}
