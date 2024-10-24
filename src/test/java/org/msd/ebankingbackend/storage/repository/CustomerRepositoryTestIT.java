package org.msd.ebankingbackend.storage.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class CustomerRepositoryTestIT {


    @BeforeEach
    void cleanData() {

    }

    @Test
    void initially_empty() {
    }

}
