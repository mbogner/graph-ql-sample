package dev.mbo.graphqlsample.model.base

import java.io.Serializable

interface Identifiable<T : Serializable> {

    fun getIdentifier(): T?

}
