# Fairphone RSS Reader

This is an Android application built with Kotlin that fetches and displays RSS feeds from the Fairphone website.

Click for demo -> https://streamable.com/kybsko

## About

This project was created as part of a Fairphone assignment. It demonstrates the use of modern Android development practices, including Jetpack Compose, Coroutines, and Hilt.

## Features

*   Fetches RSS feed from `https://www.fairphone.com/en/feed/`
*   Parses the feed into news items
*   Displays item headers as a list
*   Allows viewing the full text of news items
*   Allows opening linked URLs in a web browser
*   Supports marking items as read
*   Supports sharing news items

## Architecture

The project follows a clean architecture approach, with separate layers for presentation, domain, and data.

*   **Presentation:** Uses Jetpack Compose for building the UI.
*   **Domain:** Contains use cases and business logic.
*   **Data:** Handles data access from remote and local sources.

## Dependencies

The project uses the following key dependencies:

*   Jetpack Compose
*   Coroutines
*   Hilt
*   Retrofit
*   Room
*   OkHttp

## Testing

The project includes unit tests for use cases, repositories, and ViewModels. It also includes UI tests using Paparazzi for screenshot testing.
