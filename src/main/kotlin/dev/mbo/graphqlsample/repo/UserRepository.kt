package dev.mbo.graphqlsample.repo

import dev.mbo.graphqlsample.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

interface UserRepository : JpaRepository<User, UUID> {

    fun findByIdIn(ids: Collection<UUID>): List<User>

    @Query("SELECT u FROM User u JOIN FETCH u.posts WHERE u.id IN :userIds")
    fun findByUserIdsWithPosts(@Param("userIds") userIds: Collection<UUID>): Set<User>

}
