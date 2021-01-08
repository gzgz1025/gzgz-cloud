package com.gzgz.cloud.event;

/**
 * @ClassName: EventAutoConfiguration
 * @Description:
 * @Author: pzl
 * @CreateDate: 2021/1/8 14:53
 * @Version: 1.0
 */

import net.engio.mbassy.bus.MessagePublication.Factory;
import net.engio.mbassy.bus.config.BusConfiguration;
import net.engio.mbassy.bus.config.Feature.AsynchronousHandlerInvocation;
import net.engio.mbassy.bus.config.Feature.AsynchronousMessageDispatch;
import net.engio.mbassy.bus.config.Feature.SyncPubSub;
import net.engio.mbassy.bus.error.IPublicationErrorHandler.ConsoleLogger;
import net.engio.mbassy.listener.MetadataReader;
import net.engio.mbassy.subscription.SubscriptionManagerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Iterator;
import java.util.Map;

@Configuration
@EnableConfigurationProperties({EventProperties.class})
@ConditionalOnProperty(
        value = {"event.enable"},
        matchIfMissing = true
)
public class EventAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(EventAutoConfiguration.class);

    public EventAutoConfiguration() {
    }

    @Bean
    public EventBus messageBus(ThreadPoolTaskExecutor commonTaskExecutor) {
        AsynchronousHandlerInvocation asynchronousHandlerInvocation = new AsynchronousHandlerInvocation();
        asynchronousHandlerInvocation.setExecutor(commonTaskExecutor.getThreadPoolExecutor());
        EventBus bus = new EventBus((new BusConfiguration()).addFeature(this.pubSub()).addFeature(asynchronousHandlerInvocation).addFeature(AsynchronousMessageDispatch.Default()).addPublicationErrorHandler(new ConsoleLogger()).addPublicationErrorHandler(new BizErrorHandler()).setProperty("bus.id", "global bus"));
        return bus;
    }

    @Bean
    public SyncPubSub pubSub() {
        return (new SyncPubSub()).setMetadataReader(new MetadataReader()).setPublicationFactory(new Factory()).setSubscriptionFactory(new CustomSubscriptionFactory()).setSubscriptionManagerProvider(new SubscriptionManagerProvider());
    }

    @Configuration
    public static class EventHandlerConfig implements ApplicationContextAware {
        @Autowired
        private EventBus eventBus;
        private Map<String, Object> beansWithAnnotation;
        private ApplicationContext applicationContext;

        public EventHandlerConfig() {
        }

        @PostConstruct
        public void afterPropertiesSet() throws Exception {
            this.beansWithAnnotation = this.applicationContext.getBeansWithAnnotation(EventHandler.class);
            Iterator var1 = this.beansWithAnnotation.values().iterator();

            while(var1.hasNext()) {
                Object o = var1.next();
                this.eventBus.subscribe(o);
                EventAutoConfiguration.log.info("注册事件处理器:{}", o.getClass().getName());
            }

        }

        @PreDestroy
        public void destroy() throws Exception {
            Iterator var1 = this.beansWithAnnotation.values().iterator();

            while(var1.hasNext()) {
                Object o = var1.next();
                this.eventBus.unsubscribe(o);
                EventAutoConfiguration.log.info("销毁事件处理器:{}", o.getClass().getName());
            }

        }

        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
        }
    }
}
