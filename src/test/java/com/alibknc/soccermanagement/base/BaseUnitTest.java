package com.alibknc.soccermanagement.base;

import com.alibknc.soccermanagement.factory.ObjectFactory;
import com.alibknc.soccermanagement.factory.EntityFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseUnitTest {

    protected EntityFactory entityFactory;
    protected ObjectFactory objectFactory;

    @BeforeAll
    public void beforeAll() {
        entityFactory = new EntityFactory();
        objectFactory = new ObjectFactory();
    }

}
