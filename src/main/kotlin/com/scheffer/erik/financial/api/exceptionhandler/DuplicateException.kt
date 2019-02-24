package com.scheffer.erik.financial.api.exceptionhandler

class DuplicateException(val tableName: String, val duplicatedField: String) : RuntimeException()