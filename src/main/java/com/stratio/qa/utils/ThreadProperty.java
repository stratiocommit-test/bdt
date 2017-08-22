/*
 * Copyright (c) 2014. Stratio Big Data Inc., Sucursal en España. All rights reserved.
 *
 * This software is licensed under the Apache Licence 2.0. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the terms of the License for more details.
 * SPDX-License-Identifier: Artistic-2.0
 */

package com.stratio.qa.utils;

import java.util.Properties;

public final class ThreadProperty {
    private static final ThreadLocal<Properties> PROPS = new ThreadLocal<Properties>() {
        protected Properties initialValue() {
            return new Properties();
        }
    };

    /**
     * Default Constructor.
     */
    private ThreadProperty() {
    }

    /**
     * Set a string to share in other class.
     *
     * @param key
     * @param value
     */
    public static void set(String key, String value) {
        PROPS.get().setProperty(key, value);
    }

    /**
     * Get a property shared.
     *
     * @param key
     * @return String
     */
    public static String get(String key) {
        return PROPS.get().getProperty(key);
    }
}
