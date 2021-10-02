package com.witsoftware.rest.config;

import ch.qos.logback.access.tomcat.LogbackValve;
import com.witsoftware.rest.calculator.utils.Constants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    @Bean
    public Queue operationQueue(){
        return new Queue(Constants.OPERATION_QUEUE);
    }

    @Bean
    public Queue resultQueue(){
        return new Queue(Constants.RESULT_QUEUE);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(Constants.EXCHANGE);
    }
    @Bean
    public Binding operationBinding( TopicExchange exchange){
        return BindingBuilder.bind(operationQueue()).to(exchange).with(Constants.OPERATION_ROUTING_KEY);
    }

    @Bean
    public Binding resultBinding(TopicExchange exchange){
        return BindingBuilder.bind(resultQueue()).to(exchange).with(Constants.RESULT_ROUTING_KEY);
    }
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addContextValves(new LogbackValve());
        return tomcat;
    }
}
