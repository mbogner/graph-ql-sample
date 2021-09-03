@file:Suppress("unused")

package dev.mbo.graphqlsample.graphql.query.fieldresolvers

import dev.mbo.graphqlsample.graphql.query.dataloader.UserByIdDataLoaderFactory
import dev.mbo.graphqlsample.model.Post
import dev.mbo.graphqlsample.model.User
import graphql.kickstart.tools.GraphQLResolver
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component
import java.util.UUID
import java.util.concurrent.CompletableFuture

@Component
class PostFieldResolver : GraphQLResolver<Post> {

    fun author(post: Post, environment: DataFetchingEnvironment): CompletableFuture<User> {
        return environment
            .getDataLoader<UUID, User>(UserByIdDataLoaderFactory.NAME)
            .load(post.userId)
    }

}
