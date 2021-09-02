package dev.mbo.graphqlsample.repo

import dev.mbo.graphqlsample.model.Post
import dev.mbo.graphqlsample.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PostRepository : JpaRepository<Post, UUID> {

    fun findByUser(user: User): List<Post>

}
