package com.g5619.service.impl;

import com.g5619.entity.res.CreateActivityReq;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class ActivityServiceImplTest {
    @Autowired
    private ActivityServiceImpl activityService;


    /**
     * test add activity
     */
    @Test
    void testInsertActivity() {
        //test the activity information is complete
        CreateActivityReq createActivityReq = new CreateActivityReq();
        createActivityReq.setUserId(8886L);
        createActivityReq.setAddress("Sydney");
        createActivityReq.setAllPerson(6);
        createActivityReq.setLevel(1);
        createActivityReq.setName("Ski Activity");
        createActivityReq.setStartTime(new Date());
        createActivityReq.setEndTime(new Date());
        int activity = activityService.createActivity(createActivityReq);
        Assertions.assertEquals(1,activity);
        //Test Add Repeat Activity
        int res =  activityService.createActivity(createActivityReq);
        Assertions.assertEquals(-1,res);
        //test the activity information is not complete
        CreateActivityReq createActivityReq1 = new CreateActivityReq();
        createActivityReq.setUserId(8887L);
        createActivityReq.setAddress(null);
        createActivityReq.setAllPerson(6);
        createActivityReq.setLevel(1);
        createActivityReq.setName("Ski Activity");
        createActivityReq.setStartTime(new Date());
        createActivityReq.setEndTime(new Date());
        int activity1 = activityService.createActivity(createActivityReq1);
        Assertions.assertEquals(1,activity1);
        // test the date is not given
        CreateActivityReq createActivityReq2 = new CreateActivityReq();
        createActivityReq.setUserId(8889L);
        createActivityReq.setAddress("Beijing");
        createActivityReq.setAllPerson(6);
        createActivityReq.setLevel(1);
        createActivityReq.setName("Ski Activity");
        createActivityReq.setStartTime(null);
        createActivityReq.setEndTime(null);
        int activity2 = activityService.createActivity(createActivityReq1);
        Assertions.assertEquals(-1,activity2);
    }

    /**
     * test delete activity
     */
    @Test
    void testDelActivity() {
        //test the activity is not in the database
        int i = activityService.delActivity(1L);
        Assertions.assertEquals(-1, i);
        // test the activity id was given wrong
        int i2 = activityService.delActivity(-1L);
        Assertions.assertEquals(-1, i2);
        //test the activity is in the database
        int i3 = activityService.delActivity(338L);
        Assertions.assertEquals(-1,i3);
    }

}
