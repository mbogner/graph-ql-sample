package dev.mbo.graphqlsample.graphql.config

import graphql.kickstart.execution.context.GraphQLContext
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext
import graphql.kickstart.servlet.context.DefaultGraphQLWebSocketContext
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.websocket.Session
import javax.websocket.server.HandshakeRequest

/**
 * https://github.com/philip-jvm/learn-spring-boot-graphql/blob/master/src/main/java/com/learn/graphql/context/CustomGraphQLContextBuilder.java
 */
@Component
class CustomGraphQLContextBuilder @Autowired constructor(
    private val dataLoaderRegistryFactory: DataLoaderRegistryFactory
) : GraphQLServletContextBuilder {

    override fun build(
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ): GraphQLContext {
        val context = DefaultGraphQLServletContext.createServletContext()
            .with(httpServletRequest)
            .with(httpServletResponse)
            .with(dataLoaderRegistryFactory.create())
            .build()
        return CustomGraphQLContext(context)
    }

    override fun build(session: Session, handshakeRequest: HandshakeRequest): GraphQLContext {
        return DefaultGraphQLWebSocketContext.createWebSocketContext()
            .with(session)
            .with(handshakeRequest)
            .build()
    }

    override fun build(): GraphQLContext {
        throw IllegalStateException("Unsupported")
    }

}
