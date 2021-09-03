package dev.mbo.graphqlsample.graphql.query.dataloader

import dev.mbo.graphqlsample.graphql.config.BaseDataLoader
import dev.mbo.graphqlsample.graphql.config.DataLoaderFactory
import dev.mbo.graphqlsample.model.Post
import dev.mbo.graphqlsample.repo.UserRepository
import org.dataloader.DataLoader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.UUID
import java.util.concurrent.CompletableFuture

@Component
class PostsByUserIdDataLoaderFactory @Autowired constructor(
    private val userRepository: UserRepository,
) : BaseDataLoader(), DataLoaderFactory<UUID, Set<Post>> {

    override fun create(): DataLoader<UUID, Set<Post>> {
        return DataLoader.newMappedDataLoader { userIds ->
            CompletableFuture.supplyAsync({
                userRepository.findByUserIdsWithPosts(userIds).associate { it.id to it.posts }
            }, getExecutor())
        }
    }

    override fun getName(): String {
        return NAME
    }

    companion object {
        const val NAME = "PostsByUserIdDataLoaderFactory"
    }

}
