# Picture Quest

* [User Stories](docs/user-stories.md)
* [Wireframes](docs/wireframes.md)
* [Entity Relationship Diagram](docs/entitymodel.md)

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
