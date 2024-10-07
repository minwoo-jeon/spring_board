package com.myportfolio.web.dao;

import com.myportfolio.web.domain.UserDTO;

public interface UserDao {
    UserDTO selectUser(String id) throws Exception;
    int deleteUser(String id) throws Exception;
    int insertUser(UserDTO user) throws Exception;
    int updateUser(UserDTO user) throws Exception;
    int count() throws Exception;
    void deleteAll() throws Exception;
}