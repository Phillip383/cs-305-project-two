# Introduction

A simple angular front-end that connects to a spring-boot back-end for CS-305 Software Security project two. The front-end uses POST instead of GET to request a hash using `SHA-256` for data entered in the text box. Utilizing a POST instead of GET ensures that data won't be leaked via browser history, or URL parameters.

The text box is reactive to user input. For example, when focused the background color changes. The servers hash value response is reactive to user input. When the user changes the data in the text box, the hash value is hidden and displayed again once the form is resubmitted. The back-end spring-boot server utilizes a self-signed key. However, due to security practices. The .properties file and keystore are not included in the repo.

This was my first time actually tying together a front-end and back-end to consume RESTful API endpoints, it was, quite the learning journey, but not as hard as I expected. Getting the development environment correct was the worst part.

#Installing and Running

##Prerequisites
I plan to create a container and automation scripts to do this. However, if I do not have the time this week to get it done; this is the steps required to test if you want.

Clone the repo:

`git clone https://github.com/Phillip383/cs-305-project-two.git` or the SSH link:

`git clone git@github.com:Phillip383/cs-305-project-two.git`

Node and NPM are required. [Node Download](https://nodejs.org/en/download)

Angular CLI is required and can be installed via NPM.

`npm install -g @angular/cli`
More can be found at [Angular Installation](https://angular.dev/installation)

Java 21+ is required.
I upgraded spring-boot to the most modern version, and using java lts version 21.

##Running
Copy the resources folder from `ssl-server-student/src/main/resources` to the repos `backend/src/main` directory. This moves the keystore and properties file to the spring-boot project to enable ssl and setup the server on port 8443. If both the assignment and repo are in the same directory, this command should work from the cloned repos root.

`cp -r ../ssl-server-student/src/main/resources backend/src/main`

`cd` into front-end,run `npm install && ng serve --ssl`

`cd` into back-end, run `mvn clean spring-boot:run`

Since the key is self-signed the web browser will throw a warning, it will have to be skipped to continue. You might have to traverse to https://localhost:8443 and accept that warning as well to let the front-end communicate with the server.
