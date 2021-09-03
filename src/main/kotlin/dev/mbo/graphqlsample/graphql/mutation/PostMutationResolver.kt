@file:Suppress("unused")

package dev.mbo.graphqlsample.graphql.mutation

import dev.mbo.graphqlsample.model.Post
import dev.mbo.graphqlsample.repo.PostRepository
import dev.mbo.graphqlsample.repo.UserRepository
import graphql.kickstart.tools.GraphQLMutationResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.UUID
import javax.transaction.Transactional

@Component
class PostMutationResolver @Autowired constructor(
    private val userRepository: UserRepository,
    private val postRepository: PostRepository,
) : GraphQLMutationResolver {

    @Transactional
    fun createPost(name: String, author: UUID): Post? {
        val user = userRepository.findById(author)
        if (user.isPresent) {
            return postRepository.save(Post.create(name, user.get()))
        }
        return null
    }
}
