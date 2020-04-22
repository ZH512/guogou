package com.neu.admin;

import com.neu.service.UserlistService;
import com.neu.util.response.BaseResponse;
import com.neu.vo.ShoppingUserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class AdminUserlistController {
    @Autowired
    private UserlistService userlistService;



    @GetMapping("/list")
    public ResponseEntity<ShoppingUserList> list(String username,
                                                 String mobile,
                                                 @RequestParam(defaultValue = "10") String limit,
                                                 @RequestParam (defaultValue = "1")String page,
                                                 @RequestParam (defaultValue = "desc")String order,
                                                 @RequestParam (defaultValue = "add_time")String sort
    ) {

        try {
            List<ShoppingUserList> row = userlistService.querySelective(username, mobile,limit,page,order,sort);
            if (row != null) {
                return BaseResponse.generateOKListResponseEntity(row);
            } else {
                return BaseResponse.generateBadResponseEntity(500, "没有找到信息", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
