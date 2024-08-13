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

import com.example.psa_trakcer.core.constants.Parameters
import com.example.psa_trakcer.core.constants.TrackerConstants
import com.example.psa_trakcer.core.ecommerce.EcommerceAction
import com.example.psa_trakcer.psa.ecommerce.entities.CartEntity
import com.example.psa_trakcer.psa.ecommerce.entities.ProductEntity
import com.example.psa_trakcer.psa.event.AbstractSelfDescribing
import com.example.psa_trakcer.psa.payload.SelfDescribingJson

/**
 * Track a product or products being removed from cart.
 *
 * @param products - List of product(s) that were removed from the cart.
 * @param cart - State of the cart after this addition.
 */
class RemoveFromCartEvent @JvmOverloads constructor(
    /**
     * List of product(s) that were removed from the cart.
     */
    var products: List<ProductEntity>,

    /**
     * State of the cart after the removal.
     */
    var cart: CartEntity
) : AbstractSelfDescribing() {

    /** The event schema */
    override val schema: String
        get() = com.example.psa_trakcer.core.constants.TrackerConstants.SCHEMA_ECOMMERCE_ACTION
    
    override val dataPayload: Map<String, Any?>
        get() {
            val payload = HashMap<String, Any?>()
            payload[com.example.psa_trakcer.core.constants.Parameters.ECOMM_TYPE] = com.example.psa_trakcer.core.ecommerce.EcommerceAction.remove_from_cart.toString()
            return payload
        }

    override val entitiesForProcessing: List<SelfDescribingJson>?
        get() {
            val entities = mutableListOf<SelfDescribingJson>()
            for (product in products) {
                entities.add(product.entity)
            }
            entities.add(cart.entity)
            return entities
        }
}
