@file:Suppress("unused")

package dev.mbo.graphqlsample.graphql.query

import dev.mbo.graphqlsample.model.User
import dev.mbo.graphqlsample.repo.UserRepository
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserQueryResolver @Autowired constructor(
    private val userRepository: UserRepository,
) : GraphQLQueryResolver {

    fun allUsers(page: Int, size: Int): Set<User> {
        return userRepository.findAll(PageRequest.of(page, size)).content.toSet()
    }

    fun me(): User {
        // TODO replace this method
        val user = User()
        user.id = UUID.randomUUID()
        user.name = "name-${user.id.toString().take(8)}"
        return user
    }
}
