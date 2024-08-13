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
package com.example.psa_trakcer.psa.globalcontexts

import com.example.psa_trakcer.psa.payload.SelfDescribingJson
import com.example.psa_trakcer.psa.tracker.InspectableEvent

/**
 * Pass a FunctionalGenerator when creating [GlobalContext] to add a context entity to every event, 
 * where the entity can contain properties from the event. 
 */
abstract class FunctionalGenerator {
    abstract fun apply(event: InspectableEvent): List<SelfDescribingJson>
}
