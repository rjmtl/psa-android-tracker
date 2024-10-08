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
package com.example.psa_trakcer.core.statemachine

import com.example.psa_trakcer.psa.event.Event
import com.example.psa_trakcer.psa.payload.SelfDescribingJson
import com.example.psa_trakcer.psa.tracker.InspectableEvent

interface StateMachineInterface {
    val identifier: String
    val subscribedEventSchemasForEventsBefore: List<String>
    val subscribedEventSchemasForTransitions: List<String>
    val subscribedEventSchemasForEntitiesGeneration: List<String>
    val subscribedEventSchemasForPayloadUpdating: List<String>
    val subscribedEventSchemasForAfterTrackCallback: List<String>
    val subscribedEventSchemasForFiltering: List<String>

    fun eventsBefore(event: Event): List<Event>?
    fun transition(event: Event, state: State?): State?
    fun entities(event: InspectableEvent, state: State?): List<SelfDescribingJson>?
    fun payloadValues(event: InspectableEvent, state: State?): Map<String, Any>?
    fun afterTrack(event: InspectableEvent)
    fun filter(event: InspectableEvent, state: State?): Boolean?
}
