package com.scheffer.erik.financial.api.util

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder

fun MessageSource.getMessage(code: String) = getMessage(code, null, LocaleContextHolder.getLocale())
