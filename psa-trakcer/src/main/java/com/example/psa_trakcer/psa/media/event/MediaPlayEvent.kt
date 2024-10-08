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
import com.example.psa_trakcer.core.media.event.MediaPlayerUpdatingEvent
import com.example.psa_trakcer.psa.event.AbstractSelfDescribing
import com.example.psa_trakcer.psa.media.entity.MediaPlayerEntity

/**
 * Media player event sent when the player changes state to playing from previously being paused.
 */
class MediaPlayEvent : AbstractSelfDescribing(), MediaPlayerUpdatingEvent {
    override val schema: String
        get() = MediaSchemata.eventSchema("play")

    override val dataPayload: Map<String, Any?>
        get() = emptyMap()

    override fun update(player: MediaPlayerEntity) {
        player.paused = false
    }
}
