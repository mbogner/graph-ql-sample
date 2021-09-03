package dev.mbo.graphqlsample.graphql.config

import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService
import java.util.concurrent.Executor
import java.util.concurrent.Executors

abstract class BaseDataLoader {

    companion object {

        protected val executor: Executor = DelegatingSecurityContextExecutorService(
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
        )
    }

    fun getExecutor(): Executor {
        return executor
    }

}
