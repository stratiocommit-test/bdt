/*
 * Copyright (c) 2014. Stratio Big Data Inc., Sucursal en España. All rights reserved.
 *
 * This software is licensed under the Apache Licence 2.0. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the terms of the License for more details.
 * SPDX-License-Identifier: Artistic-2.0
 */

package com.stratio.qa.cucumber.converter;

import cucumber.api.Transformer;

import java.util.ArrayList;
import java.util.List;


public class ArrayListConverter extends Transformer<List<String>> {

    @Override
    public List<String> transform(String input) {

        List<String> response = new ArrayList<String>();
        String[] aInput = input.split(",");
        for (String content : aInput) {
            if (content.trim().equals("")) {
                response.add(content);
            } else {
                response.add(content.trim());
            }
        }

        return response;
    }
}
