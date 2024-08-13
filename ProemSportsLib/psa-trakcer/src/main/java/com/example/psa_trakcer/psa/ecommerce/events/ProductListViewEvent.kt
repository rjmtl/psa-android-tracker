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
package com.example.psa_trakcer.psa.ecommerce.events

import com.example.psa_trakcer.core.constants.TrackerConstants
import com.example.psa_trakcer.core.ecommerce.EcommerceAction
import com.example.psa_trakcer.psa.ecommerce.entities.ProductEntity
import com.example.psa_trakcer.psa.event.AbstractSelfDescribing
import com.example.psa_trakcer.psa.payload.SelfDescribingJson

/**
 * Track a product list view.
 *
 * @param products - List of products viewed.
 * @param name - The list name.
 */
class ProductListViewEvent @JvmOverloads constructor(var products: List<ProductEntity>, var name: String? = null) : AbstractSelfDescribing() {

    /** The event schema */
    override val schema: String
        get() = com.example.psa_trakcer.core.constants.TrackerConstants.SCHEMA_ECOMMERCE_ACTION

    override val dataPayload: Map<String, Any?>
        get() {
            val payload = HashMap<String, Any?>()
            payload["type"] = com.example.psa_trakcer.core.ecommerce.EcommerceAction.list_view.toString()
            name?.let { payload["name"] = it }
            return payload
        }

    override val entitiesForProcessing: List<SelfDescribingJson>?
        get() {
            val entities = mutableListOf<SelfDescribingJson>()
            for (product in products) {
                entities.add(product.entity)
            }
            return entities
        }
}
