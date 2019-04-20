package tests.internal;

import java.util.ArrayList;
import java.util.ListIterator;

import net.sourceforge.usbdm.deviceDatabase.DeviceDatabase;
import net.sourceforge.usbdm.deviceDatabase.IDatabaseListener;
import net.sourceforge.usbdm.jni.Usbdm;
import net.sourceforge.usbdm.jni.Usbdm.TargetType;
import net.sourceforge.usbdm.jni.Usbdm.USBDMDeviceInfo;


public class DeviceDatabaseTest {

   static class TestClass implements IDatabaseListener {
      DeviceDatabase database = null;
      void doTest(TargetType targetType) {
         try {
            ArrayList<net.sourceforge.usbdm.jni.Usbdm.USBDMDeviceInfo> deviceList = Usbdm.getDeviceList();
            ListIterator<Usbdm.USBDMDeviceInfo> it = deviceList.listIterator();
            while (it.hasNext()) {
               USBDMDeviceInfo di = it.next();
               System.err.println("Device \n" + di);
            }
            database = new DeviceDatabase(targetType, this);
            database.listDevices(System.err, false);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }

      @Override
      public void databaseLoaded(DeviceDatabase database) {
         database.listDevices(System.err, true);
      }
   }

   /**
    * @param args
    */
   public static void main(String[] args) {
      System.err.println("Application Path : " + Usbdm.getApplicationPath().toPortableString());
      System.err.println("Resource Path    : " + Usbdm.getResourcePath().toPortableString());
      System.err.println("Data Path        : " + Usbdm.getDataPath().toPortableString());

      TestClass testClass = new TestClass();

      TargetType[] targetTypes = {
            TargetType.T_ARM,  
            //          TargetType.T_CFV1, 
            //          TargetType.T_CFVx, 
            //            TargetType.T_MC56F80xx,
      };
      for (TargetType targetType:targetTypes) {
         testClass.doTest(targetType);
         try {
            Thread.sleep(5000);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }
}
