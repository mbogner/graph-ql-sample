package dev.mbo.graphqlsample.model

import dev.mbo.graphqlsample.model.base.AbstractEntity
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "users")
class User : AbstractEntity<UUID>() {

    @Id
    var id: UUID? = null
    var name: String? = null

    @OneToMany(mappedBy = "user")
    var posts: MutableSet<Post> = mutableSetOf()

    companion object {

        fun create(name: String): User {
            val user = User()
            user.id = UUID.randomUUID()
            user.name = name
            return user
        }
    }

    override fun getIdentifier(): UUID? {
        return id
    }

}
