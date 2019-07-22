package api;

import bean.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import service.RecorderService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/api/recorder")
public class RecorderAPI {
    @Resource
    private RecorderService recorderService;
    @RequestMapping(value="/insert")
    @ResponseBody
    public Object insert(HttpServletRequest request,Long markerId,String title,Long logTime,String location,String partner,String note,String images)  {
        JSONObject jsonObject=new JSONObject();
        try {
            //获取userID
            Long userID = ((UserInfoBean) request.getSession().getAttribute("user")).getId();
            Long currentTime = System.currentTimeMillis() / 1000;
            RecorderBean recorderInfo = new RecorderBean();
            recorderInfo.setTitle(title);
            recorderInfo.setLogTime(logTime);
            recorderInfo.setLocation(location);
            recorderInfo.setPartner(partner);
            recorderInfo.setNote(note);
            recorderInfo.setImages(images);
            recorderInfo.setMarkerId(markerId);
            recorderInfo.setUserId(userID);
            recorderInfo.setCreateTime(currentTime);
            jsonObject.put("result", recorderService.insert(recorderInfo));
        }catch (Exception e){
            jsonObject.put("result",0);
            jsonObject.put("msg",e.getMessage());
        }
        return jsonObject;
    }
    @RequestMapping(value="/delete")
    @ResponseBody
    public Object deleteRecorder(Integer id) {
        JSONObject jsonObject = new JSONObject();
        try {
            RecorderBean recorderBean = recorderService.getRecorderById(id);
            jsonObject.put("result",recorderService.delete(recorderBean));
        }catch (Exception e){
            jsonObject.put("result",0);
            jsonObject.put("msg",e.getMessage());
        }
        return jsonObject;
    }
    //todo 编辑待确认
    @RequestMapping(value="/update")
    @ResponseBody
    public Object updateRecoder(HttpServletRequest request ,@RequestBody Map<String,Object> param) {
        JSONObject jsonObject=new JSONObject();
        String jsonStr = JSONObject.toJSONString(param.get("recorder"));
        RecorderBean recorder = JSON.parseObject(jsonStr,RecorderBean.class);
        jsonObject.put("result",recorderService.update(recorder));
        return jsonObject;
    }
    @RequestMapping(value="/list")
    @ResponseBody
    public Object selectAllRecoder(@RequestBody Map<String,Object> param,HttpServletRequest request) {
        JSONObject jsonObject=new JSONObject();
        UserInfoBean userInfoBean = ((UserInfoBean)request.getSession().getAttribute("user"));
        int startIndex;
        String pageJson = JSONObject.toJSONString(param.get("page"));
        Page page=JSON.parseObject(pageJson,Page.class);
        startIndex=(page.getPageNo()-1)*page.getPageSize();
        page.setStartIndex(startIndex);
        //获取提供的搜索条件
        String jsonString = JSONObject.toJSONString(param.get("recorder"));
        RecorderBean recorderInfo=JSON.parseObject(jsonString,RecorderBean.class);
        //获取userID
        recorderInfo.setUserId(userInfoBean.getId());
        //设置pagelist
        PageList pageList=new PageList();
        pageList.setPage(page);
        pageList.setRecorder(recorderInfo);
        try {
            List<RecorderBean> list= new ArrayList<>();
            List<RecorderBean> recorderBeans = recorderService.list(pageList);
            //设置总行数总页数
            if(recorderBeans!=null && recorderBeans.size()>0){
                for(RecorderBean recorderBean:recorderBeans){
                    if(recorderBean.getImages()!=null &&recorderBean.getImages().trim().length()>0){
                        List<ImageBean> imageBeans =  recorderService.getImagesByRecorderImages(recorderBean);
                        recorderBean.setImageBeanList(imageBeans);
                    }
                }
                list.addAll(recorderBeans);
                int totalRow=recorderService.getRecorderNum(null,userInfoBean.getId());
                page.setTotalRows(totalRow);
                if(totalRow%page.getPageSize()!=0) {
                    page.setTotalPages(totalRow/page.getPageSize()+1);
                }else {
                    page.setTotalPages(totalRow/page.getPageSize());
                }
            }
            jsonObject.put("result", 1);
            jsonObject.put("list", list);
            jsonObject.put("page", page);
        }catch (Exception e) {
            // TODO: handle exception
            System.err.println(e.getMessage());
            jsonObject.put("result", 0);
        }
        return jsonObject;
    }
    /**
     * 多个文件上传
     * @param files
     * @return
     */
    @RequestMapping(value = "/multiImageUpload", method = POST)
    @ResponseBody
    public Object uploadFile(@RequestParam(name = "file[]") MultipartFile[] files){
        //判断file数组不能为空并且长度大于0
        JSONObject jsonObject = new JSONObject();
       StringBuilder sb = new StringBuilder("");
        try {
            if (files != null && files.length > 0) {
                //循环获取file数组中得文件
                for (int i = 0; i < files.length; i++) {
                    MultipartFile file = files[i];
                    //保存文件
                    String filePath = FilenameUtils.concat("E:\\images", file.getOriginalFilename());
                    file.transferTo(new File(filePath));
                    ImageBean imageBean = new ImageBean();
                    imageBean.setCreateTime(System.currentTimeMillis()/1000);
                    imageBean.setPath(filePath);
                    Integer id=recorderService.saveImage(imageBean);
                    if(id != 0){
                        if(i==files.length-1){
                            sb.append(id.toString());
                        }else{
                            sb.append(id.toString()+",");
                        }
                    }
                    // 转存文件
                }
            }
            jsonObject.put("result",1);
            jsonObject.put("ids",sb);
        }catch (IOException e) {
            jsonObject.put("result",0);
            jsonObject.put("msg",e.getMessage());
        }
        //跳转视图
        return jsonObject;
    }
    @RequestMapping(value = "/getNum")
    @ResponseBody
    public Object recorderNum(HttpServletRequest request,Long markerId){
        JSONObject jsonObject = new JSONObject();
        try {
            UserInfoBean userInfoBean = (UserInfoBean) request.getSession().getAttribute("user");
            Integer num = recorderService.getRecorderNum(markerId,userInfoBean.getId());
            jsonObject.put("result",1);
            jsonObject.put("recorderNum",num);
        }catch (Exception e){
            jsonObject.put("result",0);
            jsonObject.put("msg",e.getMessage());
        }
        return jsonObject;
    }

}
