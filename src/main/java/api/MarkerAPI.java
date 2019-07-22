package api;

import bean.MarkerBean;
import bean.UserInfoBean;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.MarkerService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/api/marker")
public class MarkerAPI {
    @Resource
    private MarkerService markerService;

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(double pointX, double pointY, HttpServletRequest request) {
        UserInfoBean userInfoBean= (UserInfoBean) request.getSession().getAttribute("user");
        MarkerBean markerBean = new MarkerBean();
        JSONObject jsonObject = new JSONObject();
        //从数据库中查询，判断该点周围很近的位置是否已经存在，避免造成点的叠加
        List<MarkerBean> list = markerService.getMarkerByUser(userInfoBean.getId());
        for (MarkerBean marker2 : list) {
            //地图上标注周围0.05范围内不允许再产生标注
            if (Math.abs(marker2.getPointX() - pointX) < 0.05 && Math.abs(marker2.getPointY() - pointY) < 0.05) {
                jsonObject.put("result", 0);
                jsonObject.put("msg", "离其他足迹远点吧！");
                return jsonObject;
            }
        }
        markerBean.setPointX(pointX);
        markerBean.setPointY(pointY);
        markerBean.setCreateTime(System.currentTimeMillis() / 1000);
        markerBean.setUserId(userInfoBean.getId());
        jsonObject.put("result",markerService.insertMarker(markerBean));
        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Object markerList(HttpServletRequest request,Long userId) {
        JSONObject jsonObject=new JSONObject();
        try {
            if(userId==null){
                userId=((UserInfoBean)request.getSession().getAttribute("user")).getId();
            }
            List<MarkerBean> list=markerService.getMarkerByUser(userId);
            jsonObject.put("result",1);
            jsonObject.put("list", list);
            return jsonObject;
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            jsonObject.put("result", 0);
            return jsonObject;
        }
    }
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(Long id,HttpServletRequest request) {
        UserInfoBean userInfoBean = (UserInfoBean)request.getSession().getAttribute("user");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("result", markerService.deleteMarkerInfo(id,userInfoBean.getId()));
        }catch (Exception e){
            jsonObject.put("result",0);
            jsonObject.put("msg",e.getMessage());
        }
        return jsonObject;
    }

}

