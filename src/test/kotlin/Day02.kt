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
                requiredWrappingPaper(box) shouldBe expectedPaper
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

data class Box(val l: Int, val w: Int, val h: Int)

fun parseBox(line: String): Box =
    line.split('x')
        .map { it.toInt() }
        .let { (l, w, h) -> Box(l, w, h) }

fun requiredWrappingPaper(box: Box): Int =
    listOf(box.l, box.w, box.h)
        .sorted()
        .let { (a, b, c) -> 2 * a * b + 2 * b * c + 2 * a * c + a * b }

fun totalWrappingPaper(input: String): Int =
    input.lineSequence()
        .filter { it.isNotBlank() }
        .map(::parseBox)
        .map(::requiredWrappingPaper)
        .sum()
