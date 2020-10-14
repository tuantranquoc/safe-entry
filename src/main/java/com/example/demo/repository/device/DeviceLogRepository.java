package com.example.demo.repository.device;

import com.example.demo.model.device.DeviceLog;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceLogRepository extends JpaRepository<DeviceLog, Integer> {
    DeviceLog findDeviceLogById(int id);
    List<DeviceLog> findByUserIdAndTimestampGreaterThanEqualAndTimestampLessThanEqual(int userId, Long fromTimestamp, Long toTimestamp);
}
