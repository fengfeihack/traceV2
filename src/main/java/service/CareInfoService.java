package service;

import org.springframework.stereotype.Service;

import java.util.List;


public interface CareInfoService {
 List<Long> getRecentCareFourUserId(Long userId);
 List<Long> getAllCareUserIds(Long userId);
}
