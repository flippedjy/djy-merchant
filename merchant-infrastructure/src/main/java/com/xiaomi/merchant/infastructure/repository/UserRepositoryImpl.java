package com.xiaomi.merchant.infastructure.repository;

import com.xiaomi.merchant.domain.entity.User;
import com.xiaomi.merchant.domain.repository.UserRepository;
import com.xiaomi.merchant.infastructure.dao.UserDao;
import com.xiaomi.merchant.infastructure.dataobj.UserDo;
import com.xiaomi.merchant.type.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class UserRepositoryImpl implements UserRepository {


    @Autowired
    private UserDao userDao;

    @Override
    public User find(String userId) {
        UserDo userDo = userDao.selectByUserId(userId);
        if(Objects.isNull(userDo)){
            throw new RuntimeException("not a valid user");
        }
        User user = new User(userDo.getUserId(),userDo.getName(),new Phone(userDo.getPhone()),userDo.getXiaomiId());
        return user;
    }
}
