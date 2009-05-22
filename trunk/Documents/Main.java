/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

import java.io.File;

/**
 *
 * @author nganguyen
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try
    {
                    Runtime rt=Runtime.getRuntime();

                    //(A)	Calling a html from Java

                    //1St Way
                        //rt.exec("cmd.exe /C start C:/1.html");

                    /* 2nd Way

                                String[] cmd = new String[4];
                                cmd[0] = "cmd.exe";
                                cmd[1] = "/C";
                                cmd[2] = "start";
                                cmd[3] = "C://j2sdk1.4.2_03//bin//nil.html";
                    */
                    //(B) Calling an .exe from java
                    String path=System.getProperty("user.dir");//+"/service/getlink.exe");
                    path+="/service/getlink.exe";            		
                    rt.exec(path);
                    rt.freeMemory();
                    rt.exit(1);                  

            		}
            		catch(Throwable t)


                		{
                			System.out.print(t.getMessage());

                		}
                	}
    }

