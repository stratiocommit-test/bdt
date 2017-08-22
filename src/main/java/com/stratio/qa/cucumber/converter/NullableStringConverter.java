/*
 * Copyright (c) 2014. Stratio Big Data Inc., Sucursal en España. All rights reserved.
 *
 * This software is licensed under the Apache Licence 2.0. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the terms of the License for more details.
 * SPDX-License-Identifier: Artistic-2.0
 */

package com.stratio.qa.cucumber.converter;

import cucumber.api.Transformer;

public class NullableStringConverter extends Transformer<String> {
    public static final int DEFAULT_RADIX = 16;

    @Override
    public String transform(String input) {

        if ("//NONE//".equals(input)) {
            return "";
        } else if ("//NULL//".equals(input)) {
            return null;
        } else if (input.startsWith("0x")) {

            int cInt = Integer.parseInt(input.substring(2), DEFAULT_RADIX);
            char[] cArr = Character.toChars(cInt);
            return String.valueOf(cArr);
        } else {
            return input;
        }

    }
}