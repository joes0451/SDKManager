
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

!! To ensure that the SDK has the best chance of being properly set up
please follow these steps in order:

Select Manage SDK->Packages and select the highest 'platforms;..' level
that you'd like to use, like 'platforms;android-30', check "Accept licenses"
and hit Submit.

Then select the highest 'build-tools;..' level that you'd like to use,
like 'build-tools;31.0.0-rc1' check "Accept licenses" and hit Submit.

Then select 'platform-tools', check "Accept licenses" and hit Submit.

Then select Manage SDK->Accept Licenses, check "Accept Licenses" and hit Submit.

This will ensure that many essential things, like the emulator,
'platforms' and 'tools' directories, are properly set up and all the licenses are accepted.

The biggest potential issue that can happen is that your firewall
can block the execution of the 'sdkmanager' and 'avdmanager' commands
and you'll get partial or no results.

You can try to fix this by temporarily turning off your firewall,
to see if that fixes it, or by trying to set your firewall to not block it.
It seems to want to block java.exe.

It can interact with the SDK that Android Studio uses but it is
recommended to use Android Studio to manage that.

Packages:

The packages highlighted in green are packages that are currently installed.
The ones in gold are packages that are installed and that an update is available for.

It is recommended that you only install up to two packages at a time.

If there are a large number of packages to update,
or doing "Update all" has issues, it might be faster to manually
do --update from the command line.

*****************************************************************
When you have finished adding any packages, always select:
Manage SDK->Accept Licenses     and check the 'Accept Licenses' checkbox
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
This gives it a better chance of launching.

After you check the "Accept licenses" checkbox, licenses
will automatically be accepted, and when an AVD is created
it will do an Enter for the default choice for the:

'Do you wish to create a custom hardware profile? [no]'

prompt.



SDK Manager 1.0.4:
    Fixed the update available highlighting.
    
SDK Manager 1.0.5:
    Some minor improvements.
    
SDK Manager 1.0.7:
    Added many options for launching the emulator.
    Fixed --include_obsolete results.
    Fixed showing 'emulator' in Package list.
    Other improvements.
    

Please let me know of any issues you have so that
I can try to fix it.

joes0451@outlook.com

