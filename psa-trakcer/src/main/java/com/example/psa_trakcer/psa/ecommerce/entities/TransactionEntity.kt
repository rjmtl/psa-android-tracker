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
 * Provided to certain Ecommerce events. The Transaction properties will be sent with the event as a
 * Transaction entity.
 * Entity schema: iglu:com.psaanalytics.psa.ecommerce/transaction/jsonschema/1-0-0
 */
data class TransactionEntity @JvmOverloads constructor(
    /**
     * The ID of the transaction.
     */
    var transactionId: String,

    /**
     * The total value of the transaction.
     */
    var revenue: Number,

    /**
     * The currency used for the transaction (ISO 4217).
     */
    var currency: String,

    /**
     * The payment method used for the transaction.
     */
    var paymentMethod: String,

    /**
     * Total quantity of items in the transaction.
     */
    var totalQuantity: Int,

    /**
     * Total amount of tax on the transaction.
     */
    var tax: Number? = null,

    /**
     * Total cost of shipping on the transaction.
     */
    var shipping: Number? = null,

    /**
     * Discount code used.
     */
    var discountCode: String? = null,

    /**
     * Discount amount taken off.
     */
    var discountAmount: Number? = null,

    /**
     * Whether the transaction is a credit order or not.
     */
    var creditOrder: Boolean? = null
) {
    internal val entity: SelfDescribingJson
        get() = SelfDescribingJson(
            com.example.psa_trakcer.core.constants.TrackerConstants.SCHEMA_ECOMMERCE_TRANSACTION,
            mapOf<String, Any?>(
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_TRANSACTION_ID to transactionId,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_TRANSACTION_REVENUE to revenue,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_TRANSACTION_CURRENCY to currency,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_TRANSACTION_PAYMENT_METHOD to paymentMethod,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_TRANSACTION_QUANTITY to totalQuantity,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_TRANSACTION_TAX to tax,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_TRANSACTION_SHIPPING to shipping,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_TRANSACTION_DISCOUNT_CODE to discountCode,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_TRANSACTION_DISCOUNT_AMOUNT to discountAmount,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_TRANSACTION_CREDIT_ORDER to creditOrder
            ).filter { it.value != null }
        )
}
