/*
 * Copyright (c) 2014. Stratio Big Data Inc., Sucursal en España. All rights reserved.
 *
 * This software is licensed under the Apache Licence 2.0. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the terms of the License for more details.
 * SPDX-License-Identifier: Artistic-2.0
 */
package com.stratio.qa.aspects;

import gherkin.formatter.model.Tag;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class RunOnEnvTagAspectTest {

    public RunOnTagAspect runontag = new RunOnTagAspect();

    @Test
    public void testGetParams() throws Exception {
        String[] expectedResponse = {"HELLO", "BYE"};
        assertThat(expectedResponse).as("Params are correctly obtained").isEqualTo(runontag.getParams("@runOnEnv(HELLO,BYE)"));
    }

    @Test
    public void testCheckParams() throws Exception {
        System.setProperty("HELLO_OK","OK");
        assertThat(true).as("Params are correctly checked").isEqualTo(runontag.checkParams(runontag.getParams("@runOnEnv(HELLO_OK)")));
    }

    @Test
    public void testCheckParams_2() throws Exception {
        System.setProperty("HELLO_KO","KO");
        assertThat(false).as("Params are correctly checked 2").isEqualTo(runontag.checkParams(runontag.getParams("@runOnEnv(HELLO_KO,BYE_KO)")));
    }
    @Test
    public void testCheckEmptyParams() throws Exception {
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> runontag.checkParams(runontag.getParams("@runOnEnv()")))
                .withMessage("Error while parsing params. Params must be at least one");
    }
    @Test
    public void testGetEmptyParams() throws Exception {
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> runontag.getParams("@runOnEnv"))
                .withMessage("Error while parsing params. Format is: \"runOnEnv(PARAM)\", but found: " + "@runOnEnv");
    }

    @Test
    public void testTagIterationRun() throws Exception {
        System.setProperty("HELLO","OK");
        List<Tag> tagList = new ArrayList<>();
        tagList.add(new Tag("@runOnEnv(HELLO)", 1));
        assertThat(false).isEqualTo(runontag.tagsIteration(tagList,1));
    }

    @Test
    public void testTagIterationIgnoreRun() throws Exception {
        List<Tag> tagList = new ArrayList<>();
        tagList.add(new Tag("@runOnEnv(BYE)", 1));
        assertThat(true).isEqualTo(runontag.tagsIteration(tagList,1));
    }

    @Test
    public void testTagIterationSkip() throws Exception {
        List<Tag> tagList = new ArrayList<>();
        tagList.add(new Tag("@skipOnEnv(HELLO_NO)", 1));
        assertThat(false).isEqualTo(runontag.tagsIteration(tagList,1));
    }

    @Test
    public void testTagIterationIgnoreSkip() throws Exception {
        System.setProperty("HELLO","OK");
        List<Tag> tagList = new ArrayList<>();
        tagList.add(new Tag("@skipOnEnv(HELLO)", 1));
        assertThat(true).isEqualTo(runontag.tagsIteration(tagList,1));
    }
}
