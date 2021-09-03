package dev.mbo.graphqlsample.repo

import dev.mbo.graphqlsample.model.Post
import dev.mbo.graphqlsample.model.User
import org.hibernate.hql.spi.id.AbstractIdsBulkIdHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

interface PostRepository : JpaRepository<Post, UUID> {

}
