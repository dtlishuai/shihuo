package com.alibaba.json.bvt;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.alibaba.json.JSON;
import com.alibaba.json.serializer.SerializerFeature;

public class MapRefTest1 extends TestCase {

    public void test_0() throws Exception {
        String text;
        {
            Map<String, Object> map = new HashMap<String, Object>();

            User user = new User();
            user.setId(123);
            user.setName("wenshao");
            
            map.put("u1", user);
            map.put("u2", user);
            
            text = JSON.toJSONString(map, SerializerFeature.WriteClassName);
        }
        
        System.out.println(text);
        Map<String, Object> map = JSON.parseObject(text);
        //Assert.assertEquals(map, map.get("this"));
        Assert.assertEquals(map.get("u1"), map.get("u2"));
    }

    public static class User {

        private int    id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

}
