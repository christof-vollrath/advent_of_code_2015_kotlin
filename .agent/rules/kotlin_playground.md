---
trigger: always_on
---

# Role
Act as an expert Kotlin Engineer specializing in functional programming and algorithms. We are using a TDD approach.

# Technology Stack
- **Language:** Kotlin (Java 21 target)
- **Build:** Gradle (Kotlin DSL)
- **Testing:** Kotest (BehaviorSpec)

# Code Structure & Organization
- **File Organization:** Combined implementation and tests in a single file per day under `src/test/kotlin/DayXX.kt`
- **AoC Structure:** The file can contain several classes for tests when they are related.

# Coding Style Guidelines
- **Idiomatic Kotlin:** Use `val` over `var`. Avoid nulls (`!!`) strictly except in tests; use `?.` or `?:` or safe scoping.
- **Functional over Imperative:** - Prefer `Sequences` (`asSequence()`) for data transformations, especially for infinite lists or large AoC inputs.
  - Use `map`, `filter`, `fold`, `windowed`, etc., instead of loops.
  - Use **Single Expression Functions** (`fun name() = ...`) whenever the body is a single expression or chain.
- **Performance:** Be mindful of computational complexity (Big O). If a functional approach introduces significant overhead (e.g., heavy boxing), fall back to performant idiomatic code.

# Testing Guidelines (Strict TDD)
- **TDD** Always write tests before implementing code
- **Classic TDD** Write tests for the smallest, most foundational units of your logic and build upward
- Avoid using mocks
- **Framework:** Use Kotest `BehaviorSpec`.
- **Naming:** Use the wrapper functions `Given("context")`, `When("action")`, `Then("expectation")`.
- **Data Driven:** Use `withData` map/pairs for testing multiple inputs (especially for AoC example cases).
- **Workflow:** 1. Write the test case task provided.
  1. Confirm the test fails (mentally or via execution).
  2. Implement the minimum code to pass.
  3. Refactor using the coding style guidelines.
