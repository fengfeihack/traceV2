package dao;

import bean.ImageBean;
import bean.PageList;
import bean.RecorderBean;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


@Repository
public interface RecorderDAO {
    Integer insert(RecorderBean recorderInfo);
    Integer delete(Integer id);
    Integer update(RecorderBean recorder);
    List<RecorderBean> list(PageList pageList);
    Integer saveImage(ImageBean imageBean);
    Integer getRecorderNum(RecorderBean recorderBean);
    List<ImageBean> getImagesByRecorderImages(RecorderBean recorderBean);
    RecorderBean getRecorderById(Integer id);
}
