@file:Suppress("unused")

package dev.mbo.graphqlsample.graphql.query

import dev.mbo.graphqlsample.model.Post
import dev.mbo.graphqlsample.repo.PostRepository
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class PostQueryResolver @Autowired constructor(
    private val postRepository: PostRepository,
) : GraphQLQueryResolver {

    fun allPosts(page: Int, size: Int): Set<Post> {
        return postRepository.findAll(PageRequest.of(page, size)).content.toSet()
    }
}
