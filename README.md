# Tic Tac Toe – Android App

A simple **Tic-Tac-Toe** game built to demonstrate **Clean Architecture**, **TDD**, and **modern Android best practices** using **Kotlin**, **Jetpack Compose**, **Coroutines**, **Flow**, **MVVM**, and **Hilt**.

## Purpose

This application is designed to:

- Demonstrate **Clean Architecture** boundaries
- Apply **TDD** correctly
- Handle **compose** and **one-time UI effects**  (Snackbar) properly
- Keep domain logic **100% platform-independent**

## ⚙️ Build, Environment, Tools Details

IDE - Android Studio Otter 3

### Android SDK

| Config | Value |
|------|------|
| **Min SDK** | 30 |
| **Target SDK** | 36 |
| **Compile SDK** | 36 |

---

### Gradle

| Tool | Version |
|----|--------|
| **Gradle Wrapper** | 9.1.0 |
| **Android Gradle Plugin (AGP)** | 8.13.2 |
| **Kotlin** | 2.2.0 |

> Versions align with current stable Android Studio releases.
---
## 📁 Project Structure
com.example.tictactoe_tdd
│
├── domain
│ ├── model // GameState, Cell, Player, Result
│ ├── rules // GameRules (pure business logic)
│ └── usecase // MakeMoveUseCase
│
├── data
│ └── repository // GameRepository (state holder)
│
├── presentation
│ ├── ViewModel // Reducer + Effects
│ ├── UI // Compose screen
│ └── Effects // Snackbar events
│
└── di // Hilt modules
