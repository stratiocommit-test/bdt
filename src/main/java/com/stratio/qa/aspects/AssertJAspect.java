/*
 * Copyright (c) 2014. Stratio Big Data Inc., Sucursal en España. All rights reserved.
 *
 * This software is licensed under the Apache Licence 2.0. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the terms of the License for more details.
 * SPDX-License-Identifier: Artistic-2.0
 */

package com.stratio.qa.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class AssertJAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass()
            .getCanonicalName());

    @Pointcut("execution(* org.assertj.core.internal.Failures.failure(..))")
    protected void logAssertJFailurePointcut() {
    }

    /**
     * @param pjp ProceedingJoinPoint
     * @return AssertionError
     * @throws Throwable exception
     */
    @Around("logAssertJFailurePointcut()")
    public AssertionError aroundLogAssertJFailurePointcut(
            ProceedingJoinPoint pjp) throws Throwable {

        AssertionError ae = (AssertionError) pjp.proceed();
        if (ae.getStackTrace()[2].getMethodName().equals("assertCommandExistsOnTimeOut") ||
                ae.getStackTrace()[2].getMethodName().equals("assertSeleniumNElementExistsOnTimeOut") ||
                ae.getStackTrace()[2].getMethodName().equals("sendRequestTimeout")) {
            logger.warn("Assertion failed: {}", ae.getMessage());
        } else {
            logger.error("Assertion failed: {}", ae.getMessage());
        }
        return ae;

    }
}