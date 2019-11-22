package com.test;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.Validate;
import org.springframework.util.Assert;

import java.util.Objects;

public class CheckNull {

    public static void main(String[] args) {

        Object object = new Object();
        // jdk
        Objects.requireNonNull(object, "null");
        // apache
        Validate.notNull(object, "null");
        // guava
        Preconditions.checkNotNull(object, "null");
        // spring
        Assert.notNull(object, "null");

    }

}
