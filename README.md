# camelQuartzScheduler
Using Apache Camel + Active MQ + SpringBoot + Quartz Scheduling

# Issue

When we use Camel with latest Spring boot for Scheduling, we usally get below error

    java.lang.InstantiationError: org.quartz.JobDetail
    
    at org.apache.camel.routepolicy.quartz.ScheduledRoutePolicy.createJobDetail(ScheduledRoutePolicy.java:172)


To resolve the same I tried find various ways but didn't got much help just a hint from https://stackoverflow.com/questions/6427497/problem-in-using-apache-camel-with-quartz-scheduler

Thanks to @[Ted Naleid](https://stackoverflow.com/users/8912/ted-naleid)