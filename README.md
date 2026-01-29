# Tic Tac Toe – Android App

A simple **Tic-Tac-Toe** game built to demonstrate **Clean Architecture**, **TDD**, and **modern Android best practices** using **Kotlin**, **Jetpack Compose**, **Coroutines**, **Flow**, **MVVM**, and **Hilt**.

## Purpose

This application is designed to:

- Demonstrate **Clean Architecture** with clear layer boundaries
- Apply **TDD** correctly
- Use a **single source of truth** for UI state
- Handle **one-time UI effects** (Snackbar) safely
- Follow **Compose best practices**
- Keep domain logic **100% platform-independent**

## Architecture Overview

The project follows **Clean Architecture** with unidirectional dependency flow:

```
Presentation [UI, viewmodel(Reducer + Effect)]
↓
Domain [Model, Usecase, Rules]
↓
Data [Repository (State Holder)]
```

---
## 🧪 Testing Strategy (TDD)

This project follows **strict Test-Driven Development**:

> 🔴 Red → 🟢 Green → 🔁 Refactor  
> **Only green commits are allowed**

### Current Test Coverage

- ✅ `GameRules` fully unit tested
- JVM unit tests only (no Android dependency)

### Tested Scenarios

- Row win
- Column win
- Diagonal win
- Draw state
- In-progress game
- Game movement
- Game reset
  
---

## 🚀 How to Install & Run

### Prerequisites

- Android Studio (latest stable)
- JDK 17
- Android SDK installed

---

### Steps

## 🚀 How to Run

```bash
git clone <repository-url>
```

Open in Android Studio → Sync → Run ▶

---

## 🧪 Run Unit Tests

```bash
./gradlew test
```

or

```bash
./gradlew :app:testDebugUnitTest
```



