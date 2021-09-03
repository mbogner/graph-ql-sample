@file:Suppress("unused")

package dev.mbo.graphqlsample.graphql.mutation

import dev.mbo.graphqlsample.model.User
import dev.mbo.graphqlsample.repo.UserRepository
import graphql.kickstart.tools.GraphQLMutationResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
class UserMutationResolver @Autowired constructor(
    private val userRepository: UserRepository,
) : GraphQLMutationResolver {

    @Transactional
    fun createUser(name: String): User {
        return userRepository.save(User.create(name))
    }
}
