package com.gzgz.cloud.event;

/**
 * @ClassName: EventProperties
 * @Description:
 * @Author: pzl
 * @CreateDate: 2021/1/8 14:52
 * @Version: 1.0
 */
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("com.gzgz.learning.event")
public class EventProperties {
    public static final String PREFIX = "com.gzgz.learning.event";
    private boolean enable = true;

    public EventProperties() {
    }

    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof EventProperties)) {
            return false;
        } else {
            EventProperties other = (EventProperties)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                return this.isEnable() == other.isEnable();
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof EventProperties;
    }

    public int hashCode() {
        //boolean PRIME = true;
        int result = 1;
        result = result * 59 + (this.isEnable() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "EventProperties(enable=" + this.isEnable() + ")";
    }
}

