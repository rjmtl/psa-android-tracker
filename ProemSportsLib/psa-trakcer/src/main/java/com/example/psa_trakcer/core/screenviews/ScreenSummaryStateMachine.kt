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
package com.example.psa_trakcer.core.screenviews

import com.example.psa_trakcer.core.constants.TrackerConstants
import com.example.psa_trakcer.core.event.ScreenEnd
import com.example.psa_trakcer.core.statemachine.State
import com.example.psa_trakcer.core.statemachine.StateMachineInterface
import com.example.psa_trakcer.psa.event.*
import com.example.psa_trakcer.psa.payload.SelfDescribingJson
import com.example.psa_trakcer.psa.tracker.InspectableEvent

class ScreenSummaryStateMachine : StateMachineInterface {

    override val identifier: String
        get() = ID

    override val subscribedEventSchemasForTransitions: List<String>
        get() = listOf(com.example.psa_trakcer.core.constants.TrackerConstants.SCHEMA_SCREEN_VIEW, com.example.psa_trakcer.core.constants.TrackerConstants.SCHEMA_SCREEN_END, Foreground.schema, Background.schema, com.example.psa_trakcer.core.constants.TrackerConstants.SCHEMA_LIST_ITEM_VIEW, com.example.psa_trakcer.core.constants.TrackerConstants.SCHEMA_SCROLL_CHANGED)

    override val subscribedEventSchemasForEntitiesGeneration: List<String>
        get() = listOf(com.example.psa_trakcer.core.constants.TrackerConstants.SCHEMA_SCREEN_END, Foreground.schema, Background.schema)

    override val subscribedEventSchemasForPayloadUpdating: List<String>
        get() = emptyList()

    override val subscribedEventSchemasForAfterTrackCallback: List<String>
        get() = emptyList()

    override val subscribedEventSchemasForFiltering: List<String>
        get() = listOf(com.example.psa_trakcer.core.constants.TrackerConstants.SCHEMA_LIST_ITEM_VIEW, com.example.psa_trakcer.core.constants.TrackerConstants.SCHEMA_SCREEN_END, com.example.psa_trakcer.core.constants.TrackerConstants.SCHEMA_SCROLL_CHANGED)

    override val subscribedEventSchemasForEventsBefore: List<String>
        get() = listOf(com.example.psa_trakcer.core.constants.TrackerConstants.SCHEMA_SCREEN_VIEW)

    override fun transition(event: Event, state: State?): State? {
        if (event is ScreenView) {
            return ScreenSummaryState()
        }
        val screenSummaryState = state as ScreenSummaryState? ?: return null
        when (event) {
            is Foreground -> {
                screenSummaryState.updateTransitionToForeground()
            }
            is Background -> {
                screenSummaryState.updateTransitionToBackground()
            }
            is ScreenEnd -> {
                screenSummaryState.updateForScreenEnd()
            }
            is ListItemView -> {
                screenSummaryState.updateWithListItemView(event)
            }
            is ScrollChanged -> {
                screenSummaryState.updateWithScrollChanged(event)
            }
        }
        return state
    }

    override fun entities(event: InspectableEvent, state: State?): List<SelfDescribingJson>? {
        val screenSummaryState = state as ScreenSummaryState? ?: return null

        return listOf(
            SelfDescribingJson(
                com.example.psa_trakcer.core.constants.TrackerConstants.SCHEMA_SCREEN_SUMMARY,
                screenSummaryState.data
            )
        )
    }

    override fun payloadValues(event: InspectableEvent, state: State?): Map<String, Any>? {
        return null
    }

    override fun afterTrack(event: InspectableEvent) {
    }

    override fun filter(event: InspectableEvent, state: State?): Boolean {
        if (event.schema == com.example.psa_trakcer.core.constants.TrackerConstants.SCHEMA_SCREEN_END) {
            return state != null
        }
        // do not track list item view and scroll changed events
        return false
    }

    override fun eventsBefore(event: Event): List<Event>? {
        return listOf(ScreenEnd())
    }

    companion object {
        val ID: String
            get() = "ScreenSummaryContext"
    }
}
