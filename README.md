# Navi_App
## Overview
The Navi App is an Android-based application designed to streamline photogrammetry and navigation workflows. It dynamically calculates ground coverage, manages image overlap, and provides real-time navigation feedback for use cases such as aerial mapping, surveying, and mission planning. The application uses a Model-View-Controller (MVC) architecture for modularity and maintainability.

## Prerequisites
- **Android Studio** 
- **Java** 
- Android **10+** (for secure data saving)
- Basic understanding of **Android Development** and **UTM Coordinates**

## Installation Instructions
1. **Set up Android Studio**: Download and install [Android Studio](https://developer.android.com/studio).
2. **Clone/Download the Project**: Clone or download the repository to your local machine.
3. **Open the Project**: Open Android Studio and select **Open an Existing Project**.
4. **Sync Gradle**: Wait for Gradle to sync the project.
5. **Run the Project**: Build the project and run it on an emulator or physical device.

## Code Overview
The app is structured into four primary layers:
- **Input Layer**: Captures all user inputs, including camera configuration and UTM coordinates.
- **Processing Layer**: Performs calculations for the field of view (FoV) and ground coverage.
- **Visualization Layer**: Displays the dynamic ground coverage on a custom canvas, updating in real-time.
- **Storage Layer**: Handles saving and loading user data, ensuring compatibility with both new and old Android versions.

## Code Explanation

### MainActivity.java
Handles user inputs and launches the canvas activity for visualizing the ground coverage and navigation paths.

### CanvasActivity.java
Manages the visualization of ground coverage and updates the canvas based on the user's inputs (camera height, angle, and speed).

### ImageBuilder.java
Contains methods for calculating image width and height based on camera parameters (focal length, sensor size, camera height, angle).

### UserInputData.java
Stores the user input data, including camera settings and coordinates, and provides methods to save the data to a file.

### GroundCoverageView.java
A custom view responsible for rendering the ground coverage area dynamically on the canvas as a user interacts with the app.

### Overlay.java
Represents the overlays (rectangles or polygons) drawn on the canvas to visualize the ground coverage area.

## Example Usage
1. **Enter Camera Settings**: Input focal length, sensor height, sensor width, camera height, and camera angle.
2. **Input Coordinates**: Provide Easting and Northing coordinates for navigation.
3. **Adjust Camera Settings**: Use buttons to modify camera height, angle, and speed.
4. **View Ground Coverage**: Observe the real-time visual updates of the ground coverage area and navigation paths.

## Output
The app provides real-time visual feedback, displaying the area covered by the camera through dynamic overlays on a custom canvas. Additionally, the current Easting and Northing coordinates are displayed to assist with navigation.

