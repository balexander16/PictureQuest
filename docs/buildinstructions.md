## Warning
* **To test this app you really need an android phone**
* You can clone and import and run this on an emulator but the entire app runs on image detection and does not work well in an emulator do to only having one fake room!

## Build Instructions. 

Please follow these steps to setup this application. 
1. Fork a copy of this app into your own repository to then clone or download from their. Or simply clone this app locally. 
    * Copy the SSH Key url from the clone button.
    * In IntelliJ select Check out from Version Control
         * Git, then paste the URL you copied to your clipboard.
2. Import changes to the gradle and sync if necessary. 
3. Check if your clone, imported successfully, if it did not, such as an initial clone into Intellij. Pleaes close the program and import in IntelliJ.
4. Check to make sure the app configurations, (top of IntelliJ) has android app config
    * If that isn't there simply click the edit configs button (middle top). 
        * The plus button of the next pop-up window (top right). 
        * Name the config "app", with a module level app. 
        * Confirm this worked, you should be able to switch from project view to android view. (Top left under file drop down in IntelliJ)
5. You will need a Clarifai API key to make this work. Get your own at clarifai.com 
    * Or if you are Nick, Shaun, or Todd please retrieve this from Learn! 
6. You will then need to make a child directory in where ever you cloned or saved this project on your local machine. Called keys
    * for instance if this in .../projects/PictureQuest please create a directory in .../projects.
7. Inside of your keys directory please create a clarifai.properties file. 
8. The only thing inside of this file should be api_key = [YOUR API KEY]
    * **Replacing [YOUR API KEY] with your API key from Clarifai.**
9. Now everything should be running smoothly, you may need to build rebuild or to do an import changes or a sync gradle after creating that file so your dependencies have the proper file to use. 
10. Enjoy the Quest!
