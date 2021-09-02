@file:Suppress("unused")

package dev.mbo.graphqlsample.graphql.query

import dev.mbo.graphqlsample.logging.LoggedMethod
import dev.mbo.graphqlsample.model.Post
import dev.mbo.graphqlsample.repo.PostRepository
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Suppress("unused")
@Component
class PostQueryResolver @Autowired constructor(
    private val postRepository: PostRepository,
) : GraphQLQueryResolver {

    @LoggedMethod
    fun allPosts(page: Int, size: Int): List<Post> {
        return postRepository.findAll(PageRequest.of(page, size)).content
    }
}
