package service;

import bean.MarkerBean;

import java.util.List;

public interface MarkerService {
    List<MarkerBean> getMarkerByUser(Long userId);
    Integer insertMarker(MarkerBean markerBean);
    Integer deleteMarkerInfo(Long id,Long userId);
}
