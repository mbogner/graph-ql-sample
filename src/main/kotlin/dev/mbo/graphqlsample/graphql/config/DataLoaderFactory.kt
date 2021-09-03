package dev.mbo.graphqlsample.graphql.config

import org.dataloader.DataLoader

interface DataLoaderFactory<K, V> {

    fun create(): DataLoader<K, V>
    fun getName(): String
}
