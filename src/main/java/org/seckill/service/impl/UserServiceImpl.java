package org.seckill.service.impl;

import org.seckill.dao.UserDao;
import org.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by never615 on 6/20/16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired private UserDao userDao;

    @Override
    public boolean isUserExist(long userId) {
        long count = userDao.isExistUser(userId);
        return count == 1;
    }



}
