package com.alibaba.json.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.alibaba.json.JSON;
import com.alibaba.json.serializer.SerializeConfig;
import com.alibaba.json.serializer.SimpleDateFormatSerializer;

public class DateDemo extends TestCase { 

    public void test_0() throws Exception {
        Date date = new Date(); 
        String text = JSON.toJSONString(date, mapping);
        Assert.assertEquals(JSON.toJSONString(new SimpleDateFormat("yyyy-MM-dd").format(date)), text);
    }

    private static SerializeConfig mapping = new SerializeConfig();
    static {
        mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));
    }

}
