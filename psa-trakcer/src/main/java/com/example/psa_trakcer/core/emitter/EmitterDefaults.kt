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
package com.example.psa_trakcer.core.emitter

import com.example.psa_trakcer.psa.emitter.BufferOption
import com.example.psa_trakcer.psa.network.HttpMethod
import com.example.psa_trakcer.psa.network.Protocol

import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.time.DurationUnit
import kotlin.time.toDuration

object EmitterDefaults {
    var httpMethod = HttpMethod.POST
    var bufferOption = BufferOption.Single
    var httpProtocol = Protocol.HTTPS
    var tlsVersions: EnumSet<TLSVersion> = EnumSet.of(TLSVersion.TLSv1_2)
    var emitRange: Int = BufferOption.LargeGroup.code
    var emitterTick = 5
    var emptyLimit = 5
    var byteLimitGet: Long = 40000
    var byteLimitPost: Long = 40000
    var emitTimeout = 30
    var threadPoolSize = 15
    var serverAnonymisation = false
    var retryFailedRequests = true
    var timeUnit = TimeUnit.SECONDS
    var maxEventStoreAge = 30.toDuration(DurationUnit.DAYS)
    var maxEventStoreSize: Long = 1000
}
