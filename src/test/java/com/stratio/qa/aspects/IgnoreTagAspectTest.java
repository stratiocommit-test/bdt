/*
 * Copyright (c) 2014. Stratio Big Data Inc., Sucursal en España. All rights reserved.
 *
 * This software is licensed under the Apache Licence 2.0. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the terms of the License for more details.
 * SPDX-License-Identifier: Artistic-2.0
 */
package com.stratio.qa.aspects;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class IgnoreTagAspectTest {

    public IgnoreTagAspect ignoretag = new IgnoreTagAspect();

    @Test
    public void testJiraTicket() throws Exception {
        List<String> tagList = new ArrayList<>();
        tagList.add(0, "@ignore");
        tagList.add(1, "@tillfixed(XXX-123)");
        String scnName = "Jira ticket ignore";
        IgnoreTagAspect.ignoreReasons exit = IgnoreTagAspect.ignoreReasons.JIRATICKET;

        assertThat(exit).as("Scenario 'Jira ticket ignore' ignored because of ticket: XXX-123").isEqualTo(ignoretag.manageTags(tagList,scnName));
    }

    @Test
    public void testManual() throws Exception {
        List<String> tagList = new ArrayList<>();
        tagList.add(0, "@ignore");
        tagList.add(1, "@manual");
        String scnName = "Manual ignore";
        IgnoreTagAspect.ignoreReasons exit = IgnoreTagAspect.ignoreReasons.MANUAL;

        assertThat(exit).as("Scenario 'Manual ignore' ignored because it is marked as manual test.").isEqualTo(ignoretag.manageTags(tagList,scnName));
    }

    @Test
    public void testTooComplex() throws Exception {
        List<String> tagList = new ArrayList<>();
        tagList.add(0, "@ignore");
        tagList.add(1, "@toocomplex");
        String scnName = "Too complex ignore";
        IgnoreTagAspect.ignoreReasons exit = IgnoreTagAspect.ignoreReasons.TOOCOMPLEX;

        assertThat(exit).as("Scenario 'Too complex ignore' ignored because the test is too complex.").isEqualTo(ignoretag.manageTags(tagList,scnName));
    }

    @Test
    public void testRunOnEnvs() throws Exception {
        List<String> tagList = new ArrayList<>();
        tagList.add(0, "@ignore");
        tagList.add(1, "@envCondition");
        String scnName = "Condition ignore";
        IgnoreTagAspect.ignoreReasons exit = IgnoreTagAspect.ignoreReasons.ENVCONDITION;

        assertThat(exit).isEqualTo(ignoretag.manageTags(tagList,scnName));
    }

    @Test
    public void testUnimplemented() throws Exception {
        List<String> tagList = new ArrayList<>();
        tagList.add(0, "@ignore");
        tagList.add(1, "@unimplemented");
        String scnName = "Unimplemented ignore";
        IgnoreTagAspect.ignoreReasons exit = IgnoreTagAspect.ignoreReasons.UNIMPLEMENTED;

        assertThat(exit).as("Scenario 'Unimplemented ignore' ignored because it is not yet implemented.").isEqualTo(ignoretag.manageTags(tagList,scnName));
    }

    @Test
    public void testNotKnownReason() throws Exception {
        List<String> tagList = new ArrayList<>();
        tagList.add(0, "@ignore");
        tagList.add(1, "@hellomyfriend");
        String scnName = "Not known reason ignore";
        IgnoreTagAspect.ignoreReasons exit = IgnoreTagAspect.ignoreReasons.NOREASON;

        assertThat(exit).as("Scenario 'test ignore in scenario' ignored because of ticket: XXX-123").isEqualTo(ignoretag.manageTags(tagList,scnName));
    }

    @Test
    public void testNotIgnored() throws Exception {
        List<String> tagList = new ArrayList<>();
        tagList.add(0, "@hellomyfriend");
        String scnName = "Not ignored scenario";
        IgnoreTagAspect.ignoreReasons exit = IgnoreTagAspect.ignoreReasons.NOTIGNORED;

        assertThat(exit).as("Scenario not ignored.").isEqualTo(ignoretag.manageTags(tagList,scnName));
    }
}
