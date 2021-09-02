package dev.mbo.graphqlsample.model.base

import org.springframework.data.util.ProxyUtils
import java.io.Serializable
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractEntity<I : Serializable> : Identifiable<I> {

    /**
     * id only check for jpa entity
     */
    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (this === other) return true
        if (javaClass != ProxyUtils.getUserClass(other)) return false
        if (other !is Identifiable<*>) return false
        if (null == getIdentifier() || getIdentifier() != other.getIdentifier()) return false
        return true
    }

    /**
     * @see <a href="https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier">external link</a>
     * for further infos why this is a static value.
     */
    override fun hashCode(): Int {
        return javaClass.name.hashCode()
    }

    override fun toString(): String {
        return "${javaClass.simpleName}[id=${getIdentifier()}]"
    }
}
