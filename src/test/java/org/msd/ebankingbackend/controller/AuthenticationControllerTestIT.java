package org.msd.ebankingbackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.msd.ebankingbackend.EbankingBackendApplication;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@ActiveProfiles("test")
@AutoConfigureJsonTesters
@SpringBootTest(classes = EbankingBackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AuthenticationControllerTestIT {


}
