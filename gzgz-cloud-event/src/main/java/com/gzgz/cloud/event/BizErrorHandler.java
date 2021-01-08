package com.gzgz.cloud.event;

import com.google.common.base.Throwables;
import net.engio.mbassy.bus.error.IPublicationErrorHandler;
import net.engio.mbassy.bus.error.PublicationError;

/**
 * @ClassName: BizErrorHandler
 * @Description:
 * @Author: pzl
 * @CreateDate: 2021/1/8 15:01
 * @Version: 1.0
 */
public class BizErrorHandler implements IPublicationErrorHandler {
    public BizErrorHandler() {
    }

    public void handleError(PublicationError error) {
        throw new RuntimeException(Throwables.getRootCause(error.getCause()).getMessage());
    }
}
