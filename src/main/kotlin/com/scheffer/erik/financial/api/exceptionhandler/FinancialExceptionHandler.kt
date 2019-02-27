package com.scheffer.erik.financial.api.exceptionhandler

import com.scheffer.erik.financial.api.util.getMessage
import org.apache.commons.lang3.exception.ExceptionUtils
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@ControllerAdvice
class FinancialExceptionHandler(private val messageSource: MessageSource)
    : ResponseEntityExceptionHandler() {

    override fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException,
                                              headers: HttpHeaders,
                                              status: HttpStatus,
                                              request: WebRequest): ResponseEntity<Any> {
        val userMessage = messageSource.getMessage("message.invalid")
        val developerMessage = ex.cause?.toString() ?: ex.toString()
        val errors = listOf(ErrorMessageWrapper(userMessage, developerMessage))
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request)
    }

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException,
                                              headers: HttpHeaders,
                                              status: HttpStatus,
                                              request: WebRequest) =
            handleExceptionInternal(ex,
                    createErrorMessageWrappers(ex.bindingResult),
                    headers,
                    HttpStatus.BAD_REQUEST,
                    request)

    @ExceptionHandler(DuplicateException::class)
    fun handleDuplicate(ex: DuplicateException): ResponseEntity<List<ErrorMessageWrapper>> {
        val message = messageSource.getMessage("message.duplicate.errorMessage",
                arrayOf(ex.tableName, ex.duplicatedField), LocaleContextHolder.getLocale())
        return ResponseEntity.status(HttpStatus.CONFLICT).body(listOf(ErrorMessageWrapper(message, message)))
    }

    @ExceptionHandler(EmptyResultDataAccessException::class)
    fun handleEmptyResultDataAccessException(ex: EmptyResultDataAccessException, request: WebRequest) =
            handleExceptionInternal(ex,
                    listOf(ErrorMessageWrapper(messageSource.getMessage("resource.notFound"), ex.toString())),
                    HttpHeaders(),
                    HttpStatus.NOT_FOUND,
                    request)

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolationException(ex: DataIntegrityViolationException, request: WebRequest) =
            handleExceptionInternal(ex,
                    listOf(ErrorMessageWrapper(messageSource.getMessage("resource.operation-not-allowed"),
                            ExceptionUtils.getRootCauseMessage(ex))),
                    HttpHeaders(),
                    HttpStatus.BAD_REQUEST,
                    request)

    private fun createErrorMessageWrappers(bindingResult: BindingResult) =
            bindingResult.fieldErrors.map {
                ErrorMessageWrapper(messageSource.getMessage(it, LocaleContextHolder.getLocale()), it.toString())
            }

    data class ErrorMessageWrapper(val userMessage: String, val developerMessage: String)
}