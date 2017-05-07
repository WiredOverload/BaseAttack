# BaseAttack
Simple 2D game for Intermediate Programming final

Created by Michael Hodges and Christion Jungers

  
This game consists of two sets of five bases. One set is the player's bases and the other set are the AI controlled enemy bases. These bases can perform 4 different actions that all cost in game currency generated by the bases. Three of these actions spawn different types of ships that attack enemy ships and bases, whereas the fourth action upgrades the base increasing its health and income. If a base is destroyed by an enemy ship, all bases below it shift up one to take its place. The game ends when either the player runs out of bases or the enemy runs out of bases. This game has three different levels of difficultly that increase or decrease the enemies’ health and income. Whenever a game is started, the time and difficultly in which it started is logged in a file called game_log.txt where the game result, victory or defeat, will be logged. Additionally there is a pause menu in which the music may be muted.

There was quite a bit in this project that was new to both programmers, and so there were quite a few problems that were eventually solved. Most of the largest serious bugs were either relabeled as features or caused the intended feature to become dropped completely as this entire project was started and finished in little more than a week.
The first major bug encountered was what happened when a base was destroyed. It was originally intended that once a base is destroyed, it would simply change its sprite into a destroyed-looking one and would stop receiving updates, however, that would require adding a check for a new Boolean variable "isAlive" at many points in the game and did not address what to do with the enemy attacking ships already en-route. Additionally, this move did not make sense from a gameplay perspective as any currency invested in the destroyed or attacking base becomes completely wasted, leaving the optimal strategy to boringly distribute all upgrades equally among bases. The solution was to have the current design of shifting all bases up by one once a base dies, making the bases act like extra lives, and maintaining the attacker's upgrades.
The second major bug caused the feature itself to become completely scrapped. Originally, it was planned to have a health bar underneath every structure or minion with health. The bar would turn from completely green to partially red depending on how hurt the structure or minion was. This feature was previously created in another game by one of the programmers; however, there was a key difference in structure between the two games. The current game uses a stackpane as its root node; however, the older game simply used a group root. This difference meant that every update, the two lines that made up the health bar needed to be recalculates and then reshifted to appear in the correct spot. Additionally, once a minion or base dies, the health bar would remain in the same place and would not be automatically removed as it would in a group. The solution was to simply remove every element of the problem including the health bar.
One final bug that was actually addressed was an issue with the background clouds. Originally, there was a very flat background that did not give any sort of atmosphere to the game. In its place, the current animated cloudy background with stars was created. This animated background is made up of three images, the black background with stars, a mostly transparent cloud layer that is twice the width of the background and mirrored in the middle of its width, and an exact copy of the previous cloud layer, but flipped vertically. The black background does not move, the first cloud layer moves at a constant speed, and the second cloud layer moves at twice the other cloud layer's speed. These two clouds layers were drawn twice each in order to make sure there was a constant stream of cloud layers visible without any edges, however there wa a bug where if the game ran long enough, eventually the faster cloud layer would not redraw itself at the beginning, creating a very visible edge that travelled across the screen. This problem was fixed by increasing the length of the animation timer, and adding a modulus of half the length of that timer to the faster cloud updates.

Abstraction: Although some of the imported classes used abstraction, the created class files for this project did not use abstraction. All of the classes preformed very different functions with not much common ground, and so implementing an abstract class would not have been helpful or useful in this project.
Polymorphism: This project did not use much polymorphism at all, mostly because of the limited number of cases that each component had. There was never a time in which an object needed to be initialized with less information or with two different sets of information, and so only one constructor was ever need for any object. For any other method that was needed, the method had a unique name and did not need to override any other imported method. The only exception was for the "handle" method for button logic.
Inheritance: While this game did include four different class files, each class file was different enough to where inheritance didn't make the most sense to use. The only application I can think of is using some kind of "structure" class to contain health, x coordinate, and y coordinate and have the minion and base class inherit the structure class, however, that would mean only two classes would inherit another class and would only use inheritance in order to neatly contain variables. Each function in every class worked very differently, and unfortunately, there was not a good way to have a standard inherited update or render class. This project did however import many classes thant made good use of inheritance.
Encapsulation: This project used large amounts of encapsulation. Every variable for every class was private, and to access the variable one needed to use getters and setters. There was also a strong emphasis on keeping classes as separated as possible and only sending over the bare minimum of data in order to get a method to function properly.
