/*
 * Copyright (c) 2014. Stratio Big Data Inc., Sucursal en España. All rights reserved.
 *
 * This software is licensed under the Apache Licence 2.0. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the terms of the License for more details.
 * SPDX-License-Identifier: Artistic-2.0
 */

package com.stratio.qa.utils;

import com.ning.http.client.cookie.Cookie;

import java.util.List;

public class HttpResponse {

    private int statusCode;

    private String response;

    private List<Cookie> cookies;

    /**
     * Constructor of an HttpResponse.
     *
     * @param statusCode
     * @param response
     */
    public HttpResponse(Integer statusCode, String response, List<Cookie> cookies) {
        this.statusCode = statusCode;
        this.response = response;
        this.setCookies(cookies);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int status) {
        this.statusCode = status;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<Cookie> getCookies() {
        return cookies;
    }

    public void setCookies(List<Cookie> cookies) {
        this.cookies = cookies;
    }

}