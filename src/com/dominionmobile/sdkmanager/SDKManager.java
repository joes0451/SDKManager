
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
	 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
import java.util.Iterator;


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

import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.text.*;
import java.util.Properties;
import java.util.ArrayList;



public class SDKManager
{
    //{{{ Data
    
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
	private static JList sIJList;
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
    static volatile String projectHomeS;
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
    
    static volatile boolean bBreakOut;
    static volatile boolean bIncludeObsolete;
    static volatile boolean bTextAreaInit;
    static volatile boolean bHideOutput;
    static volatile boolean bUpdateSelected;
    static volatile boolean bPackages;
    
	static volatile int iOS;
	static volatile int iFontSize;
	static volatile int iAdvSelectedIndex;
	static volatile int iLongest;
	
	static ArrayList DevicesAr;
	static ArrayList SystemImagesAr;
	static ArrayList PackageAr;
	static ArrayList InstalledAr;
	static ArrayList AVDsAr;
	static ArrayList UpdateAr;
	
	private static SDKManager sdkManager;
	
	static final int WINDOWS = 0;
	static final int LINUX = 1;
	static final int LINUX_MAC = 2;

	static final int DISPLAY_WIDTH = 105;
	
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
     
    //{{{  SDKManager() constructor
    public SDKManager()
    {
		// Determine OS..
		String sOs = System.getProperty("os.name").toLowerCase();
		if ( sOs.contains("win") )
			iOS = WINDOWS;
		else if ( (sOs.contains("nix")) ||
				(sOs.contains("nux")) ||
				(sOs.contains("mac")) )
			iOS = LINUX_MAC;
			
		bTextAreaInit = true;
		bHideOutput = false;
		bPackages = false;
   
		createGui();
		
		RefreshProperties();

        // Check 'tools' directory..		
		StringBuffer sB = new StringBuffer();
		sB.append(sSDKPath);
		sB.append("/tools");
		//System.out.println("(tools)sB.toString(): '"+sB.toString()+"'");
		File tFile = new File(sB.toString());
		if ( tFile.exists() )
		{
		    //System.out.println("tools exists");
		    sToolsDir = "tools";
		}
		else
		{
		    sB = new StringBuffer();
            sB.append(sSDKPath);
            sB.append("/cmdline-tools");
            //System.out.println("(cmdline-tools)sB.toString(): '"+sB.toString()+"'");
            tFile = new File(sB.toString());
            if ( tFile.exists() )
            {
                sToolsDir = "cmdline-tools";
            }
		}

        //System.out.println("sToolsDir: '"+sToolsDir+"'");		
	 }    //}}}

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
                
                sb.append(";avdmanager ");
                sb.append("list");
			}
			else
			{
			    sb.append("cd ");
				//sb.append("SET PATH=");
				sb.append(sSDKPath);        // Like: "C:/android-sdk-wind"
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
            {
            }
			

/*		    
			if ( commandResultS == null )
				System.out.println("commandResultS null");
			else
				System.out.println("commandResultS: '"+commandResultS+"'");
/**/

            if ( (commandResultS != null) && (commandResultS.length() > 0) )
            {
                AVDsAr = new ArrayList();
                
                // Get to start..
                iLoc2 = commandResultS.indexOf("Virtual Devices:");
                if ( iLoc2 != -1 )
                {
                    iLoc8 = commandResultS.indexOf("The following", iLoc2);
                    
                    while ( true )
                    {
                        //System.out.println("-----------------------------------");
                        aVDInfo = new AVDInfo();
                        iLoc4 = commandResultS.indexOf("Name:", iLoc2);
                        
                        if ( iLoc4 >= iLoc8 )
                            break;
                        
                        if ( iLoc4 != -1 )
                        {
                            iLoc4 += 6;
                            iStart = iLoc4;
                            iLoc7 = commandResultS.indexOf((int)0x0a, iLoc4);
                            if ( iLoc7 != -1 )
                            {
                                sName = commandResultS.substring(iStart, iLoc7);
                                sName = sName.trim();
                                //System.out.println("sName: '"+sName+"'");
                                aVDInfo.sName = sName;
                            }
                        }
                        
                        iLoc3 = commandResultS.indexOf("---", iLoc2);
                        iLoc4 = commandResultS.indexOf("Device:", iLoc2);
                        if ( (iLoc4 < iLoc3) && (iLoc4 != -1) )
                        {
                            iLoc4 += 8;
                            iStart = iLoc4;
                            iLoc6 = commandResultS.indexOf("Path:", iLoc4);
                            if ( iLoc6 != -1 )
                            {
                                sDevice = commandResultS.substring(iStart, iLoc6);
                                sDevice = sDevice.trim();
                                //System.out.println("sDevice: '"+sDevice+"'");
                                aVDInfo.sDevice = sDevice;
                            }
                        }
                            
                        iLoc4 = commandResultS.indexOf("Path:", iLoc2);
                        if ( iLoc4 != -1 )
                        {
                            iLoc4 += 6;
                            iStart = iLoc4;
                            iLoc6 = commandResultS.indexOf("Target:", iLoc4);
                            if ( iLoc6 != -1 )
                            {
                                sPath = commandResultS.substring(iStart, iLoc6);
                                sPath = sPath.trim();
                                //System.out.println("sPath: '"+sPath+"'");
                                aVDInfo.sPath = sPath;
                            }
                        }

                        iLoc4 = commandResultS.indexOf("Target:", iLoc2);
                        if ( iLoc4 != -1 )
                        {
                            iLoc4 += 8;
                            iStart = iLoc4;
                            iLoc6 = commandResultS.indexOf("Based on", iLoc4);
                            if ( iLoc6 != -1 )
                            {
                                sTarget = commandResultS.substring(iStart, iLoc6);
                                sTarget = sTarget.trim();
                                //System.out.println("sTarget: '"+sTarget+"'");
                                aVDInfo.sTarget = sTarget;
                            }
                        }
                   
                        iLoc4 = commandResultS.indexOf("Based on:", iLoc2);
                        if ( iLoc4 != -1 )
                        {
                            iLoc4 += 10;
                            iStart = iLoc4;
                            iLoc7 = commandResultS.indexOf((int)0x0a, iLoc4);
                            if ( iLoc7 != -1 )
                            {
                                sBasedOn = commandResultS.substring(iStart, iLoc7);
                                sBasedOn = sBasedOn.trim();
                                //System.out.println("sBasedOn: '"+sBasedOn+"'");
                                aVDInfo.sBasedOn = sBasedOn;
                            }
                        }
                        
                        AVDsAr.add((AVDInfo)aVDInfo);
                        
                        // Next..
                        iLoc2 = iLoc4;
                        
                    }   // End while..
                }
            }

            if ( operationRequestLatch != null )
                operationRequestLatch.countDown();
            
		}
	 }    //}}}
	 
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
            {
            }
			

