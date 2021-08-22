/*
 * :folding=explicit:collapseFolds=1:
 */

/**
 *	  SDK Manager is a manager for the Android SDK
 *
 *	  Copyright (c) 2021 Joseph Siebenmann
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General  Public License as published by
 *    the Free Software Foundation; version 2.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see<http://www.gnu.org/licenses/>.
 */

package com.dominionmobile.sdkmanager;

import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.*;
import java.awt.Color;
import java.awt.List;
import java.awt.Font;
import java.util.Iterator;
import java.io.File;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.BorderFactory;
import javax.swing.UIDefaults;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import javax.swing.ListModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.TextArea;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.UIManager;
import javax.swing.JSplitPane;
import javax.swing.JList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.swing.plaf.ColorUIResource;
import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.Semaphore;

import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.text.*;
import java.util.Properties;
import java.util.ArrayList;

public class SDKManager
{
	//{{{   Data

	private static JFrame mainJFrame;
	private static JFrame frame;
	private static JFrame progressJFrame;
	private static JFrame createFrame;
	private static JFrame avdsFrame;
	private static JFrame packageFrame;
	private static JFrame acceptLicensesFrame;
	private static JPanel mainPanel;
	private static JPanel conesolePanel;
	private static JPanel textAreaPanel;
	private static JTextArea jTextArea;
	private static JTextArea avdTextArea;
	private static TextArea consoleTextArea;

	private static JTextField deviceField;
	private static JScrollPane textScrollPane;
	private static JCheckBox acceptLicensesCheckBox;
	private static JCheckBox uninstallCheckBox;
	private static JCheckBox forceCheckBox;
	private static JCheckBox finalAcceptLicensesCheckBox;
	private static JCheckBox startCheckBox;
	private static JCheckBox deleteCheckBox;
	private static JCheckBox updateCheckBox;
	private static JScrollPane avdScrollPane;
	private static JList avdJList;
	private static JList packageJList;
	private static JList sIJList;    // system-images
	private static JList devicesJList;

	private JLabel statusLabel;
	private JLabel deviceLabel;
	private JLabel statusPath;
	private JMenu subMenu;

	static volatile String sSDKPath;
	static volatile String sIO;
	static volatile String sActionCommand;
	static volatile String outputEndS;
	static volatile String commandResultS;
	static volatile String sDeviceName;
	static volatile String commandS;
	static volatile String sAvdDirectory;
	static volatile String sJavaPath;
	static volatile String sIncludeObsolete;
	static volatile String sInternalCommand;
	static volatile String sTest;
	static volatile String sToolsDir;
	static volatile String sShowCommandResults;
	static volatile String sPackageChannel;
	static volatile String sADV_BasedOn;
	static volatile String sUseHTTPS;
	static volatile String sUseForce32Bit;
	static volatile String sGPUMode;
	static volatile String sAccel;
	static volatile String sDisableBootAnim;
	static volatile String sEngine;
	static volatile String sKernel;
	static volatile String sQuickBoot;
	static volatile String sMemory;
	static volatile String sDisableJNIChecks;
	static volatile String sSELinuxSecurity;
	static volatile String sDisableVMAcceleration;
	static volatile String sNoCachePartition;
	static volatile String sWipeData;

	static volatile boolean bBreakOut;
	static volatile boolean bIncludeObsolete;
	static volatile boolean bTextAreaInit;
	static volatile boolean bHideOutput;
	static volatile boolean bUpdateSelected;
	static volatile boolean bPackages;
	static volatile boolean bCommandFinished;
	static volatile boolean bGetAVDFinished;
	static volatile boolean bAVDSubmit;
	static volatile boolean bAddTools;
	static volatile boolean bGetPackagesFinished;
	static volatile boolean bDontSetTools;

	static volatile int iOS;
	static volatile int iFontSize;
	static volatile int iAdvSelectedIndex;
	static volatile int iLongest;
	static volatile int iPrevLength;

	static ArrayList DevicesAr;
	static ArrayList SystemImagesAr;
	static ArrayList PackageAr;
	static ArrayList InstalledAr;
	static ArrayList AVDsAr;
	static ArrayList UpdateAr;
	static ArrayList InstalledObsoleteAr;
	static ArrayList AvailableObsoleteAr;

	private static SDKManager sdkManager;
	//private static Semaphore eventArControl = new Semaphore(1);

	static final int WINDOWS = 0;
	static final int LINUX = 1;
	static final int LINUX_MAC = 2;

	static final int DISPLAY_WIDTH = 105;
	//static final int DISPLAY_WIDTH = 125;
	//static final int DISPLAY_WIDTH = 145;
	
	static final String SPACING = " ";
	//static final String SPACING = "  ";

	static final String CREATE_ADV = "Create";
	static final String START = "Start";
	static final String PACKAGES = "Packages";
	static final String EDIT_ADV = "Edit";
	static final String AVDS = "AVDs";
	static final String AVDS_SUBMIT = "avds_submit";
	static final String AVDS_CANCEL = "avds_cancel";
	static final String ACCEPT_LICENSES = "Accept Licenses";
	static final String ACCEPT_LICENSES_SUBMIT = "accept_licenses_submit";
	static final String ACCEPT_LICENSES_CANCEL = "accept_licenses_cancel";
	static final String CREATE_SUBMIT = "create_submit";
	static final String CREATE_CANCEL = "create_cancel";
	static final String PACKAGE_SUBMIT = "package_submit";
	static final String PACKAGE_CANCEL = "package_cancel";

	static CountDownLatch interactiveRequestLatch;
	static CountDownLatch commandRequestLatch;
	static CountDownLatch operationRequestLatch;

	private CommandBgThread commandBgThread;
	private GetDevicesBgThread getDevicesBgThread;
	private GetSystemImagesBgThread getSystemImagesBgThread;
	private GetPackagesBgThread getPackagesBgThread;
	private GetAVDsBgThread getAVDsBgThread;
	private InteractiveCommand interactiveCommand;
	private CreateThread createThread;
	private AVDsThread aVDsThread;
	private PackagesThread packagesThread;

	//}}}

	//{{{   SDKManager() constructor
	public SDKManager()
	{
		// Determine OS..
		String sOs = System.getProperty("os.name").toLowerCase();
		if (sOs.contains("win"))
			iOS = WINDOWS;
		else if ((sOs.contains("nix")) ||
			(sOs.contains("nux")) ||
			(sOs.contains("mac")))
			iOS = LINUX_MAC;

		bTextAreaInit = true;
		bHideOutput = false;
		bPackages = false;
		sGPUMode = "";
		sPackageChannel = "";
		sIncludeObsolete = "";
		sShowCommandResults = "";
		sUseHTTPS = "";
		sUseForce32Bit = "";
		sAccel = "";
		sDisableBootAnim = "";
		sEngine = "";
		sKernel = "";
		sQuickBoot = "";
		sMemory = "";
		bAVDSubmit = false;
		bDontSetTools = false;

		createGui();

		RefreshProperties();

		// Check 'tools' directory..
        // If 'cmdline-tools' is available use that..		
		StringBuffer sB = new StringBuffer();
		sB.append(sSDKPath);
		sB.append("/cmdline-tools");
		File tFile = new File(sB.toString());
		String[] dirList;
		String sLastDir = "";

		// Handle 'cmdline-tools' sub-directories..	
		if (tFile.exists())
		{
		    dirList = tFile.list();
		    if ( (dirList != null) && (dirList.length > 0) )
		    {
                // Use version directory..
                for ( int iZ = 0; iZ < dirList.length; iZ++ )
                {
                    //System.out.println("["+iZ+"]: '"+dirList[iZ]+"'");
                    if ( dirList[iZ].equals("bin") || dirList[iZ].equals("lib") ||      // Ignore 'bin' and 'lib' directories
                            dirList[iZ].equals("NOTICE.txt") || dirList[iZ].equals("source.properties") )
                        continue;
                        
                    sLastDir = dirList[iZ];
                }
/*
                if ( sLastDir == null )
                    System.out.println("sLastDir null");
                else
                    System.out.println("sLastDir: '"+sLastDir+"'");
/**/                
                // Like:  'latest'
                if ( sLastDir.equals("") )
                {
                    //sToolsDir = "cmdline-tools";    // No version directory..
                    sToolsDir = "tools/bin";   // Default..
                    //System.out.println("(1)sToolsDir set: '"+sToolsDir+"'");
                }
                else
                {
                    StringBuffer sB2 = new StringBuffer();
                    sB2.append("cmdline-tools");
                    sB2.append("/");
                    
                    
                    sB2.append(sLastDir);
                    
                    sB2.append("/bin");
                    
                    sToolsDir = sB2.toString();     // Like:  'cmdline-tools/latest'
                    //System.out.println("(2)sToolsDir set: '"+sToolsDir+"'");
                }
		    }
		}
		else
		{
			sB = new StringBuffer();
			sB.append(sSDKPath);
			sB.append("/tools");
			tFile = new File(sB.toString());
			if (tFile.exists())
			{
				sToolsDir = "tools";
				//System.out.println("(3)sToolsDir set: '"+sToolsDir+"'");
			}
		}
		
		//System.out.println("sToolsDir: '"+sToolsDir+"'");		
	} //}}}

	//{{{   GetAVDsBgThread
	@SuppressWarnings("unchecked")
	class GetAVDsBgThread extends Thread
	{
		public void run()
		{
			//System.out.println("\nGetAVDsBgThread run()");
			StringBuffer sb = new StringBuffer();
			String sT = "";
			String sName = "";
			String sDevice = "";
			String sPath = "";
			String sTarget = "";
			String sBasedOn = "";
			int iLoc2 = 0;
			int iLoc3 = 0;
			int iLoc4 = 0;
			int iLoc5 = 0;
			int iLoc6 = 0;
			int iLoc7 = 0;
			int iLoc8 = 0;
			int iStart;
			AVDInfo aVDInfo;

			sb = new StringBuffer();

			if (iOS == LINUX_MAC)
			{
				sb.append("export PATH=${PATH}:");
				sb.append(sSDKPath);
				sb.append("/");
				sb.append(sToolsDir);
				sb.append("/bin");

				sb.append(";export JAVA_HOME=");
				sb.append(sJavaPath);

				sb.append(";export ANDROID_HOME=");
				sb.append(sSDKPath);

				sb.append(";export ANDROID_SDK_ROOT=");
				sb.append(sSDKPath);

				sb.append(";avdmanager ");
				sb.append("list");
			}
			else
			{
				sb.append("cd ");
				//sb.append("SET PATH=");
				sb.append(sSDKPath); // Like: "C:/android-sdk-wind"
				sb.append("/");
				sb.append(sToolsDir);
				sb.append("/bin");
				//sb.append(";%PATH%");

				sb.append("&&SET JAVA_HOME=");
				sb.append(sJavaPath);

				sb.append("&&SET ANDROID_HOME=");
				sb.append(sSDKPath);

				sb.append("&&SET ANDROID_SDK_ROOT=");
				sb.append(sSDKPath);

				sb.append("&&avdmanager ");
				sb.append("list");
				sb.append("\n");
			}

			//System.out.println("sb: '"+sb.toString()+"'");
			bCommandFinished = false;
			//commandRequestLatch = new CountDownLatch(1);
			sInternalCommand = sb.toString();
			commandBgThread = new CommandBgThread();
			commandBgThread.start();

			// Seems to need some more time..	
			// Wait for Thread to finish..
			while ( true )
			{
				try
				{
					Thread.sleep(500);
				}
				catch (InterruptedException ie)
				{
				}

				if ( bCommandFinished )
					break;
			}

			//System.out.println("Dropped out");
/*                
            // Wait for Thread to finish..
            try
            {
                commandRequestLatch.await();
            }
            catch (InterruptedException ie)
            {
            }
/**/

/*
            if ( commandResultS == null )
                System.out.println("commandResultS null");
            else
                System.out.println("commandResultS: '"+commandResultS+"'");
/**/

			if ((commandResultS != null) && (commandResultS.length() > 0))
			{
				AVDsAr = new ArrayList();

				// Get to start..
				iLoc2 = commandResultS.indexOf("Virtual Devices:");
				if (iLoc2 != -1)
				{
				    // End: 'The following Android Virtual Devices could not be loaded:'
					iLoc8 = commandResultS.indexOf("The following", iLoc2);

					while (true)
					{
						//System.out.println("-----------------------------------");
						aVDInfo = new AVDInfo();
						iLoc4 = commandResultS.indexOf("Name:", iLoc2);

						if ( (iLoc8 != -1) && (iLoc4 >= iLoc8) )
							break;    // Past the end

						if ( iLoc4 != -1 )    // "Name:"
						{
							iLoc4 += 6;
							iStart = iLoc4;
							iLoc7 = commandResultS.indexOf((int) 0x0a, iLoc4);
							if (iLoc7 != -1)
							{
								sName = commandResultS.substring(iStart, iLoc7);
								sName = sName.trim();
								//System.out.println("sName: '"+sName+"'");
								aVDInfo.sName = sName;
							}
						}
						else
						    break;

						iLoc3 = commandResultS.indexOf("---", iLoc2);
						
						if ( (iLoc2 + 16) < commandResultS.length() )
						{
                            iLoc4 = commandResultS.indexOf("Device:", iLoc2 + 16);    // Virtual Devices:
                            //if (( iLoc4 < iLoc3 ) && (iLoc4 != -1))
                            //if (( (iLoc3 != -1) && (iLoc4 < iLoc3) ) && (iLoc4 != -1))
                            if ( iLoc4 != -1 )
                            {
                                iLoc4 += 8;
                                iStart = iLoc4;
                                iLoc6 = commandResultS.indexOf("Path:", iLoc4);
                                if (iLoc6 != -1)
                                {
                                    sDevice = commandResultS.substring(iStart, iLoc6);
                                    sDevice = sDevice.trim();
                                    //System.out.println("sDevice: '"+sDevice+"'");
                                    aVDInfo.sDevice = sDevice;
                                }
                            }
                        }

						iLoc4 = commandResultS.indexOf("Path:", iLoc2);
						if (iLoc4 != -1)
						{
							iLoc4 += 6;
							iStart = iLoc4;
							iLoc6 = commandResultS.indexOf("Target:", iLoc4);
							if (iLoc6 != -1)
							{
								sPath = commandResultS.substring(iStart, iLoc6);
								sPath = sPath.trim();
								//System.out.println("sPath: '"+sPath+"'");
								aVDInfo.sPath = sPath;
							}
						}

						iLoc4 = commandResultS.indexOf("Target:", iLoc2);
						if (iLoc4 != -1)
						{
							iLoc4 += 8;
							iStart = iLoc4;
							iLoc6 = commandResultS.indexOf("Based on", iLoc4);
							if (iLoc6 != -1)
							{
								sTarget = commandResultS.substring(iStart, iLoc6);
								sTarget = sTarget.trim();
								//System.out.println("sTarget: '"+sTarget+"'");
								aVDInfo.sTarget = sTarget;
							}
						}

						iLoc4 = commandResultS.indexOf("Based on:", iLoc2);
						if (iLoc4 != -1)
						{
							iLoc4 += 10;
							iStart = iLoc4;
							iLoc7 = commandResultS.indexOf((int) 0x0a, iLoc4);
							if (iLoc7 != -1)
							{
								sBasedOn = commandResultS.substring(iStart, iLoc7);
								sBasedOn = sBasedOn.trim();
								//System.out.println("sBasedOn: '"+sBasedOn+"'");
								aVDInfo.sBasedOn = sBasedOn;
							}
						}

						// Next..
						//System.out.println("(Next)iLoc4: "+iLoc4);
						if ( iLoc4 == -1 )
						{
						    // No next tag..
						    // Force it to fail..
						    AVDsAr = new ArrayList();
						    break;
						}
						else
						{
                            if ( aVDInfo != null )
                                AVDsAr.add((AVDInfo)aVDInfo);
						}
						
						iLoc2 = iLoc4;

					} // End while..

/*					
					if ( AVDsAr == null )
					    System.out.println("AVDsAr null");
					else
					    System.out.println("AVDsAr.size(): "+AVDsAr.size());
/**/

					//System.out.println("Dropped out");
				}
			}

			if (operationRequestLatch != null)
				operationRequestLatch.countDown();

			bGetAVDFinished = true;
			//System.out.println("Exiting GetAVDsBgThread");
		}
	} //}}}

