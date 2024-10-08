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
package com.example.psa_trakcer.psa.tracker

import com.example.psa_trakcer.psa.payload.SelfDescribingJson

/**
 * The inspectable properties of a tracked event, used in the GlobalContexts API to generate context entities.
 */
interface InspectableEvent {
    /**
     * The schema of the event
     */
    val schema: String?
    /**
     * The name of the event
     */
    val name: String?
    /**
     * The payload of the event
     */
    val payload: MutableMap<String, Any>
    /**
     * The list of context entities
     */
    val entities: MutableList<SelfDescribingJson>
}