/*		    
			if ( commandResultS == null )
				System.out.println("commandResultS null");
			else
				System.out.println("commandResultS: '"+commandResultS+"'");
/**/

            if ( (commandResultS != null) && (commandResultS.length() > 0) )
            {
                // Get to start..
                DevicesAr = new ArrayList();
                iLoc2 = 0;
                
                while ( true )
                {
                    iLoc2 = commandResultS.indexOf("id:", iLoc2);
                    if ( iLoc2 != -1 )
                    {
                        iLoc3 = commandResultS.indexOf((int)0x22, iLoc2);
                        if ( iLoc3 != -1 )
                        {
                            iLoc4 = commandResultS.indexOf((int)0x22, iLoc3 + 1);
                            if ( iLoc4 != -1 )
                            {
                                sT = commandResultS.substring(iLoc3 + 1, iLoc4);
                                sT = sT.trim();
                                //System.out.println("(Add): '"+sT+"'");
                                DevicesAr.add((String)sT);
                            }
                        }
                    }
                    else
                        break;
                    
                    iLoc2 += 3;     // Next..
                }   // End while..              
            }

            if ( operationRequestLatch != null )
                operationRequestLatch.countDown();
            
		}
	 }    //}}}

    //{{{   AVDsThread
    class AVDsThread extends Thread
    {
        public void run()
        {
            AVDInfo aVDInfo;
            int iSz = 0;
            int iLength = 0;
            String[] tSa;
            String sPath = "";
            
            RefreshProperties();
            
            bHideOutput = true;
            operationRequestLatch = new CountDownLatch(1);
            getAVDsBgThread = new GetAVDsBgThread();
            getAVDsBgThread.start();
        
            // Wait for Thread to finish..
            try
            {
                operationRequestLatch.await();
            }
            catch (InterruptedException ie)
            {
            }
            
            if ( (AVDsAr != null) && (AVDsAr.size() > 0) )
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
                for ( int iJ = 0; iJ < AVDsAr.size(); iJ++ )
                {
                    aVDInfo = (AVDInfo)AVDsAr.get(iJ);
                    sPath = aVDInfo.sPath;
                    iLength = sPath.length();
                    if ( iLength > iLongest )
                        iLongest = iLength;
                        
                    tSa[iJ] = aVDInfo.sName;
                }
                
                avdJList = new JList(tSa);
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
                    "No AVDs found.",
                    "AVDs",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }    //}}}

    //{{{   PackagesThread	 
    class PackagesThread extends Thread
    {
        public void run()
        {
            //System.out.println("\nPackagesThread run()");
            bHideOutput = true;
            RefreshProperties();
            
            operationRequestLatch = new CountDownLatch(1);
            getPackagesBgThread = new GetPackagesBgThread();
            getPackagesBgThread.start();
        
            // Wait for Thread to finish..
            try
            {
                operationRequestLatch.await();
            }
            catch (InterruptedException ie)
            {
            }
            
            bHideOutput = false;
            
            if ( (PackageAr != null) && (PackageAr.size() > 0) )
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
        }
    }    //}}}

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
            {
            }
            
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
            {
            }
            
            bHideOutput = false;
            createAVD();
        }
    }    //}}}
	 
	//{{{   GetPackagesBgThread
	 @SuppressWarnings("unchecked")
	 class GetPackagesBgThread extends Thread
	 {
		public void run()
		{
		    //System.out.println("\nGetPackagesBgThread run()");
		    StringBuffer sb = new StringBuffer();
		    String sT = "";
		    String sPackage = "";
		    String sInstalled = "";
		    String sUpdate = "";
		    StringBuffer sB;
		    boolean bFoundUpdates = false;
		    int iLoc2 = 0;
		    int iLoc3 = 0;
		    int iLoc4 = 0;
		    int iLoc5 = 0;
		    int iLoc6;
		    int iLoc7;
		    int iLoc8 = 0;
		    int iStart = 0;
		    int iCount;
		    byte[] bOutAr = null;
		    byte[] bAr = {(byte)0x0a, (byte)0x20, (byte)0x20};
		    String sStart = new String(bAr);
		    
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
                
                sb.append(";sdkmanager --list ");
                
				if ( sIncludeObsolete.equals("true") )
				{
				    sb.append("--include_obsolete ");
				}

				if ( sPackageChannel.equals("stable") )
				    sb.append("--channel=0 ");
				else if ( sPackageChannel.equals("beta") )
				    sb.append("--channel=1 ");
				else if ( sPackageChannel.equals("dev") )
				    sb.append("--channel=2 ");
				else if ( sPackageChannel.equals("canary") )
				    sb.append("--channel=3 ");

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

				if ( sIncludeObsolete.equals("true") )
				{
				    sb.append("--include_obsolete ");
				}
				
				if ( sPackageChannel.equals("stable") )
				    sb.append("--channel=0 ");
				else if ( sPackageChannel.equals("beta") )
				    sb.append("--channel=1 ");
				else if ( sPackageChannel.equals("dev") )
				    sb.append("--channel=2 ");
				else if ( sPackageChannel.equals("canary") )
				    sb.append("--channel=3 ");

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
            {
            }

/*
			if ( commandResultS == null )
				System.out.println("commandResultS null");
			else
				System.out.println("commandResultS: '"+commandResultS+"'");
/**/

            if ( (commandResultS != null) && (commandResultS.length() > 0) )
            {
                PackageAr = new ArrayList();
                InstalledAr = new ArrayList();
                UpdateAr = new ArrayList();
                
                iLoc3 = commandResultS.indexOf("Installed packages:");
                //if ( iLoc3 != -1 )
                    //System.out.println("Found Installed packages:");
                
                iLoc5 = commandResultS.indexOf("Available Packages:");
                //if ( iLoc5 != -1 )
                    //System.out.println("Found Available Packages:");
                
                iLoc4 = commandResultS.indexOf("Available Updates:");
                if ( iLoc4 != -1 )
                {
                    //System.out.println("Found Available Updates:");
                    bFoundUpdates = true;
                }
                else
                {
                    iLoc4 = commandResultS.length();
                }

                // Get installed packages..                
                iLoc6 = commandResultS.indexOf("Location", iLoc3);  // Past 'Installed packages:'..
                if ( iLoc6 != -1 )
                {
                    //System.out.println("Found Location");
                    iLoc7 = iLoc6;
                    for ( int iJ = 0;; iJ++ )
                    {
                        iLoc7 = commandResultS.indexOf(sStart, iLoc7);
                        if ( iJ == 0 )
                        {
                            // Skip first one..
                            iLoc7 += 2;
                            continue;
                        }
                        
                        if ( iLoc7 >= iLoc5 )
                            break;
                        
                        if ( iLoc7 != -1 )
                        {
                            // Skip to start..
                            iLoc7 += 3;
                            iStart = iLoc7;
                            
                            for ( ; ! Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );
                            sInstalled = commandResultS.substring(iStart, iLoc7);
                            //System.out.println("sInstalled: '"+sInstalled+"'");
                            InstalledAr.add((String)sInstalled);
                        }
                    }   // End while..
                }
                
                // Get available packages..
                iLoc6 = commandResultS.indexOf("Description", iLoc5);   // From 'Available Packages:'
                if ( iLoc6 != -1 )
                {
                    iLoc8 = commandResultS.indexOf("add-ons;", iLoc6);
                    if ( iLoc8 != -1 )
                    {
                        iLoc7 = iLoc8;
                        iStart = iLoc7;
                        while ( true )
                        {
                            for ( ; ! Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );
                            sPackage = commandResultS.substring(iStart, iLoc7);
                            //System.out.println("sPackage: '"+sPackage+"'");
                            PackageAr.add((String)sPackage);

                            // Next..
                            iLoc7 = commandResultS.indexOf(sStart, iLoc7);      // 0x0a 0x20 0x20
                            if ( iLoc7 == -1 )
                                break;
                            
                            for ( ; Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );
                            
                            if ( iLoc7 >= iLoc4 )   // 'Available Updates:'
                                break;

                            iStart = iLoc7;
                        }
                    }
                }
                        
                // Get available updates..
                if ( bFoundUpdates )
                {
                    iLoc8 = iLoc4;
                    for ( int iX = 0; iX < 3; iX++ )
                    {
                        iLoc8 = commandResultS.indexOf(sStart, iLoc8);
                        iLoc8 += 2;     // Next..
                    }
                    
                    iLoc7 = iLoc8;
                    for ( ; Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );
                    iStart = iLoc7;
                    
                    while ( true )
                    {
                        for ( ; ! Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );
                        sUpdate = commandResultS.substring(iStart, iLoc7);
                        //System.out.println("sUpdate: '"+sUpdate+"'");
                        UpdateAr.add((String)sUpdate);
    
                        // Next..
                        iLoc7 = commandResultS.indexOf(sStart, iLoc7);      // 0x0a 0x20 0x20
                        if ( iLoc7 == -1 )
                            break;
                        
                        for ( ; Character.isWhitespace(commandResultS.charAt(iLoc7)); iLoc7++ );
                        
                        if ( iLoc7 >= commandResultS.length() )   // 'Available Updates:'
                            break;
    
                        iStart = iLoc7;
                    }   // End while..
                }
            }

            //System.out.println("\nExiting GetPackagesBgThread");
            if ( operationRequestLatch != null )
                operationRequestLatch.countDown();
            
		}
	 }    //}}}

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
                
                sb.append(";sdkmanager --list ");

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
            {
            }

