package service.impl;

import bean.CareInfoBean;
import dao.CareInfoDAO;
import org.springframework.stereotype.Service;
import service.CareInfoService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
public class CareInfoServiceImpl implements CareInfoService {
    @Resource
    private CareInfoDAO careInfoDAO;
@Override
public List<Long> getRecentCareFourUserId(Long userId) {
    List<CareInfoBean> careInfoBeanList = careInfoDAO.getRecentCareFourUserId(userId);
    List<Long> list = new ArrayList<>();
    list.add(careInfoBeanList.get(0).getBeCareID());
    list.add(careInfoBeanList.get(1).getBeCareID());
    list.add(careInfoBeanList.get(2).getBeCareID());
    list.add(careInfoBeanList.get(3).getBeCareID());
    return list;
}

    @Override
    public List<Long> getAllCareUserIds(Long userId) {
        List<CareInfoBean> careInfoBeanList = careInfoDAO.getAllCareUserIds(userId);
        List<Long> userIds = new ArrayList<>();
        if (careInfoBeanList!=null && careInfoBeanList.size()>0) {
            for(CareInfoBean careInfoBean:careInfoBeanList){
                userIds.add(careInfoBean.getBeCareID());
            }
        }
        return userIds;
    }
}
