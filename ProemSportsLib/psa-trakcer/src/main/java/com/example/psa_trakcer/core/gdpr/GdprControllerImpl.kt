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
package com.example.psa_trakcer.core.gdpr

import androidx.annotation.RestrictTo
import com.example.psa_trakcer.core.Controller
import com.example.psa_trakcer.core.tracker.ServiceProviderInterface
import com.example.psa_trakcer.core.tracker.Tracker
import com.example.psa_trakcer.psa.configuration.GdprConfiguration
import com.example.psa_trakcer.psa.controller.GdprController
import com.example.psa_trakcer.psa.util.Basis

@RestrictTo(RestrictTo.Scope.LIBRARY)
class GdprControllerImpl(serviceProvider: ServiceProviderInterface) : com.example.psa_trakcer.core.Controller(serviceProvider),
    GdprController {
    private var gdpr: Gdpr? = null
    
    override val basisForProcessing: Basis?
        get() = gdpr?.basisForProcessing
            
    override val documentId: String?
        get() = if (gdpr == null) { null } else gdpr!!.documentId
    
    override val documentVersion: String?
        get() = if (gdpr == null) { null } else gdpr!!.documentVersion

    override val documentDescription: String?
        get() = if (gdpr == null) { null } else gdpr!!.documentDescription
    
    override fun reset(
        basisForProcessing: Basis,
        documentId: String?,
        documentVersion: String?,
        documentDescription: String?
    ) {
        tracker.enableGdprContext(
            basisForProcessing,
            documentId,
            documentVersion,
            documentDescription
        )
        gdpr = tracker.gdprContext
        dirtyConfig.gdpr = gdpr
    }

    override val isEnabled: Boolean
        get() = tracker.gdprContext != null

    override fun enable(): Boolean {
        val gdpr = gdpr ?: return false
        
        tracker.enableGdprContext(
            gdpr.basisForProcessing,
            gdpr.documentId,
            gdpr.documentVersion,
            gdpr.documentDescription
        )
        dirtyConfig.isEnabled = true
        return true
    }

    override fun disable() {
        dirtyConfig.isEnabled = false
        tracker.disableGdprContext()
    }

    // Private methods
    private val tracker: Tracker
        get() = serviceProvider.getOrMakeTracker()
    private val dirtyConfig: GdprConfiguration
        get() = serviceProvider.gdprConfiguration
}
