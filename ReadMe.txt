
SDK Manager lets you manage the Android SDK that you would
use 'sdkmanager' and 'avdmanager' to manage packages, create and
launch AVDs.

** Note **
    The directory structure for new manually created Android SDKs seems to have changed.
    The new path should be like:
    
    [SDK Home]/cmdline-tools/[latest (.zip)]
    
    Without this path some commands for 'avdmanager', and possibly other others,
    could fail.
    
    -- Note --
    If updating the 'cmdline-tools;..' Package use the latest version number, like: 'cmdline-tools;13.0',
    rather than using 'cmdline-tools;latest' as the 'latest' directory could already exist and cause problems.
    

Go into config.properties and fill in your paths to Java
and the SDK that you want to manage.

Setting up your Android SDK for the first time:

The latest SDK version: 
https://developer.android.com/studio/#downloads

Scroll down to "Command line tools only" and choose the Windows, Mac or Linux
version of the Android SDK tools package.

- Create a directory for your Android SDK, with no embedded spaces in the path,
    and include a 'cmdline-tools' directory.
    
	like: C:\android-sdk\cmdline-tools

- Extract the .zip into your 'cmdline-tools' directory.
- Rename the new 'cmdline-tools' directory to 'latest'
    The directory structure should look like:
    
    android-sdk/cmdline-tools/latest

Set the path of the new Android SDK directory, 'android_sdk_path', in config.properties.

Run SDK Manager.

Select 'Manage SDK->Packages', and one Package at a time,
    select the "Accept licenses" checkbox
    and do a "Submit":    

    'platform-tools'
    'platforms;android-33'  ( Or whichever is the latest, or what is needed for your application )
    'platforms;android-19'  ( Or whichever is needed for the minimum target SDK )
    'build-tools;34.0.0'
    'emulator'
    
Complete accepting licenses:
Select 'Manage SDK->Accept Licenses', check "Accept Licenses" and hit Submit.

This will ensure that many essential things
are properly set up and all the licenses are accepted.


The biggest potential issue that can happen is that your firewall
can block the execution of the 'sdkmanager' and 'avdmanager' commands
and you'll get partial or no results.

You can try to fix this by temporarily turning off your firewall,
to see if that fixes it, or by trying to set your firewall to not block it.
Usually it's trying to block java.exe.

It can interact with the SDK that Android Studio uses but it is
recommended to use Android Studio to manage that.

** Note **
    On some early versions of the SDK, sometimes
    the Package names get truncated with ellipsis, if they do
    it can't convert it back, so selecting it won't work.
    
    
Packages:

The packages highlighted in green are packages that are currently installed.
The ones in gold are packages that are installed and that an update is available for.

It is recommended that you only install up to two packages at a time, but one at a time
is preferable.

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
it will do an Enter for the default choice for the prompt:

'Do you wish to create a custom hardware profile? [no]'



SDK Manager 1.2.0:
    Option workaround for "Installed Build Tools revision 32.0.0 is corrupted" dx.bat issues.
    
SDK Manager 1.3.0:
    Detects new recommended SDK directory structure resulting in improved operations.
    Improved device list for AVDs.
    
    
Please let me know of any issues so that
I can try to fix it.

joes0451@outlook.com

