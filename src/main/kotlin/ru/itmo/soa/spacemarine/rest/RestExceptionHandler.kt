package ru.itmo.soa.spacemarine.rest

import jakarta.validation.ConstraintViolationException
import org.hibernate.validator.internal.engine.path.PathImpl
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.itmo.soa.spacemarine.rest.data.ErrorResponse
import java.time.LocalDateTime

@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ErrorResponse {
        System.err.println(e.message)

        val fields = e.bindingResult.fieldErrors

        return ErrorResponse(
            errorMessage = "Неправильно введенны поля! ${fields.joinToString { it.field }}",
            timestamp = LocalDateTime.now().toString()
        )
    }

    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleConstraintViolationException(e: ConstraintViolationException): ErrorResponse {
        System.err.println(e.message)
        val response = ErrorResponse(
            errorMessage = "Неправильно введенны поля! ${
                e.constraintViolations.joinToString {
                    (it.propertyPath as PathImpl).leafNode.name
                }
            }}",
            timestamp = LocalDateTime.now().toString()
        )
        return response
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(e: Exception): ErrorResponse {
        System.err.println(e.message)
        return ErrorResponse(
            timestamp = LocalDateTime.now().toString(),
            errorMessage = "Произошла ошибка! Попробуйте еще раз!"
        )
    }


}