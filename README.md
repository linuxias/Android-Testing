# Android-Testing


## UnitTesting
This project is basic testing project

## Testing_RoomDB
This is an example project to test the Room database.

Define DAO, Database, Entities, and test queries such as inserting and deleting data into the database.
It also supports testing by mapping getOrAwaitValue for LiveData testing.

## Setup_For_Testing

This project is setup project for testing such as viewmodel, repository, fragment, and so on.
The project was based on ideas from the Mars project provided by Android developer Code Labs.
DI uses the dagger-hilt framework, and RestAPI used the Retrofit library to create this project.

Each test was organized by creating a branch for each step. The order of each step is as follows.

### 1. setup_for_testing
This branch organizes the base code for test learning.

### 2. testing_with_viewmodel
This is the branch where the project code and test code for the ViewModel test are added.
Contains the FakeRepository needed to test the ViewModel. ViewModel test code is written as Unit Test, not InstrumentedTest.

### 3. testing_with_hilt
Here's the code for a simple way to apply the Dagger-Hilt dependency injection framework to your tests.
Applying Dagger-Hilt to your tests makes testing very easy. I organized the method and wrote the test code.
