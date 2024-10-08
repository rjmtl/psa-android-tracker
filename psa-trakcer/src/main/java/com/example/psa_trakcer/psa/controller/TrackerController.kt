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
package com.example.psa_trakcer.psa.controller

import android.net.Uri
import com.example.psa_trakcer.psa.tracker.CrossDeviceParameterConfiguration
import com.example.psa_trakcer.core.tracker.TrackerConfigurationInterface
import com.example.psa_trakcer.psa.ecommerce.EcommerceController
import com.example.psa_trakcer.psa.event.Event
import com.example.psa_trakcer.psa.media.controller.MediaController
import java.util.*

/**
 * Controller for managing the tracker.
 */
interface TrackerController : TrackerConfigurationInterface {
    /** Version of the tracker.  */
    val version: String

    /**
     * Whether the tracker is running and able to collect/send events.
     * @see [pause] and [resume]
     */
    val isTracking: Boolean

    /**
     * Namespace of the tracker.
     * It is used to identify the tracker when there are multiple trackers running in the same app.
     */
    val namespace: String
    
    // Controllers
    
    /**
     * NetworkController.
     * Note: don't retain the reference. It may change on tracker reconfiguration.
     */
    val network: NetworkController?

    /**
     * SessionController.
     * Note: don't retain the reference. It may change on tracker reconfiguration.
     */
    val session: SessionController?

    /**
     * EmitterController.
     * Note: don't retain the reference. It may change on tracker reconfiguration.
     */
    val emitter: EmitterController

    /**
     * SubjectController.
     * Note: don't retain the reference. It may change on tracker reconfiguration.
     */
    val subject: SubjectController

    /**
     * GdprController.
     * Note: don't retain the reference. It may change on tracker reconfiguration.
     */
    val gdpr: GdprController

    /**
     * GlobalContextsController.
     * Note: don't retain the reference. It may change on tracker reconfiguration.
     */
    val globalContexts: GlobalContextsController

    /**
     * Controller for managing tracker plugins
     * Note: don't retain the reference. It may change on tracker reconfiguration.
     */
    val plugins: PluginsController

    /**
     * Media controller for managing media tracking instances and tracking media events.
     */
    val media: MediaController

    /**
     * Controller for ecommerce
     * Note: don't retain the reference. It may change on tracker reconfiguration.
     */
    val ecommerce: EcommerceController
    
    // Methods
    
    /**
     * Track the event.
     * The tracker will process and send the event.
     * 
     * @param event The event to track.
     * @return The event's unique ID, or null when tracking is paused
     */
    fun track(event: Event): UUID?

    /**
     * Pause the tracker.
     * The tracker will stop any new activity tracking, but will continue to send any remaining events
     * already tracked but not yet sent.
     * Calling [track] will not have any effect, and the event tracked will be lost.
     */
    fun pause()

    /**
     * Resume the tracker.
     * The tracker will start tracking again.
     */
    fun resume()

    /**
     * Adds user and session information to a URI.
     *
     * For example, calling decorateLink on `appSchema://path/to/page` with all extended parameters enabled will return:
     *
     *  `appSchema://path/to/page?_sp=domainUserId.timestamp.sessionId.subjectUserId.sourceId.platform.reason`
     *
     *  @param uri The URI to add the query string to
     *  @param extendedParameters Any optional parameters to include in the query string.
     *
     *  @return Optional URL
     *      - null if [SessionController.userId] is null from `sessionContext(false)` being passed in [TrackerConfiguration]
     *      - otherwise, decorated URL
     */
    fun decorateLink(
        uri: Uri,
        extendedParameters: CrossDeviceParameterConfiguration? = null
    ): Uri?
}