/*			
			if ( commandResultS == null )
				System.out.println("commandResultS null");
			else
				System.out.println("commandResultS: '"+commandResultS+"'");
/**/

            if ( (commandResultS != null) && (commandResultS.length() > 0) )
            {
                SystemImagesAr = new ArrayList();
                
                // Get to start..
                iLoc5 = commandResultS.indexOf("Installed packages:");
                iLoc6 = commandResultS.indexOf("Available Packages:");
                iLoc2 = iLoc5;
                
                while ( true )
                {
                    iLoc2 = commandResultS.indexOf("system-images;", iLoc2);
                    if ( iLoc2 != -1 )
                    {
                        // End at:  'Available Packages:'
                        if ( iLoc2 >= iLoc6 )
                            break;
                        
                        iStart = iLoc2;
                        iLoc3 = iLoc2;
                        for ( ; ! Character.isWhitespace(commandResultS.charAt(iLoc3)); iLoc3++ );
                        
                        sT = commandResultS.substring(iStart, iLoc3);
                        sT = sT.trim();
                        //System.out.println("(Add): '"+sT+"'");
                        SystemImagesAr.add((String)sT);
                    }
                    else
                        break;
                    
                    iLoc2 += 14;     // Next..
                }   // End while..              
            }

            if ( operationRequestLatch != null )
                operationRequestLatch.countDown();
            
		}
	 }    //}}}

	//{{{	RefreshProperties()
	private void RefreshProperties()
	{
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
			
		}
		catch (IOException ioe)
		{
			System.out.println("RefreshProperties() Exception");
			ioe.printStackTrace();
		}
		
		StringBuffer sB = new StringBuffer();
		sB.append("  ");
		sB.append(sSDKPath);
		statusLabel.setText(sB.toString());

	}    //}}}

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
            //Color green = new Color((int)0x7c, (int)0xfc, (int)0x00);   // LawnGreen
            Color green = new Color((int)0x7f, (int)0xff, (int)0x00);   // Chartreuse
            Color gold = new Color((int)0xff, (int)0xd7, (int)0x00);   // Gold
            boolean bUpdateMatch = false;
            
            Component c = super.getListCellRendererComponent(
                list,
                value,
                index,
                isSelected,
                cellHasFocus);
            
            //System.out.println("value.toString(): '"+value.toString()+"'");
            //System.out.println("index: "+index);
            

            if ( (InstalledAr != null) && (InstalledAr.size() > 0) )
            {
                for ( int iJ = 0; iJ < InstalledAr.size(); iJ++ )
                {
                    sT = (String)InstalledAr.get(iJ);
                    if ( sT.equals(value.toString()) )
                    {
                        bUpdateMatch = false;
                        if ( (UpdateAr != null) && (UpdateAr.size() > 0) )
                        {
                            for ( int iX = 0; iX < UpdateAr.size(); iX++ )
                            {
                                sT2 = (String)UpdateAr.get(iX);
                                if ( sT2.equals(sT) )
                                {
                                    bUpdateMatch = true;
                                    break;
                                }
                            }
                        }

                        if ( bUpdateMatch )
                            c.setBackground(gold);
                        else
                            c.setBackground(green);
                        
                        break;
                    }
                }
            }
            
            return c;
        }
    }   //}}}

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
		
		iFontSize = 13;
		Font defaultFont = new Font("Monospaced", Font.BOLD, iFontSize);
		consoleTextArea.setFont(defaultFont);
		
		consoleTextArea.setRows(20);
		consoleTextArea.setColumns(105);
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
		gbc.insets = new Insets(2, 2, 2, 2);	// top left bottom right
		
		statusBar.add(statusLabel, gbc);

		mainPanel.add(statusBar, BorderLayout.SOUTH);
		
	}    //}}}

	//{{{   InteractiveCommand
	 @SuppressWarnings("unchecked")
	class InteractiveCommand extends Thread
	{
		public void run()
		{
			//System.out.println("== InteractiveCommand run() ==");
			//System.out.println("sInternalCommand: '"+sInternalCommand+"'");
		    
            ProcessBuilder processBuilder;
            Process process = null;
            InputStream inputStream;
            InputStream errorStream;
            OutputStream outputStream;
            String sT = "";
            StringBuffer sBOut = null;
            StringBuffer sB;
            byte[] bBuf = new byte[1024];
            
            byte[] bZd = {(byte)0x0d};
            String sZeroD = new String(bZd);
            
            byte[] bReply = {(byte)0x79, (byte)0x0d, (byte)0x0a};   // 'y'
            byte[] bReplyDA = {(byte)0x0d, (byte)0x0a};     // Enter
            String sZeroDZeroA = new String(bReplyDA);
            
            int iBytesRead = 0;
            int iLoc2 = 0;
	    
            try
            {        
                processBuilder = new ProcessBuilder();
                
                if ( iOS == LINUX_MAC )
                    processBuilder.command("/bin/bash", "-c", sInternalCommand);
                else
                    processBuilder.command("cmd.exe", "/c", sInternalCommand);
                
                process = processBuilder.start();
            }
            catch (IOException ioe)
            {
            }
            
            inputStream = process.getInputStream();
            errorStream = process.getErrorStream();
            outputStream = process.getOutputStream();
            sBOut = new StringBuffer();
            consoleTextArea.setText("");
            
            try
            { 
                while ( true )
                {
                    iBytesRead = 0;
                    iBytesRead = inputStream.read(bBuf, 0, 1024);
                    //System.out.println("iBytesRead: "+iBytesRead);
                    if ( iBytesRead == -1 )
                    {
                        // EOF..
                        //System.out.println("Got EOF");
                        break;
                    }
                    else if ( iBytesRead > 0 )
                    {
                        sT = new String(bBuf, 0, iBytesRead);
                        //System.out.println("(input)"+sT);

                        sB = new StringBuffer(sT);
                        iLoc2 = 0;
                        while ( true )
                        {
                            iLoc2 = sB.indexOf(sZeroD, iLoc2);
                            if ( iLoc2 != -1 )
                            {
                                if ( (iLoc2 + 1 < sB.length()) )
                                {
                                    if ( sB.charAt(iLoc2 + 1) != (char)0x0a )
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
                            
                            iLoc2 += 2;    // Next..
                            if ( iLoc2 > sB.length() )
                                break;
                            
                        }   // End while..
                        
                        sT = sB.toString();
                        //System.out.println("(Modified input)"+sT);
                        
                        sBOut.append(sT);


/*                        
						System.out.println("\n\n");
						char cTChr;

                        for ( int g = 0; g < sT.length(); g++ )
                        {
                            cTChr = (char)sT.charAt(g);
                            if ( (cTChr < 0x21) || (cTChr > 0x7e) )
                                System.out.print("["+Integer.toHexString((int)cTChr)+"]");
                            else
                                System.out.print(cTChr);
                        }
						System.out.println("\n\n");
/**/                        

                        if ( bHideOutput == false )
                            consoleTextArea.append(sT);
                        
                        if ( sBOut.substring(sBOut.length() - 10, sBOut.length() - 1).contains("(y/N)") )
                        {
                            // Reply for accept license agreement
                            System.out.println("===Sending reply===");
/*                            
                            if ( acceptLicensesCheckBox == null )
                                System.out.println("acceptLicensesCheckBox null");
                            else
                                System.out.println("acceptLicensesCheckBox not null");
/**/

/*
                            if ( finalAcceptLicensesCheckBox == null )
                                System.out.println("finalAcceptLicensesCheckBox null");
                            else
                                System.out.println("finalAcceptLicensesCheckBox not null");
/**/                            
                            if ( ((acceptLicensesCheckBox != null) && (acceptLicensesCheckBox.isSelected())) ||
                                ((finalAcceptLicensesCheckBox != null) && (finalAcceptLicensesCheckBox.isSelected())) )
                            {
                                outputStream.write(bReply);     // 'y'
                            }
                            else
                            {
                                outputStream.write(bReplyDA);   // Enter (N)
                            }
                            
                            outputStream.flush();
                            
                            try
                            {
                                Thread.sleep(250);
                            }
                            catch (InterruptedException ie)
                            {
                            }
                            
                        }
                        else if ( sBOut.substring(sBOut.length() - 10, sBOut.length() - 1).contains("[no]") )
                        {
                            // Reply for create avd:  'Do you wish to create a custom hardware profile? [no]'
                            System.out.println("===Sending reply===");
                            outputStream.write(bReplyDA);   // Enter
                            outputStream.flush();
                            
                            try
                            {
                                Thread.sleep(250);
                            }
                            catch (InterruptedException ie)
                            {
                            }
                        }
                    }
                    else
                    {
                        iBytesRead = errorStream.read(bBuf, 0, 1024);
                        //System.out.println("iBytesRead: "+iBytesRead);
                        if ( iBytesRead == -1 )
                        {
                            // EOF..
                            //System.out.println("Got EOF");
                            break;
                        }
                        else
                        {
                            sT = new String(bBuf, 0, iBytesRead);
                            //System.out.println("(error)"+sT);
                            
                            sB = new StringBuffer(sT);
                            iLoc2 = 0;
                            while ( true )
                            {
                                iLoc2 = sB.indexOf(sZeroD, iLoc2);
                                if ( iLoc2 != -1 )
                                {
                                    if ( (iLoc2 + 1 < sB.length()) )
                                    {
                                        if ( sB.charAt(iLoc2 + 1) != (char)0x0a )
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
                                
                                iLoc2 += 2;    // Next..
                                if ( iLoc2 > sB.length() )
                                    break;
                                
                            }   // End while..
                            
                            sT = sB.toString();
                            
                            sBOut.append(sT);
                            
                            if ( bHideOutput == false )
                                consoleTextArea.append(sT);
                        }
                    }
                }   // End while..
            }
            catch (IOException ioe)
            {
            }
			finally
			{
				try
				{
				    if ( inputStream != null )
				        inputStream.close();
				    
				    if ( errorStream != null )
				        errorStream.close();
				    
				    if ( outputStream != null )
				        outputStream.close();
				}
				catch (IOException ioe)
				{
				}
			}
			
			process.destroy();
            
            commandResultS = sBOut.toString();
            
            if ( interactiveRequestLatch != null )
                interactiveRequestLatch.countDown();
            
		}
	}    //}}}

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
			
            byte[] bReply = {(byte)0x79, (byte)0x0d, (byte)0x0a};
            String sReply = new String(bReply);
            byte[] b0d0a = {(byte)0x0d, (byte)0x0a};
            String sEnd = new String(b0d0a);
            
            try
            {        
                processBuilder = new ProcessBuilder();
                
                if ( iOS == LINUX_MAC )
                    processBuilder.command("/bin/bash", "-c", sInternalCommand);
                else
                    processBuilder.command("cmd.exe", "/c", sInternalCommand);
                
                process = processBuilder.start();
            }
            catch (IOException ioe)
            {
            }
            
            inputStream = process.getInputStream();
            errorStream = process.getErrorStream();
            sBOut = new StringBuffer();
            
            try
            {        
                inputBufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                errorBufferedReader = new BufferedReader(new InputStreamReader(errorStream));
                
                consoleTextArea.setText("");
                
                while ( true )
                {
                    if ( inputBufferedReader.ready() )
                    {
                       sLine = inputBufferedReader.readLine();
                       if ( sLine == null )
                       {
                           //System.out.println("sLine null, breaking..");
                           break;
                       }
                       else
                       {
                           //System.out.println("(input): '"+sLine+"'");

                           sB = new StringBuffer(sLine);
                           sB.append(sEnd);
                           
                           sBOut.append(sB.toString());
 
/*
                            System.out.println("\n\n");
                            char cTChr;
                            
                            for ( int g = 0; g < sB.length(); g++ )
                            {
                                cTChr = (char)sB.charAt(g);
                                if ( (cTChr < 0x21) || (cTChr > 0x7e) )
                                    System.out.print("["+Integer.toHexString((int)cTChr)+"]");
                                else
                                    System.out.print(cTChr);
                            }
                            System.out.println("\n\n length(): "+sB.length());
/**/ 

                            if ( (bHideOutput == false) || (sShowCommandResults.equals("true")) )
                            {
                                consoleTextArea.append(sB.toString());
                            }
                       }
                    }
                    else if ( errorBufferedReader.ready() )
                    {
                        sLine = errorBufferedReader.readLine();
                        if ( sLine == null )
                        {
                            //System.out.println("sLine null, breaking..");
                            break;
                        }
                        else
                        {
                           //System.out.println("(error): '"+sLine+"'");
                           sB = new StringBuffer(sLine);
                           sB.append(sEnd);
                           
                            if ( (bHideOutput == false) || (sShowCommandResults.equals("true")) )
                            {
                                consoleTextArea.append(sB.toString());
                            }
                           
                           sBOut.append(sB.toString());
                        }
                    }
                    else
                    {
                        // Didn't get anything..
                        try
                        {
                            iExitVal = process.exitValue();
                            //System.out.println("iExitVal: "+iExitVal);
                            break;
                        }
                        catch (IllegalThreadStateException itse)
                        {
                        }
                    }
                }   // End while..
            }
            catch (IOException ioe)
            {
                System.out.println("InteractiveCommandBgThread Exception:");
                ioe.printStackTrace();
            }
			finally
			{
				try
				{
				    if ( inputBufferedReader != null )
				        inputBufferedReader.close();
				    
				    if ( inputStream != null )
				        inputStream.close();
				    
				    if ( errorBufferedReader != null )
				        errorBufferedReader.close();
				    
				    if ( errorStream != null )
				        errorStream.close();
				}
				catch (IOException ioe)
				{
				}
			}

			if ( process != null )
			{
			    process.destroy();
			    process = null;
			}
			
			commandResultS = sBOut.toString();
			
            if ( commandRequestLatch != null )
                commandRequestLatch.countDown();
			
        }
    }   //}}}

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
        byte[] bAr = {(byte)0x0d, (byte)0x0a};
        String sCR = new String(bAr);

		JPanel panel = new JPanel(new GridBagLayout());
		textAreaPanel = new JPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(4, 4, 4, 4);	// External padding of the component

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
        
        if ( (AVDsAr != null) && (AVDsAr.size() > 0) )
        {
             //System.out.println("iAdvSelectedIndex: "+iAdvSelectedIndex);
            aVDInfo = (AVDInfo)AVDsAr.get(iAdvSelectedIndex);
            if ( (aVDInfo.sName != null) && (! aVDInfo.sName.equals("")) )
            {
                avdTextArea.append("Name:  ");
                avdTextArea.append(aVDInfo.sName);
                avdTextArea.append("\n");
            }
            
            if ( (aVDInfo.sDevice != null) && (! aVDInfo.sDevice.equals("")) )
            {
                avdTextArea.append("Device:  ");
                avdTextArea.append(aVDInfo.sDevice);
                avdTextArea.append("\n");
            }
    
            if ( (aVDInfo.sPath != null) && (! aVDInfo.sPath.equals("")) )
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
            
            if ( (aVDInfo.sTarget != null) && (! aVDInfo.sTarget.equals("")) )
            {
                avdTextArea.append("Target:  ");
                avdTextArea.append(aVDInfo.sTarget);
                avdTextArea.append("\n");
            }
    
            if ( (aVDInfo.sBasedOn != null) && (! aVDInfo.sBasedOn.equals("")) )
            {
                sB = new StringBuffer();
                sB.append("Based on:  ");
                sB.append(aVDInfo.sBasedOn);
                sADV_BasedOn = aVDInfo.sBasedOn;    // Save for ABI..
                
                avdTextArea.append(sB.toString());
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
		
	}    //}}}

	//{{{   packageDialog()
	 @SuppressWarnings("unchecked")
	public void packageDialog()
	{
	    //System.out.println("packageDialog()");
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
		gbc.insets = new Insets(4, 4, 4, 4);	// External padding of the component

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
            tSa[iJ] = (String)PackageAr.get(iJ);
            //System.out.println("["+iJ+"]: '"+tSa[iJ]+"'");
        }
        
         packageJList = new JList(tSa);
        packageJList.setCellRenderer(new ColorCellRenderer());
        packageJList.setVisibleRowCount(8);
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

		updateCheckBox = new JCheckBox("  Update all");
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
	    
	}    //}}}

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
		gbc.insets = new Insets(4, 4, 4, 4);	// External padding of the component

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
	    
	}    //}}}
	
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
		gbc.insets = new Insets(4, 4, 4, 4);	// External padding of the component

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
	    
	}    //}}}
	
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
            sB.append(sSDKPath);
            sB.append("/tools");
            //System.out.println("(tools)sB.toString(): '"+sB.toString()+"'");
            File tFile = new File(sB.toString());
            if ( tFile.exists() )
            {
                //System.out.println("tools exists");
                sToolsDir = "tools";
            }
            else
            {
                sB = new StringBuffer();
                sB.append(sSDKPath);
                sB.append("/cmdline-tools");
                //System.out.println("(cmdline-tools)sB.toString(): '"+sB.toString()+"'");
                tFile = new File(sB.toString());
                if ( tFile.exists() )
                {
                    sToolsDir = "cmdline-tools";
                }
            }
			
			if ( CREATE_ADV.equals(sActionCommand) )
			{
			    createThread = new CreateThread();
			    createThread.start();
			}
			else if ( CREATE_SUBMIT.equals(sActionCommand) )
			{
			    //System.out.println("CREATE_SUBMIT");
			    
                StringBuffer sb = new StringBuffer();
                String sName = "";
                String sSystemImage = "";
                String sDevice = "";
                //String sT = "";
                int[] iAr;
                int iLoc2 = 0;
                boolean bIsSelected;
                ListModel model;
                
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

                sb.append('"');                    
                sb.append(sName);
                sb.append('"');
                
                // Note:
                // Seems like by including the --abi option
                // once it creates the AVD, the first time you
                // start it, all the directories and files for that AVD
                // are added and the Emulator will launch..
                
                sb.append(" --abi ");
                
                iAr = sIJList.getSelectedIndices();
                model = sIJList.getModel();
                sSystemImage = (String)model.getElementAt(iAr[0]);
                //System.out.println("sSystemImage: '"+sSystemImage+"'");

                sT = sSystemImage.substring(25); 
                //System.out.println("sT: '"+sT+"'");
                sB = new StringBuffer(sT);
                iLoc2 = sB.indexOf(";");
                if ( iLoc2 != -1 )
                {
                    sB.setCharAt(iLoc2, (char)0x2f);
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
                sDevice = (String)model.getElementAt(iAr[0]);
                //System.out.println("sDevice: '"+sDevice+"'");
                
                sb.append('"');
                sb.append(sDevice);
                sb.append('"');
                
                bIsSelected = forceCheckBox.isSelected();
                if ( bIsSelected )
                {
                    sb.append(" -f");
                }
                
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
                {
                }

			    createFrame.setVisible(false);
			    createFrame.dispose();

			}
			else if ( CREATE_CANCEL.equals(sActionCommand) )
			{
			    createFrame.setVisible(false);
			    createFrame.dispose();
			}
			else if ( ACCEPT_LICENSES.equals(sActionCommand) )
			{
			    RefreshProperties();
			    acceptLicensesDialog();
			}
			else if ( ACCEPT_LICENSES_SUBMIT.equals(sActionCommand) )
			{
			    //System.out.println("ACCEPT_LICENSES_SUBMIT");
                StringBuffer sb;
                
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
                    
                    sb.append(";sdkmanager --licenses ");

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
                {
                }
                
                acceptLicensesFrame.setVisible(false);
                acceptLicensesFrame.dispose();
			}
			else if ( ACCEPT_LICENSES_CANCEL.equals(sActionCommand) )
			{
			    acceptLicensesFrame.setVisible(false);
			    acceptLicensesFrame.dispose();
			}
			else if ( AVDS.equals(sActionCommand) )
			{
			    //System.out.println("\nAVDS");
			    aVDsThread = new AVDsThread();
			    aVDsThread.start();
			}
			else if ( AVDS_SUBMIT.equals(sActionCommand) )
			{
			    //System.out.println("AVDS_SUBMIT");
                StringBuffer sb = new StringBuffer();
                String sAVDName = "";
                //String sT = "";
                String[] tSa;
                int iLength;
                int iSz;
                int[] iAr;
                boolean bIsStartSelected;
                boolean bIsDeleteSelected;
                boolean bDoCommand = false;
                ListModel model;
                AVDInfo aVDInfo;
                
                sb = new StringBuffer();
                
                bIsStartSelected = startCheckBox.isSelected();
                //System.out.println("bIsStartSelected: "+bIsStartSelected);
                if ( bIsStartSelected )
                {
                    bDoCommand = true;
                    
                    if ( iOS == LINUX_MAC )
                    {
                        sb.append("cd ");
                        //sb.append("export PATH=${PATH}:");
                        sb.append(sSDKPath);
                        sb.append("/");
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
                    sAVDName = (String)model.getElementAt(iAr[0]);
                    //System.out.println("sAVDName: '"+sAVDName+"'");
                    
                    sb.append('"');
                    sb.append(sAVDName);
                    sb.append('"');
                    
                    if ( iOS == WINDOWS )
                        sb.append("\n");
                }

                bIsDeleteSelected = deleteCheckBox.isSelected();
                //System.out.println("bIsDeleteSelected: "+bIsDeleteSelected);
                if ( bIsDeleteSelected )
                {
                    bDoCommand = true;
                    
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
                    sAVDName = (String)model.getElementAt(iAr[0]);
                    //System.out.println("sAVDName: '"+sAVDName+"'");
                    
                    sb.append('"');
                    sb.append(sAVDName);
                    sb.append('"');

                    if ( iOS == WINDOWS )                    
                        sb.append("\n");
                }

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
                    {
                    }
                    
                }

			    avdsFrame.setVisible(false);
			    avdsFrame.dispose();
                
			}
			else if ( AVDS_CANCEL.equals(sActionCommand) )
			{
			    avdsFrame.setVisible(false);
			    avdsFrame.dispose();
			}
			else if ( PACKAGES.equals(sActionCommand) )
			{
			    //System.out.println("\nPACKAGES");
			    packagesThread = new PackagesThread();
			    packagesThread.start();
			    
			}
			else if ( PACKAGE_SUBMIT.equals(sActionCommand) )
			{
			    //System.out.println("PACKAGE_SUBMIT");
			    int[] iSelAr = packageJList.getSelectedIndices();
			    int iLength;
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
                            //System.out.println("sImage: '"+sImage+"'");
                            
                            sb.append('"');
                            sb.append(sImage);
                            sb.append('"');
                            sb.append(" ");
                        }
                    }
                }
                
                //System.out.println("bDoChannels: "+bDoChannels);
                if ( bDoChannels )
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
                
                sb.append("--sdk_root=");
                sb.append(sSDKPath);
                    
                if ( iOS == WINDOWS )
                    sb.append("\n");

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
                {
                }

			    packageFrame.setVisible(false);
			    packageFrame.dispose();
                
			}
			else if ( PACKAGE_CANCEL.equals(sActionCommand) )
			{
			    packageFrame.setVisible(false);
			    packageFrame.dispose();
			}
		}
	};    //}}}

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
            byte[] bAr = {(byte)0x0d, (byte)0x0a};
            String sCR = new String(bAr);

            if ( bValueIsAdjusting == false )
            {
                int[] iSelAr = avdJList.getSelectedIndices();   
                if ( (iSelAr != null) && (iSelAr.length > 0) )
                {
                    iAdvSelectedIndex = iSelAr[0];
                    //System.out.println("iAdvSelectedIndex: "+iAdvSelectedIndex);
                    
                }
                
                avdTextArea.selectAll();
                avdTextArea.replaceSelection("");

                aVDInfo = (AVDInfo)AVDsAr.get(iAdvSelectedIndex);
                
                if ( (aVDInfo.sName != null) && (! aVDInfo.sName.equals("")) )
                {
                    avdTextArea.append("Name:  ");
                    avdTextArea.append(aVDInfo.sName);
                    avdTextArea.append("\n");
                }
                
                if ( (aVDInfo.sDevice != null) && (! aVDInfo.sDevice.equals("")) )
                {
                    avdTextArea.append("Device:  ");
                    avdTextArea.append(aVDInfo.sDevice);
                    avdTextArea.append("\n");
                }
        
                if ( (aVDInfo.sPath != null) && (! aVDInfo.sPath.equals("")) )
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
                
                if ( (aVDInfo.sTarget != null) && (! aVDInfo.sTarget.equals("")) )
                {
                    avdTextArea.append("Target:  ");
                    avdTextArea.append(aVDInfo.sTarget);
                    avdTextArea.append("\n");
                }
        
                if ( (aVDInfo.sBasedOn != null) && (! aVDInfo.sBasedOn.equals("")) )
                {
                    sB = new StringBuffer();
                    sB.append("Based on:  ");
                    sB.append(aVDInfo.sBasedOn);
                    sADV_BasedOn = aVDInfo.sBasedOn;    // Save for ABI..
                    
                    avdTextArea.append(sB.toString());
                }
                
                avdsFrame.invalidate();
                avdsFrame.validate();
                avdsFrame.repaint();
            }
		}
    };  //}}}
    
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
	};	//}}}
	
	//{{{	processPath()
	/**
     * Remove double quotes
     * Flip '\' -> '/'
     */
	String processPath(String inS)
	{
	    //System.out.println("\nprocessPath()");
		StringBuffer sb = null;

		if ( (inS != null) && (! inS.equals("")) )
			sb = new StringBuffer(inS);
		else
			return inS;
		
		for ( int g = 0; g < sb.length(); )
		{
			if ( sb.charAt(g) == '\\' )
				sb.setCharAt(g, '/');
			else if ( sb.charAt(g) == (char)'"' )
			{
				sb.deleteCharAt(g);
				continue;
			}
			
			g++;
		}

        //System.out.println("(Returning)sb: "+sb.toString());		
		return sb.toString();
	}	//}}}

	//{{{    AVDInfo
	class AVDInfo
	{
        String sName;       // 'android21s'
        String sDevice;     // 'Nexus S (Google)'
        String sPath;       // 'C:\Users\Joe Siebenmann\.android\avd\android21s.avd'
        String sTarget;
        String sBasedOn;    // 'Android 5.0 (Lollipop) Tag/ABI: default/armeabi-v7a'	    
	    
	}    //}}}
	
	//{{{	main()	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater( new Runnable()
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
	}	//}}}

}

 
