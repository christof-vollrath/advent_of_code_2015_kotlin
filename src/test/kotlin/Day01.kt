import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

@io.kotest.common.ExperimentalKotest
class Day01Part1: BehaviorSpec() { init {

    Given("examples") {
        data class FloorDirectionsAndExpectedResult(val directions: String, val  expectedFloor: Int)
        context("input data") {
            withData(
                FloorDirectionsAndExpectedResult("(())", 0),
                FloorDirectionsAndExpectedResult("()()", 0),
                FloorDirectionsAndExpectedResult("(((", 3),
                FloorDirectionsAndExpectedResult("(()(()(", 3),
                FloorDirectionsAndExpectedResult("))(((((", 3),
                FloorDirectionsAndExpectedResult("())", -1),
                FloorDirectionsAndExpectedResult("))(", -1),
                FloorDirectionsAndExpectedResult(")))", -3),
                FloorDirectionsAndExpectedResult(")())())", -3),
            ) { (directions, expectedFloor) ->
                followDirections(directions) shouldBe expectedFloor
            }
        }
    }
    Given("exercise input") {
        val inputString = readResource("inputDay01.txt")!!
        When("following directions") {
            val floor = followDirections(inputString)
            Then("it should be on the right floor") {
                floor shouldBe 138
            }
        }
    }
} }

@io.kotest.common.ExperimentalKotest
class Day01Part2: BehaviorSpec() { init {

    Given("examples") {
        data class FloorDirectionsAndExpectedResult(val directions: String, val  expectedSteps: Int)
        context("input data") {
            withData(
                FloorDirectionsAndExpectedResult(")", 1),
                FloorDirectionsAndExpectedResult("()())", 5),
            ) { (directions, expectedSteps) ->
                stepsToFirstBasement(directions) shouldBe expectedSteps
            }
        }
    }
    Given("exercise input") {
        val inputString = readResource("inputDay01.txt")!!
        When("following directions until basement") {
            val floor = stepsToFirstBasement(inputString)
            Then("it should have found the right steps") {
                floor shouldBe 1771
            }
        }
    }
} }

fun followDirections(directions: String): Int {
    val up = directions.count { it == '(' }
    val down = directions.count { it == ')' }
    return up - down
}

fun stepsToFirstBasement(directions: String): Int {
    var floor = 0
    directions.forEachIndexed { i, c ->
        when(c) {
            '(' -> floor++
            ')' -> floor--
            else -> throw IllegalArgumentException("Unexpected char $c")
        }
        if (floor == -1) return i + 1
    }
    throw IllegalArgumentException("Unable to reach basement")
}