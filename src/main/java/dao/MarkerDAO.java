package dao;

import bean.MarkerBean;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MarkerDAO {
    List<MarkerBean> getMarkerByUser(Long userId);
    Integer insertMarker(MarkerBean markerBean);
    Integer deleteMarkerInfo(Long id);
}
