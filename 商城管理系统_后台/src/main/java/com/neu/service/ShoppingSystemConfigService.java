package com.neu.service;

import com.neu.dao.ShoppingSystemMapper;
import com.neu.vo.ShoppingSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ShoppingSystemConfigService {

//    @Autowired
//    private ShoppingSystemMapper systemMapper;
//
//    /**
//     * 对前台返回商场配置信息列表
//     * @return
//     */
//    public Map<String, String> listMail(ShoppingSystem shoppingSystem) {
//        List<ShoppingSystem> systemList = systemMapper.selectByExample(shoppingSystem);
//        Map<String, String> data = new HashMap<>();
//        for(ShoppingSystem system : systemList){
//            data.put(system.getKeyName(), system.getKeyValue());
//        }
//        return data;
//    }
//
//    /**
//     * 运费配置信息
//     * @param shoppingSystem
//     * @return
//     */
//    public Map<String, String> listExpress(ShoppingSystem shoppingSystem) {
//        List<ShoppingSystem> systemList = systemMapper.selectByExample1(shoppingSystem);
//        Map<String, String> data = new HashMap<>();
//        for(ShoppingSystem system : systemList){
//            data.put(system.getKeyName(), system.getKeyValue());
//        }
//        return data;
//    }
//
//    /**
//     * 商城首页配置信息
//     * @param shoppingSystem
//     * @return
//     */
//    public Map<String, String> listWx(ShoppingSystem shoppingSystem) {
//        List<ShoppingSystem> systemList = systemMapper.selectByExample2(shoppingSystem);
//        Map<String, String> data = new HashMap<>();
//        for(ShoppingSystem system : systemList){
//            data.put(system.getKeyName(), system.getKeyValue());
//        }
//        return data;
//    }
//
//    /**
//     * 修改当前运费配置信息
//     */
////    public void updateConfig(Map<String, String> data) {
////        for (Map.Entry<String, String> entry : data.entrySet()) {
////            LitemallSystemExample example = new LitemallSystemExample();
////            example.or().andKeyNameEqualTo(entry.getKey()).andDeletedEqualTo(false);
////
////            LitemallSystem system = new LitemallSystem();
////            system.setKeyName(entry.getKey());
////            system.setKeyValue(entry.getValue());
////            system.setUpdateTime(LocalDateTime.now());
////            systemMapper.updateByExampleSelective(system, example);
////    }
//    public int updateConfig(Map<String,Object> map){
//        try {
//            Iterator iter = map.entrySet().iterator();
//            while (iter.hasNext()) {
//                Map.Entry entry = (Map.Entry) iter.next();
//                String key = (String) entry.getKey();
//                String val = (String) entry.getValue();
//                systemMapper.updateByExampleSelective(key,val);
//            }
//            return 1;
//        }catch (Exception e){
//            e.printStackTrace();
//            return 0;
//        }
//    }
}
