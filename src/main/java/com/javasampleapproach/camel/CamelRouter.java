package com.javasampleapproach.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.routepolicy.quartz.CronScheduledRoutePolicy;
import org.springframework.stereotype.Component;


@Component
public class CamelRouter extends RouteBuilder {

//	String queueIn = "jms:queue:IN?idleTaskExecutionLimit=1&maxMessagesPerTask=10";
String queueIn = "jms:queue:IN?idleTaskExecutionLimit=1&maxMessagesPerTask=10&concurrentConsumers=10&maxConcurrentConsumers=10";
	String queueOut_1 = "jms:queue:OUT_1";
	
	String queueOut_2 = "jms:queue:OUT_2";
	
	String queueOut_3 = "jms:queue:OUT_3";
	
    @Override
    public void configure() throws Exception {
		CronScheduledRoutePolicy startPolicy = new CronScheduledRoutePolicy();
		startPolicy.setRouteStartTime("0/30 * * * * ?"); // Start on every 30th Sec
		startPolicy.setRouteStopTime("25/30 * * * * ?"); // Stop after start + 25 sec gracefully

        from(queueIn)
			.routeId("JMSRoute")
			.routePolicy(startPolicy)
			.noAutoStartup()
			.onCompletion()
				.log("Done")
			.end()
			.choice()
				.when()
					.simple("${body} contains 'o1'")
					.log("01 ${body}.size()")
					.to(queueOut_1)
				.when()
					.simple("${body} contains 'o2'")
					.log("02 ${body}.size()")
					.to(queueOut_2)
				.otherwise()
					.log("03 ${body}.size()")
					.to(queueOut_3)
			.endChoice();
    }
}