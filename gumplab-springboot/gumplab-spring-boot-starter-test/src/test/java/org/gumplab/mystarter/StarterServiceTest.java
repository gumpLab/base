package org.gumplab.mystarter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StarterServiceTest {

    @Autowired
    StarterService starterService;

    @Test
    public void contextLoads() {
        System.err.println(Arrays.toString(starterService.split(".")));
    }

}
