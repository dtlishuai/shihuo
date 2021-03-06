package com.alibaba.json.bvt.serializer;

import java.awt.Point;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.alibaba.json.JSON;
import com.alibaba.json.serializer.SerializerFeature;


public class PointSerializerTest extends TestCase {
    
    public void test_null() throws Exception {
        VO vo = new VO();
        
        Assert.assertEquals("{\"value\":null}", JSON.toJSONString(vo, SerializerFeature.WriteMapNullValue));
    }

    private static class VO {

        private Point value;

        public Point getValue() {
            return value;
        }

        public void setValue(Point value) {
            this.value = value;
        }

    }
}
