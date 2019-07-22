package service;

import bean.ImageBean;
import bean.PageList;
import bean.RecorderBean;

import java.util.List;

public interface RecorderService {
    Integer insert(RecorderBean recorder);
    Integer delete(RecorderBean recorderBean);
    Integer update(RecorderBean recorder);
    List<RecorderBean> list(PageList pageList);
    Integer saveImage(ImageBean imageBean);
    Integer getRecorderNum(Long markerId,Long userId);
    List<ImageBean> getImagesByRecorderImages(RecorderBean recorderBean);
    RecorderBean getRecorderById(Integer id);
}
