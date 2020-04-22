package com.neu.service;

import com.neu.dao.ShoppingSystemMapper;
import com.neu.vo.ShoppingSystem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class SystemConfigService {
    @Resource
    ShoppingSystemMapper shoppingSystemMapper;

    public Map<String, String> listExpress() {
        Map<String, String> map=new HashMap();
        List<ShoppingSystem> list=shoppingSystemMapper.selectByExample();
        for (int i = 0; i <list.size() ; i++) {
            if (list.get(i).getId()==2||list.get(i).getId()==9){
                map.put(list.get(i).getKey_name(),list.get(i).getKey_value());
            }
        }
        return map;
    }


    @Transactional
    public int updateConfig(Map<String, String> data) {
        int row=0,row1=0;
        Set<String> keySet = data.keySet();
        for (String key : keySet) {
            row1=shoppingSystemMapper.updateByExampleSelective(key,data.get(key));
            row=row1+row;
        }
        if (row!=data.size()){
            throw new RuntimeException();
        }
        return row;
    }

    public Map<String, String> listWx() {
        Map<String, String> map=new HashMap();
        List<ShoppingSystem> list=shoppingSystemMapper.selectByExample();
        for (int i = 0; i <list.size() ; i++) {
            if (list.get(i).getId()==1||list.get(i).getId()==15||list.get(i).getId()==4||list.get(i).getId()==5||list.get(i).getId()==11||list.get(i).getId()==7){
                map.put(list.get(i).getKey_name(),list.get(i).getKey_value());
            }
        }
        return map;
    }

    public Map<String, String> listMail() {
        Map<String, String> map=new HashMap();
        List<ShoppingSystem> list=shoppingSystemMapper.selectByExample();
        for (int i = 0; i <list.size() ; i++) {
            if (list.get(i).getId()==6||list.get(i).getId()==16||list.get(i).getId()==12||list.get(i).getId()==13){
                map.put(list.get(i).getKey_name(),list.get(i).getKey_value());
            }
        }
        return map;
    }
}
