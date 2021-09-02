package dev.mbo.graphqlsample.model

import dev.mbo.graphqlsample.model.base.AbstractEntity
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "posts")
class Post : AbstractEntity<UUID>() {

    @Id
    var id: UUID? = null
    var name: String? = null

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null

    companion object {

        fun create(name: String, user: User): Post {
            val post = Post()
            post.id = UUID.randomUUID()
            post.name = name
            post.user = user
            return post
        }

    }

    override fun getIdentifier(): UUID? {
        return id
    }

}
