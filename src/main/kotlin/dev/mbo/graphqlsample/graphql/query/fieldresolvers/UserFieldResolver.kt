@file:Suppress("unused")

package dev.mbo.graphqlsample.graphql.query.fieldresolvers

import dev.mbo.graphqlsample.graphql.query.dataloader.PostsByUserIdDataLoaderFactory
import dev.mbo.graphqlsample.model.Post
import dev.mbo.graphqlsample.model.User
import dev.mbo.graphqlsample.repo.PostRepository
import graphql.kickstart.tools.GraphQLResolver
import graphql.schema.DataFetchingEnvironment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.UUID
import java.util.concurrent.CompletableFuture

@Component
class UserFieldResolver @Autowired constructor(
    private val postRepository: PostRepository,
) : GraphQLResolver<User> {

    fun posts(user: User, environment: DataFetchingEnvironment): CompletableFuture<Set<Post>> {
        return environment
            .getDataLoader<UUID, Set<Post>>(PostsByUserIdDataLoaderFactory.NAME)
            .load(user.id)
    }

}
