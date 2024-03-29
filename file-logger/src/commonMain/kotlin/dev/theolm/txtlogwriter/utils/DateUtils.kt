package dev.theolm.txtlogwriter.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal fun getLocalDateTime() = run {
    val date = Clock.System.now()
    date.toLocalDateTime(TimeZone.currentSystemDefault())
}
