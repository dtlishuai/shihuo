package com.alibaba.json.bvt;

import java.nio.charset.Charset;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.alibaba.json.JSON;
import com.alibaba.json.serializer.SerializeConfig;
import com.alibaba.json.serializer.SerializerFeature;

public class CharsetFieldTest extends TestCase {

    public void test_codec() throws Exception {
        User user = new User();
        user.setValue(Charset.forName("UTF-8"));

        SerializeConfig mapping = new SerializeConfig();
        mapping.setAsmEnable(false);
        String text = JSON.toJSONString(user, mapping, SerializerFeature.WriteMapNullValue);

        User user1 = JSON.parseObject(text, User.class);

        Assert.assertEquals(user1.getValue(), user.getValue());
    }

    public void test_codec_null() throws Exception {
        User user = new User();
        user.setValue(null);

        SerializeConfig mapping = new SerializeConfig();
        mapping.setAsmEnable(false);
        String text = JSON.toJSONString(user, mapping, SerializerFeature.WriteMapNullValue);

        User user1 = JSON.parseObject(text, User.class);

        Assert.assertEquals(user1.getValue(), user.getValue());
    }

    public static class User {

        private Charset value;

        public Charset getValue() {
            return value;
        }

        public void setValue(Charset value) {
            this.value = value;
        }

    }
}
