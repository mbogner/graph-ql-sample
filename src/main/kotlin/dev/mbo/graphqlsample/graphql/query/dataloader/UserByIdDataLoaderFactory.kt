package dev.mbo.graphqlsample.graphql.query.dataloader

import dev.mbo.graphqlsample.graphql.config.BaseDataLoader
import dev.mbo.graphqlsample.graphql.config.DataLoaderFactory
import dev.mbo.graphqlsample.model.User
import dev.mbo.graphqlsample.repo.UserRepository
import org.dataloader.DataLoader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.UUID
import java.util.concurrent.CompletableFuture

@Component
class UserByIdDataLoaderFactory @Autowired constructor(
    private val userRepository: UserRepository,
) : BaseDataLoader(), DataLoaderFactory<UUID, User> {

    override fun create(): DataLoader<UUID, User> {
        return DataLoader.newMappedDataLoader { userIds ->
            CompletableFuture.supplyAsync(
                { userRepository.findByIdIn(userIds).associateBy { it.id } },
                getExecutor()
            )
        }
    }

    override fun getName(): String {
        return NAME
    }

    companion object {

        const val NAME = "UserByIdDataLoaderFactory"
    }

}
