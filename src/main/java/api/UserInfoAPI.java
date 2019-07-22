package api;

import bean.UserInfoBean;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserInfoService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/api/userInfo")
public class UserInfoAPI {
@Resource
private UserInfoService userInfoService;

@RequestMapping(value = "/getUserInfo")
@ResponseBody

public Object detail(HttpServletRequest request) {
    JSONObject jsonObject = new JSONObject();
    try {
        UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("user");
        userInfoBean = userInfoService.getUserUserInfoBeanById(userInfoBean.getId()).get(0);
        jsonObject.put("userInfo", userInfoBean);
        jsonObject.put("result", 1);
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
        jsonObject.put("result", 0);
        jsonObject.put("errorMsg", e.getMessage());
    }
    return jsonObject;
}

/**
 * 修改信息
 */
@RequestMapping(value = "/update", method = RequestMethod.POST)
@ResponseBody
public Object update(HttpServletRequest request,String username,String email,String name) {
    JSONObject jsonObject = new JSONObject();
    try {
        UserInfoBean userInfoBean = new UserInfoBean();
        Long userId = ((UserInfoBean)request.getSession().getAttribute("user")).getId();
        userInfoBean.setId(userId);
        userInfoBean.setUsername(username);
        userInfoBean.setActualName(name);
        userInfoBean.setEmail(email);
        int result = userInfoService.updateUserInfo(userInfoBean);
        jsonObject.put("result",result);
    } catch (Exception e) {
        jsonObject.put("msg", e.getMessage());
        jsonObject.put("result", 0);
    }
    return jsonObject;
}
/**
 * 修改密码
 */
@RequestMapping(value="/updatePwd",method=RequestMethod.POST)
@ResponseBody
public Object updatePassword(HttpServletRequest request, String oldPwd,String newPwd) {
    JSONObject jsonObject = new JSONObject();
    int result;
    UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("user");
    if(userInfoBean.getPassword().equals(oldPwd)) {
        userInfoBean.setPassword(newPwd);
        result = userInfoService.updateUserInfo(userInfoBean);
        jsonObject.put("result", result);
    }else {
        jsonObject.put("result", 0);
        jsonObject.put("msg", "更新失败，原密码错误");
    }
    return jsonObject;
}

}
