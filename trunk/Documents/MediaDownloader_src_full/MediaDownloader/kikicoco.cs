using System;
using System.IO;
using System.Net;
using System.Text;

namespace MediaDownloader
{
	/// <summary>
	/// Summary description for kikicoco.
	/// </summary>
	public class kikicoco
	{
		public kikicoco()
		{
			//
			// TODO: Add constructor logic here
			//

		}
		public static String GetHtmlContent(string inputUrl)
		{
			StreamReader streamReader=null;
			string outputString="";
			try
			{
				WebRequest myRequest = WebRequest.Create(inputUrl);
				WebResponse myResponse = myRequest.GetResponse();
				streamReader =new StreamReader(myResponse.GetResponseStream(),Encoding.UTF8);
				outputString = streamReader.ReadToEnd( );
				streamReader.Close( );
			}
			catch
			{
				outputString="Có lỗi xảy ra!";
			}
			//Response.Write(outputString);
			return outputString;
		}
		public static string GetHtmlContent3(string url, string headers)
		{
			HttpWebRequest req =null;
			try
			{
				req = (HttpWebRequest) HttpWebRequest.Create(url);
			}
			catch (Exception err)
			{
			
			}
			if (headers.Length > 0)
			{
				string[] lines = headers.Split('\n');
				foreach (string line in lines)
				{
					int pos = line.IndexOf(':');
					if (pos != -1)
					{
						string header = line.Substring(0, pos).Trim();
						string headerValue = line.Substring(pos + 1).Trim();
						try
						{
							req.Headers.Add(header, headerValue);
						}
						catch (ArgumentException)
						{
							
						}
					}
				}
			}
			HttpWebResponse resp = null;
			try
			{			
				resp = (HttpWebResponse) req.GetResponse();
			}
			catch (WebException err)
			{
				
			}
			catch (Exception err)
			{
				
			}
			Stream rcvStream = resp.GetResponseStream();
			string respContent="";
			byte[] respBytes = new byte[10240];
			int byteCount;
			do
			{
				byteCount = rcvStream.Read(respBytes, 0, 10240);
				respContent += Encoding.ASCII.GetString(respBytes, 0, byteCount);
			} while (byteCount > 0);
			resp.Close();
			rcvStream.Close();		
			return respContent;
		}

	}
}
