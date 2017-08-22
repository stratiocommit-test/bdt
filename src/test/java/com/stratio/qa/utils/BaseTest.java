/*
 * Copyright (c) 2014. Stratio Big Data Inc., Sucursal en España. All rights reserved.
 *
 * This software is licensed under the Apache Licence 2.0. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the terms of the License for more details.
 * SPDX-License-Identifier: Artistic-2.0
 */
package com.stratio.qa.utils;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

abstract public class BaseTest extends BaseGTest {

    protected String browser = "";

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext context) {
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite(ITestContext context) {
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass(ITestContext context) {
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) {
        ThreadProperty.set("browser", this.browser);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(Method method) {
    }

    @AfterClass()
    public void afterClass() {
    }
}
