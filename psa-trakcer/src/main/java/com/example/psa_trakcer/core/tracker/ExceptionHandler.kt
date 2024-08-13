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

import androidx.annotation.RestrictTo
import com.example.psa_trakcer.core.constants.Parameters
import com.example.psa_trakcer.core.constants.TrackerConstants
import com.example.psa_trakcer.core.utils.NotificationCenter.postNotification
import com.example.psa_trakcer.core.utils.Util.addToMap
import com.example.psa_trakcer.core.utils.Util.stackTraceToString
import com.example.psa_trakcer.psa.event.SelfDescribing
import com.example.psa_trakcer.psa.payload.SelfDescribingJson

@RestrictTo(RestrictTo.Scope.LIBRARY)
class ExceptionHandler : Thread.UncaughtExceptionHandler {
    private val defaultHandler: Thread.UncaughtExceptionHandler? = Thread.getDefaultUncaughtExceptionHandler()

    /**
     * Sends a Psa Event and then re-throws.
     *
     * @param t The thread that crashed
     * @param e The throwable
     */
    override fun uncaughtException(t: Thread, e: Throwable) {
        Logger.d(TAG, "Uncaught exception being tracked...")

        // Ensure message is not-null/empty
        var message = truncateString(e.message, MAX_MESSAGE_LENGTH)
        if (message == null || message.isEmpty()) {
            message = "Android Exception. Null or empty message found"
        }
        val stack = truncateString(stackTraceToString(e), MAX_STACK_LENGTH)
        val threadName = truncateString(t.name, MAX_THREAD_NAME_LENGTH)
        var lineNumber: Int? = null
        var className: String? = null
        if (e.stackTrace.isNotEmpty()) {
            val stackElement = e.stackTrace[0]

            // Ensure lineNumber is greater than or equal to zero
            lineNumber = stackElement.lineNumber
            if (lineNumber < 0) {
                lineNumber = null
            }
            className = truncateString(stackElement.className, MAX_CLASS_NAME_LENGTH)
        }
        val exceptionName = truncateString(e.javaClass.name, MAX_EXCEPTION_NAME_LENGTH)
        val data: MutableMap<String, Any> = HashMap()
        addToMap(com.example.psa_trakcer.core.constants.Parameters.APP_ERROR_MESSAGE, message, data)
        addToMap(com.example.psa_trakcer.core.constants.Parameters.APP_ERROR_STACK, stack, data)
        addToMap(com.example.psa_trakcer.core.constants.Parameters.APP_ERROR_THREAD_NAME, threadName, data)
        addToMap(com.example.psa_trakcer.core.constants.Parameters.APP_ERROR_THREAD_ID, t.id, data)
        addToMap(com.example.psa_trakcer.core.constants.Parameters.APP_ERROR_LANG, "JAVA", data)
        addToMap(com.example.psa_trakcer.core.constants.Parameters.APP_ERROR_LINE, lineNumber, data)
        addToMap(com.example.psa_trakcer.core.constants.Parameters.APP_ERROR_CLASS_NAME, className, data)
        addToMap(com.example.psa_trakcer.core.constants.Parameters.APP_ERROR_EXCEPTION_NAME, exceptionName, data)
        addToMap(com.example.psa_trakcer.core.constants.Parameters.APP_ERROR_FATAL, true, data)
        val event =
            SelfDescribing(SelfDescribingJson(com.example.psa_trakcer.core.constants.TrackerConstants.APPLICATION_ERROR_SCHEMA, data))
        val notificationData: MutableMap<String, Any> = HashMap()
        notificationData["event"] = event
        postNotification("SnowplowCrashReporting", notificationData)
        defaultHandler?.uncaughtException(t, e)
    }

    /**
     * Truncates a string at a maximum length
     *
     * @param str The string to truncate
     * @param maxLength The maximum length
     * @return the truncated string
     */
    private fun truncateString(str: String?, maxLength: Int): String? {
        return str?.substring(0, str.length.coerceAtMost(maxLength))
    }

    companion object {
        private val TAG = ExceptionHandler::class.java.simpleName
        private const val MAX_MESSAGE_LENGTH = 2048
        private const val MAX_STACK_LENGTH = 8096
        private const val MAX_THREAD_NAME_LENGTH = 1024
        private const val MAX_CLASS_NAME_LENGTH = 1024
        private const val MAX_EXCEPTION_NAME_LENGTH = 1024
    }
}
