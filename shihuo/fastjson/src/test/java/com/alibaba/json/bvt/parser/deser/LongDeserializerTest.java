package com.alibaba.json.bvt.parser.deser;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.alibaba.json.JSON;
import com.alibaba.json.parser.DefaultExtJSONParser;
import com.alibaba.json.parser.JSONToken;
import com.alibaba.json.parser.ParserConfig;
import com.alibaba.json.parser.deserializer.LongDeserializer;

public class LongDeserializerTest extends TestCase {

    public void test_bigdecimal() throws Exception {
        Assert.assertEquals(0, JSON.parseObject("0", Long.class).intValue());
        Assert.assertEquals(0, JSON.parseObject("0.0", Long.class).intValue());
        Assert.assertEquals(0, JSON.parseObject("'0'", Long.class).intValue());

        Assert.assertEquals(null, JSON.parseObject("null", Long.class));

        DefaultExtJSONParser parser = new DefaultExtJSONParser("null", ParserConfig.getGlobalInstance(), JSON.DEFAULT_PARSER_FEATURE);
        Assert.assertEquals(null, LongDeserializer.instance.deserialze(parser, null, null));
        Assert.assertEquals(JSONToken.LITERAL_INT, LongDeserializer.instance.getFastMatchToken());
    }
}
