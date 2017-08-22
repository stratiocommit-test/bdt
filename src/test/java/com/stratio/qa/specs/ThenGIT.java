/*
 * Copyright (c) 2014. Stratio Big Data Inc., Sucursal en España. All rights reserved.
 *
 * This software is licensed under the Apache Licence 2.0. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the terms of the License for more details.
 * SPDX-License-Identifier: Artistic-2.0
 */
package com.stratio.qa.specs;

import com.stratio.qa.cucumber.testng.CucumberRunner;
import com.stratio.qa.utils.BaseTest;
import cucumber.api.CucumberOptions;
import org.testng.annotations.Test;
import org.testng.annotations.Factory;
import com.stratio.qa.data.BrowsersDataProvider;

@CucumberOptions(features = {"src/test/resources/features/readWebElementTextToVariable.feature"})
public class ThenGIT extends BaseTest {

    @Factory(enabled = false, dataProviderClass = BrowsersDataProvider.class, dataProvider = "availableUniqueBrowsers")
    public ThenGIT(String browser) {
        this.browser = browser;
    }

    @Test
    public void thenGTest() throws Exception {
        new CucumberRunner(this.getClass()).runCukes();
    }
}
