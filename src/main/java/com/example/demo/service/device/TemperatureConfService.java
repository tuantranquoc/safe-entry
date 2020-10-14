package com.example.demo.service.device;

import com.example.demo.dto.Map;
import com.example.demo.repository.conf.EmailConfRepository;
import com.example.demo.repository.conf.TemperatureConfRepository;
import com.example.demo.repository.device.DeviceLogRepository;
import com.example.demo.repository.device.DeviceRepository;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.service.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TemperatureConfService {
    private final DeviceRepository deviceRepository;
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final DeviceLogRepository deviceLogRepository;
    private final Map map;
    private final TemperatureConfRepository temperatureConfRepository;
    private final EmailConfRepository emailConfRepository;

    @Autowired
    public TemperatureConfService(DeviceRepository deviceRepository, AuthenticationService authenticationService,
                                  UserRepository userRepository, DeviceLogRepository deviceLogRepository, Map map, TemperatureConfRepository temperatureConfRepository, EmailConfRepository emailConfRepository) {
        this.deviceRepository = deviceRepository;
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
        this.deviceLogRepository = deviceLogRepository;
        this.map = map;
        this.temperatureConfRepository = temperatureConfRepository;
        this.emailConfRepository = emailConfRepository;
    }
}
