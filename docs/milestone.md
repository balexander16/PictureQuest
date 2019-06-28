## Milestone Deliverables:

### Currently running systems in Picture Quest 
* Room is running and the scene fragment is displaying a test scene from the database.  
* Camera2 api is implemented and capturing an image to internal storage on the android device. 
* Navigation through three fragments, scene, choice, and camera handled throigh nav-graph. 
    * Opening fragment is Scene, which currently displays one scene taken from the database. 
      * Still deciding how to implement this in a user friendly experience. It is currently in a simple list view not to complicate things. Will most likely use some scrolling text contained within an area that will be ontop of an image of a scroll or something like that. 
    * Bottom Navigation links to a total of three fragments. 
      * Scene is mentioned above.
      * Choice is currently an empty placeholder fragment.
      * Camera goes to the camera2 api that is currently running. It currently saves pictures into internal storage on device. 
* Have put in implementation to use Clarifai Java API library. Currently working on getting the last image taken to be used as the 
input once and only once. Deciding if input file should then be cleared or saved to be viewed later on in the game to account for what images got you where you are. 

### Things in the repository
* Entity Diagram with annotations
* Wireframes with annotations
* Readme files with links. 
