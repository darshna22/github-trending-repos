## Description
The project's idea is to list trending projects from Github and then tap on one of them and show their details.

**Requirements**
- Write your application in Kotlin
- Ensure your application looks good on different screen sizes and densities
- Ensure your application supports Android API 23+
- Use good architecture and design patterns
- Use valuable external libraries that you are used to
- Use reactive programming

**Bonus** 
- Filtering and ordering functionalities
- Unit tests
- Shared element transition or other animations (but only if they are functional to the user experience)



# Trending Repositories
<!-- to comment use such block-->
<!--[![API](https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=23)-->

<p align="center">
  <a href="https://android-arsenal.com/api?level=23" target="_blank" rel="noopener noreferrer">
    <img src="https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat"/>
  </a>
</p>

<p float="left">
<img src="https://user-images.githubusercontent.com/41982681/205622514-9dfbad13-06bc-4a7c-a6f7-8699c0c26962.png" width="270" height="400"/>
<img src="https://user-images.githubusercontent.com/41982681/205623284-5fa74b41-46c1-4ec1-8fdb-dd4a7584284a.png" width="270" height="400"/>
<img src="https://user-images.githubusercontent.com/41982681/205623477-7c47320c-a82a-452d-ab08-bb6673c36f1a.png" width="270" height="400"/>
</p>  


https://user-images.githubusercontent.com/41982681/205624817-7b409874-c6d8-4ac0-b52b-bccda44b6674.mp4

## API
Since there is no official API for Trending Repositories (it is one of the internal GitHub API’s),

I have decided to use [GitHub Search API](https://docs.github.com/en/rest/search?apiVersion=2022-11-28#search-repositories) and sort the repositories by their stars.

## Tech stack
* Minimum SDK level 23
* [Kotlin](https://kotlinlang.org/) based + Coroutines for asynchronous.
* Dagger-Hilt (alpha) for dependency injection.
* JetPack
  * LiveData - notify domain layer data to views.
  * Lifecycle - dispose of observing data when lifecycle state changes.
  * ViewModel - UI related data holder, lifecycle aware.
  * Navigation Component - handle everything needed for in-app navigation.
  * Data Binding - declaratively bind observable data to UI elements.
  * [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)- load and display pages of data from a larger dataset
* Architecture
  * MVVM Architecture (View - DataBinding - ViewModel - Model)
  * Repository pattern
* [Glide](https://github.com/bumptech/glide) - loading images.
* [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
* [Material-Components](https://github.com/material-components/material-components-android) - Material design components like ripple animation, cardView.
* mockito-test library



