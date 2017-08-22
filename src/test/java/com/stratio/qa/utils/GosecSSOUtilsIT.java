/*
 * Copyright (c) 2014. Stratio Big Data Inc., Sucursal en España. All rights reserved.
 *
 * This software is licensed under the Apache Licence 2.0. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the terms of the License for more details.
 * SPDX-License-Identifier: Artistic-2.0
 */

package com.stratio.qa.utils;

import org.testng.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;


public class GosecSSOUtilsIT {
    private final Logger logger = LoggerFactory.getLogger(GosecSSOUtilsIT.class);
    private static GosecSSOUtils gosecSsoUtils = new GosecSSOUtils("www.google.com",
            "anyUser", "anyPassWord");

    @Test
    public void gosecUtilsConstructorTest() throws Exception {
        assertThat(gosecSsoUtils.ssoHost).isEqualTo("www.google.com");
        assertThat(gosecSsoUtils.userName).isEqualTo("anyUser");
        assertThat(gosecSsoUtils.passWord).isEqualTo("anyPassWord");
    }

    @Test
    public void gosecUtilsFakeTokenGeneratorTest() throws Exception {
        assertThat(gosecSsoUtils.ssoTokenGenerator().size()).isEqualTo(0);
    }

}
