package com.freitas.dgskotlinissue1341.exceptionhandler

import com.freitas.dgskotlinissue1341.exception.MissingDataException
import com.netflix.graphql.types.errors.TypedGraphQLError
import graphql.GraphQLError
import graphql.execution.DataFetcherExceptionHandler
import graphql.execution.DataFetcherExceptionHandlerParameters
import graphql.execution.DataFetcherExceptionHandlerResult
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class CustomDataFetchingExceptionHandler : DataFetcherExceptionHandler {

    override fun handleException(handlerParameters: DataFetcherExceptionHandlerParameters): CompletableFuture<DataFetcherExceptionHandlerResult> {
        return if (handlerParameters.exception is MissingDataException) {
            val debugInfo = mapOf(
                    "some-field" to "some-value",
                    "message" to handlerParameters.exception.message
            )
            val graphqlError = TypedGraphQLError.newInternalErrorBuilder()
                    .message("This custom thing went wrong!")
                    .debugInfo(debugInfo)
                    .path(handlerParameters.path).build()
            val result = DataFetcherExceptionHandlerResult.newResult()
                    .error(graphqlError)
                    .build()
            CompletableFuture.completedFuture(result)
        } else {
            super.handleException(handlerParameters)
        }
    }

}