# About
The goal of this project is to develop an application for creating and managing events for online meetings, with the ability to send invitations. Users can create new events by defining various parameters, including checking the correct time for different locations of the users they want to invite. After adding the desired number of events, users can review all created events. Each event in the review screen has the option to be deleted. Users can send invitations via Viber, SMS, etc.

## Initial Screen
Upon launching the application, users are presented with a screen allowing them to define a new event and review existing events. Users can customize the background image, button text color, and font according to their preferences.

## Defining a New Event
By clicking on the "Add Event" button, users can define a new event. The event definition screen should include the following fields:

- Event name input field
- Event description input field
- AutoComplete field for entering the location to check the current time. City names for which time verification is possible should be defined in the resources.
- Button to check the time for the specified location, which uses Retrofit to call an API for obtaining the exact time.
- Button to set the event date, opening a DatePicker dialog for user selection.
- Button to set the event time, opening a TimePicker dialog for user selection.
- Spinner to define the event priority (High, Medium, Low)
- URL input field for the event
- Save button to save the event, utilizing Room for local database storage.
- Event Overview
- By clicking the "SHOW EVENTS" button on the main screen, users can review all events saved in the database using RecyclerView. Each element in the list should display the event's name, description, date, time, and URL. The background color of each item should correspond to the event priority.

## Invitation Sending
The application should provide an option to send invitations with event data using Chooser.

## Visual Design
Each screen should have a background image, and each view element should have specified colors and text fonts according to the design specifications.

## Technologies
- Room for local database management
- Retrofit for server communication
- ViewModel and LiveData for data management
