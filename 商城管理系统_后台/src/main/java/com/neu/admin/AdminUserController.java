package com.neu.admin;

import com.neu.common.validator.Order;
import com.neu.common.validator.Sort;
import com.neu.domain.ShoppingUser;
import com.neu.service.ShoppingUserService;
import com.neu.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/admin/user")
@Validated
public class AdminUserController {
    @Autowired
    private ShoppingUserService userService;

    /**
     *用户列表
     * @param username
     * @param mobile
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @GetMapping("/list1")
    public Object list(String username, String mobile,

                       @RequestParam(defaultValue = "1") Integer page,

                       @RequestParam(defaultValue = "10") Integer limit,

                       @Sort @RequestParam(defaultValue = "add_time") String sort,

                       @Order @RequestParam(defaultValue = "desc") String order) {

        List<ShoppingUser> userList = userService.querySelective(username, mobile, page, limit, sort, order);

        return ResponseUtil.okList(userList);

    }
}
