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
package com.example.psa_trakcer.core.tracker

import com.example.psa_trakcer.core.ecommerce.EcommerceControllerImpl
import com.example.psa_trakcer.core.emitter.EmitterControllerImpl
import com.example.psa_trakcer.core.emitter.NetworkControllerImpl
import com.example.psa_trakcer.core.gdpr.GdprControllerImpl
import com.example.psa_trakcer.core.globalcontexts.GlobalContextsControllerImpl
import com.example.psa_trakcer.core.session.SessionControllerImpl
import com.example.psa_trakcer.psa.configuration.EmitterConfiguration
import com.example.psa_trakcer.psa.configuration.GdprConfiguration
import com.example.psa_trakcer.psa.configuration.NetworkConfiguration
import com.example.psa_trakcer.psa.configuration.PluginIdentifiable
import com.example.psa_trakcer.psa.configuration.SessionConfiguration
import com.example.psa_trakcer.psa.configuration.SubjectConfiguration
import com.example.psa_trakcer.psa.configuration.TrackerConfiguration
import com.example.psa_trakcer.psa.media.controller.MediaController

interface ServiceProviderInterface {
    val namespace: String

    // Internal services
    val isTrackerInitialized: Boolean
    fun getOrMakeTracker(): Tracker
    fun getOrMakeEmitter(): com.example.psa_trakcer.core.emitter.Emitter
    fun getOrMakeSubject(): Subject

    // Controllers
    fun getOrMakeTrackerController(): TrackerControllerImpl
    fun getOrMakeEmitterController(): EmitterControllerImpl
    fun getOrMakeNetworkController(): NetworkControllerImpl
    fun getOrMakeGdprController(): GdprControllerImpl
    fun getOrMakeGlobalContextsController(): GlobalContextsControllerImpl
    fun getOrMakeSubjectController(): SubjectControllerImpl
    fun getOrMakeSessionController(): SessionControllerImpl
    val pluginsController: PluginsControllerImpl
    val mediaController: MediaController
    val ecommerceController: EcommerceControllerImpl

    // Configuration Updates
    val trackerConfiguration: TrackerConfiguration
    val networkConfiguration: NetworkConfiguration
    val subjectConfiguration: SubjectConfiguration
    val emitterConfiguration: EmitterConfiguration
    val sessionConfiguration: SessionConfiguration
    val gdprConfiguration: GdprConfiguration

    // Plugins
    val pluginConfigurations: List<PluginIdentifiable>
    fun addPlugin(plugin: PluginIdentifiable)
    fun removePlugin(identifier: String)
}
