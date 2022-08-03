![ic_launcher_geha](app/src/main/res/mipmap-xhdpi/ic_launcher_geha_round.png?raw=true) ![ic_launcher_geha](https://user-images.githubusercontent.com/29587914/182734052-23e204a1-4a95-4911-81df-270af408f08c.png)

# geha

Consume GitHub REST API, well, eventually.

## Topics covered in this project

- Kotlin:
    - Extension function
    - Data class: implement parcelable interface with Parcelize plugin
    - Companion object
    - `@Volatile` annotation
    - Synchronized access
    - Lazy initialization
    - Data model mapping
    - Lambda
    - Method reference
    - Try-catch block
    - Sealed class
- Load string array resources
- Android resources annotation
- Custom Android application class to hold shared instances
- View binding
- Data binding
- RecyclerView: ListAdapter (with item diffing)
- CoordinatorLayout: to coordinate scrolling behaviour between its children
- TabLayout & ViewPager2
- ShapeableImageView: gives the option to clip ImageView into various shape, e.g.: circular
- Snackbar: displays error messages
- String resources with parameters
- Use `Intent`:
    - explicitly, to navigate and pass data between different activities with a defined parent-child
      relationship between them
    - implicitly, to share plain formatted text to different installed applications
- Define styles resources
- Customize material brand theme colors
- Customize app icon
- Display splash screen with AndroidX SplashScreen compat library

## Result

<table>
  <tr>
    <th></th>
    <th>Nexus 4 API 21</th>
    <th>Redmi Note 4 API 24</th>
  </tr>
  <tr>
   <td rowspan="2">Splash screen</td>
    <td>
      <img src="https://user-images.githubusercontent.com/29587914/182735890-2cf4f1dc-5509-42a8-9f86-e93e73cc5eeb.png" />
    </td>
    <td>
      <img src="https://user-images.githubusercontent.com/29587914/182735902-48c0ab09-b689-4bed-a181-2ea71b8368c6.jpg" />
    </td>
  </tr>
 <tr>
    <td>
      <video src="https://user-images.githubusercontent.com/29587914/182735896-3cfeb6b1-a702-4e46-9a8e-95a8c73fb928.webm"></video>
    </td>
    <td>
      <video src="https://user-images.githubusercontent.com/29587914/182735906-fed0eb60-6b46-460b-b646-bc69fc287893.mp4"></video>
    </td>
  </tr>
</table>
