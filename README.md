# 📊 TextAnalyzr

**TextAnalyzr** is a simple Java application for analyzing text. It provides several useful statistics and insights, including character and word counts, finding the most common character, and calculating word frequencies. Perfect for quick text insights! 📖✨

## 🚀 Features

- **Character Count**: Get the total number of characters in your input.
- **Word Count**: Find out the total number of words.
- **Most Common Character**: Discover which character appears most frequently.
- **Character & Word Frequency**: Check how often a specific character or word appears.
- **Unique Word Count**: Count the number of unique words used.

## 🛠️ Getting Started

### Prerequisites
- **Java 11+**: Ensure you have Java installed. You can download it from [here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
- **Gradle**: Project setup with Gradle for easy build and test management. You can install Gradle from [here](https://gradle.org/install/).

### Running the Application
1. Clone this repository:
   ```bash
   git clone https://github.com/AR10Dev/TextAnalyzr.git
   cd TextAnalyzr
   ```
2. Build and run the application with Gradle:
   ```bash
   ./gradlew run
   ```
3. Follow the prompts to analyze your text input. You will be asked to enter the text you want to analyze, and the application will display the results based on the features mentioned above.

## 🧪 Running Tests

Run tests to ensure everything is working smoothly:
```bash
./gradlew test
```
The tests cover various aspects of the application, including character count, word count, most common character, and frequency calculations. Check the test results to ensure all functionalities are working as expected.

## 🤖 GitHub Actions

This project includes CI workflows using GitHub Actions:
- **Build**: Compiles and tests the project on every push or pull request.
- **Formatting Check**: Uses the Spotless plugin to ensure consistent code formatting.
- **Release**: Automatically creates a GitHub release and uploads the JAR file when a new tag is pushed.

## 🎨 Contributing

Feel free to submit issues or pull requests to contribute! All improvements are welcome.

### Guidelines
- Follow the coding standards used in the project.
- Ensure your code passes all tests before submitting a pull request.
- Provide a clear description of the changes you are making.

## 📄 License

This project is licensed under the GPL-3.0 license.