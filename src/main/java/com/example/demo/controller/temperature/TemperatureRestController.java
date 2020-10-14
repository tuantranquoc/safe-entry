package com.example.demo.controller.temperature;


import com.example.demo.repository.device.DeviceLogRepository;
import com.example.demo.repository.device.DeviceRepository;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.service.device.DeviceService;
import com.example.demo.service.auth.AuthenticationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class TemperatureRestController {

    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final DeviceLogRepository deviceLogRepository;
    private final AuthenticationService authenticationService;
    private final DeviceService deviceService;

    public TemperatureRestController(UserRepository userRepository, DeviceRepository deviceRepository,
                                     DeviceLogRepository deviceLogRepository, AuthenticationService authenticationService, DeviceService deviceService) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.deviceLogRepository = deviceLogRepository;
        this.authenticationService = authenticationService;
        this.deviceService = deviceService;
    }
}
