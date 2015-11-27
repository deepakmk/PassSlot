package com.capgemini.passslot.resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

/**
 * Created by dmallya on 11/26/2015.
 */
public class HelloImmediateTest {

    HelloImmediate hi;
    Response res;

    @Before
    public void setUp() throws Exception{
        hi = new HelloImmediate();
        res = new Response() {
            @Override
            public Object getEntity() {
                return null;
            }

            @Override
            public int getStatus() {
                return 0;
            }

            @Override
            public MultivaluedMap<String, Object> getMetadata() {
                return null;
            }
        };
    }
    @Test
    public void testListTaxis() throws Exception {
            res = hi.listTaxis();
            Assert.assertNotNull(res);
    }

    @Test
    public void testPublicTransport() throws Exception {

    }
}