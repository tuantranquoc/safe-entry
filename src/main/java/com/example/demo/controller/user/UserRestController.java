package com.example.demo.controller.user;

import com.example.demo.dto.user.LoginDto;
import com.example.demo.dto.user.RegisterDto;
import com.example.demo.repository.device.DeviceLogRepository;
import com.example.demo.repository.device.DeviceRepository;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.service.device.DeviceService;
import com.example.demo.service.auth.AuthenticationService;
import com.example.demo.service.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/rest/user/")
public class UserRestController {
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final DeviceLogRepository deviceLogRepository;
    private final AuthenticationService authenticationService;
    private final DeviceService deviceService;

    @Autowired
    public UserRestController(UserRepository userRepository, DeviceRepository deviceRepository, DeviceLogRepository deviceLogRepository, AuthenticationService authenticationService, DeviceService deviceService) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.deviceLogRepository = deviceLogRepository;
        this.authenticationService = authenticationService;
        this.deviceService = deviceService;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Object login(HttpSession session, @RequestBody LoginDto loginDto) {
        if (authenticationService.login(loginDto, session)) {
            return Message.LOGIN_SUCCESS;
        }
        return Message.LOGIN_FAILURE;
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Object register(@RequestBody RegisterDto registerDto
                           ) {
        if (authenticationService.register(registerDto)) {
            return Message.REGISTER_SUCCESS;
        }
        return Message.REGISTER_FAILURE;
    }
}
