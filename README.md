# Jellyfin Android

> **This is not an official client**

The current Jellyfin Android app uses Cordova and adds a bit of custom code on top of it. Unfortunately it is not possible to develop using Android Studio and sharing code with the Android TV client is hard because Cordova.
When I was looking around in the code I noticed most of the "custom" code is defined in a JavaScript object called `NativeShell`. By supplying my own variant of it I could make the webview think it was an app.

The next thing I did was creating a simple Android app that contains a WebView and a JavaScriptInterface that the Nativeshell can interact with. That is the code you find in this repository.

## Architecture
The `MainActivity` creates a `WebViewFragment` and gives it the Jellyfin server URL. The fragment will load this url and inject JavaScript into the `index.html` file it loads from the server.
When sending data between the NativeShell in JS and Kotlin all JSON will be encoded to strings because objects are not supported in the WebView. So far this codebase is very much a prototype.

## Running
Because this app, currently, loads the web client from a given server it needs a server URL. There is currently no way to specify this in the app because the value is hardcoded in the `MainActivity`. It is currently set to `https://demo.jellyfin.org/stable/` but could be replaced with something else.

## License
All code in this repository is currently not licensed. This means that, depending on your region, all rights belong to the original author (Niels van Velzen).
