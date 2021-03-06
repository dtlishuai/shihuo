package com.alibaba.json.bvt.serializer;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.alibaba.json.serializer.JSONSerializer;
import com.alibaba.json.serializer.SerializeWriter;

public class JSONSerializerTest1 extends TestCase {
    public void test_0 () throws Exception {
        SerializeWriter out = new SerializeWriter();
        JSONSerializer serializer = new JSONSerializer(out);
        
        Assert.assertEquals(0, serializer.getNameFilters().size());
        Assert.assertEquals(0, serializer.getNameFilters().size());
        
        Assert.assertEquals(0, serializer.getValueFilters().size());
        Assert.assertEquals(0, serializer.getValueFilters().size());
        
        Assert.assertEquals(0, serializer.getPropertyFilters().size());
        Assert.assertEquals(0, serializer.getPropertyFilters().size());
        
        serializer.writeWithFormat("123", null);
    }
}
