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
package com.psaanalytics.core.screenviews

import com.psaanalytics.core.constants.Parameters
import com.psaanalytics.core.constants.TrackerConstants
import com.psaanalytics.core.statemachine.State
import com.psaanalytics.core.statemachine.StateMachineInterface
import com.psaanalytics.psa.event.Event
import com.psaanalytics.psa.event.ScreenView
import com.psaanalytics.psa.payload.SelfDescribingJson
import com.psaanalytics.psa.tracker.InspectableEvent

class ScreenStateMachine : StateMachineInterface {
    /*
     States: Init, Screen
     Events: SV (ScreenView)
     Transitions:
      - Init (SV) Screen
      - Screen (SV) Screen
     Entity Generation:
      - Screen
     */

    override val identifier: String
        get() = ID
    
    override val subscribedEventSchemasForTransitions: List<String>
        get() = listOf(TrackerConstants.SCHEMA_SCREEN_VIEW)

    override val subscribedEventSchemasForEntitiesGeneration: List<String>
        get() = listOf("*")

    override val subscribedEventSchemasForPayloadUpdating: List<String>
        get() = listOf(TrackerConstants.SCHEMA_SCREEN_VIEW)

    override val subscribedEventSchemasForAfterTrackCallback: List<String>
        get() = emptyList()

    override val subscribedEventSchemasForFiltering: List<String>
        get() = emptyList()

    override val subscribedEventSchemasForEventsBefore: List<String>
        get() = emptyList()

    override fun transition(event: Event, state: State?): State? {
        return (event as? ScreenView)?.let { screenView ->
            ScreenState(
                id = screenView.id,
                name = screenView.name,
                type = screenView.type,
                transitionType = screenView.transitionType,
                fragmentClassName = screenView.fragmentClassName,
                fragmentTag = screenView.fragmentTag,
                activityClassName = screenView.activityClassName,
                activityTag = screenView.activityTag,
                previousScreenState = state as? ScreenState
            )
        }
    }

    override fun entities(event: InspectableEvent, state: State?): List<SelfDescribingJson>? {
        if (state == null) return ArrayList()
        val screenState = state as? ScreenState
        val entity = screenState?.getCurrentScreen(true)
        return entity?.let { listOf(it) }
    }

    override fun payloadValues(event: InspectableEvent, state: State?): Map<String, Any>? {
        if (state is ScreenState) {
            val addedValues: MutableMap<String, Any> = HashMap()
            var value = state.previousName
            if (value != null && value.isNotEmpty()) {
                addedValues[Parameters.SV_PREVIOUS_NAME] = value
            }
            value = state.previousId
            if (value != null && value.isNotEmpty()) {
                addedValues[Parameters.SV_PREVIOUS_ID] = value
            }
            value = state.previousType
            if (value != null && value.isNotEmpty()) {
                addedValues[Parameters.SV_PREVIOUS_TYPE] = value
            }
            return addedValues
        }
        return null
    }

    override fun afterTrack(event: InspectableEvent) {
    }

    override fun filter(event: InspectableEvent, state: State?): Boolean? {
        return null
    }

    override fun eventsBefore(event: Event): List<Event>? {
        return null
    }

    companion object {
        val ID: String
            get() = "ScreenContext"
    }
}
