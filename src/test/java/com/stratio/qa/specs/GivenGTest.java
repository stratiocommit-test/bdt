/*
 * Copyright (c) 2014. Stratio Big Data Inc., Sucursal en España. All rights reserved.
 *
 * This software is licensed under the Apache Licence 2.0. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the terms of the License for more details.
 * SPDX-License-Identifier: Artistic-2.0
 */
package com.stratio.qa.specs;

import com.stratio.qa.utils.ThreadProperty;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class GivenGTest {

    @Test
    public void testSaveElementFromVariable() throws Exception {
        ThreadProperty.set("class", this.getClass().getCanonicalName());

        String baseData = "indicesJSON.conf";
        String envVar = "envVar";

        String jsonString = new String(Files.readAllBytes(
                Paths.get(getClass().getClassLoader().getResource(baseData).getFile())));

        CommonG commong = new CommonG();
        GivenGSpec giveng = new GivenGSpec(commong);

        try {
            giveng.saveElementEnvironment(null, null, jsonString.concat(".$.[0]"), envVar);
        } catch (Exception e) {
            fail("Error parsing JSON String");
        }

        assertThat(ThreadProperty.get(envVar)).as("Not correctly ordered").isEqualTo("stratiopaaslogs-2016-07-26");
    }


    @Test
    public void testSaveDCOSCookie() throws Exception {
        ThreadProperty.set("class", this.getClass().getCanonicalName());
        CommonG commong = new CommonG();
        GivenGSpec giveng = new GivenGSpec(commong);
        String email = "admin@demo.stratio.com";
        String dcosSecret = "04uth_jwt_s3cr3t";

        giveng.setDCOSCookie(dcosSecret,email);
    }
}
