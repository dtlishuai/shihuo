package com.alibaba.json.bvt.bug;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.alibaba.json.JSON;
import com.alibaba.json.JSONArray;
import com.alibaba.json.JSONObject;


public class Bug_for_jiangwei1 extends TestCase {
    public void test_double() throws Exception {
        JSONObject json = JSON.parseObject("{\"val\":12.3}");
        Assert.assertEquals(12.3D, json.getDoubleValue("val"));
    }
    
    public void test_JSONArray_double() throws Exception {
        JSONArray json = JSON.parseArray("[12.3]");
        Assert.assertEquals(12.3D, json.getDoubleValue(0));
    }
}