
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

Set the path of the new Android SDK directory, 'android_sdk_path', in config.properties.

!! To ensure that the SDK has the best chance of being properly set up
please follow these steps in order:

Run SDK Manager.

Select 'Manage SDK->Packages' and select the highest 'build-tools;..' level
that you'd like to use, like 'build-tools;31.0.0-rc3', check "Accept licenses"
and hit Submit. This should set up the 'emulator' and 'tools' directories/packages.

** Note **
    If you get: 'Warning: Dependant package with key emulator not found!'
    while trying to install a Package, and you don't have the 'tools' directory yet,
    set 'include_obsolete' in config.properties to 'true', list the Packages again and
    select 'tools', check "Accept licenses" and hit Submit.

Select 'Manage SDK->Packages' and select the highest 'platforms;..' level
that you'd like to use, like 'platforms;android-30', check "Accept licenses"
and hit Submit.

Select 'Manage SDK->Packages' and check if the 'platform-tools' package was installed, from above,
if it's there jump to below to finish accepting the licenses,
otherwise select 'platform-tools', check "Accept licenses" and hit Submit.

Finish accepting licenses:
Then select Manage SDK->Accept Licenses, check "Accept Licenses" and hit Submit.

This will ensure that many essential things, like the 'emulator',
'platforms' and 'tools' directories, are properly set up and all the licenses are accepted.

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

If there are a large number of packages to update,
or doing "Update all installed packages" has issues, it might be faster to manually
do --update from the command line.

*****************************************************************
When you have finished adding any packages, always select:
Manage SDK->Accept Licenses     and check the 'Accept Licenses' checkbox, and hit Submit,
to be sure that all your licenses have been accepted.
*****************************************************************

For listing, installing and updating packages, you can choose the
Channel source in config.properties.

AVDs:

In order for the AVDs to work, you should look over the "system-images;.."
packages and select the ones you want to use when creating AVDs
to run with the Emulator.

When you are selecting system-images packages for AVDs, check that the 'system-images' directory
has both 'kernel-qemu' and 'kernel-ranchu' files.  If it only has 'kernel-qemu'
you can try to download the 'google_apis' version which will usually have both.
Having both gives it a better chance of launching.

After you check the "Accept licenses" checkbox, licenses
will automatically be accepted, and when an AVD is created
it will do an Enter for the default choice for the:

'Do you wish to create a custom hardware profile? [no]'

prompt.


SDK Manager 1.1.1:
    Many small improvements.
    
SDK Manager 1.1.3:  
    Fixed multiple 'emulator' listings in package list.
    Improved AVD operations.
    New Emulator options.

SDK Manager 1.1.4:
    Improved updating packages.
    New Emulator options.


Please let me know of any issues so that
I can try to fix it.

joes0451@outlook.com

