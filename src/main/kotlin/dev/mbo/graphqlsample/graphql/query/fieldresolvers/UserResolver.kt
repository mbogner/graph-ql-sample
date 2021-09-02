package dev.mbo.graphqlsample.graphql.query.fieldresolvers

import dev.mbo.graphqlsample.logging.LoggedMethod
import dev.mbo.graphqlsample.model.Post
import dev.mbo.graphqlsample.model.User
import dev.mbo.graphqlsample.repo.PostRepository
import graphql.kickstart.tools.GraphQLResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Suppress("unused")
@Component
class UserResolver @Autowired constructor(
    private val postRepository: PostRepository,
) : GraphQLResolver<User> {

    @LoggedMethod
    fun posts(user: User): List<Post> {
        return postRepository.findByUser(user)
    }

}
