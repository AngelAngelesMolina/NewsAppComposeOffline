# NewAppCompose

NewAppCompose is a modern Android application built with Jetpack Compose that allows users to browse and read news articles. The app follows Clean Architecture principles and uses a robust tech stack for performance and maintainability.

## 🚀 Features

- **Browse Latest News**: Stay updated with the latest news articles from various sources.
- **Article Details**: Read full articles with a clean and focused UI.
- **Offline Support**: Cached articles using Room database for offline reading.
- **Modern UI**: Fully built with Jetpack Compose and Material 3 design system.
- **Dark Mode Support**: Seamless transition between light and dark themes.

## 📸 Screenshots

| News Feed | Article Detail |
| --- | --- |
| ![News Feed](https://github.com/user-attachments/assets/14099b57-5665-4360-b137-0afcaae767fa) | ![Article Detail](https://github.com/user-attachments/assets/de5fc311-365b-46da-88df-1325345f9dd9) |

## 🛠 Tech Stack

- **UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose) (Material 3)
- **Dependency Injection**: [Koin](https://insert-koin.io/)
- **Networking**: [Ktor](https://ktor.io/)
- **Local Database**: [Room](https://developer.android.com/training/data-storage/room)
- **Image Loading**: [Coil](https://coil-kt.github.io/coil/)
- **Concurrency**: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html)
- **Navigation**: [Navigation Compose](https://developer.android.com/jetpack/compose/navigation)
- **Serialization**: [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)
- **Architecture**: MVVM + Clean Architecture

## 📂 Project Structure

The project is organized into features and a core module to maintain separation of concerns:

```text
com.jaamcoding.newappcompose
├── core/                # Shared logic and infrastructure
│   ├── data/            # Local (Room) and Remote (Ktor) data implementations
│   ├── domain/          # Shared domain models and abstractions
│   ├── di/              # Core Dependency Injection modules
│   └── presentation/    # Shared UI components and Theme
├── news/                # News listing feature
│   ├── di/              # Feature-specific DI
│   └── presentation/    # News list ViewModels, States, and Composables
├── article/             # Article detail feature
│   ├── di/              # Feature-specific DI
│   └── presentation/    # Article detail ViewModels and Composables
└── App.kt               # Application class with Koin initialization
```

## ⚙️ Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/NewAppCompose.git
   ```
2. Open the project in **Android Studio Ladybug** or newer.
3. Add your API keys in `secrets.properties` (if required).
4. Build and run the app on an emulator or physical device.

## 📄 License

This project is licensed under the MIT License.
