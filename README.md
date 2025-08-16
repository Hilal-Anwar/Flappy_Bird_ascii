# Flappy Bird ASCII 🐦

A terminal-based ASCII art version of the classic Flappy Bird game, implemented in Java with animated characters and real-time keyboard input.

## 🎮 Game Preview

https://user-images.githubusercontent.com/50636048/211141144-d26ffacb-bfd6-442b-a4cb-3859756d15c0.mp4

https://user-images.githubusercontent.com/50636048/166179101-19d83ebf-f1fd-4e1d-8fbf-717d7250ca3d.mp4

## ✨ Features

- **ASCII Art Graphics**: Beautiful terminal-based graphics with animated bird sprites
- **Real-time Input**: Responsive keyboard controls using JLine library
- **Dynamic Obstacles**: Procedurally generated obstacles that move across the screen
- **Scoring System**: Score tracking with level progression (every 10 points)
- **Game States**: Loading screen, gameplay, and game over with restart functionality
- **Cross-platform**: Runs on Windows, macOS, and Linux terminals
- **Customizable**: Configurable game window dimensions

## 🎯 How to Play

1. **Controls**:
   - `UP Arrow` or `Space`: Make the bird fly up
   - `ESC`: Exit the game
   - `Space` (on game over): Restart the game

2. **Objective**:
   - Navigate the bird through obstacles without hitting them
   - Avoid hitting the ground or ceiling
   - Score points by successfully passing through obstacles
   - Try to achieve the highest score possible!

3. **Scoring**:
   - +1 point for each obstacle successfully passed
   - Level increases every 10 points
   - Higher levels may increase game difficulty

## 🚀 Getting Started

### Prerequisites

- **Java 21** or higher
- **Maven 3.6+** for building the project
- A terminal that supports ANSI escape sequences (most modern terminals)

### Installation

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd "Flappy Bird"
   ```

2. **Build the project**:
   ```bash
   mvn clean compile assembly:single
   ```

3. **Run the game**:
   ```bash
   java -jar target/FlappyBird-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```

   Or using Maven:
   ```bash
   mvn exec:java -Dexec.mainClass="org.flappy_bird.game.Main"
   ```

### Quick Start

1. When prompted, enter the desired game window dimensions:
   - **Width**: Recommended 80-120 characters
   - **Height**: Recommended 30-50 characters

2. Wait for the loading animation to complete

3. Use the UP arrow key to control the bird and avoid obstacles!

## 🛠️ Technical Details

### Architecture

The game is built with a modular architecture:

- **Main.java**: Entry point and game initialization
- **Game.java**: Core game logic, rendering, and game state management
- **Display.java**: Terminal interface using JLine library
- **KeyBoardInput.java**: Real-time keyboard input handling
- **Obstacles.java**: Obstacle generation and management
- **AnimateAsciiImage.java**: Bird animation system
- **BirdParts.java**: Individual bird sprite components
- **Block.java**: Obstacle building blocks

### Dependencies

- **JLine 3.23.0**: Terminal control and keyboard input
- **JNA 5.12.1**: Native library access for system integration
- **Jansi 2.4.0**: ANSI escape sequence support for colors and formatting

### Key Features Implementation

- **Animation**: Custom ASCII art animation system with frame switching
- **Collision Detection**: Pixel-perfect collision detection between bird and obstacles
- **Game Physics**: Gravity simulation with upward thrust mechanics
- **Terminal Graphics**: ANSI escape codes for colors and screen clearing
- **State Management**: Game state handling for menu, playing, and game over states

## 🎨 Customization

### Modifying Bird Sprites

The bird sprites are defined as ASCII art strings in `Game.java`:

```java
String b1 = """
            __     __------
        ___/o `\ ,~   _~~  .
        ~ -.   ,'   _~-----
            `\     ~~~--_'__
              `~-==-~~~~~---'
        """;
```

### Adjusting Game Difficulty

- **Speed**: Modify the `speed` variable in `Game.java`
- **Gravity**: Adjust the bird's downward movement rate
- **Obstacle Spacing**: Modify obstacle generation in `Obstacles.java`

## 📁 Project Structure

```
Flappy Bird/
├── src/main/java/org/flappy_bird/game/
│   ├── Main.java                 # Application entry point
│   ├── Game.java                 # Core game logic
│   ├── Display.java              # Terminal interface
│   ├── KeyBoardInput.java        # Input handling
│   ├── Obstacles.java            # Obstacle management
│   ├── AnimateAsciiImage.java    # Animation system
│   ├── BirdParts.java            # Bird components
│   ├── Block.java                # Obstacle blocks
│   ├── Point.java                # 2D coordinates
│   ├── Position.java             # Position enumeration
│   └── IsPart.java               # Component checker
├── target/                       # Compiled artifacts
├── pom.xml                       # Maven configuration
├── .gitignore                    # Git ignore rules
└── README.md                     # This file
```

## 🐛 Troubleshooting

### Common Issues

1. **Game doesn't display correctly**:
   - Ensure your terminal supports ANSI escape sequences
   - Try using a different terminal (Windows Terminal, iTerm2, etc.)
   - Increase terminal window size

2. **Input lag or unresponsive controls**:
   - Close other applications that might interfere with keyboard input
   - Try running in a native terminal instead of IDE terminal

3. **Build errors**:
   - Ensure Java 21+ is installed and configured
   - Verify Maven is properly installed
   - Clear Maven cache: `mvn dependency:purge-local-repository`

### Performance Tips

- Use a terminal with good refresh rate support
- Avoid running in resource-constrained environments
- Consider smaller game window dimensions for better performance

## 🤝 Contributing

Contributions are welcome! Here are some areas where you can help:

- **Bug fixes**: Report and fix gameplay issues
- **Features**: Add new game modes, power-ups, or visual effects
- **Performance**: Optimize rendering and input handling
- **Documentation**: Improve code comments and user guides
- **Testing**: Test on different platforms and terminals

### Development Setup

1. Fork the repository
2. Create a feature branch: `git checkout -b feature-name`
3. Make your changes and test thoroughly
4. Commit your changes: `git commit -am 'Add new feature'`
5. Push to the branch: `git push origin feature-name`
6. Submit a pull request

## 📜 License

This project is open source. Feel free to use, modify, and distribute as needed.

## 🙏 Acknowledgments

- Original Flappy Bird game by Dong Nguyen
- ASCII art community for inspiration
- JLine library developers for terminal control capabilities
- Contributors and testers

---

**Enjoy the game! 🎮** 

If you encounter any issues or have suggestions, please feel free to open an issue or contribute to the project.

