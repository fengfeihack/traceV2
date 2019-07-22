package service.impl;

import bean.MarkerBean;
import dao.MarkerDAO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import service.MarkerService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MarkerServiceImpl implements MarkerService {
    @Resource
    private MarkerDAO markerDAO;

    @Cacheable(value = "marker", key = "'markers_'+#userId")
    @Override
    public List<MarkerBean> getMarkerByUser(Long userId) {
        return markerDAO.getMarkerByUser(userId);
    }

    @CacheEvict(value = "marker",key = "'markers_'+#markerBean.getUserId()")
    @Override
    public Integer insertMarker(MarkerBean markerBean) {
        return markerDAO.insertMarker(markerBean);
    }

    @CacheEvict(value = "marker",key = "'markers_'+#userId")
    @Override
    public Integer deleteMarkerInfo(Long id,Long userId) {
        return markerDAO.deleteMarkerInfo(id);
    }
}
