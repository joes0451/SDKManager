
SDK Manager lets you manage the Android SDK that you would normally
use 'sdkmanager' and 'avdmanager' to manage packages, create and
launch AVDs.

Go into config.properties and fill in your paths to Java
and the SDK that you want to manage.

Setting up your Android SDK for the first time:
The latest SDK version: 

https://developer.android.com/studio/#downloads

Scroll down to "Command line tools only" and choose the Windows, Mac or Linux
version of the Android SDK tools package.

- Create a directory for your Android SDK, with no embedded spaces in the path,
	like: C:\android-sdk

- Unzip into your directory.

To ensure that the SDK is set up idealy for your development:

Select  Manage SDK -> Packages
and select 'platform-tools' and the latest build-tools like: 'build-tools;30.0.3',
check "Accept licenses" and hit Submit.

Then select a minimum of one of the platforms, like: 'platforms;android-29'.
Then when finished select  Manage SDK -> Accept Licenses,
check the "Accept licenses" chackbox and hit Submit.

This will ensure that many essential things, like the emulator
and the 'platforms' directory, are properly set up and all the licenses are accepted.

The biggest potential issue that can happen is that your firewall
can block the execution of the 'sdkmanager' and 'avdmanager' commands
and you'll get partial or no results.

You can try to fix this by temporarily turning off your firewall,
to see if that fixes it, or by trying to set your firewall to not block it.

It can interact with the SDK that Android Studio uses but it is
recommended to use Android Studio to manage that.

Packages:

The packages highlighted in green are packages that are currently installed.
The ones in gold are packages that are installed and that an update is available for.

It is recommended that you only install up to two packages at a time.

*****************************************************************
When you have finished adding any packages, always select:
Manage SDK->Accept Licenses     and check the 'Accept Licenses' checkbox
to be sure that all your licenses have been accepted.
*****************************************************************

For listing, installing and updating packages, you can choose the
Channel source in config.properties.

In order for the AVDs to work, you should look over the "system-images.."
packages and select the ones you want to use when creating AVDs
to run with the Emulator.

After you check the "Accept licenses" checkbox, licenses
will automatically be accepted, and when an AVD is created
it will do an Enter for the default choice for the:

'Do you wish to create a custom hardware profile? [no]'

prompt.


SDK Manager 1.0.2:
    Fixed Accept, and made some other improvements.

SDK Manager 1.0.3:
    Some minor improvements.


Please let me know of any issues you have so that
I can try to fix it.

joes0451@outlook.com

