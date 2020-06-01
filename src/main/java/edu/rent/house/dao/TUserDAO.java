package edu.rent.house.dao;

import edu.rent.house.pojo.TUser;
import edu.rent.house.pojo.TUserExample;
import org.springframework.stereotype.Repository;

/**
 * TUserDAO继承基类
 */
@Repository
public interface TUserDAO extends MyBatisBaseDao<TUser, Integer, TUserExample> {
}