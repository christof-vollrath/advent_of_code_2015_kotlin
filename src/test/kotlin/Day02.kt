import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class Day02Part1 : BehaviorSpec({

    Given("parsing box dimensions") {
        When("parsing an input line") {
            Then("it should construct a Box with dimensions") {
                parseBox("2x3x4") shouldBe Box(2, 3, 4)
                parseBox("1x1x10") shouldBe Box(1, 1, 10)
            }
        }
    }

    Given("examples for required wrapping paper") {
        data class BoxAndExpectedPaper(val box: Box, val expectedPaper: Int)
        context("input data") {
            withData(
                BoxAndExpectedPaper(Box(2, 3, 4), 58),
                BoxAndExpectedPaper(Box(1, 1, 10), 43)
            ) { (box, expectedPaper) ->
                box.requiredPaper() shouldBe expectedPaper
            }
        }

        When("calculating total for multiple presents") {
            val sampleInput = """
                2x3x4
                1x1x10
            """.trimIndent()
            val total = totalWrappingPaper(sampleInput)
            Then("it should sum up wrapping paper for all presents") {
                total shouldBe 58 + 43
            }
        }
    }

    Given("exercise input") {
        val inputString = readResource("inputDay02.txt")!!
        When("calculating total wrapping paper") {
            val total = totalWrappingPaper(inputString)
            Then("it should calculate the total square feet of wrapping paper") {
                total shouldBe 1588178
            }
        }
    }
})

data class Box(val l: Int, val w: Int, val h: Int) {
    /** Calculates the paper needed for this specific box (Part 1). */
    fun requiredPaper(): Int {
        val sides = listOf(l * w, w * h, h * l).sorted()
        val surfaceArea = sides.sum() * 2
        val smallestSide = sides.first()
        return surfaceArea + smallestSide
    }

    /** Calculates the ribbon needed for this specific box (Part 2). */
    fun requiredRibbon(): Int {
        val dimensions = listOf(l, w, h).sorted()
        val smallestPerimeter = 2 * (dimensions[0] + dimensions[1])
        val volume = l * w * h
        return smallestPerimeter + volume
    }
}

/** Parses a string like "2x3x4" into a Box object. */
fun parseBox(line: String): Box {
    val (l, w, h) = line.split('x').map { it.toInt() }
    return Box(l, w, h)
}

fun totalWrappingPaper(input: String): Int =
    input.lineSequence()
        .filter { it.isNotBlank() }
        .map(::parseBox)
        .sumOf { it.requiredPaper() }

fun totalRequiredRibbon(input: String): Int =
    input.lineSequence()
        .filter { it.isNotBlank() }
        .map(::parseBox)
        .sumOf { it.requiredRibbon() }

class Day02Part2 : BehaviorSpec({

    Given("examples for required ribbon") {
        data class BoxAndExpectedRibbon(val box: Box, val expectedRibbon: Int)
        context("input data") {
            withData(
                BoxAndExpectedRibbon(Box(2, 3, 4), 34),
                BoxAndExpectedRibbon(Box(1, 1, 10), 14)
            ) { (box, expectedRibbon) ->
                box.requiredRibbon() shouldBe expectedRibbon
            }
        }

        When("calculating total for multiple presents") {
            val sampleInput = """
                2x3x4
                1x1x10
            """.trimIndent()
            val total = totalRequiredRibbon(sampleInput)
            Then("it should sum up ribbon for all presents") {
                total shouldBe 34 + 14
            }
        }
    }

    Given("exercise input") {
        val inputString = readResource("inputDay02.txt")!!
        When("calculating total ribbon") {
            val total = totalRequiredRibbon(inputString)
            Then("it should calculate the total feet of ribbon needed") {
                total shouldBe 3783758
            }
        }
    }
})
