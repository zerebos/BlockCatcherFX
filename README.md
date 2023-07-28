# BlockCatcherFX

This is a JavaFX version of the uber-popular [web game of the same name](https://github.com/rauenzi/BlockCatcher). However, it is not a direct 1:1 port, as this has different features such as randomized block sizes and speeds.

Note: At this time, the project is not complete and has only been resurfaced. This code was originally written back in 2015/2016.

![Preview](./preview.png)

## How To Play

If you're on Windows, the releases page should have a zip for you to extract. From there, just run the `exe`. Builds for Linux/Mac coming eventually.

For anyone else, you'll need to clone this repository and use the javafx:run command in the Maven toolkit.

# Controls

The player plays as a black bar at the bottom of the window. The bar follows the user's mouse. Holding down the mouse button pauses movement.

As of writing, there is no win condition or stop condition other than exiting the program. This is the top priority for implementation.