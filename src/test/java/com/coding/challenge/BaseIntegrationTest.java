package com.coding.challenge;

import io.restassured.http.Header;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.coding.challenge.repository.EmployeeDetailsRepositoryImpl;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public abstract class BaseIntegrationTest {

    @LocalServerPort
    protected int port;

    @Autowired
    protected EmployeeDetailsRepositoryImpl employeeDetailsRepository;

    protected Header getValidAuthHeader() {
        Header header = new Header("API-KEY", "xJ9a34fo");
        return header;
    }
}
