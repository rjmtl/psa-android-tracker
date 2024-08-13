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
package com.example.psa_trakcer.core.event

import com.example.psa_trakcer.core.constants.TrackerConstants
import com.example.psa_trakcer.psa.event.AbstractSelfDescribing
import java.util.*

class ScreenEnd : AbstractSelfDescribing() {

    // Tracker methods
    override val dataPayload: Map<String, Any?>
        get() {
            val payload = HashMap<String, Any?>()
            return payload
        }

    override val schema: String
        get() = com.example.psa_trakcer.core.constants.TrackerConstants.SCHEMA_SCREEN_END

}
