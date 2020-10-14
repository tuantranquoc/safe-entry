package com.example.demo.repository.device;

import com.example.demo.model.device.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
    Device findDeviceById(int id);
    Device findDeviceByDeviceTypeAndId(String deviceType, int id);
}
