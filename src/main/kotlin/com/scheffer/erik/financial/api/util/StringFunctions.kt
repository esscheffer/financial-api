package com.scheffer.erik.financial.api.util

import org.apache.commons.lang3.StringUtils

inline fun ifNotNullOrEmpty(s: String?, action: (s: String) -> Unit) {
    if (StringUtils.isNotEmpty(s)) action(s!!)
}