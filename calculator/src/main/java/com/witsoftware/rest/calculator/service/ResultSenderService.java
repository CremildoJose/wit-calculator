package com.witsoftware.rest.calculator.service;

import com.witsoftware.rest.calculator.model.MathOperationResult;
import com.witsoftware.rest.calculator.utils.Constants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultSenderService {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public  ResultSenderService(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate =  rabbitTemplate;
    }

    public void send(MathOperationResult mathOperationResult){

        this.rabbitTemplate.convertAndSend(Constants.EXCHANGE,
                    Constants.RESULT_ROUTING_KEY,
                   mathOperationResult);
    }


}
