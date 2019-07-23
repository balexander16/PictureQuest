# Picture Quest

* [User Stories](docs/user-stories.md)
* [Wireframes](docs/wireframes.md)
* [Data Model Documentation](docs/entitymodel.md)
* [Milestone Page](docs/milestone.md)
* [Javadocs](docs/api/overview-summary.html)
* [User Instructions](docs/userinstructions.md)
* [Build Instructions](docs/buildinstructions.md)
* [Copyright for Project and for Third Party Libraries](docs/license.md)

## Personal Android Proect:
Picture Quest is an android game where users walk through their communities and play through a fantasy world created by the world around them. By capturing images the program will recognize objects within them and create a text based world the players will interact with. Signs, cars, buildings, whatever is around you after inputs can be points leading to new activities. Initial versions will be entirely text based such as a sign stating "Town to the left" and the instructions would be walk for one minute to your left to get to the town. Then require another picture input when there. Further versions might implement map functions.

## Who will use this:
Hopefully that app will be appealing to fans off RPG's that are looking to get up and be active. Or just fan's of interactive games like pokemon GO.

## What data the app will need:
* The app will need to utilize an object recognition API, I am currently weighing pro's/cons of Clarifai, Google Cloud Vision or Amazon Rekognition.
* The app will need to use a picture data base associated with those such as Google Images.
##Persistant Data:
* User Log In/ID
* Possibility to have a few save states per user. 3 'characters' etc.
* Current Story point upon reload based upon user.
* Possibility to display a refresher or a current status upon reentry.
## Navigation
* I was thinking to primarily use bottom button navigation. So it would display the text/then present a button that would switch to camera mode.
* After the picture is taken it would then display up to 4 results/choices based upon possible objects detected. Then switch to above with prompts repeat.
* Toggle button items in a layout would work great like I saw demonstrated in chapter 15.

## API usage
* The primary API to be utilized will be Clarifai Object Recognition API, utliizing the community/free developer tier which does have a throttle of 5,000 inputs a month. 
* I would also like to utilize google sign in to keep track of users and use each user to save story states. 

## Aims and Motivation 
* I chose to develop this app because I was interested in utilizing and understanding some image recognition services. I was also interested in making an interactive application that can get people up and moving around the areas they live in as well as taking pictures of their cities.
* I believe this is a potentially useful or interesting app because it is a fun novel way to explore your surrounds.

## Current State of Completion
* The game runs and functions as intended, but could use some functionality tweaks and changes. 
* Currently there is no reset button or anything to toggle the game back to reset, unless you get to a point in the game that the character either dies, or returns back to home. The user must continue through the game. 
* The app flow could be better. Ideally I realized late that bottom navigation might be the wrong type of navigation. 
   * Ideally it should be at a scene then just a floating action button could take you to the camera. 
   * After taking a picture, if a choice is found it could then take you to the choice fragment. 
   * Then you need to select your choice which takes you to the next scene, which is already implemented. 
* I would like to change a fix the way that the scene is displayed. It's currently in a List View which is how I initially created it but honestly there is probably a better method to display. 
* Would like to add some aesthetics to the game. Maybe a scroll behind the text or other things. 

## Android API Versions
* App was tested on API version 26 min with a target api of 28 on a Nexus 5X API 28
* This was done for the Clarifai API, that is why the version level is so high. 
* It was also tested on a Google Pixel 2 with an API version 28 
  * This is my personal phone and was tested extensively on this since actual real pictures instead of an emulator is more ideal.
* App functions in any orientation in all fragments. As far as I know... CameraFragment has a seperate layout for landscape.
* Highly recommend using an actual phone instead of a emulator. 

## List of 3rd Party Libraries. 
* Facebook Stetho for database testing.
* Google OAuth

*Please see the 3rd Party License and Copyright link above

## List of External Services
* Clarifai Java Client API for the Clarifai Services. 
* Gson 

*Please see the 3rd Party License and Copyright link above

## Aesthetic Improvements that would Improve App
* Better formatting and display of the scenes 
* Better descriptions in choice, instead of just the objext found. 
* Images along with the text story to set theme better

## Stretch Goals
* Add in a reset function in options. 
* Add in more aesthetics, images, scroll all kinds of things. 
* Add more OAuth providers incase someone doesn's use Google. 
* Add some more text in the Choices for the buttons to add more naration. 
* Create a much longer story, currently stands at 18 total possible scenes. 

## License
 Copyright 2019 Brian Alexander 

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
