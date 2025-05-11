import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.datatest.withData
import io.kotest.matchers.equals.shouldBeEqual

@io.kotest.common.ExperimentalKotest
class Day01Part1: BehaviorSpec() { init {

    Given("example input") {
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
                followDirections(directions) shouldBeEqual expectedFloor
            }
        }
    }
    Given("exercise input") {
        val inputString = readResource("inputDay01.txt")!!
        When("following directions") {
            val floor = followDirections(inputString)
            Then("it should be on the right floor") {
                floor shouldBeEqual 138
            }
        }
    }
} }

fun followDirections(directions: String): Int {
    val up = directions.count { it == '(' }
    val down = directions.count { it == ')' }
    return up - down
}
