import kotlin.test.*
import nimby.mod.createMod
import nimby.IntegerOption
import nimby.maximumTrainLengthMeters
import nimby.minimumTrainLengthMeters

class ModTests {
    @Test fun declaresThePlayerLengthPreferenceWith850MetersByDefault() {
        val mod = createMod()
        val option = assertIs<IntegerOption>(mod.options.single())
        assertEquals("maxLengthMeters", option.id)
        assertEquals(850, option.defaultValue)
        assertEquals(minimumTrainLengthMeters, option.minimum)
        assertEquals(maximumTrainLengthMeters, option.maximum)
        val limit = assertNotNull(mod.trainEditor).maximumLength
        assertEquals(option.id, limit.optionId)
        assertEquals(850, limit.maximumLengthMeters)
    }
    @Test fun doesNotDeclareAClockWindowOrShortcut() {
        val mod = createMod()
        assertTrue(mod.windows.isEmpty())
        assertTrue(mod.services.isEmpty())
    }
    @Test fun refusalMessagesUseTheModTranslationCatalogue() {
        val limit = assertNotNull(createMod().trainEditor).maximumLength
        assertEquals("\u001eNRF:[\"composition.lengthExceeded\",{}]", limit.exceeded)
        assertEquals("\u001eNRF:[\"composition.lengthUnavailable\",{}]", limit.lengthUnavailable)
        assertEquals("\u001eNRF:[\"composition.verificationUnavailable\",{}]", limit.verificationUnavailable)
    }
}
