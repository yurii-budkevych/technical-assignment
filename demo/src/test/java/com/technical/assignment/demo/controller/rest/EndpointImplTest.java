package com.technical.assignment.demo.controller.rest;

import com.technical.assignment.demo.dto.Tree;
import com.technical.assignment.demo.storage.TreeDataStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EndpointImplTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TreeDataStorage treeDataStorage;

    private Tree[] test_suit_one;

    @BeforeEach
    public void setUp() {
        test_suit_one = new Tree[]{new Tree("Norway maple",100d,100d),
                new Tree("northern red oak",50d,50d),
                new Tree("Japanese zelkova",10d,10d)};
    }

    @Test
    public void nearbyTreesShouldReturnNoData() throws Exception {
        assertThat(restTemplate.getForObject("http://localhost:" + port + "service/nearby-trees?x=1&y=1&radius=1",
                String.class)).contains("no data");
    }

    @Test
    public void nearbyTreesShouldReturnExpectedData() throws Exception {
        treeDataStorage.saveData(new Tree[]{new Tree("Norway maple", 100d, 100d)});

        assertThat(restTemplate.getForObject("http://localhost:" + port + "service/nearby-trees?x=100&y=100&radius=1",
                String.class)).contains("Norway maple");
    }

    @Test
    public void nearbyTreesShouldReturnExpectedData2() throws Exception {
        treeDataStorage.saveData(test_suit_one);

        assertThat(restTemplate.getForObject("http://localhost:" + port + "service/nearby-trees?x=100&y=100&radius=100",
                String.class)).contains("{\"Japanese zelkova\":1,\"Norway maple\":1,\"northern red oak\":1}");
    }

    @Test
    public void nearbyTreesShouldReturnBadRequestForNullInputs() throws Exception {
        treeDataStorage.saveData(test_suit_one);

        assertThat(restTemplate.getForObject("http://localhost:" + port + "service/nearby-trees?x=&y=&radius=100",
                String.class)).contains("Bad Request");
    }
}