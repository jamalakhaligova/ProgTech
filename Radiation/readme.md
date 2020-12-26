First Assignment
----


- There is a planet, where different kind of plants are living. All the plants are using nutrients to
live. If a plant runs out of its nutrients, it dies. Each day one radiation type can occur from the
followings: alpha, delta, or no radiation. Radiations affect the plants differently based on their
types. The reaction of a plant to a given radiation consists of the following: it changes its nutrient
level, and affects the radiation of the next day. The radiation of the next day:
-a. alpha, if the need for alpha radiation is 3 or more greater than for the delta radiation
-b. delta, if the need for delta radiation is 3 or more greater than for the alpha radiation
-c. no radiation, otherwise

- There is no radiation on the first day…
Simulate the behaviors of the plants, and print out the radiation of the day and the properties
of the plants on each day.
- Properties of the plants: name (string), nutrients (integer), living (boolean). The types of the
plants in the simulation: puffs, deltatree, parabush.
On a day of the the simulation the living plant first changes its nutrients, then if it is still alive, it
can affect the radiation of the next day




- Read the data of the simulation from a text file. The first line contains the number (n) of the
plants. The following n lines contain the information about the plants: name, type, initial nutrient
level. Type is represented by one character: p - Puffs, d - Deltratree, b - Parabush. The last line of
the file defines the number of the days you have to simulate.
The program should ask for the name of the file, and it has to print out the name of the survivors
(we can assume that the file is existing and its format is valid).

A possible file content:
```
4
Piggy p 7
Slender d 5
Dumpy b 4
Willowy d 3
10
```
