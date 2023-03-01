## About

**I'm bored App** helps you find things to do when you're bored!
This project is an Android application which requests data from the Rest API provided by
the [BoredAPI](https://www.boredapi.com/api/activity) website. You just need to press the button and
you will have a new activity. If the activity has a link, you can tap on it and it will take you to
the website. You can also search in the menu of the *Proposed Activities*, a list of the activities
that have been shown so far.

⚠️ Software developed as exercise, not for production use.

## Prerequisites

- Install [Android SDK tools](https://developer.android.com/studio/install).

## License

This project is licensed under the [MIT](LICENSE) license.

## Architecture pattern, libraries and Android components used

- MVVM (with a "Repository" implementation)
- LiveData with ViewBinding
- Retrofit (http client)
- ROOM database
- Dependency Injection with Dagger Hilt
- Testing with MockWebServer
- Navigation Drawer: used to navigate many screens or functionalities of the app by clicking on the "hamburger" icon
- Activity with various fragments
- RecyclerView
- Management of the different layout designs according to the orientation of the phone: landscape or portrait

![Screenshot_1](https://user-images.githubusercontent.com/101328677/222126157-a7cb8853-9ebe-4033-9d87-9c688a0e276e.png)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
![Screenshot_2](https://user-images.githubusercontent.com/101328677/222126436-777122c0-aa27-4ce1-83af-7b0a155e6e16.png)
##
![Screenshot_3](https://user-images.githubusercontent.com/101328677/222126696-015a01fc-fb40-44dc-a478-f85e80c4a3f7.png)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
![Screenshot_4](https://user-images.githubusercontent.com/101328677/222126717-677829fd-75f4-496b-965e-fb90d4f606f4.png)




