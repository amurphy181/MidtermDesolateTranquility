# README

### Group Members
Joseph DiBiasi, Jared Groves, Alex Murphy

## AWS Information

link: http://18.188.17.166:8080/MVCDesolateMidterm/

To gain instant access and reactivate events for viewing the existing Admin account can be used. 

username: admin

password: admin

## Why this project?

We started this problem aiming to solve a problem familiar to gaming-inclined people everywhere: when looking for friends to play games with online, how do you know what they are playing and on what platform? There are ways to do this in each of the game platform silos - Steam on PC, Xbox Live, PSN for Playstation - but these do not speak with each other in a way that would allow one to see what friends are playing across the board.

This is where our app comes into play: when you log in, all of your friends on the social platform show up on your personalized main page, indicating their gaming status, the platform being used, and how many people are in their party at that moment. We also allowed for the creation of events in the future and in-person, for those so inclined, which allows for better planning for pastimes like Magic the Gathering or Dungeons and Dragons.  

## Project Methodologies
#### Group Project
The most serious challenge we faced during this project was not as much the technical hurdles as it was the issues that crop up when three people are working on one set of code. We solved this in a fairly expected way: solid planning. Early on, we recognized that having more than one controller (in this case, for User, Event, and Game objects) as well as similarly-themed database accessor objects and associated entities. This allowed us to work on separate sections as the back-end was being built up and tested and then, once we had the basic structure set, further allowed for a division of labor between the front and back-ends, minimizing merge conflicts to a total of two minor overlaps over the course of the entire project.

#### Agile Work Environment
Greatly helping our group dynamic was our implementation of a more agile work environment. The more workplace-oriented practice of having two week sprints was quite untenable for a week-long project, so we modified these practices to have both morning and afternoon stand-up meetings in place of a traditional sprint. This allowed us to keep close tabs on where the project stood, where any issues were cropping up, and whether or not we needed to sit down for some pair programming to figure out an intractable problem. Using these practices, we were able to not only avoid code conflicts but the conflicts that arise when communication has broken down and team members find themselves at loggerheads.

#### Pair Programming
At last, pair programming. Your friendly readme writer happens to be a huge proponent of this practice, as it solidifies techniques and information that may have been glossed over previously. As a group, we found that talking out problems with each other provided a solution much more quickly than hacking it out solo, and most importantly, all contributors were able to walk away with a more thorough understanding of the solved problem.

## Technologies

### CRUD
Creating, reading, updating, and deleting (or more gently, deactivating) data was the main purpose of this project. From the back-end to the front, all data that needed to persist from one session to the next does so, making the social aspects of the site much more workable.
### JPA
The Java Persistence API allowed us to maintain information from one session to the next with Java in the middle. This made our back-end database much more useful, as we would otherwise have had to hard-code any needed long-lasting information with no ability to modify it throughout.
### SQL
Sql was a big part of this project since we were working with a database. Our project had a small but tightly connected database; this meant we had a lot of foreign key relationships between tables and even a self-join with User through the Friends table. This created an interesting dynamic when working with the database on the Java side and allowed us to gain experience writing unique SQL queries.
### JAVA
This project allowed our group to fully grasp the versatility of the Java language for full-stack applications. Our previous lessons in Object-Oriented programming paid huge dividends on this project, whether it was lining up entities in the framework to match database tables or - quite crucially for a web application - providing the framework to allow our Tomcat server to communicate with the back-end and the various languages used throughout.
### AWS
"Who's Playing" is accessible through an Amazon Web Services server, conveniently free for usage below a certain bandwidth. We kept copies of the SQL database at the root level of our server, then accessed it with our WAR file from the top. For testing purposes, this was incredibly helpful because we could run multiple users at once, rather than hosting the application locally and hoping that everything would work once published.


### Issues Seen, Issues Solved
#### Mapped Relationships
Mapping Relationships: Our database was more like a bush than a tree. There were so many relationships throughout. Just about every table had multiple many-to-many or many-to-one or one-to-many relationships with other tables. Mapping them in the POJOs was an exercise in patience and attention to detail. We definitely overcame some issues in this regard. Translating the relationships in the database to the Java side was a skill we got really comfortable in, allowing us to swap data from entity to entity and on out to the front-end.
#### Modals
Modals. Modals saved our collective behinds. We already were having issues learning how to manipulate and format our front end when we learned about modals. With them, we were able to dramatically increase the amount of information viewable on the main page without having to have the user scroll down. All the modals were pre-generated as the page loaded, and only displayed when clicked. It really served as a great way to give the user only the information he/she wanted at any given time.
#### Password Hashing
One of our main goals was to provide a realistic user experience, complete with basic security. To start, every password was stored in the database as a plaintext field, which would not fly in any real-world setting. Java provides a convenient framework for security with the Bcrypt function, allowing for secure, salted password hashing after setting up the Spring bean and autowiring the encoding object. Once implemented, our user controller ran the input string through the Bcrypt, hashed and salted the string
#### HTML/CSS
HTML/CSS: This one was a beast. None of us had much experience in the front end design aspect, so it really was a case of learn-on-the-fly. We had issues with Google caching the .css file, and not knowing why new elements were not being properly selected. It was really rewarding to see our final product though, all things considered. I feel like we all learned a great deal about front-end design as a whole, and are a lot more comfortable mucking around in the front end of web applications.
#### Git Issues
One of the learning experiences with this project was using Git with multiple members. We did not experience many Git issues, but we did have an issue with a detached head. This was caused by checking out a commit and working with that instead of the master connection. When the time came to commit our files we found we had conflicts. This actually ended up causing us to lose the code we had written within the detached head. Thankfully the damage was not too great, and we learned a valuable lesson about working apart from the Git master along with how to solve the issue in the future.
#### Spring MVC
We launched our website using Spring MVC. To do that, we had to configure web and persistence xml files, along with a servlet to handle both our data and the controllers. This meant that we had to carefully set up the build path and ensure all the necessary files were downloaded for our program to use. Once the initial set up process was complete, we were free to work on our project with Spring handling the communication between entities, Dao’s, and controllers.
#### DAOs
Spring allowed us to use simple annotations to make gathering data, storing it, and maneuvering around our site quite easy. After our initial set up, we only had to annotate items correctly, and Spring would do the rest by pulling the correct methods/data/path for us. We were able to have multiple DAOs and Controllers and work in them individually. This greatly sped up our development process as we could work in one of the unoccupied classes instead of having to wait for a teammate to finish working in the DAO/Controller.
#### Admin vs. Regular User
We have multiple privilege levels. Users can only see other active events and can only deactivate events that they themselves created.
Admins can see ALL events, both active and inactive and choose to deactivate any event they see fit. Admins can also reactivate events, giving them a fresh 12 hours and resetting their timers. Admins can also activate and deactivate users as well as give or take-away admin rights to other users.
