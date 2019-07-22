package dao;

import bean.CareInfoBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareInfoDAO {
  List<CareInfoBean> getRecentCareFourUserId(Long userId);
  List<CareInfoBean> getAllCareUserIds(Long userId);
}
