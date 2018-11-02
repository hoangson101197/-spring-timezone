package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.TimeZone;

@Controller
public class TimeController {

    @GetMapping("/worldclock")
    public String getTimeByTimezone(ModelMap model, @RequestParam(name = "city", required = false, defaultValue = "Asia/Ho_Chi_Minh") String city) {

        //Nhận thời gian hiện tại tại địa phương
        Date date = new Date();
        // Nhận múi giờ theo địa phương
        TimeZone local = TimeZone.getDefault();
        //Nhận múi giờ theo thành phố được chỉ định
        TimeZone locale = TimeZone.getTimeZone(city);

        // Tính toán thời gian hiện tại trong thành phố được chỉ định
        long local_time = date.getTime() + (locale.getRawOffset() - local.getRawOffset());
        // Đặt lại ngày theo locale_time
        date.setTime(local_time);

        // Đặt dữ liệu được gửi đến chế độ xem
        model.addAttribute("city", city);
        model.addAttribute("date", date);
        return "index";
    }
}
