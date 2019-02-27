package com.scheffer.erik.financial.api.exceptions

class DuplicateException(val tableName: String, val duplicatedField: String) : RuntimeException()