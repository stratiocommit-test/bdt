/*
 * Copyright (c) 2014. Stratio Big Data Inc., Sucursal en España. All rights reserved.
 *
 * This software is licensed under the Apache Licence 2.0. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the terms of the License for more details.
 * SPDX-License-Identifier: Artistic-2.0
 */

package com.stratio.qa.conditions;

import org.assertj.core.api.Condition;
import org.openqa.selenium.WebElement;

public class TextFieldCondition {

    private Condition<WebElement> cond;

    public TextFieldCondition() {
        cond = new Condition<WebElement>("textField") {
            @Override
            public boolean matches(WebElement value) {
                switch (value.getTagName()) {
                    case "input":
                        return ("text".equals(value.getAttribute("type")) || "input".equals(value.getAttribute("type")));
                    case "textarea":
                        return ("text".equals(value.getAttribute("type")) || "textarea".equals(value.getAttribute("type")));
                    default:
                        return false;
                }
            }
        };
    }

    public Condition<WebElement> getCondition() {
        return cond;
    }

}