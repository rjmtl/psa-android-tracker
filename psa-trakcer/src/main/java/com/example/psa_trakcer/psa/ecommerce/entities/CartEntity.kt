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
package com.example.psa_trakcer.psa.ecommerce.entities

import com.example.psa_trakcer.core.constants.Parameters
import com.example.psa_trakcer.core.constants.TrackerConstants
import com.example.psa_trakcer.psa.payload.SelfDescribingJson

/**
 * Provided to certain Ecommerce events. The Cart properties will be sent with the event as a 
 * Cart entity.
 * Entity schema: iglu:com.psaanalytics.psa.ecommerce/cart/jsonschema/1-0-0
 */
data class CartEntity @JvmOverloads constructor(
    /**
     * The total value of the cart after this interaction.
     */
    var totalValue: Number,

    /**
     * The currency used for this cart (ISO 4217).
     */
    var currency: String,

    /**
     * The unique ID representing this cart.
     */
    var cartId: String? = null
) {
    internal val entity: SelfDescribingJson
        get() = SelfDescribingJson(
            com.example.psa_trakcer.core.constants.TrackerConstants.SCHEMA_ECOMMERCE_CART,
            mapOf<String, Any?>(
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_CART_ID to cartId,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_CART_VALUE to totalValue,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_CART_CURRENCY to currency,
            ).filter { it.value != null }
        )
}
