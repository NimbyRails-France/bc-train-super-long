package nimby.mod

import nimby.*

fun createMod(): ToolMod = toolMod(modInfo) {
    metadata(author = "NimbyRails France", name = tr("mod.name"), description = tr("mod.description"))
    // Declare the player's preference. The SDK owns native changes and lifecycle;
    // this mod needs no recurring reads, event callback or separate window.
    val maxLengthMeters = IntegerOption(
        "maxLengthMeters", tr("options.maxLengthMeters"), defaultValue = 850,
        minimum = minimumTrainLengthMeters, maximum = maximumTrainLengthMeters,
        description = tr("options.maxLengthMeters.help"),
    )
    options(maxLengthMeters)
    trainEditor {
        maximumLength(
            meters = maxLengthMeters,
            exceeded = tr("composition.lengthExceeded"),
            lengthUnavailable = tr("composition.lengthUnavailable"),
            verificationUnavailable = tr("composition.verificationUnavailable"),
        )
    }
}
