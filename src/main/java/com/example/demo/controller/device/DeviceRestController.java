package com.example.demo.controller.device;


import com.example.demo.dto.device.DeviceLogDto;
import com.example.demo.dto.device.DeviceLogSubmitDto;
import com.example.demo.dto.user.UserTemperature;
import com.example.demo.repository.device.DeviceRepository;
import com.example.demo.service.device.DeviceService;
import com.example.demo.service.message.Message;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/rest/device/")
public class DeviceRestController {

    private final DeviceRepository deviceRepository;
    private final DeviceService deviceService;

    public DeviceRestController(DeviceRepository deviceRepository, DeviceService deviceService) {
        this.deviceRepository = deviceRepository;
        this.deviceService = deviceService;
    }

//    @RequestMapping(value = "submit", method = RequestMethod.GET)
//    public Object submit(HttpSession session, @RequestParam(value = "timestamp", required = true) Long timestamp,
//                         @RequestParam(value = "deviceId", required = true) int deviceId,
//                         @RequestParam(value = "username", required = true) String username,
//                         @RequestParam(value = "deviceType", required = true) String deviceType,
//                         @RequestParam(value = "location", required = true) String location,
//                         @RequestParam(value = "temperature", required = true) int temperature) {
//        if (deviceService.submitLog(session, deviceId, username, temperature, deviceType, timestamp, location)) {
//            return Message.SUCCESS;
//        }
//        return Message.PERMISSION_DENIED;
//    }

    @RequestMapping(value = "submit", method = RequestMethod.POST)
    public Object submit(HttpSession session, @RequestBody DeviceLogSubmitDto deviceLogSubmitDto) {
        if (deviceService.submitLog(session, deviceLogSubmitDto)) {
            return Message.SUCCESS;
        }
        return Message.PERMISSION_DENIED;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<DeviceLogDto> listDeviceLog(HttpSession session, @RequestParam(name = "start") int start,
                                            @RequestParam(name = "end") int end) {
        return deviceService.getList(start, end);
    }

    @RequestMapping(value = "list/user", method = RequestMethod.GET)
    public List<DeviceLogDto> listDeviceLogOfUser(HttpSession session) {
        return deviceService.getListByTimeInterval(1602664219*1000L,1602664219*1000L);
    }

    @RequestMapping(value = "list/user/temperature", method = RequestMethod.GET)
    public List<UserTemperature> listTemperatureOfUser(HttpSession session) {
        return deviceService.getListByTimeInterval();
    }


}
