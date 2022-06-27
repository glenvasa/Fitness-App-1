# Your Fitness / Your Life

- Fitness tracking web appliction for people serious about improving their health.
- Application tracks all aspects of User's daily food intake and exercise regimen.

## Technologies Used in Development
- Client-side: HTML, CSS, Tailwind CSS, Thymeleaf, JavaScript.
- Server-side: Java SDK 18, Spring Boot, Tomcat Server, Maven Build Tools, Spring Security.
- Data access: Spring Data JPA, MariaDb.
- SMS messaging: Twilio API.
- Version control: GitHub.



## User Flow / App Functionality
- User logs in each day and is prompted on HomePage to Create a Daily Health Profile in which they input their Current Weight, Weight Goal, and Exercise Level. 
- Daily Target Calories are calculated and User may proceed to Create a Meal or Create a Workout.
- HomePage displays User’s current Personal Records for each Exercise performed in all saved Workouts.

- To Create/Save a Meal, User creates/adds Servings of individual Food Items. Saved Servings/Meals are deletable.
- To Create/Save a Workout, User creates/adds Sets of individual Exercises. Saved Sets/Workouts are deletable.
- All Users can add any Food Items or Exercises stored in the DB regardless of which User created it.
- Users have ability to Create new Food Categories, Food Items, Exercise Categories, and Exercises.

- Profile Page allows User to select a date and view all Meal/Workout Cards pertaining to that date.
- User is shown the Total Calories, Protein, Carbohydrates, and Fat amounts consumed for all meals on the selected date. 
- User is informed if, on the selected date, fewer or greater calories than the Target Calorie amount were consumed.
- Profile Page displays Today’s weight, exercise level, target calories, and weight goal for comparison to the selected date.
- Profile Page allows User to update any data originally inputted at time of registration.  User may also delete their account.

- If User’s Daily Target Calories are exceeded (upon saving a new Meal), User receives a detailed text message containing words of encouragement within 3-4 seconds informing of same so the User can stay on track with their weight goal.
- If User achieves a new Personal Record for any Exercise (upon saving a new Workout), User recieves a detailed text message of congratulations within 3-4 seconds.

## Description of Functionality in Controller Classes
- This document details all HTTP requests/routes in all Controllers including: where requests originated in the View, data sent/retrieved to/from database through service method calls, all data attributes added to model, html pages returned, and reasons for the forgoing activity based on User experience considerations.
- <a href="https://docs.google.com/document/d/1NfciJLEmpYaysyvyymCTEvvb_erk786KrhxmxelmN4k/edit?usp=sharing" target="_blank">Controllers Description - Google Doc</a>

## Database Entity Relationship Diagram
<img src="src/main/resources/static/img/Fitness-App-1.png"> 

## Web Application Wireframes

<img src="src/main/resources/static/img/wireframe-1.jpg"> 
<img src="src/main/resources/static/img/wireframe-2.jpg"> 
<img src="src/main/resources/static/img/wireframe-3.jpg"> 
<img src="src/main/resources/static/img/wireframe-4.jpg"> 
<img src="src/main/resources/static/img/wireframe-5.jpg"> 
<img src="src/main/resources/static/img/wireframe-6.jpg"> 

## User Stories

- As a User, I want to create a daily health profile in which I input my weight, weight goal, and exercise level to calculate my daily target caloric intake.
- As a User, I want to track all of the specific food items I consume each day.
- As a User, I want to track the total number of calories, fat, protein, and carbohydrates consumed each day.
- As a User, I want to be able to create/store food items for later use in Servings/Meals.
- As a User, I want to create Servings of individual food items and save them as a Meal.
- As a User, after I save a Meal, I want to view instant data re: all calories, fat, protein, and carbohydrates consumed so far that day AND how many calories I am either below or above my daily target caloric intake amount.
- As User, I want to receive a text message alert when I have exceeded my daily target caloric amount so I can refrain from additional consumption and stay on track with my weight goals.
- As a User, I want to have access to my daily meal information for any date I select and have the ability to compare my past health profile information with today’s.
- As a User, I want to track all of the Workouts of exercises I perform each day.
- As a User, I want to track the specific sets of exercises contained in each Workout, including name, weight and repetitions.
- As a User, I want to be able to create/store exercises for later use in Sets/Workouts.
- As a User, I want to create Sets of individual Exercises and save them as a Workout.
- As a User, after I save a Workout, I want to view instant data re: all Workouts and my Personal Records for each Exercise.
- As User, I want to receive a text message alert when I achieve a new personal record for a particular exercise as this is motivation to continue my health journey.

## Challenges Faced in Developing "Your Fitness / Your Life"

- Deciding if I should use React.js/Next.js for the frontend instead of Thymeleaf. I had used React several times in the past and earlier this year began to appreciate the benefits of using Next.js on top of React. For this project, as was the case for most of my Java Developer's journey, I was interested in using technology that I hadn't used before. I ended up liking how seemlessly Thymeleaf integrated with Spring Boot and, while recognizing the benefits of using a front-end framework, 
decided that for this project Thymeleaf was a fine choice. 

- Overthinking the number of features and pieces of functionality I should complete by the project deadline. There are always features I will want to implement, however, with a limited time frame, I recognized that priorities must be set.

- Learning how to implement most, if not all, of the technologies used in this project on my own. Since I decided to start this project well in advance of the scheduled curriculum, I needed to learn how to utilize the core technologies (i.e. Spring Boot, Thymeleaf, Spring JPA) on my own through tutorials and documentation.

- Forcing myself to stay disciplined in maintaining a daily running log of all project thoughts, failures, and successes so I could encapsulate my experience and learn from mistakes along the way. The link to my 50-page document "Building Your Fitness / Your Life: Capstone Project" is below.
