# FlickrSearchAppDemo

this is a project that uses the Flickr api to search for images. it allows the user to search for images and display them in a list along with the users icon and username. 
when clicked it navigates to a screen that displays more details about the image as well as a button that displays other images by the same user to view.

this project uses Koin dependecy injection and is used throughout the app eg.creating services and viewmodels. I have modularised it by feature and api and ui domain. this is to encapsulate them to follow good programming principles.
the App module only knows about the UI modules and the UI modules only know of their respective API modules. there is also a Common folder that contains all the modules that are available to most modules. eg networking
which contains everything required for connecting to the Flicr API and is utilised by the API modules for their services. 

I have used MVVM as its what google recommends and is very popular at the moment. has a host of benifits such as separaion of concerns, reuasiablity etc. 

I have also chosen Jetpack compose to do the UI and navigation. Jetpack compose many benifits over the older xml. 
