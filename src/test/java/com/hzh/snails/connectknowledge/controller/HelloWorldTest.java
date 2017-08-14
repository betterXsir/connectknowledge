package com.hzh.snails.connectknowledge.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//Note the use of webEnvironment=RANDOM_PORT to start the server
// with a random port (useful to avoid conflicts in test environments)
//, and the injection of the port with @LocalServerPort.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloWorldTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void helloWorld() throws Exception{
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/hello", String.class))
                .contains("Hello World");
    }
}
