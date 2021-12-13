import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class SerializationTest {
    private val plainObject = Json.encodeToString(DerivedObject)
    private val plainClass = Json.encodeToString(DerivedClass("123"))
    private val genericObject = Json.encodeToString<SealedClass>(DerivedObject)
    private val genericClass = Json.encodeToString<SealedClass>(DerivedClass("123"))

    @Test
    fun testExpectedLiteral() {
        assertEquals("{}", plainObject)
        assertEquals("{\"field\":\"123\"}", plainClass)
        assertEquals("{\"type\":\"DerivedObject\"}", genericObject)
        assertEquals("{\"type\":\"DerivedClass\",\"field\":\"123\"}", genericClass)
    }

    @Test
    fun difference() {
        assertNotEquals(plainClass, genericClass)
        assertNotEquals(plainObject, genericObject)
    }

    @Test
    fun deserializable() {
        assertEquals(DerivedObject, Json.decodeFromString<DerivedObject>(plainObject))
        assertEquals(DerivedObject, Json.decodeFromString<SealedClass>(genericObject))
        assertEquals(DerivedClass("123"), Json.decodeFromString<DerivedClass>(plainClass))
        assertEquals(DerivedClass("123"), Json.decodeFromString<SealedClass>(genericClass))
    }
}