package com.lastingwar.springbootutils.controller.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yhm
 * @create 2020-11-03 18:12
 */
@Slf4j
@RestController
public class LogJsonController {

    public static final String KAFKA_TOPIC_STARTUP = "GMALL_STARTUP";
    public static final String KAFKA_TOPIC_EVENT = "GMALL_EVENT";

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @PostMapping("log")
    public String doLog(@RequestParam("logString") String logString ){

        // 0 补充时间戳
        JSONObject jsonObject = JSON.parseObject(logString);
        jsonObject.put("ts",System.currentTimeMillis());

        // 1 落盘 file
        String jsonString = jsonObject.toJSONString();
        log.info(jsonString);

        // 2 推送到kafka
        if( "startup".equals(jsonObject.getString("type"))){
            kafkaTemplate.send(KAFKA_TOPIC_STARTUP,jsonString);
        }else{
            kafkaTemplate.send(KAFKA_TOPIC_EVENT,jsonString);
        }

        return "success";
    }
}
