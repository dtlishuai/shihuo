package com.alibaba.json.bvt;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.alibaba.json.util.Base64;


public class Base64Test extends TestCase {
    public void test_base64() throws Exception {
        Assert.assertEquals(Base64.decodeFast(new char[0], 0, 0).length, 0);
        Assert.assertEquals(Base64.decodeFast("ABC".toCharArray(), 0, 3).length, 2);
    }
}