	//{{{   GetDevicesBgThread
	@SuppressWarnings("unchecked")
	class GetDevicesBgThread extends Thread
	{
		public void run()
		{
			//System.out.println("\nGetDevicesBgThread run()");
			StringBuffer sb = new StringBuffer();
			String sT = "";
			int iLoc2 = 0;
			int iLoc3 = 0;
			int iLoc4 = 0;

			sb = new StringBuffer();

			if (iOS == LINUX_MAC)
			{
				sb.append("export PATH=${PATH}:");
				sb.append(sSDKPath);
				sb.append("/");
				sb.append(sToolsDir);
				sb.append("/bin");

				sb.append(";export JAVA_HOME=");
				sb.append(sJavaPath);

				sb.append(";export ANDROID_HOME=");
				sb.append(sSDKPath);

				sb.append(";export ANDROID_SDK_ROOT=");
				sb.append(sSDKPath);

				sb.append(";avdmanager ");
				sb.append("list device");
			}
			else
			{
				sb.append("cd ");
				//sb.append("SET PATH=");
				sb.append(sSDKPath);
				sb.append("/");
				sb.append(sToolsDir);
				sb.append("/bin");
				//sb.append(";%PATH%");

				sb.append("&&SET JAVA_HOME=");
				sb.append(sJavaPath);

				sb.append("&&SET ANDROID_HOME=");
				sb.append(sSDKPath);

				sb.append("&&SET ANDROID_SDK_ROOT=");
				sb.append(sSDKPath);

				sb.append("&&avdmanager ");
				sb.append("list device");
				sb.append("\n");
			}

			commandRequestLatch = new CountDownLatch(1);
			sInternalCommand = sb.toString();
			commandBgThread = new CommandBgThread();
			commandBgThread.start();

			// Wait for Thread to finish..
			try
			{
				commandRequestLatch.await();
			}
			catch (InterruptedException ie)
			{}

/*		    
            if ( commandResultS == null )
                System.out.println("commandResultS null");
            else
                System.out.println("commandResultS: '"+commandResultS+"'");
/**/

			if ((commandResultS != null) && (commandResultS.length() > 0))
			{
				// Get to start..
				DevicesAr = new ArrayList();
				iLoc2 = 0;

				while (true)
				{
					iLoc2 = commandResultS.indexOf("id:", iLoc2);
					if (iLoc2 != -1)
					{
						iLoc3 = commandResultS.indexOf((int) 0x22, iLoc2);
						if (iLoc3 != -1)
						{
							iLoc4 = commandResultS.indexOf((int) 0x22, iLoc3 + 1);
							if (iLoc4 != -1)
							{
								sT = commandResultS.substring(iLoc3 + 1, iLoc4);
								sT = sT.trim();
								//System.out.println("(Add): '"+sT+"'");
								DevicesAr.add((String) sT);
							}
						}
					}
					else
						break;

					iLoc2 += 3; // Next..
				} // End while..              
			}

			if (operationRequestLatch != null)
				operationRequestLatch.countDown();

		}
	} //}}}

	//{{{   AVDsThread
	class AVDsThread extends Thread
	{
		public void run()
		{
		    //System.out.println("\nAVDsThread run()");
			AVDInfo aVDInfo;
			int iSz = 0;
			int iLength = 0;
			String[] tSa;
			String sPath = "";

			RefreshProperties();

			bHideOutput = true;
			bGetAVDFinished = false;
			//operationRequestLatch = new CountDownLatch(1);
			getAVDsBgThread = new GetAVDsBgThread();
			getAVDsBgThread.start();

			while (true)
			{
				try
				{
					Thread.sleep(500);
				}
				catch (InterruptedException ie)
				{}

				if ( bGetAVDFinished )
					break;
			}

/*            
            // Wait for Thread to finish..
            try
            {
                operationRequestLatch.await();
            }
            catch (InterruptedException ie)
            {
            }
/**/

			if ((AVDsAr != null) && (AVDsAr.size() > 0))
			{
				iAdvSelectedIndex = 0;

				avdTextArea = new JTextArea();
				avdTextArea.setRows(4);
				avdTextArea.setColumns(5);
				avdTextArea.setLineWrap(true);
				avdTextArea.setEditable(false);

				avdsFrame = new JFrame();
				avdsFrame.setLayout(new BorderLayout());
				avdsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				avdsFrame.setTitle("AVDs");

				avdScrollPane = new JScrollPane();

				iSz = AVDsAr.size();
				tSa = new String[iSz];
				for (int iJ = 0; iJ < AVDsAr.size(); iJ++)
				{
					aVDInfo = (AVDInfo) AVDsAr.get(iJ);
					sPath = aVDInfo.sPath;
					// sPath was null

                    iLength = 0;
                    if ( (sPath != null) && (sPath.length() > 0) )				
                        iLength = sPath.length();
                    
					if (iLength > iLongest)
						iLongest = iLength;

					tSa[iJ] = aVDInfo.sName;
				}

				avdJList = new JList(tSa);
				avdJList.setFont(new Font("Monospaced", Font.BOLD, 12));
				avdJList.setVisibleRowCount(5);
				avdJList.addListSelectionListener(createSelectionListener);
				avdJList.setSelectedIndex(0);

				avdScrollPane.getViewport().setView(avdJList);

				bHideOutput = false;
				AVDsDialog();
			}
			else
			{
				// No ADVs found, put up Dialog..
				JOptionPane.showMessageDialog(
					mainJFrame,
					//"No AVDs found.",
					//"No AVDs, without errors, found.",
					"Data error or no AVDs found.",
					"AVDs",
					JOptionPane.ERROR_MESSAGE);
			}
		}
	} //}}}

	//{{{   PackagesThread	 
	class PackagesThread extends Thread
	{
		public void run()
		{
			//System.out.println("\nPackagesThread run()");
			bHideOutput = true;
			RefreshProperties();
			
			//operationRequestLatch = new CountDownLatch(1);
			bGetPackagesFinished = false;
			getPackagesBgThread = new GetPackagesBgThread();
			getPackagesBgThread.start();

			while (true)
			{
				try
				{
					Thread.sleep(750);
				}
				catch (InterruptedException ie)
				{}

				if ( bGetPackagesFinished )
					break;
			}

/*			
			// Wait for Thread to finish..
			try
			{
				operationRequestLatch.await();
			}
			catch (InterruptedException ie)
			{}
/**/

			bHideOutput = false;
/*			
			if ( PackageAr == null )
			    System.out.println("PackageAr null");
			else
			    System.out.println("PackageAr.size(): "+PackageAr.size());
/**/
			if ((PackageAr != null) && (PackageAr.size() > 0))
			{
				packageDialog();
			}
			else
			{
				// No Packages found, put up Dialog..
				JOptionPane.showMessageDialog(
					mainJFrame,
					"No Packages found.\nYour firewall may be blocking commands from running.",
					"Packages",
					JOptionPane.ERROR_MESSAGE);
			}
			
			//System.out.println("Exiting PackagesThread");
		}
	} //}}}

	//{{{   CreateThread
	class CreateThread extends Thread
	{
		public void run()
		{
			RefreshProperties();

			bHideOutput = true;
			operationRequestLatch = new CountDownLatch(1);
			getSystemImagesBgThread = new GetSystemImagesBgThread();
			getSystemImagesBgThread.start();

			// Wait for Thread to finish..
			try
			{
				operationRequestLatch.await();
			}
			catch (InterruptedException ie)
			{}

			bHideOutput = true;
			operationRequestLatch = new CountDownLatch(1);
			getDevicesBgThread = new GetDevicesBgThread();
			getDevicesBgThread.start();

			// Wait for Thread to finish..
			try
			{
				operationRequestLatch.await();
			}
			catch (InterruptedException ie)
			{}

			bHideOutput = false;
			createAVD();
		}
	} //}}}

