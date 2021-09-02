@file:Suppress("unused")

package dev.mbo.graphqlsample.graphql.query

import dev.mbo.graphqlsample.logging.LoggedMethod
import dev.mbo.graphqlsample.model.User
import dev.mbo.graphqlsample.repo.UserRepository
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import java.util.UUID

@Suppress("unused")
@Component
class UserQueryResolver @Autowired constructor(
    private val userRepository: UserRepository,
) : GraphQLQueryResolver {

    @LoggedMethod
    fun allUsers(page: Int, size: Int): List<User> {
        return userRepository.findAll(PageRequest.of(page, size)).content
    }

    @LoggedMethod
    fun me(): User {
        // TODO replace this method
        val user = User()
        user.id = UUID.randomUUID()
        user.name = "name-${user.id.toString().take(8)}"
        return user
    }
}
