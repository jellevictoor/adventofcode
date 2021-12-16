import org.junit.Test
import kotlin.test.assertEquals

class Day16TestCase {
    @Test fun decodePacket(){
        val input = Package("D2FE28")
        assertEquals("110100101111111000101000",input.binaryRepresentation())
    }
}