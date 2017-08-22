/*
 * Copyright (c) 2014. Stratio Big Data Inc., Sucursal en España. All rights reserved.
 *
 * This software is licensed under the Apache Licence 2.0. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the terms of the License for more details.
 * SPDX-License-Identifier: Artistic-2.0
 */
package com.stratio.qa.cucumber.converter;


import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayListConverterTest {
    private ArrayListConverter converter = new ArrayListConverter();

    @Test
    public void test() {
        assertThat(converter.transform("")).as("Empty input converter").hasSize(1);
    }

    @Test
    public void test_1() {
        assertThat(converter.transform("foo")).as("Single string input converter").hasSize(1);
    }

    @Test
    public void test_2() {
        assertThat(converter.transform("foo")).as("Single string input converter").contains("foo");
    }

    @Test
    public void test_3() {
        assertThat(converter.transform("foo,bar")).as("Complex string input converter").hasSize(2);
    }

    @Test
    public void test_4() {
        assertThat(converter.transform("foo , bar")).as("Single string input converter").contains("foo", "bar");
    }

    @Test
    public void test_5() {
        assertThat(converter.transform("foo , , bar")).as("Single string input converter").contains("foo", " ", "bar");
    }

    @Test
    public void test_6() {
        assertThat(converter.transform("foo ,   , bar")).as("Single string input converter").contains("foo", "   ", "bar");
    }

}
