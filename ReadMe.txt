
SDK Manager lets you manage the Android SDK that you would normally
use 'sdkmanager' and 'avdmanager' to manage packages, create and
launch AVDs.

The biggest potential issue that can happen is that your firewall
can block the execution of the 'sdkmanager' and 'avdmanager' commands
and you'll get partial or no results.

You can try to fix this by temporarily turning off your firewall,
to see if that fixes it, or by trying to set your firewall to not block it.


Go into config.properties and fill in your paths to Java
and the SDK that you want to manage.

It can interact with the SDK that Android Studio uses but it is
recommended to use Android Studio to manage that.

Packages:

The packages highlighted in green are packages that are currently installed.
The ones in gold are packages that are installed and that an update is available for.

It is recommended that you only install up to two packages at a time.

When you have finished adding any packages, always select:
Manage SDK->Accept Licenses
to be sure that all your licenses have been accepted.

For listing and installing packages, you can choose the
Channel source in config.properties.

In order for the AVDs to work, you should look over the "system-images.."
packages and select the ones you want to use when creating AVDs
to run with the Emulator.

After you check the "Accept licenses" checkbox, licenses
will automatically be accepted, and when an AVD is created
it will do an Enter for the default choice for the:

'Do you wish to create a custom hardware profile? [no]'

prompt.


Please let me know of any issues you have so that
I can try to fix it.

joes0451@outlook.com