	//{{{   GetPackagesBgThread
	@SuppressWarnings("unchecked")
	class GetPackagesBgThread extends Thread
	{
		public void run()
		{
			//System.out.println("\nGetPackagesBgThread run()");
			StringBuffer sb = new StringBuffer();
			String sT = "";
			String sT2 = "";
			String sPackage = "";
			String sInstalled = "";
			String sUpdate = "";
			String sVersion = "";
			String sUpdateVersion = "";
			String sEmulatorEntry = "";
			String sToolsEntry = "";
			StringBuffer sB;
			boolean bFoundUpdates = false;
			boolean bFoundUpdate;
			boolean bAddEmulator = false;
			boolean bAddTools = false;
			boolean bDoAdd = false;
			int iLoc2 = 0;
			int iLoc3 = 0;
			int iLoc4 = 0;
			int iLoc5 = 0;
			int iLoc6;
			int iLoc7 = 0;
			int iLoc8 = 0;
			int iLoc10 = 0;
			int iLoc11 = 0;
			int iLoc12 = 0;
			int iLoc14 = 0;
			int iLoc15 = 0;
			int iLocInstalledPackages = 0;    // 3
			int iLocInstalledObsolete = 0;    // 14
			int iLocAvailablePackages = 0;    // 5
			int iLocAvailableObsolete = 0;    // 10
			int iLocAvailableUpdates = 0;    // 4
			
			int iStart = 0;
			int iStart2 = 0;
			int iCol = 0;
			int iCount;
			int iTotal = 0;
			int iPrevTotal = 0;
			int iM = 0;
			int iPkgLen = 0;
			iPrevLength = 0;
			byte[] bOutAr = null;
			byte[] bAr = {(byte) 0x0a, (byte) 0x20, (byte) 0x20};
			String sStart = new String(bAr);

			RefreshProperties();			
			
            // Check 'tools' directory..
            // If 'cmdline-tools' is available use that..		
            sB = new StringBuffer();
/*            
            if ( sSDKPath == null )
                System.out.println("sSDKPath null");
            else
                System.out.println("sSDKPath: '"+sSDKPath+"'");
/**/                    
            sB.append(sSDKPath);
            sB.append("/cmdline-tools");
            File tFile = new File(sB.toString());
            File tFile2;
            String[] dirList;
            String sLastDir = "";
            StringBuffer sTb;
            byte[] buf;
            
            InstalledAr = new ArrayList();
    
            // Handle 'cmdline-tools' sub-directories..	
            if (tFile.exists())
            {
                dirList = tFile.list();
                if ( (dirList != null) && (dirList.length > 0) )
                {
                    // Use version directory..
                    for ( int iZ = 0; iZ < dirList.length; iZ++ )
                    {
                        //System.out.println("["+iZ+"]: '"+dirList[iZ]+"'");
                        if ( dirList[iZ].equals("bin") || dirList[iZ].equals("lib") ||      // Ignore 'bin' and 'lib' directories
                                dirList[iZ].equals("NOTICE.txt") || dirList[iZ].equals("source.properties") )
                            continue;

                        if ( dirList[iZ].equals("latest") )
                        {
                            if ( InstalledAr != null )
                                InstalledAr.add((String)"cmdline-tools;latest");
                        }
                        else
                        {
                            sTb = new StringBuffer();
                            sTb.append(sB.toString());
                            sTb.append("/");
                            sTb.append(dirList[iZ]);
                            sTb.append("/source.properties");
                            // Like:  'C:/android-sdk-Z/cmdline-tools/4.0/source.properties'
                            //System.out.println("sTb: '"+sTb.toString()+"'");
                            
                            tFile2 = new File(sTb.toString());
                            if ( tFile2.exists() )
                            {
                                buf = readFile(1024, sTb.toString());
                                if ( (buf != null) && (buf.length > 0) )
                                {
                                    sT = new String(buf);
                                    //System.out.println("sT: '"+sT+"'");
                                    iLoc2 = sT.indexOf("Pkg.Path");
                                    if ( iLoc2 != -1 )
                                    {
                                        iLoc3 = sT.indexOf((int)0x0a, iLoc2);
                                        if ( iLoc3 != -1 )
                                        {
                                            sT2 = sT.substring(iLoc2 + 9, iLoc3);
                                            sT2 = sT2.trim();
                                            if ( InstalledAr != null )
                                            {
                                                //System.out.println("((C)Add)sInstalled: '"+sT2+"'");
                                                InstalledAr.add((String)sT2);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                           
                        sLastDir = dirList[iZ];
                        
                    }   // End for..
/*    
                    if ( sLastDir == null )
                        System.out.println("sLastDir null");
                    else
                        System.out.println("sLastDir: '"+sLastDir+"'");
/**/ 

                    // Note:
                    // It adds '/bin' last..
                    // Figure out bin path for tools / cmdline-tools..
                    
                    if ( sLastDir.equals("") )
                    {
                        // No other directories..
                        sToolsDir = "cmdline-tools";
                        //System.out.println("(1)sToolsDir set: '"+sToolsDir+"'");
                    }
                    else
                    {
                        StringBuffer sB2 = new StringBuffer();
                        sB2.append("cmdline-tools");
                        sB2.append("/");
                        sB2.append(sLastDir);   // Like 'latest' or '3.0'..
                        
                        sToolsDir = sB2.toString();     // Like:  'cmdline-tools/latest'
                        //System.out.println("(2)sToolsDir set: '"+sToolsDir+"'");
                    }
                }
            }
            else
            {
                // No 'comdline-tools'..
                sB = new StringBuffer();
                sB.append(sSDKPath);
                sB.append("/tools");
                tFile = new File(sB.toString());
                if (tFile.exists())
                {
                    sToolsDir = "tools";
                    //System.out.println("(3)sToolsDir set: '"+sToolsDir+"'");
                }
            }
/*            
            if ( sToolsDir == null )
                System.out.println("sToolsDir null");
            else
                System.out.println("sToolsDir: '"+sToolsDir+"'");
/**/			
			
			
			sb = new StringBuffer();

			if (iOS == LINUX_MAC)
			{
				sb.append("export PATH=${PATH}:");
				sb.append(sSDKPath);
				sb.append("/");
				sb.append(sToolsDir);
				sb.append("/bin");

				sb.append(";export JAVA_HOME=");
				sb.append(sJavaPath);

				sb.append(";export ANDROID_HOME=");
				sb.append(sSDKPath);

				sb.append(";export ANDROID_SDK_ROOT=");
				sb.append(sSDKPath);

				sb.append(";sdkmanager --list ");

                // We always include Obsolete to make managing
                // things easier..
                sb.append("--include_obsolete ");

				if ( (sUseHTTPS != null) && (sUseHTTPS.equals("false")) )
				{
					sb.append("--no_https ");
				}

				if ( (sPackageChannel != null) && (sPackageChannel.length() > 0) )
				{
                    sPackageChannel = sPackageChannel.trim();
                    if (sPackageChannel.equals("stable"))
                        sb.append("--channel=0 ");
                    else if (sPackageChannel.equals("beta"))
                        sb.append("--channel=1 ");
                    else if (sPackageChannel.equals("dev"))
                        sb.append("--channel=2 ");
                    else if (sPackageChannel.equals("canary"))
                        sb.append("--channel=3 ");
                }

				sb.append("--sdk_root=");
				sb.append(sSDKPath);

			}
			else
			{
				sb.append("cd ");
				//sb.append("SET PATH=");
				//System.out.println("sSDKPath: '"+sSDKPath+"'");
				//System.out.println("sToolsDir: '"+sToolsDir+"'");
				
				sb.append(sSDKPath);
				sb.append("/");
				sb.append(sToolsDir);
				sb.append("/bin");
				//sb.append(";%PATH%");
				
				sb.append("&&SET JAVA_HOME=");
				sb.append(sJavaPath);
				
				sb.append("&&SET ANDROID_HOME=");
				sb.append(sSDKPath);

				sb.append("&&SET ANDROID_SDK_ROOT=");
				sb.append(sSDKPath);

				sb.append("&&sdkmanager --list ");

                // We always include Obsolete to make managing
                // things easier..
                sb.append("--include_obsolete ");

				if ( (sUseHTTPS != null) && (sUseHTTPS.equals("false")) )
				{
					sb.append("--no_https ");
				}
				
				if ( (sPackageChannel != null) && (sPackageChannel.length() > 0) )
				{
				    sPackageChannel = sPackageChannel.trim();
                    if (sPackageChannel.equals("stable"))
                        sb.append("--channel=0 ");
                    else if (sPackageChannel.equals("beta"))
                        sb.append("--channel=1 ");
                    else if (sPackageChannel.equals("dev"))
                        sb.append("--channel=2 ");
                    else if (sPackageChannel.equals("canary"))
                        sb.append("--channel=3 ");
                }

				sb.append("--sdk_root=");
				sb.append(sSDKPath);

				sb.append("\n");
			}

			//System.out.println("sb: '"+sb.toString()+"'");
			//commandRequestLatch = new CountDownLatch(1);
			bCommandFinished = false;
			sInternalCommand = sb.toString();
			commandBgThread = new CommandBgThread();
			commandBgThread.start();

			// Give it some more time..	
			// Wait for Thread to finish..
			while (true)
			{
				try
				{
					Thread.sleep(750);
				}
				catch (InterruptedException ie)
				{}

				if (bCommandFinished)
					break;
			}
			

/*
            // Wait for Thread to finish..
            try
            {
                commandRequestLatch.await();
            }
            catch (InterruptedException ie)
            {
            }
/**/

/*
            if ( commandResultS == null )
                System.out.println("commandResultS null");
            else
                System.out.println("commandResultS: '"+commandResultS+"'");
/**/

			if ( (commandResultS != null) && (commandResultS.length() > 0) )
			{
				PackageAr = new ArrayList();
				//InstalledAr = new ArrayList();
				UpdateAr = new ArrayList();
				AvailableObsoleteAr = new ArrayList();

				iLocInstalledPackages = commandResultS.indexOf("Installed packages:");
				iLocInstalledObsolete = commandResultS.indexOf("Installed Obsolete Packages:");
				iLocAvailablePackages = commandResultS.indexOf("Available Packages:");
				iLocAvailableObsolete = commandResultS.indexOf("Available Obsolete Packages:");
				iLocAvailableUpdates = commandResultS.indexOf("Available Updates:");

                if ( iLocAvailablePackages == -1 )
                {
                    // Error..
                    if (operationRequestLatch != null)
                        operationRequestLatch.countDown();
                    
                    return;
                }
				
				if ( iLocAvailableUpdates != -1 )
					bFoundUpdates = true;
				else
				    iLocAvailableUpdates = commandResultS.length();

                /**
                 * Available Updates
                 */
				    
				// Get available updates..
				if ( bFoundUpdates )
				{
				    //System.out.println("Available Updates");
					iLoc8 = iLocAvailableUpdates;
					for ( int iX = 0; iX < 3; iX++ )
					{
						iLoc8 = commandResultS.indexOf(sStart, iLoc8);
						iLoc8 += 2; // Next..
					}

					iLoc7 = iLoc8;
					for ( ; Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );
					iStart = iLoc7;

					while (true)
					{
					    //System.out.println("--TOP--");
						for (; !Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++);
						sUpdate = commandResultS.substring(iStart, iLoc7);
						//System.out.println("sUpdate: '"+sUpdate+"'");
						
                        for ( ; Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );
                        iLoc7 += 2;
                        iStart2 = iLoc7;
                        
                        for ( ; !Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );
                        sVersion = commandResultS.substring(iStart2, iLoc7);
                        sVersion = sVersion.trim();
                        //System.out.println("sVersion: '"+sVersion+"'");
						
                        for ( ; Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );
                        iLoc7 += 2;
                        iStart2 = iLoc7;
                        
                        for ( ; !Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );
                        sUpdateVersion = commandResultS.substring(iStart2, iLoc7);
                        sUpdateVersion = sUpdateVersion.trim();
                        //System.out.println("sUpdateVersion: '"+sUpdateVersion+"'");
                        sB = new StringBuffer();
                        sB.append(sUpdate);
						
                        iCol = 8;
                        while ( true )
                        {
                            //System.out.println("(A)--TOP--");
                            //System.out.println("iPrevLength: "+iPrevLength);
                            //System.out.println("sUpdate.length(): '"+sUpdate.length()+"'");
                            if ( sUpdate.length() < iPrevLength )
                            {
                                for ( int iX = 0; iX < iPrevLength; iX++ )
                                    sB.append(SPACING);
                                
                                break;
                            }
                            else
                            {
                                if ( sUpdate.length() >= iCol )
                                    ;
                                else
                                {
                                    iPrevLength = iCol - sUpdate.length();
                                    for ( int iX = 0; iX < (iCol - sUpdate.length()); iX++ )
                                        sB.append(SPACING);
                                    
                                    break;
                                }
                            }
                            
                            iCol += 8;
                        }
                        
						sB.append(sVersion);
						sB.append("    ");
						sB.append(sUpdateVersion);
						
						UpdateAr.add((String)sB.toString());
						//System.out.println("(UpdateAr.add()): '"+sB.toString()+"'");
/*						
						if ( sUpdate.equals("emulator") )
						{
						    sEmulatorEntry = sB.toString();
						    //System.out.println("sEmulatorEntry: '"+sEmulatorEntry+"'");
						}
/**/
						// Next..
						iLoc7 = commandResultS.indexOf(sStart, iLoc7); // 0x0a 0x20 0x20
						if (iLoc7 == -1)
							break;

						for ( ; Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );

						if ( iLoc7 >= commandResultS.length() )
							break;

						iStart = iLoc7;
					} // End while..
				}

				//System.out.println("\n");
				
                /**
                 * Installed Packages
                 */
				
                // Note:
                // There is a case where Installed had:  'build-tools;30.0.0-rc2 | 30.0.0 rc2'
                // but it wasn't in the Available Packages or in Obsolete..
                
				// Get installed packages..                
				//iLoc6 = commandResultS.indexOf("Location", iLoc3); // Past 'Installed packages:'..
				iLoc6 = commandResultS.indexOf("Location", iLocInstalledPackages); // Past 'Installed packages:'..
				if (iLoc6 != -1)
				{
				    //System.out.println("Installed Packages");
					iLoc7 = iLoc6;
					for ( int iJ = 0;; iJ++ )
					{
					    //System.out.println("==TOP==");
						iLoc7 = commandResultS.indexOf(sStart, iLoc7);
						if ( iJ == 0 )
						{
							// Skip first one, '-------'..
							iLoc7 += 2;
							continue;
						}

						if ( (iLocInstalledObsolete != -1) && (iLoc7 >= iLocInstalledObsolete) )
						    break;

						if ( (iLocAvailablePackages != -1) && (iLoc7 >= iLocAvailablePackages) )
							break;

						if ( iLoc7 != -1 )
						{
							// Skip to start..
							iLoc7 += 3;
							iStart = iLoc7;

							for ( ; !Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );
							sInstalled = commandResultS.substring(iStart, iLoc7);
							//System.out.println("sInstalled: '"+sInstalled+"'");
							
                            // Get version..
                            for ( ; Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );
                            iLoc7 += 2;
                            iStart2 = iLoc7;
                            
                            for ( ; !Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );
                            sVersion = commandResultS.substring(iStart2, iLoc7);
                            sVersion = sVersion.trim();
                            //System.out.println("sVersion: '"+sVersion+"'");
                            sB = new StringBuffer();
                            sB.append(sInstalled);
                            
                            iCol = 8;
                            while ( true )
                            {
                                //System.out.println("(B)--TOP--");
                                //System.out.println("iPrevLength: "+iPrevLength);
                                //System.out.println("sInstalled.length(): '"+sInstalled.length()+"'");
                                
                                if ( sInstalled.length() < iPrevLength )
                                {
                                    for ( int iX = 0; iX < iPrevLength; iX++ )
                                        sB.append(SPACING);
                                    
                                    break;
                                }
                                else
                                {
                                    if ( sInstalled.length() >= iCol )
                                        ;
                                    else
                                    {
                                        iPrevLength = iCol - sInstalled.length();
                                        for ( int iX = 0; iX < (iCol - sInstalled.length()); iX++ )
                                            sB.append(SPACING);
                                        
                                        break;
                                    }
                                }
                                
                                iCol += 8;
                                
                            }   // End while..
                            
                            sB.append(sVersion);
                            sInstalled = sB.toString();
/**/                            
                            
                            InstalledAr.add((String)sInstalled);
                            //System.out.println("(InstalledAr.add()): '"+sInstalled+"'");
                            
						}
					} // End while..
				}
				
				//System.out.println("\n");

                /**
                 * Installed Obsolete Packages
                 */
				
				// Get Installed Obsolete Packages..
				// Only available after you select
				// one of the Obsolete Packages..
				String sInstalledObs = "";
				
                if ( iLocInstalledObsolete != -1 )
                {
                    //System.out.println("\nInstalled Obsolete Packages");
                    InstalledObsoleteAr = new ArrayList();
                    
                    iLoc15 = commandResultS.indexOf("Location", iLocInstalledObsolete); // Past 'Installed Obsolete Packages:'..
                    if (iLoc15 != -1)
                    {
                        while ( true )
                        {
                            iLoc15 = commandResultS.indexOf(sStart, iLoc15);
                            if ( Character.isLetter(commandResultS.charAt(iLoc15 + 3)) )
                                break;
                            
                            iLoc15++;    // Next..
                        }
                        
                        iLoc7 = iLoc15 + 3;
                        iStart = iLoc7;
                    }
                    
                    while ( true )
                    {
                        if ( iLoc7 == -1 )
                            break;
                        
                        //System.out.println("(TOP)"+commandResultS.substring(iLoc7, iLoc7 + 10));
                        for ( ; !Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );
                        sInstalledObs = commandResultS.substring(iStart, iLoc7);
                        //System.out.println("sInstalledObs: '"+sInstalledObs+"'");

                        // Get version..
                        for ( ; Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );
                        iLoc7 += 2;
                        iStart2 = iLoc7;
                        
                        for ( ; !Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );
                        sVersion = commandResultS.substring(iStart2, iLoc7);
                        sVersion = sVersion.trim();
                        //System.out.println("sVersion: '"+sVersion+"'");
                        sB = new StringBuffer();
                        sB.append(sInstalledObs);
                        
                        iCol = 8;
                        while ( true )
                        {
                            //System.out.println("(B)--TOP--");
                            //System.out.println("iPrevLength: "+iPrevLength);
                            //System.out.println("sInstalled.length(): '"+sInstalled.length()+"'");
                            
                            if ( sInstalledObs.length() < iPrevLength )
                            {
                                for ( int iX = 0; iX < iPrevLength; iX++ )
                                    sB.append(SPACING);
                                
                                break;
                            }
                            else
                            {
                                if ( sInstalledObs.length() >= iCol )
                                    ;
                                else
                                {
                                    iPrevLength = iCol - sInstalledObs.length();
                                    for ( int iX = 0; iX < (iCol - sInstalledObs.length()); iX++ )
                                        sB.append(SPACING);
                                    
                                    break;
                                }
                            }
                            
                            iCol += 8;
                            
                        }   // End while..
                        
                        sB.append(sVersion);
                        //System.out.println("(Add)sB: '"+sB.toString()+"'");
                        
                        //InstalledObsoleteAr.add((String)sInstalled);
                        InstalledObsoleteAr.add((String)sB.toString());
                        
/*                        
                        if ( (sIncludeObsolete != null) && (sIncludeObsolete.equals("false")) )
                        {
                            //System.out.println("(1PackageAr.add()): '"+sInstalled+"'");
                            PackageAr.add((String)sInstalled);
                        }
/**/    
                        // Next..
                        iLoc7 = commandResultS.indexOf(sStart, iLoc7); // 0x0a 0x20 0x20
                        if ( iLoc7 != -1 )
                        {
                            if ( iLoc7 >= iLocAvailablePackages ) // 'Available Packages:'
                                break;

                            for ( ; Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );
                            iStart = iLoc7;
                        }
                    }   // End while..
                }

                
                /**
                 * Available Packages
                 */
                
				// Get available packages..
				//System.out.println("\n=========================================");
				//System.out.println("Available Packages");
				iLoc6 = commandResultS.indexOf("Description", iLocAvailablePackages); // From 'Available Packages:'
				if ( iLoc6 != -1 )
				{
					iLoc8 = commandResultS.indexOf("add-ons;", iLoc6);
					if ( iLoc8 != -1 )
					{
						iLoc7 = iLoc8;
						iStart = iLoc7;
						while (true)
						{
						    //System.out.println("--TOP--");
						    if ( (iLocAvailableObsolete != -1) && (iLoc7 >= iLocAvailableObsolete) )
						        break;
						        
						    bDoAdd = true;
						    //System.out.println("(TOP)"+commandResultS.substring(iLoc7, iLoc7 + 10));
						    // Only show Obsolete Packages if actually selected..
                            if ( (iLocAvailableObsolete != -1) && (iLoc7 >= iLocAvailableObsolete) )    // iLoc10 @  Available Obsolete Packages:
                            {
                                if ( (sIncludeObsolete != null) && (sIncludeObsolete.equals("true")) )
                                {
                                    // Skip between Available Obsolete Packages:..
                                    iLoc12 = commandResultS.indexOf("Description", iLoc7);
                                    if ( iLoc12 != -1 )
                                    {
                                        while ( true )
                                        {
                                            iLoc12 = commandResultS.indexOf(sStart, iLoc12);
                                            if ( Character.isLetter(commandResultS.charAt(iLoc12 + 3)) )
                                                break;
                                            
                                            iLoc12++;    // Next..
                                        }
                                        
                                        iLoc7 = iLoc12 + 3;
                                        iStart = iLoc7;
                                    }
                                }
                                else
                                    bDoAdd = false;
                            }
						    
							for ( ; !Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );
							sPackage = commandResultS.substring(iStart, iLoc7);
							//System.out.println("sPackage: '"+sPackage+"'");
							
                            // Get version..
                            for ( ; Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );
                            iLoc7 += 2;
                            iStart2 = iLoc7;
                            
                            for ( ; !Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );
                            sVersion = commandResultS.substring(iStart2, iLoc7);
                            sVersion = sVersion.trim();
                            //System.out.println("sVersion: '"+sVersion+"'");
                            sB = new StringBuffer();
                            sB.append(sPackage);
                            //System.out.println("\nsPackage.length(): "+sPackage.length());
                            
                            iCol = 0;
                            int iLenDif = 0;
                            while ( true )
                            {
                                //System.out.println("(D)--TOP--");
                                //System.out.println("iPrevLength: "+iPrevLength);
                                //System.out.println("sPackage.length(): '"+sPackage.length()+"'");

                                if ( iCol > sPackage.length() )
                                {
                                    if ( (sPackage.length() + 3) > iCol )
                                        ;
                                    else
                                    {
                                        for ( int iX = 0; iX < (iCol - sPackage.length()); iX++ )
                                            sB.append(SPACING);
                                        
                                        iPrevLength = sPackage.length() + (iCol - sPackage.length());
                                        iPkgLen = sPackage.length();
                                        
                                        break;
                                    }
                                }
                                
                                iCol += 8;
                                    
                            }   // End while..
                            
                            sB.append(sVersion);

                            //System.out.println("((A)PackageAr.add()): '"+sB.toString()+"'");
                            PackageAr.add((String)sB.toString());
                            
							// Next..
							iLoc7 = commandResultS.indexOf(sStart, iLoc7); // 0x0a 0x20 0x20
							if ( iLoc7 == -1 )
								break;

							for ( ; Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );

							if ( iLoc7 >= iLocAvailableUpdates ) // 'Available Updates:'
								break;

							iStart = iLoc7;
						}    // End while..
					}
				}
				
				//System.out.println("\n");

                /**
                 * Available Obsolete Packages
                 */
				
				String sAvblObs = "";
				
                if ( iLocAvailableObsolete != -1 )
                {
                    AvailableObsoleteAr = new ArrayList();
                    
                    //iLoc15 = commandResultS.indexOf("Location", iLocInstalledObsolete); // Past 'Installed Obsolete Packages:'..
                    iLoc15 = commandResultS.indexOf("Description", iLocAvailableObsolete);
                    if (iLoc15 != -1)
                    {
                        while ( true )
                        {
                            iLoc15 = commandResultS.indexOf(sStart, iLoc15);    // 0x0a 0x20 0x20
                            if ( Character.isLetter(commandResultS.charAt(iLoc15 + 3)) )
                                break;
                            
                            iLoc15++;    // Next..
                        }
                        
                        iLoc7 = iLoc15 + 3;
                        iStart = iLoc7;
                    }
                    
                    while ( true )
                    {
                        if ( iLoc7 == -1 )
                            break;
                        
                        //System.out.println("(TOP)"+commandResultS.substring(iLoc7, iLoc7 + 15));
                        for ( ; !Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );
                        sAvblObs = commandResultS.substring(iStart, iLoc7);
                        //System.out.println("(AvailableObsoleteAr.add()): '"+sAvblObs+"'");
                        
                        // Get version..
                        for ( ; Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );
                        iLoc7 += 2;
                        iStart2 = iLoc7;
                        
                        for ( ; !Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );
                        sVersion = commandResultS.substring(iStart2, iLoc7);
                        sVersion = sVersion.trim();
                        //System.out.println("sVersion: '"+sVersion+"'");
                        sB = new StringBuffer();
                        sB.append(sAvblObs);
                        //System.out.println("\nsAvblObs.length(): "+sAvblObs.length());
                        
                        iCol = 0;
                        int iLenDif = 0;
                        
                        while ( true )
                        {
                            //System.out.println("(D)--TOP--");
                            //System.out.println("iPrevLength: "+iPrevLength);
                            //System.out.println("sPackage.length(): '"+sPackage.length()+"'");

                            if ( iCol > sAvblObs.length() )
                            {
                                if ( (sAvblObs.length() + 3) > iCol )
                                    ;
                                else
                                {
                                    for ( int iX = 0; iX < (iCol - sAvblObs.length()); iX++ )
                                        sB.append(SPACING);
                                    
                                    iPrevLength = sAvblObs.length() + (iCol - sAvblObs.length());
                                    iPkgLen = sAvblObs.length();
                                    
                                    break;
                                }
                            }
                            
                            iCol += 8;
                                
                        }   // End while..
                        
                        sB.append(sVersion);
                        
                        
                        //AvailableObsoleteAr.add((String)sAvblObs);
                        AvailableObsoleteAr.add((String)sB.toString());
                        
                        // Next..
                        iLoc7 = commandResultS.indexOf(sStart, iLoc7); // 0x0a 0x20 0x20
                        if ( iLoc7 != -1 )
                        {
                            if ( iLoc7 >= iLocAvailableUpdates )
                                break;

                            for ( ; Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );
                            iStart = iLoc7;
                        }
                    }   // End while..
                }

				
                // Resolve Updates..
                String sUpdate2 = "";
                boolean bMatchedUpdate;
                boolean bRemove = false;
                
                if ( (UpdateAr != null) && (UpdateAr.size() > 0) )
                {
                    if ( (PackageAr != null) && (PackageAr.size() > 0) )
                    {
                        for ( int iX = 0; iX < UpdateAr.size(); iX++ )
                        {
                            //System.out.println("--TOP-- iX: "+iX);
                            sUpdate2 = (String)UpdateAr.get(iX);
                            iLoc2 = sUpdate2.indexOf(" ");
                            if ( iLoc2 != -1 )
                            {
                                sT = sUpdate2.substring(0, iLoc2);
                                sT = sT.trim();
                            }
                            
                            //System.out.println("sUpdate2: '"+sUpdate2+"'");
                            
                            bMatchedUpdate = false;
                            bRemove = false;
                            
                            int iMatch = 0;

                            //for ( int iZ = 0; iZ < PackageAr.size(); iZ++ )
                            for ( int iZ = 0; iZ < PackageAr.size(); )
                            {
                                //System.out.println("--TOP-- iZ: "+iZ);
                                sT2 = (String)PackageAr.get(iZ);
                                //System.out.println("PackageAr.get(iZ): '"+(String)PackageAr.get(iZ)+"'");
                                
                                
                                iLoc2 = sT2.indexOf(" ");
                                if ( iLoc2 != -1 )
                                {
                                    sT2 = sT2.substring(0, iLoc2);
                                    sT2 = sT2.trim();
                                }
                                
                                //System.out.println("sT: '"+sT+"'");
                                //System.out.println("(get())sT2: '"+sT2+"'");
                                
                                if ( sT.equals(sT2) )
                                {
                                    if ( bRemove )
                                    {
                                        // We hit a duplicate, so remove()..
                                        //System.out.println("Doing remove() on: '"+(String)PackageAr.get(iZ)+"'");
                                        PackageAr.remove(iZ);
                                        bRemove = false;    // Reset..
                                        continue;
                                    }
                                    else
                                    {

                                        bMatchedUpdate = true;
                                        // Update matched entry, replace..
                                        
                                        //System.out.println("Replaced with: '"+sUpdate2+"'");
                                        PackageAr.set(iZ, (String)sUpdate2);
                                        bRemove = true;
                                    }
                                }
                                
                                iZ++;   // Next..
                                
                            }   // End for..
                            
                            //System.out.println("(Dropped out)iMatch: "+iMatch);
                            
                            if ( bMatchedUpdate == false )
                            {
                                // No match, so add..
                                //System.out.println("((B)PackageAr.add()): '"+sUpdate2+"'");
                                PackageAr.add((String)sUpdate2);
                                //System.out.println("Added: '"+sUpdate2+"'");
                            }
                        }   // End for..
                    }
                }
/**/                

                String sIO = "";
                boolean bMatch = false;
/*
                if ( InstalledObsoleteAr == null )
                    System.out.println("InstalledObsoleteAr null");
                else
                    System.out.println("InstalledObsoleteAr.size(): "+InstalledObsoleteAr.size());
/**/                        
                        
                if ( (InstalledObsoleteAr != null) && (InstalledObsoleteAr.size() > 0) )
                {
                    if ( (InstalledAr != null) && (InstalledAr.size() > 0) )
                    {
                        for ( int iX = 0; iX < InstalledObsoleteAr.size(); iX++ )
                        {
                            //System.out.println("--TOP-- iX: "+iX);
                            sIO = (String)InstalledObsoleteAr.get(iX);
                            //System.out.println("sIO: '"+sIO+"'");
                            //sT = sIO;
                            
                            iLoc2 = sIO.indexOf(" ");
                            if ( iLoc2 != -1 )
                            {
                                sT = sIO.substring(0, iLoc2);
                                sT = sT.trim();
                            }
/**/                            
                            //System.out.println("\nsT: '"+sT+"'");
                            
                            bMatch = false;
                            for ( int iZ = 0; iZ < InstalledAr.size(); iZ++ )
                            {
                                sInstalled = (String)InstalledAr.get(iZ);
                                iLoc2 = sInstalled.indexOf(" ");
                                if ( iLoc2 != -1 )
                                {
                                    sT2 = sInstalled.substring(0, iLoc2);
                                    sT2 = sT2.trim();
                                }
                                
                                //System.out.println("sT2: '"+sT2+"'");
                                if ( sT.equals(sT2) )
                                {
                                    //System.out.println("--Match--");
                                    bMatch = true;
                                    break;
                                }
                            }
                            
                            if ( bMatch == false )
                            {
                                //InstalledAr.add((String)sInstalled);
                                InstalledAr.add((String)sIO);
                                //System.out.println("(Installed Obsolete InstalledAr.add())sIO: '"+sIO+"'");
                            }
                        }   // End for..
                    }
                }

                        
                        

                String sInst = "";
                String sPkg = "";
                //boolean bMatch = false;
                
                
                if ( (InstalledAr != null) && (InstalledAr.size() > 0) )
                {
                    if ( (PackageAr != null) && (PackageAr.size() > 0) )
                    {
                        //System.out.println("InstalledAr / PackageAr");
                        for ( int iX = 0; iX < InstalledAr.size(); iX++ )
                        {
                            //System.out.println("--TOP-- iX: "+iX);
                            sInst = (String)InstalledAr.get(iX);
                            iLoc2 = sInst.indexOf(" ");
                            if ( iLoc2 != -1 )
                            {
                                sT = sInst.substring(0, iLoc2);
                                sT = sT.trim();
                            }
                            
                            //System.out.println("\nsT: '"+sT+"'");
                            
                            bMatch = false;
                            for ( int iZ = 0; iZ < PackageAr.size(); iZ++ )
                            {
                                sPkg = (String)PackageAr.get(iZ);
                                iLoc2 = sPkg.indexOf(" ");
                                if ( iLoc2 != -1 )
                                {
                                    sT2 = sPkg.substring(0, iLoc2);
                                    sT2 = sT2.trim();
                                }
                                
                                //System.out.println("(Package)sT2: '"+sT2+"'");
                                if ( sT.equals(sT2) )
                                {
                                    //System.out.println("--Match--");
                                    bMatch = true;
                                    break;
                                }
                            }
                            
                            if ( bMatch == false )
                            {
                                // Installed wasn't in Packages so add..
                                PackageAr.add((String)sInst);
                                //System.out.println("((C)PackageAr.add()): '"+sInst+"'");
                                //System.out.println("(Installs PackageAr.add())sInst: '"+sInst+"'");
                            }
                        }
                        
                        //System.out.println("Dropped out");
                    }
                }

                String sAO = "";
/*
                if ( AvailableObsoleteAr == null )
                    System.out.println("AvailableObsoleteAr null");
                else
                    System.out.println("AvailableObsoleteAr.size(): "+AvailableObsoleteAr.size());
/**/                        
                        
                if ( (AvailableObsoleteAr != null) && (AvailableObsoleteAr.size() > 0) )
                {
                    if ( (PackageAr != null) && (PackageAr.size() > 0) )
                    {
                        for ( int iX = 0; iX < AvailableObsoleteAr.size(); iX++ )
                        {
                            //System.out.println("--TOP-- iX: "+iX);
                            sAO = (String)AvailableObsoleteAr.get(iX);
                            //System.out.println("sAO: '"+sAO+"'");
                            //sT = sAO;
                            
                            iLoc2 = sAO.indexOf(" ");
                            if ( iLoc2 != -1 )
                            {
                                sT = sAO.substring(0, iLoc2);
                                sT = sT.trim();
                            }
/**/                            
                            //System.out.println("\nsT: '"+sT+"'");
                            
                            bMatch = false;
                            for ( int iZ = 0; iZ < PackageAr.size(); iZ++ )
                            {
                                sPkg = (String)PackageAr.get(iZ);
                                iLoc2 = sPkg.indexOf(" ");
                                if ( iLoc2 != -1 )
                                {
                                    sT2 = sPkg.substring(0, iLoc2);
                                    sT2 = sT2.trim();
                                }
                                
                                //System.out.println("sT2: '"+sT2+"'");
                                if ( sT.equals(sT2) )
                                {
                                    //System.out.println("--Match--");
                                    bMatch = true;
                                    break;
                                }
                            }
                            
                            if ( bMatch == false )
                            {
                                // Wasn't in Packages so add..
                                PackageAr.add((String)sAO);
                                //System.out.println("((D)PackageAr.add()): '"+sAO+"'");
                                //System.out.println("(Add)sAO: '"+sAO+"'");
                            }
                        }   // End for..
                        
                        //System.out.println("Dropped out");
                    }
                }
			}

			bGetPackagesFinished = true;
			
			//for ( int iG = 0; iG < PackageAr.size(); iG++ )
			    //System.out.println("PackageAr ["+iG+"]: '"+(String)PackageAr.get(iG)+"'");
			
			//System.out.println("\nExiting GetPackagesBgThread");
			//if ( operationRequestLatch != null )
				//operationRequestLatch.countDown();

		}
	} //}}}
	
	//{{{   GetSystemImagesBgThread
	@SuppressWarnings("unchecked")
	class GetSystemImagesBgThread extends Thread
	{
		public void run()
		{
			//System.out.println("\nGetSystemImagesBgThread run()");
			StringBuffer sb = new StringBuffer();
			String sT = "";
			int iLoc2 = 0;
			int iLoc3 = 0;
			int iLoc4 = 0;
			int iLoc5 = 0;
			int iLoc6 = 0;
			int iStart = 0;

			sb = new StringBuffer();

			if (iOS == LINUX_MAC)
			{
				sb.append("export PATH=${PATH}:");
				sb.append(sSDKPath);
				sb.append("/");
				sb.append(sToolsDir);
				sb.append("/bin");

				sb.append(";export JAVA_HOME=");
				sb.append(sJavaPath);

				sb.append(";export ANDROID_HOME=");
				sb.append(sSDKPath);

				sb.append(";export ANDROID_SDK_ROOT=");
				sb.append(sSDKPath);

				sb.append(";sdkmanager --list ");

				if ( (sUseHTTPS != null) && (sUseHTTPS.equals("false")) )
				{
					sb.append("--no_https ");
				}

				sb.append("--sdk_root=");
				sb.append(sSDKPath);
			}
			else
			{
				sb.append("cd ");
				//sb.append("SET PATH=");
				sb.append(sSDKPath);
				sb.append("/");
				sb.append(sToolsDir);
				sb.append("/bin");
				//sb.append(";%PATH%");

				sb.append("&&SET JAVA_HOME=");
				sb.append(sJavaPath);

				sb.append("&&SET ANDROID_HOME=");
				sb.append(sSDKPath);

				sb.append("&&SET ANDROID_SDK_ROOT=");
				sb.append(sSDKPath);

				sb.append("&&sdkmanager --list ");

				if ( (sUseHTTPS != null) && (sUseHTTPS.equals("false")) )
				{
					sb.append("--no_https ");
				}

				sb.append("--sdk_root=");
				sb.append(sSDKPath);
				sb.append("\n");
			}

			commandRequestLatch = new CountDownLatch(1);
			sInternalCommand = sb.toString();
			commandBgThread = new CommandBgThread();
			commandBgThread.start();

			// Wait for Thread to finish..
			try
			{
				commandRequestLatch.await();
			}
			catch (InterruptedException ie)
			{}

/*			
            if ( commandResultS == null )
                System.out.println("commandResultS null");
            else
                System.out.println("commandResultS: '"+commandResultS+"'");
/**/

			if ((commandResultS != null) && (commandResultS.length() > 0))
			{
				SystemImagesAr = new ArrayList();

				// Get to start..
				iLoc5 = commandResultS.indexOf("Installed packages:");
				iLoc6 = commandResultS.indexOf("Available Packages:");
				iLoc2 = iLoc5;

				while (true)
				{
					iLoc2 = commandResultS.indexOf("system-images;", iLoc2);
					if (iLoc2 != -1)
					{
						// End at:  'Available Packages:'
						if (iLoc2 >= iLoc6)
							break;

						iStart = iLoc2;
						iLoc3 = iLoc2;
						for ( ; !Character.isWhitespace(commandResultS.charAt(iLoc3)); iLoc3++ );

						sT = commandResultS.substring(iStart, iLoc3);
						sT = sT.trim();
						//System.out.println("(Add): '"+sT+"'");
						SystemImagesAr.add((String) sT);
					}
					else
						break;

					iLoc2 += 14; // Next..
				} // End while..              
			}

			if (operationRequestLatch != null)
				operationRequestLatch.countDown();

		}
	} //}}}

	//{{{	RefreshProperties()
	private void RefreshProperties()
	{
	    //System.out.println("RefreshProperties()");
		// Read Properties..
		Properties prop = new Properties();

		try
		{
			prop.load(new FileInputStream("config.properties"));

			// Get Property Values..
			// Note:
			// We need to run processPath() to also
			// remove double quotes for "true" / "false"..
			sSDKPath = processPath(prop.getProperty("android_sdk_path"));
			sJavaPath = processPath(prop.getProperty("java_path"));
			sIncludeObsolete = processPath(prop.getProperty("include_obsolete"));
			sShowCommandResults = processPath(prop.getProperty("show_command_results"));
			sPackageChannel = processPath(prop.getProperty("package_channel"));
			sUseHTTPS = processPath(prop.getProperty("use_https"));
			sUseForce32Bit = processPath(prop.getProperty("use_force_32bit"));
			sGPUMode = processPath(prop.getProperty("gpu_mode"));
			sAccel = processPath(prop.getProperty("accel"));
			sDisableBootAnim = processPath(prop.getProperty("disable_boot_anim"));
			sEngine = processPath(prop.getProperty("engine"));
			sKernel = processPath(prop.getProperty("kernel"));
			sQuickBoot = processPath(prop.getProperty("quick_boot"));
			sMemory = processPath(prop.getProperty("memory"));
			sDisableJNIChecks = processPath(prop.getProperty("disable_jni_checks"));
			sSELinuxSecurity = processPath(prop.getProperty("selinux_security"));
			sDisableVMAcceleration = processPath(prop.getProperty("disable_vm_acceleration"));
			sNoCachePartition = processPath(prop.getProperty("no_cache_partition"));
			sWipeData = processPath(prop.getProperty("wipe_data"));
			
		}
		catch (IOException ioe)
		{
			System.out.println("RefreshProperties() Exception");
			ioe.printStackTrace();
		}

		StringBuffer sB = new StringBuffer();
		sB.append(SPACING);
		sB.append(sSDKPath);
		statusLabel.setText(sB.toString());

	} //}}}

	//{{{   ColorCellRenderer
	@SuppressWarnings("unchecked")
	private class ColorCellRenderer extends DefaultListCellRenderer
	{
		public Component getListCellRendererComponent(
			JList list,
			Object value,
			int index,
			boolean isSelected,
			boolean cellHasFocus)
		{
		    
			String sT = "";
			String sT2 = "";
			int iLoc2 = 0;
			//Color green = new Color((int)0x7c, (int)0xfc, (int)0x00);   // LawnGreen
			Color green = new Color((int) 0x7f, (int) 0xff, (int) 0x00); // Chartreuse
			Color gold = new Color((int) 0xff, (int) 0xd7, (int) 0x00); // Gold
			boolean bUpdateMatch = false;

			Component c = super.getListCellRendererComponent(
				list,
				value,
				index,
				isSelected,
				cellHasFocus);

			//System.out.println("value.toString(): '"+value.toString()+"'");
			//System.out.println("index: "+index);
			String sValue = value.toString();
            iLoc2 = sValue.indexOf(" ");
            if ( iLoc2 != -1 )
            {
                sValue = sValue.substring(0, iLoc2);
                sValue = sValue.trim();
            }
            
            //System.out.println("\nsValue: '"+sValue+"'");
            bUpdateMatch = false;
            if ((UpdateAr != null) && (UpdateAr.size() > 0))
            {
                //System.out.println("UpdateAr.size(): "+UpdateAr.size());
                for ( int iX = 0; iX < UpdateAr.size(); iX++ )
                {
                    sT2 = (String)UpdateAr.get(iX);
                    iLoc2 = sT2.indexOf(" ");
                    if ( iLoc2 != -1 )
                        sT2 = sT2.substring(0, iLoc2);
                    
                    //System.out.println("(UpdateAr.get()): '"+sT2+"'");
                    if (sT2.equals(sValue))
                    {
                        //System.out.println("--Update Match--");
                        c.setBackground(gold);
                        bUpdateMatch = true;
                        break;
                    }
                }
            }
            
/*            
            if ( InstalledAr == null )
                System.out.println("InstalledAr null");
            else
                System.out.println("InstalledAr.size(): "+InstalledAr.size());
/**/            
            

            if ( bUpdateMatch == false )
            {
                if ( (InstalledAr != null) && (InstalledAr.size() > 0) )
                {
                    //System.out.println("InstalledAr.size(): "+InstalledAr.size());
                    for ( int iJ = 0; iJ < InstalledAr.size(); iJ++ )
                    {
                        sT = (String)InstalledAr.get(iJ);
                        //System.out.println("\n(InstalledAr.get()): '"+sT+"'");
                        iLoc2 = sT.indexOf(" ");
                        if ( iLoc2 != -1 )
                        {
                            sT = sT.substring(0, iLoc2);
                            sT = sT.trim();
                            //System.out.println("sT: '"+sT+"'");
                            //System.out.println("\n(InstalledAr.get()): '"+sT+"'");
                            //System.out.println("sValue: '"+sValue+"'");
                        }
    
                        if ( sT.equals(sValue) )
                        {
                            //System.out.println("--Installed Match--");
                            c.setBackground(green);
                            break;
                        }
                    }
                }
            }
/**/            

			return c;
		}
	} //}}}

	//{{{	createGui()
	@SuppressWarnings("unchecked")
	public void createGui()
	{
		// Use BorderLayout..
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		GridBagLayout gridbag = new GridBagLayout();

		JPanel topPanel = new JPanel();
		topPanel.setLayout(gridbag);

		/**
		 *		Menus
		 */

		GridBagConstraints menuBarGbc = new GridBagConstraints();

		JMenuBar menuBar = new JMenuBar();

		JMenu sdkMenu = new JMenu("Manage SDK");
		JMenuItem packagesMenuItem = new JMenuItem("Packages");
		packagesMenuItem.addActionListener(actListener);
		JMenuItem licensesMenuItem = new JMenuItem("Accept Licenses");
		licensesMenuItem.addActionListener(actListener);

		JMenu avdMenu = new JMenu("Manage AVDs");
		JMenuItem createMenuItem = new JMenuItem("Create");
		createMenuItem.addActionListener(actListener);
		JMenuItem startMenuItem = new JMenuItem("AVDs");
		startMenuItem.addActionListener(actListener);

		sdkMenu.add(packagesMenuItem);
		sdkMenu.add(licensesMenuItem);
		menuBar.add(sdkMenu);

		avdMenu.add(createMenuItem);
		avdMenu.add(startMenuItem);
		menuBar.add(avdMenu);

		menuBarGbc.gridy = 0;
		menuBarGbc.gridheight = 1;
		menuBarGbc.weightx = 1.0;
		menuBarGbc.gridwidth = GridBagConstraints.REMAINDER;
		menuBarGbc.fill = GridBagConstraints.HORIZONTAL;
		menuBarGbc.anchor = GridBagConstraints.WEST;

		topPanel.add(menuBar, menuBarGbc);

		mainPanel.add(topPanel, BorderLayout.NORTH);

		/**
		 *		Console Pane
		 */

		conesolePanel = new JPanel();

		consoleTextArea = new TextArea();
		consoleTextArea.setEditable(false);

		Font defaultFont = new Font("Monospaced", Font.BOLD, 12);
		//Font defaultFont = new Font("Dialog", Font.BOLD, iFontSize);
		consoleTextArea.setFont(defaultFont);

		consoleTextArea.setRows(20);
		consoleTextArea.setColumns(DISPLAY_WIDTH);
		consoleTextArea.setForeground(Color.WHITE);
		consoleTextArea.setBackground(Color.BLACK);

		conesolePanel.add(consoleTextArea);
		mainPanel.add(conesolePanel, BorderLayout.CENTER);

		/**
		 *		Status Bar..
		 */

		Border loweredBevel = BorderFactory.createLoweredBevelBorder();

		JPanel statusBar = new JPanel();
		statusBar.setLayout(gridbag);
		GridBagConstraints gbc = new GridBagConstraints();

		statusLabel = new JLabel(" ");
		statusLabel.setBorder(loweredBevel);

		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(2, 2, 2, 2); // top left bottom right

		statusBar.add(statusLabel, gbc);

		mainPanel.add(statusBar, BorderLayout.SOUTH);

	} //}}}

	//{{{   InteractiveCommand
	@SuppressWarnings("unchecked")
	class InteractiveCommand extends Thread
	{
		public void run()
		{
			//System.out.println("\n\n== InteractiveCommand run() ==");
			//System.out.println("sInternalCommand: '"+sInternalCommand+"'");
			ProcessBuilder processBuilder;
			Process process = null;
			InputStream inputStream;
			InputStream errorStream;
			OutputStream outputStream;
			String sT = "";
			StringBuffer sBOut = null;
			StringBuffer sB;
			byte[] bBuf = new byte[2048];

			byte[] bZd = {(byte) 0x0d};
			String sZeroD = new String(bZd);

			byte[] bReply = {(byte) 0x79, (byte) 0x0d, (byte) 0x0a}; // 'y'
			byte[] bReplyDA = {(byte) 0x0d, (byte) 0x0a}; // Enter
			String sZeroDZeroA = new String(bReplyDA);

			int iBytesRead = 0;
			int iLoc2 = 0;
			int iExitVal = 0;
			int iNoBytesCount = 0;

			try
			{
				processBuilder = new ProcessBuilder();

				if (iOS == LINUX_MAC)
					processBuilder.command("/bin/bash", "-c", sInternalCommand);
				else
					processBuilder.command("cmd.exe", "/c", sInternalCommand);

				process = processBuilder.start();
			}
			catch (IOException ioe)
			{}

/*
			if ( process == null )
			    System.out.println("process null");
			else
			    System.out.println("process not null");
/**/			
			
			inputStream = process.getInputStream();
			errorStream = process.getErrorStream();
			outputStream = process.getOutputStream();
			sBOut = new StringBuffer();
			consoleTextArea.setText("");

			try
			{
				while (true)
				{
				    //System.out.println("--TOP--");
					iBytesRead = 0;
					iBytesRead = inputStream.read(bBuf, 0, bBuf.length);
					//System.out.println("(inputStream)iBytesRead: "+iBytesRead);
					if ( iBytesRead > 0 ) 
					{
					
						sT = new String(bBuf, 0, iBytesRead);
						//System.out.println("(input)"+sT);
/*						
						if ( sT == null )
						    System.out.println("sT null");
						else
						    System.out.println("sT: '"+sT+"'");
/**/

						sB = new StringBuffer(sT);
						iLoc2 = 0;
						while (true)
						{
							iLoc2 = sB.indexOf(sZeroD, iLoc2);
							if (iLoc2 != -1)
							{
								if ( ((iLoc2 + 1) >= 0) && ((iLoc2 + 1) < sB.length()) )
								{
									if (sB.charAt(iLoc2 + 1) != (char) 0x0a)
									{
										sB = sB.deleteCharAt(iLoc2);
										sB = sB.insert(iLoc2, sZeroDZeroA);
									}
								}
								else
								{
								    if ( (iLoc2 >= 0) && (iLoc2 < sB.length()) )
                                    {
                                        sB = sB.deleteCharAt(iLoc2);
                                        sB = sB.insert(iLoc2, sZeroDZeroA);
                                    }
								}
							}
							else
								break;

							iLoc2 += 2; // Next..
							if (iLoc2 > sB.length())
								break;

						} // End while..

						sT = sB.toString();
						//System.out.println("(Modified input)"+sT);

						sBOut.append(sT);

						
/*						
                        System.out.println("\n\n");
                        char cTChr;

                        for ( int g = 0; g<sT.length(); g++ )
                        {
                            cTChr = (char)sT.charAt(g);
                            if ( (cTChr<0x21) || (cTChr > 0x7e) )
                                System.out.print("["+Integer.toHexString((int)cTChr)+"]");
                            else
                                System.out.print(cTChr);
                        }
                        System.out.println("\n\n");
/**/

						if ( bHideOutput == false )
							consoleTextArea.append(sT);

						if ( ((sBOut.length() - 10) >= 0) && ((sBOut.length() - 1) >= 0) )
						{
                            if ( sBOut.substring(sBOut.length() - 10, sBOut.length() - 1).contains("(y/N)") )
                            {
                                // Reply for accept license agreement
                                System.out.println("===Sending reply===");
                                if (((acceptLicensesCheckBox != null) && (acceptLicensesCheckBox.isSelected())) ||
                                    ((finalAcceptLicensesCheckBox != null) && (finalAcceptLicensesCheckBox.isSelected())))
                                {
                                    outputStream.write(bReply); // 'y'
                                }
                                else
                                {
                                    outputStream.write(bReplyDA); // Enter (N)
                                }
    
                                outputStream.flush();
    
                                try
                                {
                                    Thread.sleep(250);
                                }
                                catch (InterruptedException ie)
                                {}
    
                            }
                            else if (sBOut.substring(sBOut.length() - 10, sBOut.length() - 1).contains("[no]"))
                            {
                                // Reply for create avd:  'Do you wish to create a custom hardware profile? [no]'
                                System.out.println("===Sending reply===");
                                outputStream.write(bReplyDA); // Enter
                                outputStream.flush();
    
                                try
                                {
                                    Thread.sleep(250);
                                }
                                catch (InterruptedException ie)
                                {}
                            }
                        }
					}
					else
					{
						iBytesRead = errorStream.read(bBuf, 0, bBuf.length);
						//System.out.println("(errorStream)iBytesRead: "+iBytesRead);
						if ( iBytesRead > 0 )
						{
							sT = new String(bBuf, 0, iBytesRead);
							//System.out.println("(error)"+sT);

							sB = new StringBuffer(sT);
							iLoc2 = 0;
							while (true)
							{
								iLoc2 = sB.indexOf(sZeroD, iLoc2);
								if (iLoc2 != -1)
								{
									if ( ((iLoc2 + 1) < sB.length()) )
									{
										if (sB.charAt(iLoc2 + 1) != (char) 0x0a)
										{
											sB = sB.deleteCharAt(iLoc2);
											sB = sB.insert(iLoc2, sZeroDZeroA);
										}
									}
									else
									{
										sB = sB.deleteCharAt(iLoc2);
										sB = sB.insert(iLoc2, sZeroDZeroA);
									}
								}
								else
									break;

								iLoc2 += 2; // Next..
								if (iLoc2 > sB.length())
									break;

							} // End while..

							sT = sB.toString();

							sBOut.append(sT);

							if (bHideOutput == false)
								consoleTextArea.append(sT);
						}
						else
						{
						    iNoBytesCount++;
						}
					}

                    // Give it enough time to output
                    // anything from the errorStream..					
                    if ( iNoBytesCount > 10 )
                    {
                        try
                        {
                            iExitVal = process.exitValue();
                            //System.out.println("iExitVal: "+iExitVal);
                            break;
                        }
                        catch (IllegalThreadStateException itse)
                        {}
                    }
				} // End while..
				//System.out.println("Dropped out");
			}
			catch (IOException ioe)
			{
			    System.out.println("Exception");
			}
			finally
			{
				try
				{
					if (inputStream != null)
						inputStream.close();

					if (errorStream != null)
						errorStream.close();

					if (outputStream != null)
						outputStream.close();
				}
				catch (IOException ioe)
				{}
			}

			//System.out.println("\nExiting InteractiveCommand");
			process.destroy();

			commandResultS = sBOut.toString();

			if ( interactiveRequestLatch != null )
				interactiveRequestLatch.countDown();

		}
	} //}}}

	//{{{   CommandBgThread    readLine()
	class CommandBgThread extends Thread
	{
		public void run()
		{
			//System.out.println("\nCommandBgThread run()");
			//System.out.println("sInternalCommand: '"+sInternalCommand+"'");

			ProcessBuilder processBuilder;
			Process process = null;
			InputStream inputStream;
			InputStream errorStream;
			BufferedReader inputBufferedReader = null;
			BufferedReader errorBufferedReader = null;
			String sLine = "";
			StringBuffer sB;
			StringBuffer sBOut;
			int iExitCode = 0;
			int iExitVal = 0;
			long lTime = 0;
			long lTime2 = 0;

			byte[] bReply = {(byte) 0x79, (byte) 0x0d, (byte) 0x0a};
			String sReply = new String(bReply);
			byte[] b0d0a = {(byte) 0x0d, (byte) 0x0a};
			String sEnd = new String(b0d0a);

			try
			{
				processBuilder = new ProcessBuilder();

				if (iOS == LINUX_MAC)
					processBuilder.command("/bin/bash", "-c", sInternalCommand);
				else
					processBuilder.command("cmd.exe", "/c", sInternalCommand);

				process = processBuilder.start();
			}
			catch (IOException ioe)
			{}

			inputStream = process.getInputStream();
			errorStream = process.getErrorStream();
			sBOut = new StringBuffer();

			try
			{
				inputBufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				errorBufferedReader = new BufferedReader(new InputStreamReader(errorStream));

				consoleTextArea.setText("");

				//System.out.println("Above top");
				while ( true )
				{
					if ( inputBufferedReader.ready() )
					{
					    lTime = System.currentTimeMillis();
						sLine = inputBufferedReader.readLine();
						if ( sLine != null )
						{
							//System.out.println("(input): '"+sLine+"'");

							sB = new StringBuffer(sLine);
							sB.append(sEnd);

							sBOut.append(sB.toString());

/*							
                            System.out.println("\n\n");
                            char cTChr;

                            for ( int g = 0; g<sB.length(); g++ )
                            {
                                cTChr = (char)sB.charAt(g);
                                if ( (cTChr<0x21) || (cTChr > 0x7e) )
                                    System.out.print("["+Integer.toHexString((int)cTChr)+"]");
                                else
                                    System.out.print(cTChr);
                            }
                            System.out.println("\n\n length(): "+sB.length());
/**/

                            // I use AWT's TextArea append() because it's responsive.
                            // If you try to use Swing's doc.insertString() it's extremely
                            // unresponsive and has nowhere near "real time" output..
							if ((bHideOutput == false) || (sShowCommandResults.equals("true")))
							{
								consoleTextArea.append(sB.toString());
							}
						}
					}
					else if (errorBufferedReader.ready())
					{
					    lTime = System.currentTimeMillis();
						sLine = errorBufferedReader.readLine();
						if ( sLine != null )
						{
							//System.out.println("(error): '"+sLine+"'");
							sB = new StringBuffer(sLine);
							sB.append(sEnd);

							if ((bHideOutput == false) || (sShowCommandResults.equals("true")))
							{
								consoleTextArea.append(sB.toString());
							}

							sBOut.append(sB.toString());
						}
					}
					else
					{
						// Didn't get anything..
						lTime2 = System.currentTimeMillis();
						if ( (bAVDSubmit) && (lTime > 0) && (lTime2 - lTime > 3000) )
						{
						    //System.out.println("Too long, breaking");
						    break;
						}
						
						try
						{
							iExitVal = process.exitValue();
							//System.out.println("iExitVal: "+iExitVal);
							break;
						}
						catch (IllegalThreadStateException itse)
						{}
					}
				} // End while..
			}
			catch (IOException ioe)
			{
				System.out.println("CommandBgThread Exception:");
				ioe.printStackTrace();
			}
			finally
			{
				try
				{
					if (inputBufferedReader != null)
						inputBufferedReader.close();

					if (inputStream != null)
						inputStream.close();

					if (errorBufferedReader != null)
						errorBufferedReader.close();

					if (errorStream != null)
						errorStream.close();
				}
				catch (IOException ioe)
				{}
			}

			if ( process != null )
			{
				process.destroy();
				process = null;
			}

			commandResultS = sBOut.toString();
			
			//System.out.println("commandResultS: '"+commandResultS+"'");
			
/*			
			//String sT4 = commandResultS.substring(5000, commandResultS.length() - 1000);
			//String sT4 = commandResultS.substring(5000);
			String sT4 = commandResultS.substring(0, 6500);
			
            System.out.println("\n\n");
            char cTChr;

            for ( int g = 0; g<sT4.length(); g++ )
            {
                cTChr = (char)sT4.charAt(g);
                if ( (cTChr<0x21) || (cTChr > 0x7e) )
                    System.out.print("["+Integer.toHexString((int)cTChr)+"]");
                else
                    System.out.print(cTChr);
            }
            System.out.println("\n\n");
/**/			

			bCommandFinished = true;
			
			//System.out.println("Exiting CommandBgThread run()");

			if (commandRequestLatch != null)
				commandRequestLatch.countDown();

		}
	} //}}}

	//{{{   AVDsDialog()
	@SuppressWarnings("unchecked")
	public void AVDsDialog()
	{
		//System.out.println("AVDsDialog()");
		int iGridY;
		int iSz = 0;
		int iLoc2;
		int iPathLength = 0;
		String[] tSa;
		StringBuffer sB;
		AVDInfo aVDInfo;
		byte[] bAr = {(byte) 0x0d, (byte) 0x0a};
		String sCR = new String(bAr);

		JPanel panel = new JPanel(new GridBagLayout());
		textAreaPanel = new JPanel();
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(4, 4, 4, 4); // External padding of the component

		iGridY = 0;

		JLabel targetSdkLbl = new JLabel("AVD: ");
		gbc.gridx = 0;
		gbc.gridy = iGridY;
		gbc.gridwidth = 1;
		panel.add(targetSdkLbl, gbc);
/*		
        if ( AVDsAr == null )
            System.out.println("AVDsAr null");
        else
            System.out.println("AVDsAr.size(): "+AVDsAr.size());
/**/

		avdScrollPane.getViewport().setView(avdJList);

		gbc.gridx = 1;
		gbc.gridy = iGridY;
		gbc.gridwidth = 3;
		panel.add(avdScrollPane, gbc);

		iGridY++;

		avdTextArea = new JTextArea();
		avdTextArea.setRows(4);
		avdTextArea.setColumns(5);
		avdTextArea.setEditable(false);

		textAreaPanel.setPreferredSize(new Dimension(415, 90));
		textAreaPanel.add(avdTextArea);

		if ((AVDsAr != null) && (AVDsAr.size() > 0))
		{
			//System.out.println("iAdvSelectedIndex: "+iAdvSelectedIndex);
			if ( iAdvSelectedIndex < AVDsAr.size() )
			{
                aVDInfo = (AVDInfo) AVDsAr.get(iAdvSelectedIndex);
                if ((aVDInfo.sName != null) && (!aVDInfo.sName.equals("")))
                {
                    avdTextArea.append("Name:  ");
                    avdTextArea.append(aVDInfo.sName);
                    avdTextArea.append("\n");
                }
    
                if ((aVDInfo.sDevice != null) && (!aVDInfo.sDevice.equals("")))
                {
                    avdTextArea.append("Device:  ");
                    avdTextArea.append(aVDInfo.sDevice);
                    avdTextArea.append("\n");
                }
    
                if ((aVDInfo.sPath != null) && (!aVDInfo.sPath.equals("")))
                {
                    sB = new StringBuffer();
                    sB.append("Path:  ");
                    sB.append(aVDInfo.sPath);
    
                    while (true)
                    {
                        iPathLength = sB.toString().length();
                        if (iPathLength<(iLongest + 4))
                            sB.append(" ");
                        else
                            break;
                    }
    
                    avdTextArea.append(sB.toString());
                    avdTextArea.append("\n");
                }
    
                if ((aVDInfo.sTarget != null) && (!aVDInfo.sTarget.equals("")))
                {
                    avdTextArea.append("Target:  ");
                    avdTextArea.append(aVDInfo.sTarget);
                    avdTextArea.append("\n");
                }
    
                if ((aVDInfo.sBasedOn != null) && (!aVDInfo.sBasedOn.equals("")))
                {
                    sB = new StringBuffer();
                    sB.append("Based on:  ");
                    sB.append(aVDInfo.sBasedOn);
                    sADV_BasedOn = aVDInfo.sBasedOn; // Save for ABI..
    
                    avdTextArea.append(sB.toString());
                }
            }
		}

		gbc.gridx = 1;
		gbc.gridy = iGridY;
		gbc.gridwidth = 3;
		panel.add(textAreaPanel, gbc);

		iGridY++;

		startCheckBox = new JCheckBox("  Start AVD");
		startCheckBox.setSelected(false);

		gbc.gridx = 1;
		gbc.gridy = iGridY;
		gbc.gridwidth = 3;
		panel.add(startCheckBox, gbc);

		iGridY++;

		deleteCheckBox = new JCheckBox("  Delete AVD");
		deleteCheckBox.setSelected(false);

		gbc.gridx = 1;
		gbc.gridy = iGridY;
		gbc.gridwidth = 3;
		panel.add(deleteCheckBox, gbc);

		iGridY++;

		panel.setBorder(new LineBorder(Color.GRAY));

		JButton submitButton = new JButton("Submit");
		submitButton = new JButton("Submit");
		submitButton.setActionCommand("avds_submit");
		submitButton.addActionListener(actListener);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("avds_cancel");
		cancelButton.addActionListener(actListener);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(submitButton);
		buttonPanel.add(cancelButton);

		avdsFrame.getContentPane().add(panel, BorderLayout.CENTER);
		avdsFrame.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

		avdsFrame.pack();
		avdsFrame.setVisible(true);
		avdsFrame.setResizable(false);

	} //}}}

	//{{{   packageDialog()
	@SuppressWarnings("unchecked")
	public void packageDialog()
	{
		//System.out.println("\npackageDialog()");
		packageFrame = new JFrame();
		packageFrame.setLayout(new BorderLayout());
		packageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		packageFrame.setTitle("Packages");

		int iGridY;
		int iSz = 0;
		String[] tSa;

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(4, 4, 4, 4); // External padding of the component

		iGridY = 0;
		JLabel targetSdkLbl = new JLabel("Packages: ");
		gbc.gridx = 0;
		gbc.gridy = iGridY;
		gbc.gridwidth = 1;
		panel.add(targetSdkLbl, gbc);

		JScrollPane packageScrollPane = new JScrollPane();
		iSz = PackageAr.size();
		tSa = new String[iSz];
		for ( int iJ = 0; iJ < PackageAr.size(); iJ++ )
		{
			tSa[iJ] = (String) PackageAr.get(iJ);
			//System.out.println("(PackageAr)["+iJ+"]: '"+tSa[iJ]+"'");
		}

		packageJList = new JList(tSa);
		//packageJList.setFont(new Font("Monospaced", Font.BOLD, 13));
		packageJList.setFont(new Font("Monospaced", Font.BOLD, 12));
		//packageJList.setFont(new Font("Dialog", Font.BOLD, 12));
		//packageJList.setFont(new Font("DialogInput", Font.BOLD, 12));
		//packageJList.setFont(new Font("SansSerif", Font.BOLD, 12));
		//packageJList.setFont(new Font("Serif", Font.BOLD, 12));
		packageJList.setCellRenderer(new ColorCellRenderer());
		//packageJList.setVisibleRowCount(8);
		packageJList.setVisibleRowCount(10);
		packageJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		packageScrollPane.getViewport().setView(packageJList);

		gbc.gridx = 1;
		gbc.gridy = iGridY;
		gbc.gridwidth = 3;
		panel.add(packageScrollPane, gbc);

		iGridY++;

		acceptLicensesCheckBox = new JCheckBox("  Accept licenses");
		acceptLicensesCheckBox.setSelected(false);

		gbc.gridx = 1;
		gbc.gridy = iGridY;
		gbc.gridwidth = 3;
		panel.add(acceptLicensesCheckBox, gbc);

		iGridY++;

		uninstallCheckBox = new JCheckBox("  Uninstall package");
		uninstallCheckBox.setSelected(false);

		gbc.gridx = 1;
		gbc.gridy = iGridY;
		gbc.gridwidth = 3;
		panel.add(uninstallCheckBox, gbc);

		iGridY++;

		updateCheckBox = new JCheckBox("  Update all installed packages");
		updateCheckBox.setSelected(false);

		gbc.gridx = 1;
		gbc.gridy = iGridY;
		gbc.gridwidth = 3;
		panel.add(updateCheckBox, gbc);

		iGridY++;

		panel.setBorder(new LineBorder(Color.GRAY));
		JButton submitButton = new JButton("Submit");
		submitButton.setActionCommand("package_submit");
		submitButton.addActionListener(actListener);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("package_cancel");
		cancelButton.addActionListener(actListener);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(submitButton);
		buttonPanel.add(cancelButton);

		packageFrame.getContentPane().add(panel, BorderLayout.CENTER);
		packageFrame.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
		packageFrame.pack();
		packageFrame.setVisible(true);
		packageFrame.setResizable(false);

	} //}}}

	//{{{   acceptLicensesDialog()
	@SuppressWarnings("unchecked")
	public void acceptLicensesDialog()
	{
		acceptLicensesFrame = new JFrame();
		acceptLicensesFrame.setLayout(new BorderLayout());
		acceptLicensesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		acceptLicensesFrame.setTitle("Licenses");

		int iGridY;
		int iSz = 0;
		String[] tSa;

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(4, 4, 4, 4); // External padding of the component

		iGridY = 0;

		finalAcceptLicensesCheckBox = new JCheckBox("  Accept Licenses");
		finalAcceptLicensesCheckBox.setSelected(false);

		gbc.gridx = 1;
		gbc.gridy = iGridY;
		gbc.gridwidth = 3;
		panel.add(finalAcceptLicensesCheckBox, gbc);

		iGridY++;

		panel.setBorder(new LineBorder(Color.GRAY));

		JButton submitButton = new JButton("Submit");
		submitButton.setActionCommand("accept_licenses_submit");
		submitButton.addActionListener(actListener);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("accept_licenses_cancel");
		cancelButton.addActionListener(actListener);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(submitButton);
		buttonPanel.add(cancelButton);

		acceptLicensesFrame.getContentPane().add(panel, BorderLayout.CENTER);
		acceptLicensesFrame.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

		acceptLicensesFrame.pack();
		acceptLicensesFrame.setVisible(true);
		acceptLicensesFrame.setResizable(false);

	} //}}}

	//{{{   createAVD()
	@SuppressWarnings("unchecked")
	public void createAVD()
	{
		//System.out.println("createAVD()");
		createFrame = new JFrame();
		createFrame.setLayout(new BorderLayout());
		createFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		createFrame.setTitle("Create AVD");

		int iGridY;
		int iSz = 0;
		String[] tSa;

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(4, 4, 4, 4); // External padding of the component

		iGridY = 0;

		JLabel avdNmLbl = new JLabel("AVD Name: ");
		gbc.gridx = 0;
		gbc.gridy = iGridY;
		gbc.gridwidth = 1;
		panel.add(avdNmLbl, gbc);

		deviceField = new JTextField(30);

		gbc.gridx = 1;
		gbc.gridy = iGridY;
		gbc.gridwidth = 3;
		panel.add(deviceField, gbc);

		iGridY++;

		JLabel targetSdkLbl = new JLabel("System Image: ");
		gbc.gridx = 0;
		gbc.gridy = iGridY;
		gbc.gridwidth = 1;
		panel.add(targetSdkLbl, gbc);

		JScrollPane systemImageScrollPane = new JScrollPane();

		iSz = SystemImagesAr.size();
		tSa = new String[iSz];
		for ( int iJ = 0; iJ < SystemImagesAr.size(); iJ++ )
			tSa[iJ] = (String)SystemImagesAr.get(iJ);

		sIJList = new JList(tSa);
		sIJList.setFont(new Font("Monospaced", Font.BOLD, 12));
		sIJList.setVisibleRowCount(5);
		sIJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		systemImageScrollPane.getViewport().setView(sIJList);

		gbc.gridx = 1;
		gbc.gridy = iGridY;
		gbc.gridwidth = 3;
		panel.add(systemImageScrollPane, gbc);

		iGridY++;

		JLabel deviceLbl = new JLabel("Device: ");
		gbc.gridx = 0;
		gbc.gridy = iGridY;
		gbc.gridwidth = 1;
		panel.add(deviceLbl, gbc);

		JScrollPane devicesScrollPane = new JScrollPane();

		iSz = DevicesAr.size();
		tSa = new String[iSz];
		for ( int iJ = 0; iJ < DevicesAr.size(); iJ++ )
			tSa[iJ] = (String)DevicesAr.get(iJ);

		devicesJList = new JList(tSa);
		devicesJList.setFont(new Font("Monospaced", Font.BOLD, 12));
		devicesJList.setVisibleRowCount(5);
		devicesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		devicesScrollPane.getViewport().setView(devicesJList);

		gbc.gridx = 1;
		gbc.gridy = iGridY;
		gbc.gridwidth = 3;
		panel.add(devicesScrollPane, gbc);

		iGridY++;

		forceCheckBox = new JCheckBox("  Force overwrite");
		forceCheckBox.setSelected(false);

		gbc.gridx = 1;
		gbc.gridy = iGridY;
		gbc.gridwidth = 3;
		panel.add(forceCheckBox, gbc);

		iGridY++;

		panel.setBorder(new LineBorder(Color.GRAY));

		JButton submitButton = new JButton("Submit");
		submitButton.setActionCommand("create_submit");
		submitButton.addActionListener(actListener);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("create_cancel");
		cancelButton.addActionListener(actListener);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(submitButton);
		buttonPanel.add(cancelButton);

		createFrame.getContentPane().add(panel, BorderLayout.CENTER);
		createFrame.getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

		createFrame.pack();
		createFrame.setVisible(true);
		createFrame.setResizable(false);

	} //}}}

	//{{{	ActionListener
	@SuppressWarnings("unchecked")
	private ActionListener actListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			//System.out.println("\n== ActionListener ==");
			sActionCommand = e.getActionCommand();
			String sT = "";
			//System.out.println("sActionCommand: '"+sActionCommand+"'");
			
			// Refresh 'tools' directory..		
			StringBuffer sB = new StringBuffer();
            String[] dirList;
            String sLastDir = "";
			
			sB.append(sSDKPath);
			sB.append("/cmdline-tools");
			File tFile = new File(sB.toString());
			if ( tFile.exists() )
			{
			    // 'latest' is the highest numbered cmdline-tools version..
			    // We only want to show numbered versions..
                dirList = tFile.list();
                if ( (dirList != null) && (dirList.length > 0) )
                {
                    // Use version directory..
                    for ( int iZ = 0; iZ < dirList.length; iZ++ )
                    {
                        //System.out.println("["+iZ+"]: '"+dirList[iZ]+"'");
                        if ( dirList[iZ].equals("bin") || dirList[iZ].equals("lib") ||
                                dirList[iZ].equals("NOTICE.txt") || dirList[iZ].equals("source.properties") ||
                                dirList[iZ].equals("latest") )
                            continue;
                            
                        sLastDir = dirList[iZ];
                    }
                    
                    //System.out.println("sLastDir: '"+sLastDir+"'");
                    if ( sLastDir.equals("") )
                    {
                        sToolsDir = "cmdline-tools";    // No version directory..
                        //System.out.println("(4)sToolsDir set: '"+sToolsDir+"'");
                    }
                    else
                    {
                        StringBuffer sB2 = new StringBuffer();
                        sB2.append("cmdline-tools");
                        sB2.append("/");
                        sB2.append(sLastDir);
                        
                        sToolsDir = sB2.toString();
                        //System.out.println("(5)sToolsDir set: '"+sToolsDir+"'");
                    }
                }
			}
			else
			{
				sB = new StringBuffer();
				sB.append(sSDKPath);
				sB.append("/tools");
				tFile = new File(sB.toString());
				if (tFile.exists())
				{
					sToolsDir = "tools";
					//System.out.println("(6)sToolsDir set: '"+sToolsDir+"'");
				}
			}
			
			//System.out.println("sToolsDir: '"+sToolsDir+"'");
			
			if (CREATE_ADV.equals(sActionCommand))
			{
				// If Create Dialog is open, close it..
				if ( createFrame != null )
				{
				    if ( createFrame.isVisible() )
				    {
                        createFrame.setVisible(false);
                        createFrame.dispose();
                    }
                }
			    
				createThread = new CreateThread();
				createThread.start();
			}
			else if (CREATE_SUBMIT.equals(sActionCommand))
			{
				//System.out.println("CREATE_SUBMIT");
				StringBuffer sb = new StringBuffer();
				String sName = "";
				String sSystemImage = "";
				String sDevice = "";
				int[] iAr;
				int iLoc2 = 0;
				boolean bIsSelected;
				ListModel model;

				sb = new StringBuffer();

				if (iOS == LINUX_MAC)
				{
					sb.append("export PATH=${PATH}:");
					sb.append(sSDKPath);
					sb.append("/");
					sb.append(sToolsDir);
					sb.append("/bin");

					sb.append(";export JAVA_HOME=");
					sb.append(sJavaPath);

					sb.append(";export ANDROID_HOME=");
					sb.append(sSDKPath);

					sb.append(";export ANDROID_SDK_ROOT=");
					sb.append(sSDKPath);

					sb.append(";avdmanager ");
					sb.append("create avd -n ");
				}
				else
				{
					sb.append("cd ");
					//sb.append("SET PATH=");
					sb.append(sSDKPath);
					sb.append("/");
					sb.append(sToolsDir);
					sb.append("/bin");
					//sb.append(";%PATH%");

					sb.append("&&SET JAVA_HOME=");
					sb.append(sJavaPath);

					sb.append("&&SET ANDROID_HOME=");
					sb.append(sSDKPath);

					sb.append("&&SET ANDROID_SDK_ROOT=");
					sb.append(sSDKPath);

					sb.append("&&avdmanager ");
					sb.append("create avd -n ");
				}

				sName = deviceField.getText();
				//System.out.println("sName: '"+sName+"'");
				
				// Replace any spaces..
				sB = new StringBuffer(sName);
				for ( int iX = 0; iX < sB.length(); iX++ )
				{
				    if ( Character.isWhitespace(sB.charAt(iX)) )
				        sB.setCharAt(iX, '_');
				}

				sb.append('"');
				sb.append(sB.toString());
				sb.append('"');

				// Note:
				// Seems like by including the --abi option
				// once it creates the AVD, the first time you
				// start it, all the directories and files for that AVD
				// are added and the Emulator will launch..

				sb.append(" --abi ");

				iAr = sIJList.getSelectedIndices();
				model = sIJList.getModel();
				sSystemImage = (String) model.getElementAt(iAr[0]);
				//System.out.println("sSystemImage: '"+sSystemImage+"'");

				sT = sSystemImage.substring(25);
				//System.out.println("sT: '"+sT+"'");
				sB = new StringBuffer(sT);
				iLoc2 = sB.indexOf(";");
				if (iLoc2 != -1)
				{
					sB.setCharAt(iLoc2, (char) 0x2f);
					//System.out.println("sB: '"+sB.toString()+"'");
					sb.append(sB.toString());
				}

				sb.append(" -k ");

				sb.append('"');
				sb.append(sSystemImage);
				sb.append('"');

				sb.append(" -d ");

				iAr = devicesJList.getSelectedIndices();
				model = devicesJList.getModel();
				sDevice = (String) model.getElementAt(iAr[0]);
				//System.out.println("sDevice: '"+sDevice+"'");

				sb.append('"');
				sb.append(sDevice);
				sb.append('"');

				bIsSelected = forceCheckBox.isSelected();
				if (bIsSelected)
				{
					sb.append(" -f");
				}

				if (iOS == WINDOWS)
					sb.append("\n");

				//System.out.println("sb: '"+sb.toString()+"'");                
				bHideOutput = false;
				interactiveRequestLatch = new CountDownLatch(1);
				sInternalCommand = sb.toString();
				interactiveCommand = new InteractiveCommand();
				interactiveCommand.start();

				// Wait for Thread to finish..
				try
				{
					interactiveRequestLatch.await();
				}
				catch (InterruptedException ie)
				{}

				createFrame.setVisible(false);
				createFrame.dispose();

			}
			else if (CREATE_CANCEL.equals(sActionCommand))
			{
				createFrame.setVisible(false);
				createFrame.dispose();
			}
			else if (ACCEPT_LICENSES.equals(sActionCommand))
			{
				// If Accept Licenses Dialog is open, close it..
				if ( acceptLicensesFrame != null )
				{
				    if ( acceptLicensesFrame.isVisible() )
				    {
                        acceptLicensesFrame.setVisible(false);
                        acceptLicensesFrame.dispose();
                    }
                }
			    
				RefreshProperties();
				acceptLicensesDialog();
			}
			else if (ACCEPT_LICENSES_SUBMIT.equals(sActionCommand))
			{
				//System.out.println("ACCEPT_LICENSES_SUBMIT");
				StringBuffer sb;

				sb = new StringBuffer();

				if (iOS == LINUX_MAC)
				{
					sb.append("export PATH=${PATH}:");
					sb.append(sSDKPath);
					sb.append("/");
					sb.append(sToolsDir);
					sb.append("/bin");

					sb.append(";export JAVA_HOME=");
					sb.append(sJavaPath);

					sb.append(";export ANDROID_HOME=");
					sb.append(sSDKPath);

					sb.append(";export ANDROID_SDK_ROOT=");
					sb.append(sSDKPath);

					sb.append(";sdkmanager --licenses ");

					if ( (sUseHTTPS != null) && (sUseHTTPS.equals("false")) )
					{
						sb.append("--no_https ");
					}

					sb.append("--sdk_root=");
					sb.append(sSDKPath);
				}
				else
				{
					sb.append("cd ");
					//sb.append("SET PATH=");
					sb.append(sSDKPath);
					sb.append("/");
					sb.append(sToolsDir);
					sb.append("/bin");
					//sb.append(";%PATH%");

					sb.append("&&SET JAVA_HOME=");
					sb.append(sJavaPath);

					sb.append("&&SET ANDROID_HOME=");
					sb.append(sSDKPath);

					sb.append("&&SET ANDROID_SDK_ROOT=");
					sb.append(sSDKPath);

					sb.append("&&sdkmanager --licenses ");

					if ( (sUseHTTPS != null) && (sUseHTTPS.equals("false")) )
					{
						sb.append("--no_https ");
					}

					sb.append("--sdk_root=");
					sb.append(sSDKPath);
					sb.append("\n");
				}

				bHideOutput = false;

				interactiveRequestLatch = new CountDownLatch(1);
				sInternalCommand = sb.toString();
				interactiveCommand = new InteractiveCommand();
				interactiveCommand.start();

				// Wait for Thread to finish..
				try
				{
					interactiveRequestLatch.await();
				}
				catch (InterruptedException ie)
				{}

				acceptLicensesFrame.setVisible(false);
				acceptLicensesFrame.dispose();
			}
			else if (ACCEPT_LICENSES_CANCEL.equals(sActionCommand))
			{
				acceptLicensesFrame.setVisible(false);
				acceptLicensesFrame.dispose();
			}
			else if (AVDS.equals(sActionCommand))
			{
				//System.out.println("\nAVDS");
				// If AVDs Dialog is open, close it..
				if ( avdsFrame != null )
				{
				    if ( avdsFrame.isVisible() )
				    {
                        avdsFrame.setVisible(false);
                        avdsFrame.dispose();
                    }
                }
				
				aVDsThread = new AVDsThread();
				aVDsThread.start();
			}
			else if (AVDS_SUBMIT.equals(sActionCommand))
			{
				//System.out.println("AVDS_SUBMIT");
				StringBuffer sb = new StringBuffer();
				String sAVDName = "";
				String sABI = "";
				String sBasedOn = "";
				String sVersion = "";
				String sAPI = "";
				//String sT = "";
				String[] tSa;
				StringBuffer Sb;
				int iLength;
				int iSz;
				int iLoc2 = 0;
				int iLoc3 = 0;
				int iMemorySize = 0;
				int[] iAr;
				boolean bIsStartSelected;
				boolean bIsDeleteSelected;
				boolean bDoCommand = false;
				boolean bUseEmulatorDirectory = false;
				ListModel model;
				AVDInfo aVDInfo;
				File file;
				bAVDSubmit = true;
/*				
				if ( AVDsAr == null )
				    System.out.println("AVDsAr null");
				else
				    System.out.println("AVDsAr.size(): "+AVDsAr.size());
/**/				
				iAr = avdJList.getSelectedIndices();
				aVDInfo = (AVDInfo)AVDsAr.get(iAr[0]);
				sBasedOn = "";
				if ( aVDInfo != null )
				    sBasedOn = aVDInfo.sBasedOn;
				
				iLoc2 = sBasedOn.indexOf("Tag/ABI:");
				if ( iLoc2 != -1 )
				{
				    sABI = sBasedOn.substring(iLoc2 + 8);
				    sABI = sABI.trim();
				}
				
				iLoc3 = 8;
				for ( ; ! Character.isWhitespace(sBasedOn.charAt(iLoc3)); iLoc3++ );
				sVersion = sBasedOn.substring(7, iLoc3);
				sVersion = sVersion.trim();
				if ( sVersion.equals("2.3.3") )
				    sAPI = "10";
				else if ( sVersion.equals("3.0") )
				    sAPI = "11";
				else if ( sVersion.equals("3.1") )
				    sAPI = "12";
				else if ( sVersion.equals("3.2") )
				    sAPI = "13";
				else if ( sVersion.equals("4.0") )
				    sAPI = "14";
				else if ( sVersion.equals("4.0.3") )
				    sAPI = "15";
				else if ( sVersion.equals("4.1") )
				    sAPI = "16";
				else if ( sVersion.equals("4.2") )
				    sAPI = "17";
				else if ( sVersion.equals("4.3") )
				    sAPI = "18";
				else if ( sVersion.equals("4.4") )
				    sAPI = "19";
				else if ( sVersion.equals("4.4W") )
				    sAPI = "20";
				else if ( sVersion.equals("5.0") )
				    sAPI = "21";
				else if ( sVersion.equals("5.1") )
				    sAPI = "22";
				else if ( sVersion.equals("6.0") )
				    sAPI = "23";
				else if ( sVersion.equals("7.0") )
				    sAPI = "24";
				else if ( sVersion.equals("7.1.1") )
				    sAPI = "25";
				else if ( sVersion.equals("8.0") )
				    sAPI = "26";
				else if ( sVersion.equals("8.1") )
				    sAPI = "27";
				else if ( sVersion.equals("9.0") )
				    sAPI = "28";
				else if ( sVersion.equals("10.0") )
				    sAPI = "29";
				else if ( sVersion.equals("11.0") )
				    sAPI = "30";

				sb = new StringBuffer();

				bIsStartSelected = startCheckBox.isSelected();
				//System.out.println("bIsStartSelected: "+bIsStartSelected);
				if ( bIsStartSelected )
				{
					bDoCommand = true;
					
					// Check emulator directory..
					sB = new StringBuffer(sSDKPath);
					sB.append("/emulator");
					file = new File(sB.toString());
					if ( file.exists() )
					{
					    bUseEmulatorDirectory = true;
					}
					

					if ( iOS == LINUX_MAC )
					{
						sb.append("cd ");
						//sb.append("export PATH=${PATH}:");
						sb.append(sSDKPath);
						sb.append("/");
						if ( bUseEmulatorDirectory )
						    sb.append("emulator");
						else
						    sb.append(sToolsDir);

/*                        
                        sb.append(";export PATH=${PATH}:");
                        sb.append(sSDKPath);
                        sb.append("/");
                        sb.append(sToolsDir);
                        sb.append("/bin");
/**/
						sb.append(";export JAVA_HOME=");
						sb.append(sJavaPath);

						sb.append(";export ANDROID_HOME=");
						sb.append(sSDKPath);

						sb.append(";export ANDROID_SDK_ROOT=");
						sb.append(sSDKPath);

						sb.append(";emulator -avd ");
					}
					else
					{
						// Note:
						// This seems to need 'cd' and not 'SET PATH'
						sb.append("cd ");
						//sb.append("SET PATH=");
						sb.append(sSDKPath);
						sb.append("/");
						if ( bUseEmulatorDirectory )
						    sb.append("emulator");
						else
						    sb.append(sToolsDir);
						//sb.append(";%PATH%");

/*                        
                        sb.append("&&SET PATH=");
                        sb.append(sSDKPath);
                        sb.append("/");
                        sb.append(sToolsDir);
                        sb.append("/bin");
                        //sb.append(";%PATH%");
/**/
						sb.append("&&SET ANDROID_HOME=");
						sb.append(sSDKPath);

						sb.append("&&SET ANDROID_SDK_ROOT=");
						sb.append(sSDKPath);

						sb.append("&&emulator -avd ");
					}

					iAr = avdJList.getSelectedIndices();
					model = avdJList.getModel();
					sAVDName = (String) model.getElementAt(iAr[0]);
					//System.out.println("sAVDName: '"+sAVDName+"'");

					sb.append('"');
					sb.append(sAVDName);
					sb.append('"');
					
					if ( (sUseForce32Bit != null) && (sUseForce32Bit.length() > 0) )
					{
					    if ( sUseForce32Bit.equals("true") )
					        sb.append(" -force-32bit");
					}
					
					if ( (sMemory != null) && (sMemory.length() > 0) )
					{
					    try
					    {
					        sMemory = sMemory.trim();
					        iMemorySize = Integer.parseInt(sMemory);
					        if ( (iMemorySize > 4096) || (iMemorySize < 128) )
					        {
                                // Illegal Memory size, put up Dialog..
                                JOptionPane.showMessageDialog(
                                    mainJFrame,
                                    "Memory size not in valid range.",
                                    "Memory",
                                    JOptionPane.ERROR_MESSAGE);
					        }
					        else
					        {
					            sb.append(" -memory ");
					            sb.append(sMemory);
					        }
					    }
					    catch (NumberFormatException nfe)
					    {
					    }
					}

					if ( (sGPUMode != null) && (sGPUMode.length() > 0) )
					{
					    sb.append(" -gpu ");
					    sGPUMode = sGPUMode.trim();
					    if ( sGPUMode.equals("auto") )
					        sb.append("auto");
					    else if ( sGPUMode.equals("host") )
					        sb.append("host");
					    else if ( sGPUMode.equals("swiftshader_indirect") )
					        sb.append("swiftshader_indirect");
					    else if ( sGPUMode.equals("angle_indirect") )
					        sb.append("angle_indirect");
					    else if ( sGPUMode.equals("guest") )
					        sb.append("guest");
					}
					
					if ( (sAccel != null) && (sAccel.length() > 0) )
					{
					    sb.append(" -accel ");
					    sAccel = sAccel.trim();
					    if ( sAccel.equals("auto") )
					        sb.append("auto");
					    else if ( sAccel.equals("off") )
					        sb.append("off");
					    else if ( sAccel.equals("on") )
					        sb.append("on");
					}

					if ( (sDisableBootAnim != null) && (sDisableBootAnim.length() > 0) )
					{
					    sDisableBootAnim = sDisableBootAnim.trim();
					    if ( sDisableBootAnim.equals("true") )
					        sb.append(" -no-boot-anim");
					}

					if ( (sEngine != null) && (sEngine.length() > 0) )
					{
					    sb.append(" -engine ");
					    sEngine = sEngine.trim();
					    if ( sEngine.equals("auto") )
					        sb.append("auto");
					    else if ( sEngine.equals("classic") )
					        sb.append("classic");
					    else if ( sEngine.equals("qemu2") )
					        sb.append("qemu2");
					}

					if ( (sQuickBoot != null) && (sQuickBoot.length() > 0) )
					{
					    sQuickBoot = sQuickBoot.trim();
					    sb.append(" -");
					    if ( sQuickBoot.equals("no-snapshot-load") )
					        sb.append("no-snapshot-load");
					    else if ( sQuickBoot.equals("no-snapshot-save") )
					        sb.append("no-snapshot-save");
					    else if ( sQuickBoot.equals("no-snapshot") )
					        sb.append("no-snapshot");
					}

					if ( (sKernel != null) && (sKernel.length() > 0) )
					{
					    // Construct path..
					    Sb = new StringBuffer();
					    Sb.append(sSDKPath);
					    Sb.append("/system-images/android-");
					    Sb.append(sAPI);
					    Sb.append("/");
					    Sb.append(sABI);
					    Sb.append("/");
					    
					    sKernel = sKernel.trim();
					    if ( sKernel.equals("kernel-ranchu") )
					    {
					        Sb.append("kernel-ranchu");
					        
					        // Check if it exists..
					        //System.out.println("Sb: '"+Sb.toString()+"'");
					        file = new File(Sb.toString());
					        if ( file.exists() )
					        {
					            sb.append(" -kernel ");
					            sb.append(Sb.toString());
					        }
					    }
					    else if ( sKernel.equals("kernel-qemu") )
					    {
					        Sb.append("kernel-qemu");
					        //System.out.println("Sb: '"+Sb.toString()+"'");
					        file = new File(Sb.toString());
					        if ( file.exists() )
					        {
					            sb.append(" -kernel ");
					            sb.append(Sb.toString());
					        }
					    }
					}
					
					if ( (sDisableJNIChecks != null) && (sDisableJNIChecks.length() > 0) )
					{
					    if ( sDisableJNIChecks.equals("true") )
					        sb.append(" -nojni");
					}
					
					if ( (sSELinuxSecurity != null) && (sSELinuxSecurity.length() > 0) )
					{
					    sSELinuxSecurity = sSELinuxSecurity.trim();
					    if ( sSELinuxSecurity.equals("disabled") )
					        sb.append(" -selinux disabled");
					    else if ( sSELinuxSecurity.equals("permissive") )
					        sb.append(" -selinux permissive");
					}

					if ( (sDisableVMAcceleration != null) && (sDisableVMAcceleration.length() > 0) )
					{
					    sDisableVMAcceleration = sDisableVMAcceleration.trim();
					    if ( sDisableVMAcceleration.equals("true") )
					        sb.append(" -no-accel");
					}

					if ( (sNoCachePartition != null) && (sNoCachePartition.length() > 0) )
					{
					    sNoCachePartition = sNoCachePartition.trim();
					    if ( sNoCachePartition.equals("true") )
					        sb.append(" -nocache");
					}

					if ( (sWipeData != null) && (sWipeData.length() > 0) )
					{
					    sWipeData = sWipeData.trim();
					    if ( sWipeData.equals("true") )
					        sb.append(" -wipe-data");
					}
					
					if (iOS == WINDOWS)
						sb.append("\n");
				}
				
				bIsDeleteSelected = deleteCheckBox.isSelected();
				//System.out.println("bIsDeleteSelected: "+bIsDeleteSelected);
				if ( bIsDeleteSelected )
				{
					bDoCommand = true;

					if (iOS == LINUX_MAC)
					{
						sb.append("export PATH=${PATH}:");
						sb.append(sSDKPath);
						sb.append("/");
						sb.append(sToolsDir);
						sb.append("/bin");

						sb.append(";export JAVA_HOME=");
						sb.append(sJavaPath);

						sb.append(";export ANDROID_HOME=");
						sb.append(sSDKPath);

						sb.append(";export ANDROID_SDK_ROOT=");
						sb.append(sSDKPath);

						sb.append(";avdmanager ");
						sb.append("delete avd -n ");
					}
					else
					{
						sb.append("cd ");
						//sb.append("SET PATH=");
						sb.append(sSDKPath);
						sb.append("/");
						sb.append(sToolsDir);
						sb.append("/bin");
						//sb.append(";%PATH%");

						sb.append("&&SET JAVA_HOME=");
						sb.append(sJavaPath);

						sb.append("&&SET ANDROID_HOME=");
						sb.append(sSDKPath);

						sb.append("&&SET ANDROID_SDK_ROOT=");
						sb.append(sSDKPath);

						sb.append("&&avdmanager ");
						sb.append("delete avd -n ");
					}

					iAr = avdJList.getSelectedIndices();
					model = avdJList.getModel();
					sAVDName = (String) model.getElementAt(iAr[0]);
					//System.out.println("sAVDName: '"+sAVDName+"'");

					sb.append('"');
					sb.append(sAVDName);
					sb.append('"');

					if (iOS == WINDOWS)
						sb.append("\n");
				}

				//System.out.println("sb: '"+sb.toString()+"'");
				//System.out.println("bDoCommand: "+bDoCommand);
				if ( bDoCommand )
				{
					bHideOutput = false;

					commandRequestLatch = new CountDownLatch(1);
					sInternalCommand = sb.toString();
					commandBgThread = new CommandBgThread();
					commandBgThread.start();

					// Wait for Thread to finish..
					try
					{
						commandRequestLatch.await();
					}
					catch (InterruptedException ie)
					{}

				}

				avdsFrame.setVisible(false);
				avdsFrame.dispose();
				bAVDSubmit = false;    // Reset..

			}
			else if ( AVDS_CANCEL.equals(sActionCommand) )
			{
				avdsFrame.setVisible(false);
				avdsFrame.dispose();
			}
			else if ( PACKAGES.equals(sActionCommand) )
			{
				//System.out.println("\nPACKAGES");
				// If Package Dialog is open, close it..
				if ( packageFrame != null )
				{
				    if ( packageFrame.isVisible() )
				    {
                        packageFrame.setVisible(false);
                        packageFrame.dispose();
                    }
                }
				
                InstalledAr = new ArrayList();
                PackageAr = new ArrayList();
                
				packagesThread = new PackagesThread();
				packagesThread.start();

			}
			else if ( PACKAGE_SUBMIT.equals(sActionCommand) )
			{
				//System.out.println("PACKAGE_SUBMIT");
				int[] iSelAr = packageJList.getSelectedIndices();
				int iLength;
				int iLoc2 = 0;
				String sImage = "";
				StringBuffer sb;
				ListModel model;
				boolean bUninstallSelected = false;
				boolean bUpdateSelected = false;
				boolean bDoChannels = true;
				boolean bDoUpdate = true;

				sb = new StringBuffer();

				if ( iOS == LINUX_MAC )
				{
					sb.append("export PATH=${PATH}:");
					sb.append(sSDKPath);
					sb.append("/");
					sb.append(sToolsDir);
					sb.append("/bin");

					sb.append(";export JAVA_HOME=");
					sb.append(sJavaPath);

					sb.append(";export ANDROID_HOME=");
					sb.append(sSDKPath);

					sb.append(";export ANDROID_SDK_ROOT=");
					sb.append(sSDKPath);

					sb.append(";sdkmanager ");
				}
				else
				{
					sb.append("cd ");
					//sb.append("SET PATH=");
					sb.append(sSDKPath);
					sb.append("/");
					sb.append(sToolsDir);
					sb.append("/bin");
					//sb.append(";%PATH%");
					
					sb.append("&&SET JAVA_HOME=");
					sb.append(sJavaPath);

					sb.append("&&SET ANDROID_HOME=");
					sb.append(sSDKPath);

					sb.append("&&SET ANDROID_SDK_ROOT=");
					sb.append(sSDKPath);

					sb.append("&&sdkmanager ");
				}

				bUpdateSelected = updateCheckBox.isSelected();
				//System.out.println("bUpdateSelected: "+bUpdateSelected);
				if ( bUpdateSelected )
				{
					sb.append("--update ");
				}
				else
				{
					bUninstallSelected = uninstallCheckBox.isSelected();
					if ( bUninstallSelected )
					{
						sb.append("--uninstall ");
						bDoChannels = false;
					}

					if ( (iSelAr != null) && (iSelAr.length > 0) )
					{
						model = packageJList.getModel();
						
						for ( int iJ = 0; iJ < iSelAr.length; iJ++ )
						{
							sImage = (String)model.getElementAt(iSelAr[iJ]);
							iLoc2 = sImage.indexOf(" ");
							if ( iLoc2 != -1 )
							    sImage = sImage.substring(0, iLoc2);
							
							//System.out.println("sImage: '"+sImage+"'");
							sb.append('"');
							sb.append(sImage);
							sb.append('"');
							sb.append(" ");
						}
					}
				}

				if ( (sUseHTTPS != null) && (sUseHTTPS.equals("false")) )
				{
					sb.append("--no_https ");
				}
				
				//System.out.println("bDoChannels: "+bDoChannels);
				if ( bDoChannels )
				{
				    if ( (sPackageChannel != null) && (sPackageChannel.length() > 0) )
				    {
                        if ( sPackageChannel.equals("stable") )
                            sb.append("--channel=0 ");
                        else if ( sPackageChannel.equals("beta") )
                            sb.append("--channel=1 ");
                        else if ( sPackageChannel.equals("dev") )
                            sb.append("--channel=2 ");
                        else if ( sPackageChannel.equals("canary") )
                            sb.append("--channel=3 ");
                    }
				}
				
				//sb.append("--include_obsolete ");
				
				sb.append("--sdk_root=");
				sb.append(sSDKPath);

				if ( iOS == WINDOWS )
					sb.append("\n");
				
				//System.out.println("sb: '"+sb.toString()+"'");

				bHideOutput = false;
				interactiveRequestLatch = new CountDownLatch(1);
				sInternalCommand = sb.toString();
				interactiveCommand = new InteractiveCommand();
				interactiveCommand.start();

				// Wait for Thread to finish..
				try
				{
					interactiveRequestLatch.await();
				}
				catch (InterruptedException ie)
				{}
/**/

				packageFrame.setVisible(false);
				packageFrame.dispose();

			}
			else if (PACKAGE_CANCEL.equals(sActionCommand))
			{
				packageFrame.setVisible(false);
				packageFrame.dispose();
			}
		}
	}; //}}}

	//{{{	readFile()
	private byte[] readFile(int iInitialSize, String fileName)
	{
		//System.out.println("readFile()");
/*		
		if ( fileName == null )
		    System.out.println("fileName null");
		else
		    System.out.println("fileName: '"+fileName+"'");
/**/

		ByteArrayOutputStream baos = new ByteArrayOutputStream(iInitialSize);
		byte[] tempBuf = new byte[768];
		
		int iBufLength = tempBuf.length;		
    	int bytes_read = 0;

		FileInputStream fis = null;

		try
		{
			File file = new File(fileName);
			if ( file.exists() )
			{
				fis = new FileInputStream(file);
	
				while ( true )
				{
					bytes_read = fis.read(tempBuf, 0, iBufLength);
					//System.out.println("bytes_read: "+bytes_read);
					if ( bytes_read == -1 )
					{
						// EOF
						break;
					}
		
					baos.write(tempBuf, 0, bytes_read);
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("readFile Exception: "+e.toString());
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if ( fis != null )
					fis.close();
			}
			catch (IOException ioe)
			{
			}
		}
		
		return baos.toByteArray();
		
	}	//}}}

	//{{{   ListSelectionListener createSelectionListener
	ListSelectionListener createSelectionListener = new ListSelectionListener()
	{
		public void valueChanged(ListSelectionEvent e)
		{
			//System.out.println("\nListSelectionListener valueChanged()");
			boolean bValueIsAdjusting = e.getValueIsAdjusting();
			//System.out.println("bValueIsAdjusting: "+bValueIsAdjusting);
			AVDInfo aVDInfo;
			int iLoc2 = 0;
			int iPathLength = 0;
			StringBuffer sB;
			byte[] bAr = {(byte) 0x0d, (byte) 0x0a};
			String sCR = new String(bAr);

			if ( bValueIsAdjusting == false )
			{
				int[] iSelAr = avdJList.getSelectedIndices();
				if ((iSelAr != null) && (iSelAr.length > 0))
				{
					iAdvSelectedIndex = iSelAr[0];
					//System.out.println("iAdvSelectedIndex: "+iAdvSelectedIndex);
				}

				avdTextArea.selectAll();
				avdTextArea.replaceSelection("");

				if ( iAdvSelectedIndex < AVDsAr.size() )
				{
                    aVDInfo = (AVDInfo)AVDsAr.get(iAdvSelectedIndex);
                    if ((aVDInfo.sName != null) && (!aVDInfo.sName.equals("")))
                    {
                        avdTextArea.append("Name:  ");
                        avdTextArea.append(aVDInfo.sName);
                        avdTextArea.append("\n");
                    }
    
                    if ((aVDInfo.sDevice != null) && (!aVDInfo.sDevice.equals("")))
                    {
                        avdTextArea.append("Device:  ");
                        avdTextArea.append(aVDInfo.sDevice);
                        avdTextArea.append("\n");
                    }
    
                    if ( (aVDInfo.sPath != null) && (!aVDInfo.sPath.equals("")) )
                    {
                        sB = new StringBuffer();
                        sB.append("Path:  ");
                        sB.append(aVDInfo.sPath);
    
                        while ( true )
                        {
                            iPathLength = sB.toString().length();
                            if ( iPathLength < (iLongest + 4) )
                                sB.append(" ");
                            else
                                break;
                        }
    
                        avdTextArea.append(sB.toString());
                        avdTextArea.append("\n");
                    }
    
                    if ((aVDInfo.sTarget != null) && (!aVDInfo.sTarget.equals("")))
                    {
                        avdTextArea.append("Target:  ");
                        avdTextArea.append(aVDInfo.sTarget);
                        avdTextArea.append("\n");
                    }
    
                    if ((aVDInfo.sBasedOn != null) && (!aVDInfo.sBasedOn.equals("")))
                    {
                        sB = new StringBuffer();
                        sB.append("Based on:  ");
                        sB.append(aVDInfo.sBasedOn);
                        sADV_BasedOn = aVDInfo.sBasedOn; // Save for ABI..
    
                        avdTextArea.append(sB.toString());
                    }
    
                    avdsFrame.invalidate();
                    avdsFrame.validate();
                    avdsFrame.repaint();
                }
			}
		}
	}; //}}}

	//{{{	WindowListener	windowClosed()
	static WindowListener windowListener = new WindowAdapter()
	{
		public void windowClosed(WindowEvent e)
		{
			// Invoked when a window has been closed as the result of calling dispose on the window.
			System.exit(0);
		}

		public void windowClosing(WindowEvent e)
		{
			// Invoked when the user attempts to close the window from the window's system menu.
			Window window = e.getWindow();
			window.dispose();
		}
	}; //}}}

	//{{{	processPath()
	/**
	 * Remove double quotes
	 * Flip '\' -> '/'
	 */
	String processPath(String inS)
	{
		//System.out.println("\nprocessPath()");
		StringBuffer sb = null;

		if ((inS != null) && (!inS.equals("")))
			sb = new StringBuffer(inS);
		else
			return inS;

		for ( int g = 0; g < sb.length(); )
		{
			if (sb.charAt(g) == '\\')
				sb.setCharAt(g, '/');
			else if (sb.charAt(g) == (char)'"')
			{
				sb.deleteCharAt(g);
				continue;
			}

			g++;
		}

		//System.out.println("(Returning)sb: "+sb.toString());		
		return sb.toString();
	} //}}}

	//{{{   AVDInfo
	class AVDInfo
	{
		String sName; // 'android21s'
		String sDevice; // 'Nexus S (Google)'
		String sPath; // 'C:\Users\Joe Siebenmann\.android\avd\android21s.avd'
		String sTarget;
		String sBasedOn; // 'Android 5.0 (Lollipop) Tag/ABI: default/armeabi-v7a'	    

	} //}}}

	//{{{	main()	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				sdkManager = new SDKManager();

				mainJFrame = new JFrame();
				mainJFrame.setContentPane(mainPanel);
				mainJFrame.setResizable(false);
				mainJFrame.pack();
				mainJFrame.addWindowListener(windowListener);
				mainJFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				mainJFrame.setVisible(true);
			}
		});
	} //}}}

}
