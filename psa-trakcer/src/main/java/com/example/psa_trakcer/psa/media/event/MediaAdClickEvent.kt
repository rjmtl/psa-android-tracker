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

package com.example.psa_trakcer.psa.media.event

import com.example.psa_trakcer.core.media.MediaSchemata
import com.example.psa_trakcer.psa.event.AbstractSelfDescribing

/**
 * Media player event fired when the user clicked on the ad.
 *
 * @param percentProgress The percentage of the ad that was played when the user clicked on it.
 */
class MediaAdClickEvent(var percentProgress: Int? = null) : AbstractSelfDescribing() {
    override val schema: String
        get() = MediaSchemata.eventSchema("ad_click")

    override val dataPayload: Map<String, Any?>
        get() = mapOf(
            "percentProgress" to percentProgress
        ).filterValues { it != null }
}
