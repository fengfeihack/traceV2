package auth;

import bean.UserInfoBean;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserInfoService;
import service.impl.UserInfoServiceImp;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.GeneralSecurityException;


@Controller
@RequestMapping(value = "/auth")
public class auth {
@Resource
private UserInfoService userInfoService;
@RequestMapping(value = "/login",method = RequestMethod.POST)
@ResponseBody
public Object login(HttpServletRequest req, String username, String password) throws Exception {

    UserInfoBean user = new UserInfoBean();
    user.setUsername(username);
    user.setPassword(password);
    JSONObject jsonObject = new JSONObject();
    UserInfoBean userInfoBean = userInfoService.getUserByUsernameAndPassword(user);
    if (userInfoBean != null) {
        req.getSession().setAttribute("user", userInfoBean);
        jsonObject.put("result", 1);
    } else {
        jsonObject.put("result", 0);
    }
    return jsonObject;
}
    @ResponseBody
    @RequestMapping("/register")
    public Object regMember(HttpServletRequest req, String username,String password,String email){
        JSONObject jsonObject = new JSONObject();
        if(!userInfoService.checkUserName(username)){
            jsonObject.put("result",0);
            jsonObject.put("msg","用户名已被占用");
        }else {
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.setUsername(username);
            userInfoBean.setPassword(password);
            userInfoBean.setEmail(email);
            if (userInfoService.saveUser(userInfoBean)) {
                //session中存临时的用户名和邮箱，便于发送给用户发送邮箱激活
                req.getSession().setAttribute("tempUser", userInfoBean.getUsername());
                req.getSession().setAttribute("tempEmail", userInfoBean.getEmail());
                jsonObject.put("result", 1);
            } else {
                jsonObject.put("result", 0);
                jsonObject.put("msg","注册信息保存失败");
            }
        }
        return jsonObject;
    }

    /**
     * 给用户发送包含激活地址的邮件
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/activate")
    public Object sendMessage(HttpServletRequest request) {

        UserInfoServiceImp userInfoServiceImp = new UserInfoServiceImp();
        JSONObject jsonObject = new JSONObject();
        //todo 邮箱写配置
        String activeUsername = request.getSession().getAttribute("tempUser").toString();
        String activeEmail = request.getSession().getAttribute("tempEmail").toString();
        String from = "1655918202@qq.com" ;
        String pwd = "adjgquwksrrvebha";
        String smtp = "smtp.qq.com";
        String url = "http://127.0.0.1:8080/trace/auth/activeUserAccount?username=";
        userInfoServiceImp.setAddress(from,activeEmail, "记忆旅行账户激活");
        try {
            userInfoServiceImp.setSend(smtp, from, pwd, activeUsername,activeEmail,url);
            jsonObject.put("result", 1);
        } catch (GeneralSecurityException e) {
            // TODO Auto-generated catch block
        }
        return jsonObject;
    }

    /**
     *
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/activeUserAccount", method = RequestMethod.GET)
    public JSONObject activeUserAccount(String username){
        JSONObject jsonObject = new JSONObject();
        try {
            Integer result = userInfoService.updateStatusByUsername(username);
            jsonObject.put("result", result);
        }catch (Exception e){
            jsonObject.put("result",0);
            jsonObject.put("msg",e.getMessage());
        }
        return jsonObject;
    }

}
