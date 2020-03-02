# SpaceLaunchNow
It provides information on upcoming rocket launches. The app will connect to the publicly available Space Launch Now API (https://spacelaunchnow.me/) and display a list of upcoming rocket launches. Compatible for both mobile and tablet screens.

## Design
It uses <a href="https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel">MVVM</a> architectural pattern along with <a href="https://developer.android.com/topic/libraries/view-binding">ViewBinding</a>, <a href="https://developer.android.com/topic/libraries/data-binding">DataBinding</a> and <a href="https://developer.android.com/topic/libraries/architecture/livedata">LiveData</a> architecture components. 
<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/MVVMPattern.png/660px-MVVMPattern.png"/>

## Components
### Model
Models are used to keep the data and business logic. Model components interact with only ViewModel components. There are two major model classes (UpcomingRockets.java and RocketDetails.java) are used to capture the data from server. There is no local persistance used so it always fetches the data from server using a repository class (SpaceLaunchNowRepository.java) and a service interface (SpaceLaunchNowService.java).
### View
Views represents graphical user interfaces (GUI) where user interacts to the app. Data binding is used between View and ViewModel components so that data will be displayed on GUI when there is a change in the model. We used two views/screens (RocketsListFragment.java and RocketDetailsFragment.java) to display the list of upcoming rocket launches and their details. They always connect to their corresponding ViewModels (ProjectListViewModel.java and ProjectViewModel.java) to fetch the data from repositories then display on GUI.
### ViewModel
ViewModel is used to handle the interations from View components and work with Model components to process the data. There are two ViewModel components (ProjectListViewModel.java and ProjectViewModel.java) are used to request repository to fetch upcoming rocket launches and rocket details.

## Preview
![ezgif com-video-to-gif](https://user-images.githubusercontent.com/61699227/75696665-0eecbb00-5d00-11ea-9529-6996d9f53e14.gif)

