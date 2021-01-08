package com.gzgz.cloud.event;

/**
 * @ClassName: CustomMessageDispatcher
 * @Description:
 * @Author: pzl
 * @CreateDate: 2021/1/8 15:01
 * @Version: 1.0
 */

import net.engio.mbassy.bus.MessagePublication;
import net.engio.mbassy.dispatch.IHandlerInvocation;
import net.engio.mbassy.dispatch.MessageDispatcher;
import net.engio.mbassy.subscription.SubscriptionContext;

import java.util.Iterator;

public class CustomMessageDispatcher extends MessageDispatcher {
    public CustomMessageDispatcher(SubscriptionContext context, IHandlerInvocation invocation) {
        super(context, invocation);
    }

    public void dispatch(MessagePublication publication, Object message, Iterable listeners) {
        publication.markDispatched();
        Iterator var4 = listeners.iterator();

        while(var4.hasNext()) {
            Object listener = var4.next();
            this.getInvocation().invoke(listener, message, publication);
        }

    }
}
