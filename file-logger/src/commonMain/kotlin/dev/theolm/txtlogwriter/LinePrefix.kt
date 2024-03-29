package dev.theolm.txtlogwriter

import dev.theolm.txtlogwriter.utils.getLocalDateTime

public sealed interface LinePrefix {
    public fun getPrefix(): String

    public data object None : LinePrefix {
        override fun getPrefix(): String = ""
    }

    public data object Date : LinePrefix {
        override fun getPrefix(): String {
            return getLocalDateTime().date.toString()
        }
    }

    public data object Time : LinePrefix {
        override fun getPrefix(): String {
            return getLocalDateTime().time.toString()
        }
    }
    public data object DateTime : LinePrefix {
        override fun getPrefix(): String {
            return getLocalDateTime().toString()
        }
    }
    public data class Custom(val linePrefix: String) : LinePrefix {
        override fun getPrefix(): String = linePrefix
    }
}
