package dev.mbo.graphqlsample.graphql.query.fieldresolvers

import dev.mbo.graphqlsample.logging.LoggedMethod
import dev.mbo.graphqlsample.model.Post
import dev.mbo.graphqlsample.model.User
import dev.mbo.graphqlsample.repo.UserRepository
import graphql.kickstart.tools.GraphQLResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Suppress("unused")
@Component
class PostResolver @Autowired constructor(
    private val userRepository: UserRepository,
) : GraphQLResolver<Post> {

    @LoggedMethod
    fun author(post: Post): User {
        return userRepository.findById(post.user?.id!!).orElseThrow()
    }

}
