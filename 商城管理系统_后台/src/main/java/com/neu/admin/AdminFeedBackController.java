package com.neu.admin;

import com.neu.common.validator.Order;
import com.neu.common.validator.Sort;
import com.neu.service.ShoppingFeedbackService;
import com.neu.util.response.BaseResponse;
import com.neu.vo.ShoppingFeedback;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/feedback")
@Api(description = "用户管理模块（意见反馈信息）")
public class AdminFeedBackController {
    @Autowired
    ShoppingFeedbackService feedbackService;

    @GetMapping("/list")
    public Object list(Integer userId, String username,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<ShoppingFeedback> feedbackList = feedbackService.querySelective(userId, username, page, limit, sort, order);
        return BaseResponse.generateOKListResponseEntity(feedbackList);
    }
}
