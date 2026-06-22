<img width="178" height="398" alt="task" src="https://github.com/user-attachments/assets/e680853f-0cbd-473b-8b79-2174c6748a7f" /># TaskFlow — Personal Task Manager

> A modern Android task management application built with Kotlin and Jetpack Compose.
> Developed as an internship project at **Largify Solutions**.

---

## 🚀 Features

- ✅ Create, edit and delete tasks
- ✅ Mark tasks complete with strikethrough style
- ✅ Swipe to delete with 5 second undo snackbar
- ✅ High / Medium / Low priority with color coding
- ✅ Due date picker (Material 3)
- ✅ Overdue task indicator
- ✅ Real-time search by title and description
- ✅ Filter tasks by All / Active / Completed
- ✅ Empty state UI when no tasks exist
- ✅ Task detail screen

---

## 🛠 Tech Stack

- **Language** — Kotlin 1.9+
- **UI** — Jetpack Compose + Material 3
- **Architecture** — MVVM + Repository Pattern
- **Database** — Room (SQLite)
- **Async** — Kotlin Coroutines + Flow
- **State Management** — StateFlow
- **Navigation** — Navigation Compose
- **Version Control** — Git + GitHub

---

## 🏛 Architecture

The app follows a clean MVVM architecture with strict separation of concerns:

```
┌─────────────────────────┐
│       UI Layer          │  Jetpack Compose (Composables)
│                         │  Observes StateFlow from ViewModel
└──────────┬──────────────┘
           │
┌──────────▼──────────────┐
│    ViewModel Layer      │  Exposes StateFlow<UiState>
│                         │  Handles all business logic
└──────────┬──────────────┘
           │
┌──────────▼──────────────┐
│    Repository Layer     │  Single source of truth
│                         │  Abstracts all data operations
└──────────┬──────────────┘
           │
┌──────────▼──────────────┐
│      Data Layer         │  Room Database
│                         │  DAO + Entity classes
└─────────────────────────┘
```

---

## 🗃 Database Schema

Each task is persisted locally using Room with the following fields:

- 🔑 **id** — Auto-generated primary key (Int)
- 📝 **title** — Required, maximum 80 characters (String)
- 📄 **description** — Optional, maximum 500 characters (String)
- 🎯 **priority** — 0 = Low · 1 = Medium · 2 = High (Int)
- 📅 **dueDate** — Stored as epoch milliseconds, optional (Long)
- ☑️ **isCompleted** — Task completion status, defaults to false (Boolean)
- 🏷 **category** — Optional label: Work, Personal, Study (String)
- 🕐 **createdAt** — Auto-set creation timestamp (Long)

---

## ⚙️ Getting Started

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or later
- Android device or emulator running API 26+
- Kotlin 1.9+

### Installation

```bash
# Clone the repository
git clone https://github.com/themuhammadahmed-rgb/TaskFlow.git

# Open in Android Studio
# Let Gradle sync complete
# Run on device or emulator
```

---

## 📦 Deliverables

- ✅ Source code on GitHub with meaningful commit history
- ✅ Debug APK (API 26+)
- ✅ Room database schema screenshot
- ✅ MVVM architecture diagram
- ✅ Demo video (2–5 minutes covering all Must Have features)
- ✅ Self-review checklist

---

## 👨‍💻 Project Info

- **Project** — TaskFlow — Personal Task Manager
- **Intern** — Muhammad Ahmed
- **Company** — Largify Solutions
- **Stack** — Kotlin · Jetpack Compose · Room · MVVM
- **Deadline** — 22nd June 2026

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
