package api;

import bean.UserInfoBean;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CareInfoService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/api/careInfo")
public class CareInfoAPI {
    @Resource
    private CareInfoService careInfoService;

    @RequestMapping(value = "/getRecentCareUser")
    @ResponseBody
    public Object recentCareUserLimitFor(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        try {
            Long userId = ((UserInfoBean) request.getSession().getAttribute("user")).getId();
            List<Long> list = careInfoService.getRecentCareFourUserId(userId);
            jsonObject.put("result", 1);
            jsonObject.put("list", list);
        } catch (Exception e) {
            jsonObject.put("result", 0);
            jsonObject.put("msg", e.getMessage());
        }
        return jsonObject;
    }

    @RequestMapping(value = "/getCareUserIds")
    @ResponseBody
    public Object careUserIdList(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        try{
            Long userId = ((UserInfoBean) request.getSession().getAttribute("user")).getId();
            List<Long> list = careInfoService.getAllCareUserIds(userId);
            jsonObject.put("result",1);
            jsonObject.put("list",list);
        }catch (Exception e){
            jsonObject.put("result", 0);
            jsonObject.put("msg", e.getMessage());
        }
        return jsonObject;
    }
}
