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
import com.example.psa_trakcer.psa.event.AbstractSelfDescribing
import com.example.psa_trakcer.psa.payload.SelfDescribingJson

/**
 * Track a checkout step.
 * Entity schema: iglu:com.psaanalytics.psa.ecommerce/checkout_step/jsonschema/1-0-0
 *
 * @param step Checkout step index.
 * @param shippingPostcode Shipping address postcode.
 * @param billingPostcode Billing address postcode.
 * @param shippingFullAddress Full shipping address.
 * @param billingFullAddress Full billing address.
 * @param deliveryProvider Can be used to discern delivery providers e.g. DHL, PostNL etc.
 * @param deliveryMethod Store pickup, standard delivery, express delivery, international, etc.
 * @param couponCode Coupon applied at checkout.
 * @param accountType Type of account used on checkout, e.g. existing user, guest.
 * @param paymentMethod Any kind of payment method the user selected to proceed. Card, PayPal, Alipay etc.
 * @param proofOfPayment E.g. invoice or receipt.
 * @param marketingOptIn If opted in to marketing campaigns to the email address.
 */
class CheckoutStepEvent @JvmOverloads constructor(
    /** Checkout step index. */
    var step: Int,
    
    /** Shipping address postcode. */
    var shippingPostcode: String? = null,
    
    /** Billing address postcode. */
    var billingPostcode: String? = null,
    
    /** Full shipping address. */
    var shippingFullAddress: String? = null,
    
    /** Full billing address. */
    var billingFullAddress: String? = null,
    
    /** Can be used to discern delivery providers DHL, PostNL etc. */
    var deliveryProvider: String? = null,
    
    /** E.g. store pickup, standard delivery, express delivery, international. */
    var deliveryMethod: String? = null,
    
    /** Coupon applied at checkout. */
    var couponCode: String? = null,
    
    /** Type of account used on checkout, e.g. existing user, guest. */
    var accountType: String? = null,
    
    /** Any kind of payment method the user selected to proceed. Card, PayPal, Alipay etc. */
    var paymentMethod: String? = null,
    
    /** E.g. invoice or receipt */
    var proofOfPayment: String? = null,
    
    /** If opted in to marketing campaigns to the email address. */
    var marketingOptIn: Boolean? = null
) : AbstractSelfDescribing() {

    /** The event schema */
    override val schema: String
        get() = com.example.psa_trakcer.core.constants.TrackerConstants.SCHEMA_ECOMMERCE_ACTION
    
    override val dataPayload: Map<String, Any?>
        get() {
            val payload = HashMap<String, Any?>()
            payload["type"] = com.example.psa_trakcer.core.ecommerce.EcommerceAction.checkout_step.toString()
            return payload
        }

    override val entitiesForProcessing: List<SelfDescribingJson>?
        get() = listOf(entity)
    
    private val entity: SelfDescribingJson
        get() = SelfDescribingJson(
            com.example.psa_trakcer.core.constants.TrackerConstants.SCHEMA_ECOMMERCE_CHECKOUT_STEP,
            mapOf(
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_CHECKOUT_STEP to step,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_CHECKOUT_SHIPPING_POSTCODE to shippingPostcode,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_CHECKOUT_BILLING_POSTCODE to billingPostcode,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_CHECKOUT_SHIPPING_ADDRESS to shippingFullAddress,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_CHECKOUT_BILLING_ADDRESS to billingFullAddress,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_CHECKOUT_DELIVERY_PROVIDER to deliveryProvider,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_CHECKOUT_DELIVERY_METHOD to deliveryMethod,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_CHECKOUT_COUPON_CODE to couponCode,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_CHECKOUT_ACCOUNT_TYPE to accountType,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_CHECKOUT_PAYMENT_METHOD to paymentMethod,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_CHECKOUT_PROOF_OF_PAYMENT to proofOfPayment,
                com.example.psa_trakcer.core.constants.Parameters.ECOMM_CHECKOUT_MARKETING_OPT_IN to marketingOptIn
            ).filter { it.value != null }
        )
}
