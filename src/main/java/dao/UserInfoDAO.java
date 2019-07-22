package dao;

import bean.UserInfoBean;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDAO {
  UserInfoBean selectById(UserInfoBean userInfoBean);
  Integer  updateUserInfo(UserInfoBean userInfoBean);
  Integer updateStatusByUsername(String username);
  UserInfoBean getUserByUsername(String username);
  Integer saveUser(UserInfoBean userInfoBean);
  UserInfoBean getUserById(Long userId);
}
