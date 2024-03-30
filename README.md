**Project Readme: AIoT Home Automation Project**

---

## Introduction

Welcome to our AIoT (Artificial Intelligence of Things) home automation project! This document serves as a guide for developers and enthusiasts interested in understanding and contributing to our project. Our project aims to create a smart home system that integrates mobile UI development, IoT gateway implementation, server-side management, and AI-powered features for home security and automation.

## Project Components

Our project consists of several key components:

- **Mobile UI**: Developed using Java, the mobile UI provides a user-friendly interface for controlling and monitoring various smart home devices and functionalities.
  
- **IoT Gateway**: Implemented in Python, the IoT gateway serves as the central hub for communication between smart home devices, the mobile UI, and external servers. It manages data transmission, device control, and sensor data processing.
  
- **Server (Adafruit)**: Utilizing the Adafruit platform, the server facilitates cloud-based management and storage of device data, user authentication, and remote access to the smart home system.
  
- **Sensors (YOLO:Home)**: The YOLO:Home sensor suite includes various sensors for monitoring environmental conditions, detecting motion, and measuring other relevant parameters within the home environment.
  
- **AI Model**: The AI model, integrated into the IoT gateway, utilizes computer vision techniques to recognize and identify individuals detected by the home security cameras.

## Key Features

Our AIoT home automation project offers the following key features:

- Remote Monitoring and Control: Users can remotely monitor and control their smart home devices through the mobile UI from anywhere with internet access.
  
- Environmental Monitoring: Sensors collect data on temperature, humidity, air quality, and other environmental parameters, providing insights into the home environment.
  
- Home Security: The AI model analyzes security camera footage to detect and recognize individuals, enhancing home security and safety.
  
- Automation and Intelligence: The system can automate routine tasks based on user preferences and environmental conditions, improving energy efficiency and convenience.

## Getting Started

To get started with the project, follow these steps:

1. **Clone the Repository**: 
   ```
   git clone <repository_url>
   ```

2. **Set Up Mobile UI**: 
   - Navigate to the `mobile_ui` directory.
   - Follow the instructions in the mobile UI readme file to set up the development environment and run the mobile application.

3. **Set Up IoT Gateway**: 
   - Navigate to the `iot_gateway` directory.
   - Follow the instructions in the IoT gateway readme file to set up the gateway software and configure communication with sensors and servers.

4. **Connect Sensors and Server**: 
   - Ensure that the sensors are properly connected and configured to communicate with the IoT gateway.
   - Set up the Adafruit server account and configure the IoT gateway to communicate with the server.

5. **Train AI Model (Optional)**: 
   - If necessary, train or fine-tune the AI model for person recognition using labeled data and machine learning techniques.

6. **Run the System**: 
   - Start the IoT gateway software.
   - Launch the mobile UI application.
   - Monitor and control smart home devices, view sensor data, and access AI-powered features through the mobile UI.

## Contribution Guidelines

We welcome contributions from the developer community to enhance and improve our AIoT home automation project. To contribute, follow the guidelines provided in the respective readme files for each component.

## License

This project is licensed under the [huynguyenn0103@gmail.com License](LICENSE), allowing for free use, modification, and distribution of the code for both commercial and non-commercial purposes.

## Contact Information

For any inquiries or feedback regarding the project, feel free to contact us at [huynguyenn0103@gmail.com](mailto:huynguyenn0103@gmail.com).

---

Thank you for your interest in our AIoT home automation project! We hope you find this readme file helpful in understanding the project and getting started with development. Happy building!