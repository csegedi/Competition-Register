# Competition Register MVC
 
## Short review
 
The searching for a job has become actual for me, that is why I have decided to create an appropriate application for an accounting of my competitions.

Using this application, I can count the number of competitions, store relevant information about my competition, and change the actual stands of the chosen competition. The application contains other statistics about my competitions.

The following application has a database connection: one table contains all competitions. The data of the chosen competition can be changed anytime in the application.

**The user**
The application currently has just one user. This user is registered from the beginning. The application starts with a login page. The user can insert new competitions into the database. The user can update some elements of the registered competitions.

**The main page of the application**
The next page shows a table, which contains all the actual competitions. For the first time, when the table is empty (because I haven’t registered for any competition yet), the application gives me information about this fact. It offers me the possibility to insert a new competition into the database.

This page also shows the following information about my competitions:

The number of all competition
The number of the feedbacks
What percentage of the firms gave me feedbacks.
The number of the actual competitions

**The attributes of the competition**
* **firm**: the name of the firm
* **date**: when I send my CV to the firm
* **technologies**: requested technologies and knowledge (for example, Java, Spring, SQL)
* **language**: required command of a language ( the required level)
* **round**: round of the interviews (NONE, FIRST, SECOND, THIRD)
* **round type**: what type of the actual round of the interview (NONE, PERSONAL, ONLINE, TECHNICAL)
* **status**: the actual stand of the competition (ACTIVE, PASSIVE, DELETED, GOTCHA)
   * **PASSIVE**: I have sent my CV to the firm, but I haven’t got any feedback.
   * **ACTIVE**: I got feedback; negotiation is in progress
   * **DELETED**: The competition isn’t actual anymore (for example, it was rejected). In this case, I don’t delete this competition from the database, but the competition with deleted status will not appear on the main page. I didn't want to delete it permanently because I wanted to see on the main page’s statistic (see above) how much feedback I got (and the refusing also counts as feedback).
  * **GOTCHA**: I got a job!!!
* **Feedback**: got feedback from the firm or not.
* **descriptions**: other personal notes
