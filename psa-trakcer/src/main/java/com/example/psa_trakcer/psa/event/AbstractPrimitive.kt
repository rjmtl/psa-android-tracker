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

/**
 * Abstract class which contains common elements to all "primitive" events, i.e. events that are
 * not based on a schema, such as [Structured] events.
 */
abstract class AbstractPrimitive protected constructor() : AbstractEvent() {
    /**
     * @return The name of the event.
     */
    abstract val name: String
}
