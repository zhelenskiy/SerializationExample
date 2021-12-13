import kotlinx.serialization.Serializable

@Serializable
sealed class SealedClass

@Serializable
data class DerivedClass(val field: String): SealedClass()

@Serializable
object DerivedObject : SealedClass()
