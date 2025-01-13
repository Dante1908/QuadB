QuadB Android App
QuadB is an Android application built using Kotlin and Jetpack Compose that allows users to search for TV shows using the TVMaze API. It dynamically fetches TV show data based on user input and displays the results in a user-friendly layout.

Features
Dynamic Search: Search for TV shows by name using real-time API requests.
Clean UI: Built with Jetpack Compose for modern and responsive UI.
Clickable Cards: Each search result is displayed as a card with information and an image, and clicking a card redirects the user to the show's official website.
Coil Integration: Displays images dynamically using the Coil library.
Error Handling: Proper error messages for API failures and invalid search queries.
Safe API Key Management: Ensures that sensitive API keys are not exposed in the source code.
Demo

Include a gif or screenshots of your app in action.

Tech Stack
Language: Kotlin
UI Framework: Jetpack Compose
HTTP Client: Retrofit
Image Loading: Coil
Dependency Injection: Hilt (optional)
API: TVMaze API
Setup Instructions
Prerequisites
Android Studio Bumblebee or higher.
Minimum SDK: 21 (Android 5.0 Lollipop)
Steps to Run the Project
Clone the repository:

bash
Copy code
git clone https://github.com/your-username/QuadB-App.git
cd QuadB-App
Open the project in Android Studio.

Add your API key:

Create a file called local.properties in the root directory of the project (if it doesn't exist).
Add the following line to store your API key:
properties
Copy code
apiKey=your-api-key-here
Sync the project:

In Android Studio, go to File > Sync Project with Gradle Files.
Run the app:

Select a device or emulator and click the Run button.
Usage
Search for TV Shows:

Use the search bar to type a query.
The app will fetch results from the TVMaze API and display them as clickable cards.
View Show Details:

Click on a show card to open its official website in your browser.
Folder Structure
graphql
Copy code
QuadB-App/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/quadb/
│   │   │   │   ├── MainActivity.kt          # Entry point of the app
│   │   │   │   ├── screens/                # UI components
│   │   │   │   ├── Data/                   # API service and models
│   │   │   │   ├── ViewModel/              # ViewModel and business logic
│   │   │   ├── res/                        # UI resources (layouts, images, etc.)
│   │   ├── AndroidManifest.xml             # App permissions and configuration
├── build.gradle                            # Project-level Gradle configuration
├── README.md                               # Documentation
API Reference
This project uses the TVMaze API for fetching TV show data.

Base URL: https://api.tvmaze.com/
Search Endpoint: /search/shows?q={query}
Example query:

sql
Copy code
https://api.tvmaze.com/search/shows?q=all
Refer to the TVMaze API documentation for more details.

Libraries Used
Jetpack Compose: For building UI components.
Retrofit: For making API requests.
Coil: For loading and displaying images.
Kotlin Coroutines: For managing asynchronous tasks.
Contributions
Contributions are welcome! Follow these steps to contribute:

Fork the repository.
Create a new branch:
bash
Copy code
git checkout -b feature-name
Commit your changes:
bash
Copy code
git commit -m "Add your message here"
Push to the branch:
bash
Copy code
git push origin feature-name
Create a pull request.
License
This project is licensed under the MIT License. See the LICENSE file for details.

Contact
For any inquiries or feedback, feel free to reach out:

Name: Aman Goswami
Email: workgoswami@gmail.com
GitHub: Dante1908
