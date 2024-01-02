# Zoo Shop Management System
## Description
This project is a coursework aimed at developing a prototype software system for managing an animal registry in a pet shop. The project is implemented using Java and incorporates object-oriented design principles and modern programming practices.

## Key Features
* Add and Remove Animals from Registry: Users can add and remove animals in the registry, with breed input validation.
* View Animal Registry: Ability to view all animals in the registry and search animals by breed.
* Sell Animals: Users can sell animals, removing the animal from the registry and recording the sale price.
* Manage Customer Data: Add and remove information about customers.
* Calculate Daily Sales Total: View the total sales for a specific day.
* Save and Load Registry: The animal registry can be saved to and loaded from a CSV file.
## Class Structure
* Animal: Represents an animal with characteristics such as name, breed, age, diet, favorite treat, and price.
* Customer: Class representing a customer, including name, surname, date of birth, and passport details.
* Magazine: Manages the pet shop logic, including adding/removing animals and customers, selling animals, and handling registry data.
* Printer: User interface for interacting with the system through a console.
* Triplet: Utility class used to hold related data (e.g., selling an animal to a specific customer).
## Running and Usage
The project can be run by executing Printer.class. The user interface is a console menu with commands for managing the animal registry, customers, and sales.
