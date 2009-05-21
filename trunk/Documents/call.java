<xmp>UNKNOWN

//**************************************
//HTML for :Call exe from java
//**************************************
public class Main {

    {
    	public static void main(String[] args) 

        	{
        		try

            		{
            			Runtime rt=Runtime.getRuntime();
            			
            			//(A)	Calling a html from Java
            			
            			//1St Way
            				rt.exec("C:/consonle.exe");
            		
            			/* 2nd Way
            					
            						String[] cmd = new String[4];
            						cmd[0] = "cmd.exe";
            						cmd[1] = "/C";
            						cmd[2] = "start";
            						cmd[3] = "C://j2sdk1.4.2_03//bin//nil.html";
            			*/
            			//(B) Calling an .exe from java
            			rt.exec("C:/consonle.exe");
            		}
            		catch(Throwable t)

                		{
                			System.out.print(t.getMessage());
                		
                		}
                	}
            }
	}	
		