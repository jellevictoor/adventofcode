import java.util.*

class Lanternfish(var internalCounter: Int) {
    companion object {
        fun breed(): Lanternfish {
            return Lanternfish(8)
        }
    }

    fun live(): Lanternfish {
        return Lanternfish(internalCounter - 1)
    }

    fun giveBirth(): Optional<Lanternfish> {
        if (internalCounter < 0) {
            resetInternalCounter()
            return Optional.of(breed())
        }
        return Optional.empty()
    }

    private fun resetInternalCounter() {
        internalCounter = 6
    }
}

class SchoolSimulator(
    val initalPopulation: List<Lanternfish>,
    val daysToSimulate: Int
) : SolutionExecutor {
    fun calculatePopulation(): Int {
        return simulate(initalPopulation, 1)
    }

    private fun simulate(population: List<Lanternfish>, day: Int): Int {
        var newPopulation = population

        for (i in day..daysToSimulate) {
            newPopulation = newPopulation
                .map { it.live() }
                .map { expand(it) }
                .flatten()
        }
        return newPopulation.size
    }

    private fun expand(it: Lanternfish): List<Lanternfish> {
        val offspring = it.giveBirth()
        if (offspring.isPresent()) {
            return listOf(it, offspring.get())
        } else {
            return listOf(it)
        }
    }

    override fun process(input: List<String>): Int {
        val initialPopulation = input
            .map { it.split(",") }
            .flatten()
            .map { it.toInt() }
            .map { Lanternfish(it) }
        return SchoolSimulator(initialPopulation, 256).calculatePopulation()
    }

}

