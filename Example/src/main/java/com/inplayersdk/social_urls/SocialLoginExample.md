<h1 align="center">
  <a target="_blank" href="https://inplayer-org.github.io/inplayer-ios-sdk/">
    <img src="https://assets.inplayer.com/images/inplayer-256.png" alt="inplayer-ios-sdk" title="InPlayer iOS SDK" width="300">
  </a>
</h1>

## Social Login

The InPlayer SDK provides a way to login with social platforms. In order to do so, please follow the example below.

### Example

First, you will need to edit your AndroidManifest.xml file with your URI Scheme. That can be done by adding the following lines into your plist file:

![alt text](manifest_example.png "Android Manifest")

After that, inside your Activity or Fragment you will need to get the list of all available Social Urls.\n
<b>You will need to use the same URI Scheme you've configured in the step above. For best practices we suggest using the package name. You can configure it by your own, but make sure it will be unique.</b>
<u>Don't forget the <b>://</b> </u> at the end, however this sufix should not be included when defining the scheme in the manifest.
This can be done with the following method:

     InPlayer.Account.getSocialLoginUrls(
            "social.inplayer://",
            InPlayerCallback { socialUrlsList, error ->
                if (error == null) {
                    //Configure the list 
                } else {
                    //Handle Error
                }
            })

This will return you a list of social login options for you to choose from (Facebook, Twitter, Google etc). 
Each social login option will be made from two String values `socialAuthkName` & `socialAuthUrl`.

After selecting the wanted SocialLogin method, open webbrowser using `Intent.ACTION_VIEW`
    
`startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(socialAuthUrl)))`

This will open the social url into external browser and allow you to log in with your credentials. 
After successfull login it should redirect you to the app.

To validate the token recieved there is one more last step required in the process.
Inside the Activity you've defined in the Manifest to contain the data scheme, you will need to override the `onResume()` method.
The Intent.data should contain URI for validation, you will need to send this value to `InPlayer.Account.validateSocialLoginToken(uri,callback)` method like the example below:

    override fun onResume() {
        super.onResume()
        intent.data?.let { uri ->
            InPlayer.Account.validateSocialLoginToken(uri, InPlayerCallback { user, error ->
                if (error == null) {
                    //Successful authentication, continue to app
                } else {
                    //Something wen't wrong when authenticating the user.
                }
            })
        }
    }
    
<h3>Congratulations!</h3>
You've successfuly authenticated using SocialUrl's

For more detailed example you can check the Example code inside `LoginActivity.kt`



