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
package com.example.psa_trakcer.psa.network

import com.example.psa_trakcer.core.constants.Parameters
import com.example.psa_trakcer.core.constants.TrackerConstants
import com.example.psa_trakcer.psa.payload.Payload
import com.example.psa_trakcer.psa.payload.SelfDescribingJson
import com.example.psa_trakcer.psa.payload.TrackerPayload

/**
 * Request class that contains the payloads to send
 * to the collector.
 */
class Request {
    val payload: Payload
    val emitterEventIds: List<Long>
    val oversize: Boolean
    val customUserAgent: String?
    
    /**
     * Create a request object.
     * @param payload to send to the collector.
     * @param id as reference of the event to send.
     * @param oversize indicates if the payload exceeded the maximum size allowed.
     */
    @JvmOverloads
    constructor(payload: Payload, id: Long, oversize: Boolean = false) {
        val ids: MutableList<Long> = ArrayList()
        ids.add(id)
        emitterEventIds = ids
        this.payload = payload
        this.oversize = oversize
        customUserAgent = getUserAgent(payload)
    }

    /**
     * Create a request object.
     * @param payloads to send to the collector as a payload bundle.
     * @param emitterEventIds as reference of the events to send.
     */
    constructor(payloads: List<Payload>, emitterEventIds: List<Long>) {
        var tempUserAgent: String? = null
        val payloadData = ArrayList<Map<*, *>>()
        for (payload in payloads) {
            payloadData.add(payload.map)
            tempUserAgent = getUserAgent(payload)
        }
        payload = TrackerPayload()
        val payloadBundle = SelfDescribingJson(com.example.psa_trakcer.core.constants.TrackerConstants.SCHEMA_PAYLOAD_DATA, payloadData)
        (payloadBundle.map as? Map<String, Any>)?.let { payload.addMap(it) }
        this.emitterEventIds = emitterEventIds
        customUserAgent = tempUserAgent
        oversize = false
    }

    /**
     * Get the User-Agent string for the request's header.
     *
     * @param payload The payload where to get the `ua` parameter.
     * @return User-Agent string from subject settings or the default one.
     */
    private fun getUserAgent(payload: Payload): String? {
        val hashMap = payload.map as? HashMap<*, *>
        return hashMap?.let { it[com.example.psa_trakcer.core.constants.Parameters.USERAGENT] as? String? }
    }
}
