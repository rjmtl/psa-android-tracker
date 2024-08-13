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
import com.example.psa_trakcer.psa.ecommerce.ErrorType
import com.example.psa_trakcer.psa.ecommerce.entities.TransactionEntity
import com.example.psa_trakcer.psa.event.AbstractSelfDescribing
import com.example.psa_trakcer.psa.payload.SelfDescribingJson

/**
 * Track a transaction error event.
 * Entity schema: iglu:com.psaanalytics.psa.ecommerce/transaction_error/jsonschema/1-0-0
 *
 * @param transaction The TransactionEntity details.
 * @param errorCode Error-identifying code for the transaction issue, e.g. E522.
 * @param errorShortcode Shortcode for the error that occurred in the transaction.
 * @param errorDescription Longer description for the error that occurred in the transaction.
 * @param errorType Type of error.
 * @param resolution The resolution selected for the error scenario.
 */
class TransactionErrorEvent @JvmOverloads constructor(
    /**
    * The transaction details.
    */
    var transaction: TransactionEntity,

    /**
    * Error-identifying code for the transaction issue, e.g. E522.
    */
    var errorCode: String? = null,

    /**
    * Shortcode for the error that occurred in the transaction e.g. declined_by_stock_api, declined_by_payment_method, card_declined, pm_card_radarBlock.
    */
    var errorShortcode: String? = null,

    /**
    * Longer description for the error that occurred in the transaction.
    */
    var errorDescription: String? = null,

    /**
    * Type of error. Hard error types mean the customer must provide another form of payment e.g. an expired card.
    * Soft errors can be the result of temporary issues where retrying might be successful e.g. processor declined the transaction.
    */
    var errorType: ErrorType? = null,

    /**
    * The resolution selected for the error scenario e.g. retry_allowed, user_blacklisted, block_gateway, contact_user, default.
    */
    var resolution: String? = null
) : AbstractSelfDescribing() {

    /** The event schema */
    override val schema: String
        get() = com.example.psa_trakcer.core.constants.TrackerConstants.SCHEMA_ECOMMERCE_ACTION
    
    override val dataPayload: Map<String, Any?>
        get() {
            val payload = HashMap<String, Any?>()
            payload["type"] = com.example.psa_trakcer.core.ecommerce.EcommerceAction.trns_error.toString()
            return payload
        }

    override val entitiesForProcessing: List<SelfDescribingJson>?
        get() {
            val entities = mutableListOf<SelfDescribingJson>()
            entities.add(entity)
            entities.add(transaction.entity)
            return entities
        }

    private val entity: SelfDescribingJson
        get() = SelfDescribingJson(
            com.example.psa_trakcer.core.constants.TrackerConstants.SCHEMA_ECOMMERCE_TRANSACTION_ERROR,
            mapOf(
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_TRANSACTION_ERROR_CODE to errorCode,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_TRANSACTION_ERROR_SHORTCODE to errorShortcode,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_TRANSACTION_ERROR_DESCRIPTION to errorDescription,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_TRANSACTION_ERROR_TYPE to errorType.toString(),
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_TRANSACTION_ERROR_RESOLUTION to resolution
            ).filter { it.value != null }
        )
}
