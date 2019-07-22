package service.impl;

import bean.ImageBean;
import bean.PageList;
import bean.RecorderBean;
import com.sun.org.apache.regexp.internal.RE;
import dao.RecorderDAO;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.RecorderService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecorderServiceImpl implements RecorderService {
    @Resource
    private RecorderDAO recorderDAO;

    @Override
    @CacheEvict(value = "recorder",allEntries = true)
    public Integer insert(RecorderBean recorder) {
        return recorderDAO.insert(recorder);
    }

    @Override
    @CacheEvict(value = "recorder", allEntries = true)
    public Integer delete(RecorderBean recorderBean) {
        return recorderDAO.delete(recorderBean.getId());
    }

    @Override
    @CacheEvict(value = "recorder",allEntries = true)
    public Integer update(RecorderBean recorder) {
        return recorderDAO.update(recorder);
    }

    @Override
    @Cacheable(value = "recorder",key = "'recorder_'+#p0.recorder.userId")
    public List<RecorderBean> list(PageList pageList) {
        return recorderDAO.list(pageList);
    }

    @Override
    public Integer saveImage(ImageBean imageBean) {
        if (recorderDAO.saveImage(imageBean) == 1) {
            return imageBean.getId();
        } else {
            return 0;
        }
    }

    @Override
    @Cacheable(value = "recorder",key = "'recorderNum_'+#userId")
    public Integer getRecorderNum(Long markerId, Long userId) {
        RecorderBean recorderBean = new RecorderBean();
        recorderBean.setMarkerId(markerId);
        recorderBean.setUserId(userId);
        return recorderDAO.getRecorderNum(recorderBean);
    }

    @Override
    public List<ImageBean> getImagesByRecorderImages(RecorderBean recorderBean) {
        return  recorderDAO.getImagesByRecorderImages(recorderBean);

    }

    @Override
    @Cacheable(value="recorder",key = "'recorderInfo'+#id")
    public RecorderBean getRecorderById(Integer id) {
        return recorderDAO.getRecorderById(id);
    }


}
