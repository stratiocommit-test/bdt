/*
 * Copyright (c) 2014. Stratio Big Data Inc., Sucursal en España. All rights reserved.
 *
 * This software is licensed under the Apache Licence 2.0. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the terms of the License for more details.
 * SPDX-License-Identifier: Artistic-2.0
 */

package com.stratio.qa.assertions;

import org.assertj.core.api.iterable.Extractor;
import org.openqa.selenium.WebElement;

public final class SeleniumExtractor implements Extractor<WebElement, String> {

    private SeleniumExtractor() {
    }

    /**
     * Get selenium extractor.
     *
     * @return {@code Extractor<WebElement, String>}
     */
    public static Extractor<WebElement, String> linkText() {
        return new SeleniumExtractor();
    }

    @Override
    public String extract(WebElement input) {
        return input.getText();
    }
}
