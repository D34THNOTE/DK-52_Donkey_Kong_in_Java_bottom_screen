# DK-52_Donkey_Kong_in_Java_bottom_screen

Java implementation of a simplified Donkey Kong game using the MVC architecture.

## Features
The project implements:

- a tick-based game loop
- player movement and collision detection
- moving hazards (barrels and platforms)
- increasing game difficulty over time

The game runs on a tick-based loop which updates moving objects such as barrels and platforms.  
Game difficulty increases as the player progresses through levels.

Player movement is handled independently from the main game tick, allowing responsive controls while the environment updates in the background.

## Controls
Space bar – spawn player  
Arrow left/right – move  
Arrow up – jump or climb ladder  
Arrow down – descend ladder

## Implementation notes
The game runs as an endless loop where the tick controls the movement of hazards.  
The tick speed increases whenever the player jumps or completes a level.

## Preview

<img width="779" height="480" alt="Gameplay preview" src="https://github.com/user-attachments/assets/4bdf49b7-8f9a-49ab-9a48-9d6bafb3acbc" />
