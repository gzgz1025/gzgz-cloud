package com.gzgz.cloud.event;

/**
 * @ClassName: EventBus
 * @Description:
 * @Author: pzl
 * @CreateDate: 2021/1/8 14:48
 * @Version: 1.0
 */

import net.engio.mbassy.bus.IMessagePublication;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.bus.config.IBusConfiguration;
import net.engio.mbassy.bus.error.IPublicationErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Nonnull;

public class EventBus<T> extends MBassador<T> {
    private static final Logger log = LoggerFactory.getLogger(EventBus.class);

    public EventBus() {
    }

    public EventBus(IPublicationErrorHandler errorHandler) {
        super(errorHandler);
    }

    public EventBus(IBusConfiguration configuration) {
        super(configuration);
    }

    public IMessagePublication publish(@Nonnull T message) {
        IMessagePublication publication = this.createMessagePublication(message);
        publication.execute();
        return publication;
    }

    public void publishAfterTransactionCommitted(final T message) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            public void afterCompletion(int status) {
                if (status == 0) {
                    EventBus.this.publish(message);
                }

            }
        });
    }

    public void publishAfterTransactionCommittedAsync(final T message) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            public void afterCompletion(int status) {
                if (status == 0) {
                    EventBus.this.publishAsync(message);
                }

            }
        });
    }
}
