package com.example.demo.service.device;

import com.example.demo.dto.device.DeviceLogDto;
import com.example.demo.dto.device.DeviceLogSubmitDto;
import com.example.demo.dto.Map;
import com.example.demo.dto.user.UserTemperature;
import com.example.demo.model.device.DeviceLog;
import com.example.demo.repository.device.DeviceLogRepository;
import com.example.demo.repository.device.DeviceRepository;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.service.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final DeviceLogRepository deviceLogRepository;
    private final Map map;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, AuthenticationService authenticationService, UserRepository userRepository, DeviceLogRepository deviceLogRepository, Map map) {
        this.deviceRepository = deviceRepository;
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
        this.deviceLogRepository = deviceLogRepository;
        this.map = map;
    }

    public boolean submitLog(HttpSession session, DeviceLogSubmitDto deviceLogSubmitDto) {
        if (deviceRepository.findDeviceByDeviceTypeAndId(deviceLogSubmitDto.getDeviceType(), deviceLogSubmitDto.getDeviceId()) == null ||
                userRepository.findUserByUsername(deviceLogSubmitDto.getUsername()) == null ||
                !authenticationService.checkLogin(session)) {
            return false;
        }
        DeviceLog deviceLog = new DeviceLog();
        deviceLog.setDevice(deviceRepository.findDeviceById(deviceLogSubmitDto.getDeviceId()));
        deviceLog.setTimestamp(deviceLogSubmitDto.getTimestamp());
        deviceLog.setLocation(deviceLogSubmitDto.getLocation());
        deviceLog.setTemperature(deviceLogSubmitDto.getTemperature());
        deviceLog.setUser(userRepository.findUserByUsername(deviceLogSubmitDto.getUsername()));
        deviceLogRepository.save(deviceLog);
        return true;
    }

    public List<DeviceLogDto> getList(int start, int end) {
        List<DeviceLogDto> deviceLogDtoList = new ArrayList<>();
        Pageable paging = PageRequest.of(start, end);
        Page<DeviceLog> deviceLogPage = deviceLogRepository.findAll(paging);
        System.out.println(deviceLogPage.stream().count());
        for (DeviceLog deviceLog :
                deviceLogPage) {
            deviceLogDtoList.add(map.deviceLogDto(deviceLog));
        }
        return deviceLogDtoList;
    }

    public List<DeviceLogDto> getListByTimeInterval(Long fromTimestamp, Long toTimestamp) {
        List<DeviceLogDto> deviceLogDtoList = new ArrayList<>();
        Long pastSixMonths = pastSixMonths();
        Long currentTime = System.currentTimeMillis();
        List<DeviceLog> deviceLogList = deviceLogRepository.findByUserIdAndTimestampGreaterThanEqualAndTimestampLessThanEqual(7, pastSixMonths, currentTime);
        for (DeviceLog deviceLog :
                deviceLogList) {
            deviceLogDtoList.add(map.deviceLogDto(deviceLog));
        }
        return deviceLogDtoList;
    }

    public Long pastSixMonths() {
        LocalDate currentDate = LocalDate.now();
        LocalDate result = currentDate.minus(6, ChronoUnit.MONTHS);
        Timestamp ts = new Timestamp(convertToDateViaInstant(result).getTime());
        return ts.getTime();
    }

    public Date convertToDateViaInstant(LocalDate dateToConvert) {
        return Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public List<UserTemperature> getListByTimeInterval() {
        List<UserTemperature> userTemperatureList = new ArrayList<>();
        Long pastSixMonths = pastSixMonths();
        Long currentTime = System.currentTimeMillis();
        List<DeviceLog> deviceLogList = deviceLogRepository.findByUserIdAndTimestampGreaterThanEqualAndTimestampLessThanEqual(7, pastSixMonths, currentTime);
        for (DeviceLog deviceLog :
                deviceLogList) {
            userTemperatureList.add(map.userTemperature(deviceLog));
        }
        return userTemperatureList;
    }

}
