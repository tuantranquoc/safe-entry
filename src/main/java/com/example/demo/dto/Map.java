package com.example.demo.dto;

import com.example.demo.dto.device.DeviceLogDto;
import com.example.demo.dto.user.UserTemperature;
import com.example.demo.model.device.DeviceLog;
import org.springframework.stereotype.Service;

@Service
public class Map {
    public DeviceLogDto deviceLogDto(DeviceLog deviceLog) {
        DeviceLogDto deviceLogDto = new DeviceLogDto();
        deviceLogDto.setLocation(deviceLog.getLocation());
        deviceLogDto.setTemperature(deviceLog.getTemperature());
        deviceLogDto.setUsername(deviceLog.getUser().getUsername());
        deviceLogDto.setDeviceName(deviceLog.getDevice().getDeviceName());
        deviceLogDto.setTimestamp(deviceLog.getTimestamp());
        return deviceLogDto;
    }

    public UserTemperature userTemperature(DeviceLog deviceLog){
        UserTemperature userTemperature = new UserTemperature();
        userTemperature.setUserId(deviceLog.getUser().getId());
        userTemperature.setUsername(deviceLog.getUser().getUsername());
        userTemperature.setTemperature(deviceLog.getTemperature());
        userTemperature.setTimestamp(deviceLog.getTimestamp());
        return userTemperature;
    }
}
