package dev.mbo.graphqlsample.graphql.config

import org.dataloader.DataLoaderRegistry
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DataLoaderRegistryFactory @Autowired constructor(
    private val dataLoaders: Set<DataLoaderFactory<*, *>>
) {

    fun create(): DataLoaderRegistry {
        val registry = DataLoaderRegistry()
        dataLoaders.forEach {
            registry.register(it.getName(), it.create())
        }
        return registry
    }

}
