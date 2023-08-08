# Anagram Game

Anagram Game is a simple command-line game where players can solve anagrams by rearranging letters to form words or 
check if two words are anagrams.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Dependencies](#dependencies)
- [Author](#author)

## Features

- Solve anagrams by rearranging letters.
- Choose from different difficulty levels.
- Keep track of high scores for each difficulty level.
- Check if two words are anagrams.

## Getting Started

1. Clone the repository: https://github.com/katarinaavucic/AnagramGame.git
2. Compile the program: 'javac AnagramGame/src/main/java/Main.java'
3. Run the program: 'java AnagramGame.src.main.java.Main'

## Usage

- Follow the on-screen instructions to play the Anagram game.
- Choose a difficulty level and solve anagrams to earn scores.
- View high scores for each difficulty level.
- Input two words to check if they are anagrams.
- Type "quit" to exit the game.

## Dependencies

The Anagram Game program requires Java to be installed on your system.

## Project Specifications

This program was created using JDK11 and JUnit 4 testing.

### Use Cases

1. Playing the anagram game and checking anagrams. This use case is responsible for the code related to gameplay.
2. Calculating and saving scores to a file. This use case is responsible for the code related to scoring.

### User Stories

1. A user wants to play a game. They open the anagram game and select option 1 to play the anagram game. They are 
allowed to choose different levels and are scored based on every round they play. They can see the high score for 
each difficulty and can compete with friends across sessions.
2. A user is curious whether two words are anagrams. They open the anagram game and select option 2 to check anagrams
and the program tells them if the two words they input are anagrams or not.

### Design Patterns

1. **Strategy Pattern:** The strategy design pattern is used to swap out different sorting algorithms according to 
difficulty level. Multiple Strategy classes that differ only in their algorithm implement the ScoringStrategy interface
and thus can be swapped.
2. **Command Pattern:** The command design pattern, is used to decrease the high complexity of the main menu in the 
GameController class and create a layer between the views and interface adapters. Multiple Command classes are created 
to send commands from StartGameMenu to GameController or the System, with no concern for what the details of these 
commands are.

### Code Smells
- Long Method in AnagramUseCase: the playAnagramGame() method is quite lengthy, however, most of the code is printing
lines to the user and calls to protected methods. Thus, I have made sure to well comment what everything does for easy 
readability.
- Primitive Obsession with difficulty levels: using Strings for the difficulty levels ("easy", "medium", "hard") could 
cause errors. If this were a bigger project, I would have created a class for them, but with such a small program it is 
not as necessary. 

### SOLID Principles

1. **Single Responsibility Principle:** All classes are acted upon by a single actor and adhere to this principle.
2. **Open-Closed Principle:** Since the classes with main functionality implement InputBoundary interfaces, the user is 
able to modify any use case as they see fit. Both the AnagramGameUseCase and HighScoreManagerUseCase are open for 
extension but closed for modification.
3. **Liskov Substitution Principle:** All the InputBoundary objects adhere to this principle. In order to adhere to the
DIP, the interactors are cast to the InputBoundary. This demonstrates the LSP, as any class that implements these 
interfaces can be used. Another example is the GameCommand interface that AnswerCommand and QuitCommand implement, and
the ScoringStrategy that EasyScoringStrategy, MediumScoringStrategy, and HardScoringStrategy implement.
4. **Interface Segregation Principle:** All interfaces only contain methods necessary for the execution of the use case,
and all non-public methods are implemented only in the use case.
5. **Dependency Inversion Principle:** All classes have a boundary between every layer in Clean Architecture, to create
the dependency inversion.

### Clean Architecture Violations

There are no clean architecture violations in this code.

## Author

This project was created by Katarina Vucic utilizing ChatGPT-3.5 for [CSC207] Software Design at the University of 
Toronto.
