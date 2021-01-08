package com.gzgz.cloud.event;

/**
 * @ClassName: CustomSubscriptionFactory
 * @Description:
 * @Author: pzl
 * @CreateDate: 2021/1/8 15:01
 * @Version: 1.0
 */
import net.engio.mbassy.dispatch.EnvelopedMessageDispatcher;
import net.engio.mbassy.dispatch.FilteredMessageDispatcher;
import net.engio.mbassy.dispatch.IHandlerInvocation;
import net.engio.mbassy.dispatch.IMessageDispatcher;
import net.engio.mbassy.subscription.SubscriptionContext;
import net.engio.mbassy.subscription.SubscriptionFactory;

public class CustomSubscriptionFactory extends SubscriptionFactory {
    public CustomSubscriptionFactory() {
    }

    protected IMessageDispatcher buildDispatcher(SubscriptionContext context, IHandlerInvocation invocation) {
        IMessageDispatcher dispatcher = new CustomMessageDispatcher(context, invocation);
        if (context.getHandler().isEnveloped()) {
            dispatcher = new EnvelopedMessageDispatcher((IMessageDispatcher)dispatcher);
        }

        if (context.getHandler().isFiltered()) {
            dispatcher = new FilteredMessageDispatcher((IMessageDispatcher)dispatcher);
        }

        return (IMessageDispatcher)dispatcher;
    }
}
