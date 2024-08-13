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
import com.example.psa_trakcer.psa.ecommerce.entities.ProductEntity
import com.example.psa_trakcer.psa.event.AbstractSelfDescribing
import com.example.psa_trakcer.psa.payload.SelfDescribingJson

/**
 * Track a refund event. Use the same transaction ID as for the original Transaction event.
 * Provide a list of products to specify certain products to be refunded, otherwise the whole transaction 
 * will be marked as refunded.
 * Entity schema: iglu:com.psaanalytics.psa.ecommerce/refund/jsonschema/1-0-0
 *
 * @param transactionId The ID of the relevant transaction.
 * @param currency The currency in which the product(s) are being priced (ISO 4217).
 * @param refundAmount The monetary amount refunded.
 * @param refundReason Reason for refunding the whole or part of the transaction.
 * @param products The products to be refunded.
 */
class RefundEvent @JvmOverloads constructor(
    /** The ID of the relevant transaction. */
    var transactionId: String,
    
    /** The monetary amount refunded. */
    var refundAmount: Number,

    /** The currency in which the product is being priced (ISO 4217). */
    var currency: String,
    
    /** Reason for refunding the whole or part of the transaction. */
    var refundReason: String? = null, 
    
    /** The products to be refunded. */
    var products: List<ProductEntity>? = null
) : AbstractSelfDescribing() {

    /** The event schema */
    override val schema: String
        get() = com.example.psa_trakcer.core.constants.TrackerConstants.SCHEMA_ECOMMERCE_ACTION
    
    override val dataPayload: Map<String, Any?>
        get() {
            val payload = HashMap<String, Any?>()
            payload["type"] = com.example.psa_trakcer.core.ecommerce.EcommerceAction.refund.toString()
            return payload
        }

    override val entitiesForProcessing: List<SelfDescribingJson>?
        get() {
            val entities = mutableListOf<SelfDescribingJson>()
            products?.let {
                for (product in it) {
                    entities.add(product.entity)
                }
            }
            entities.add(entity)
            return entities
        }

    private val entity: SelfDescribingJson
        get() = SelfDescribingJson(
            com.example.psa_trakcer.core.constants.TrackerConstants.SCHEMA_ECOMMERCE_REFUND,
            mapOf<String, Any?>(
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_REFUND_ID to transactionId,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_REFUND_CURRENCY to currency,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_REFUND_AMOUNT to refundAmount,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_REFUND_REASON to refundReason
            ).filter { it.value != null }
        )
}
