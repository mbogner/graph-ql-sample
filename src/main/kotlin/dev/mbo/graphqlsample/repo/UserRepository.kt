package dev.mbo.graphqlsample.repo

import dev.mbo.graphqlsample.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<User, UUID>
