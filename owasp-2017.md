#Vulnerabilities

##Injection

**Description**

Any place where a user can insert data to the system or application injection could be used. This data can contain harmful data.
For example, if a user can upload something to a database, the user could also upload a piece of harmful sql code. 

**Risk**

I can't think of any risk in my own code but there could maybe be a risk in one of the dependencies.

*What if we use authentication and authorization?*

This would not necessarily fix the issue since the issue lays in the dependency. 
However, authentication and authorization would still be a nice upgrade in this project since, in the current version,
everyone can access every game.

**Counter-measures**

Use a tool that checks all dependencies.

##Broken authentication

**Description**

Putting private things such as session id's in the wrong place like in the URL. 
Allowing brute force or other automated attacks is another good example.

**Risk**
In this project everyone can play everyone's games. The only thing that needs to be changed is the gameId in the url.

*What if we use authentication and authorization?*

This would fix the issue when the right authentication and authorization tool is used. 

**Counter-measures**
Use good authentication and authorization with things as weak password checkers.
For the automated attacks, only allow a certain tries when logging in. 
Don't store things as session id's in wrong places such as the URL.

##Insufficient logging and monitoring

**Description**

An attack on an application should always trigger an alert of some sort. If this is not the case attackers can go their own way without getting detected.

**Risk**

The server could get overloaded when alot of packages are sent at the same time. The server could crash.

*What if we use authentication and authorization?*

This would not fix the issue since it is not really related to authentication and authorization.

**Counter-measures**

Implement logging whenever a request is made to the server.
When the system detects to many requests or any suspicious requests block the user out, this can maybe be done with ip banning.
