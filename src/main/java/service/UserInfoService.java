package service;

import bean.UserInfoBean;

import java.security.GeneralSecurityException;
import java.util.List;

public interface UserInfoService {
    UserInfoBean getUserByUsernameAndPassword(UserInfoBean user);

    Integer updateUserInfo(UserInfoBean userInfoBean);

    Integer updateStatusByUsername(String username);

    void setSend(String host, String user, String pwd, String activeUsername, String activeEmail, String url) throws GeneralSecurityException;

    boolean checkUserName(String username);

    boolean saveUser(UserInfoBean userInfoBean);

    Integer deleteUser(String username);

    List<UserInfoBean> getUserUserInfoBeanById(Long userId);
}
