package com.xiaoliu.learn.bus.endpoint;

import com.xiaoliu.learn.bus.ClientServiceApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ClientServiceApplication.class)
public class ProducerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerTest.class);

    @Autowired
    private Producer producer;

    @Before
    public void setUp() throws Exception {
        LOGGER.info("Start Time:{}", System.currentTimeMillis());
    }

    @After
    public void tearDown() throws Exception {
        LOGGER.info("End Time:{}", System.currentTimeMillis());
    }

    @Test
    public void send() {
        try {
            for (int i = 0; i < 1000; i++) {
                producer.send();
                Thread.sleep(5);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}