package pers.gxj.quickstart.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xinjie_guo on 2022/2/11.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class BaseTest {

    @Test
    public void test(){
        System.out.println("hello,world!");
    }
}