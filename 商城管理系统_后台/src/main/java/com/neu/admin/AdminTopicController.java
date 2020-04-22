package com.neu.admin;

import com.neu.common.RequiresPermissionsDesc;
import com.neu.common.validator.Order;
import com.neu.common.validator.Sort;
import com.neu.domain.ShoppingGoods;
import com.neu.service.ShoppingGoodsService;
import com.neu.service.ShoppingTopicService;
import com.neu.util.response.BaseResponse;
import com.neu.vo.ShoppingTopic;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/admin/topic")
@Validated
public class AdminTopicController {
    private final Log logger = LogFactory.getLog(AdminTopicController.class);

    @Autowired
    private ShoppingTopicService topicService;
    @Autowired
    private ShoppingGoodsService goodsService;
    /**
    　　* @description: 查询专题信息
     　　* @author ll
    　　* @date 2019/11/20 13:56
    　　*/
    @RequiresPermissions("admin:topic:list")
    @RequiresPermissionsDesc(menu = {"推广管理", "专题管理"}, button = "查询")
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<ShoppingTopic>> list(String title, String subtitle,
                                                            @RequestParam(defaultValue = "1") Integer page,
                                                            @RequestParam(defaultValue = "10") Integer limit,
                                                            @Sort @RequestParam(defaultValue = "add_time") String sort,
                                                            @Order @RequestParam(defaultValue = "desc") String order) {
        List<ShoppingTopic> topicList = topicService.querySelective(title, subtitle, page, limit, sort, order);
        return BaseResponse.generateOKListResponseEntity(topicList);
    }
    /**
    　　* @description: 添加专题管理
    　　* @author ll
    　　* @date 2019/11/20 13:56
    　　*/
    @RequiresPermissions("admin:topic:create")
    @RequiresPermissionsDesc(menu = {"推广管理", "专题管理"}, button = "添加")
    @PostMapping("/create")
    public ResponseEntity<BaseResponse<ShoppingTopic>> create(@RequestBody ShoppingTopic topic) {
        int count = topicService.add(topic);
        if (count > 0) {
            return BaseResponse.generateOKResponseEntity("添加成功");
        }
        return BaseResponse.badArgument();
    }
    /**
    　　* @description: 查询专题详情
    　　* @author ll
    　　* @date 2019/11/20 13:55
    　　*/
    @RequiresPermissions("admin:topic:read")
    @RequiresPermissionsDesc(menu = {"推广管理", "专题管理"}, button = "详情")
    @GetMapping("/read")
    public ResponseEntity<BaseResponse<ShoppingTopic>> read(@RequestParam Integer id) {
        ShoppingTopic topic = topicService.findById(id);
        Integer[] goodsIds = topic.getGoods();
        List<ShoppingGoods> goodsList = null;
        if (goodsIds == null || goodsIds.length == 0) {
            goodsList = new ArrayList<>();
        } else {
            goodsList = goodsService.queryByIds(goodsIds);
        }
        Map<String, Object> data = new HashMap<>(2);
        data.put("topic", topic);
        data.put("goodsList", goodsList);
        return BaseResponse.generateOKResponseEntity(data);
    }
    /**
    　　* @description: 编辑专题管理
    　　* @author ll
    　　* @date 2019/11/20 13:56
    　　*/
    @RequiresPermissions("admin:topic:update")
    @RequiresPermissionsDesc(menu = {"推广管理", "专题管理"}, button = "编辑")
    @PostMapping("/update")
    public ResponseEntity<BaseResponse<ShoppingTopic>> update(@RequestBody ShoppingTopic topic) {
        if (topicService.updateById(topic) == 0) {
            return BaseResponse.badArgument();
        }
        return BaseResponse.generateOKResponseEntity("编辑成功");
    }
    /**
    　　* @description: 删除专题管理
    　　* @author ll
    　　* @date 2019/11/20 13:57
    　　*/
    @RequiresPermissions("admin:topic:delete")
    @RequiresPermissionsDesc(menu = {"推广管理", "专题管理"}, button = "删除")
    @PostMapping("/delete")
    public ResponseEntity<BaseResponse<ShoppingTopic>> delete(@RequestBody ShoppingTopic topic) {
        int count = topicService.deleteById(topic.getId());
        if (count > 0) {
            return BaseResponse.generateOKResponseEntity("删除成功");
        }
        return BaseResponse.badArgument();
    }

}
