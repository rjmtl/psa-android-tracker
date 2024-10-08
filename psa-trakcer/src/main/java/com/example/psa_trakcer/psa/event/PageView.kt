/*
 * Copyright (c) 2015-present PSA Analytics Ltd. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
package com.example.psa_trakcer.psa.event

import com.example.psa_trakcer.core.constants.Parameters
import com.example.psa_trakcer.core.constants.TrackerConstants

/**
 * A PageView event. This event has been designed for web trackers, and is not suitable for mobile apps.
 * @param pageUrl The page URL.
 */
class PageView(pageUrl: String) : AbstractPrimitive() {
    /** Page URL.  */
    private val pageUrl: String

    /** Page title.  */
    private var pageTitle: String? = null

    /** Page referrer URL.  */
    private var referrer: String? = null
    
    init {
        require(pageUrl.isNotEmpty()) { "pageUrl cannot be empty" }
        
        this.pageUrl = pageUrl
    }
    
    // Builder methods
    
    /** Page title.  */
    fun pageTitle(pageTitle: String?): PageView {
        this.pageTitle = pageTitle
        return this
    }

    /** Page referrer URL.  */
    fun referrer(referrer: String?): PageView {
        this.referrer = referrer
        return this
    }

    // Public methods
    override val dataPayload: Map<String, Any?>
        get() {
            val payload = HashMap<String, Any?>()
            payload[com.example.psa_trakcer.core.constants.Parameters.PAGE_URL] = pageUrl
            if (pageTitle != null) payload[com.example.psa_trakcer.core.constants.Parameters.PAGE_TITLE] = pageTitle
            if (referrer != null) payload[com.example.psa_trakcer.core.constants.Parameters.PAGE_REFR] = referrer
            return payload
        }
    
    override val name: String
        get() = com.example.psa_trakcer.core.constants.TrackerConstants.EVENT_PAGE_VIEW
}
