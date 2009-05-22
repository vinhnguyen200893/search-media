using System;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Text.RegularExpressions;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace MediaDownloader.Test
{
	/// <summary>
	/// Summary description for test.
	/// </summary>
	public class test : Page
	{
		protected TextBox txtUrl;
		protected Button cmdGetContent;
		protected TextBox txtContent;
	
		private void Page_Load(object sender, EventArgs e)
		{
			// Put user code to initialize the page here
		}

		#region Web Form Designer generated code
		override protected void OnInit(EventArgs e)
		{
			//
			// CODEGEN: This call is required by the ASP.NET Web Form Designer.
			//
			InitializeComponent();
			base.OnInit(e);
		}
		
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{    
			this.cmdGetContent.Click += new EventHandler(this.cmdGetContent_Click);
			this.Load += new EventHandler(this.Page_Load);

		}
		#endregion

		private void cmdGetContent_Click(object sender, EventArgs e)
		{
			txtContent.Text="";
			String link = txtUrl.Text.Trim();
			link="http://sonic200.com/front/music/listening.aspx?zumi=18920";
			txtContent.Text=GetWebContent(link)+ GetHtmlContent(link);
			
		}
		public String GetWebContent(string inputUrl)
		{
			StreamReader streamReader=null;
			string outputString="";
			try
			{
				WebRequest myRequest = WebRequest.Create(inputUrl);
				WebResponse myResponse = myRequest.GetResponse();
				streamReader =new StreamReader(myResponse.GetResponseStream(),Encoding.UTF8);
				outputString = streamReader.ReadToEnd( );
			}
			catch (Exception ex)
			{
				streamReader.Close( );
				outputString="Có lỗi xảy ra!";
			}
			streamReader.Close( );
			//Response.Write(outputString);
			return outputString;
		}
		public String GetHtmlContent(string inputUrl)
		{
			string outputString="";
			// used to build entire input
			StringBuilder sb  = new StringBuilder();
			// used on each read operation
			byte[]        buf = new byte[8192];
			// prepare the web page we will be asking for
			HttpWebRequest  request  = (HttpWebRequest)
				WebRequest.Create(inputUrl);
			// execute the request
			HttpWebResponse response = (HttpWebResponse)
				request.GetResponse();
			// we will read data via the response stream
			Stream resStream = response.GetResponseStream();
			string tempString = null;
			int    count      = 0;
			do
			{
				// fill the buffer with data
				count = resStream.Read(buf, 0, buf.Length);
				// make sure we read some data
				if (count != 0)
				{
					// translate from bytes to ASCII text
					tempString = Encoding.ASCII.GetString(buf, 0, count);
					// continue building the string
					sb.Append(tempString);
				}
			}
			while (count > 0); // any more data to read?
			// print out page source
			outputString = sb.ToString();
			return outputString;
		}
	}
	public class MyUri : Uri
	{
		public MyUri(string uriString):base(uriString)
		{
		}
		public int Depth;
	}

	public class MyWebRequest
	{
		public MyWebRequest(Uri uri, bool bKeepAlive)
		{
			Headers = new WebHeaderCollection();
			RequestUri = uri;
			Headers["Host"] = uri.Host;
			KeepAlive = bKeepAlive;
			if(KeepAlive)
				Headers["Connection"] = "Keep-Alive";
			Method = "GET";
		}
		public static MyWebRequest Create(Uri uri, MyWebRequest AliveRequest, bool bKeepAlive)
		{
			if( bKeepAlive &&
				AliveRequest != null &&
				AliveRequest.response != null &&
				AliveRequest.response.KeepAlive && 
				AliveRequest.response.socket.Connected && 
				AliveRequest.RequestUri.Host == uri.Host)
			{
				AliveRequest.RequestUri = uri;
				return AliveRequest;
			}
			return new MyWebRequest(uri, bKeepAlive);
		}
		public MyWebResponse GetResponse()
		{
			if(response == null || response.socket == null || response.socket.Connected == false)
			{
				response = new MyWebResponse();
				response.Connect(this);
				response.SetTimeout(Timeout);
			}
			response.SendRequest(this);
			response.ReceiveHeader();
			return response;
		}

		public int Timeout;
		public WebHeaderCollection Headers;
		public string Header;
		public Uri RequestUri;
		public string Method;
		public MyWebResponse response;
		public bool KeepAlive;
	}
	public class MyWebResponse
	{
		public MyWebResponse()
		{
		}
		public void Connect(MyWebRequest request)
		{
			ResponseUri = request.RequestUri;
			
			socket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
			IPEndPoint remoteEP = new IPEndPoint(Dns.Resolve(ResponseUri.Host).AddressList[0], ResponseUri.Port);
			socket.Connect(remoteEP);			
		}
		public void SendRequest(MyWebRequest request)
		{
			ResponseUri = request.RequestUri;

			request.Header = request.Method+" "+ResponseUri.PathAndQuery+" HTTP/1.0\r\n"+request.Headers;
			socket.Send(Encoding.ASCII.GetBytes(request.Header));
		}
		public void SetTimeout(int Timeout)
		{
			socket.SetSocketOption(SocketOptionLevel.Socket, SocketOptionName.SendTimeout, Timeout*1000);
			socket.SetSocketOption(SocketOptionLevel.Socket, SocketOptionName.ReceiveTimeout, Timeout*1000);
		}
		public void ReceiveHeader()
		{
			Header = "";
			Headers = new WebHeaderCollection();

			byte[] bytes = new byte[10];
			while(socket.Receive(bytes, 0, 1, SocketFlags.None) > 0)
			{
				Header += Encoding.ASCII.GetString(bytes, 0, 1);
				if(bytes[0] == '\n' && Header.EndsWith("\r\n\r\n"))
					break;
			}
			MatchCollection matches = new Regex("[^\r\n]+").Matches(Header.TrimEnd('\r', '\n'));
			for(int n = 1; n < matches.Count; n++)
			{
				string[] strItem = matches[n].Value.Split(new char[] { ':' }, 2);
				if(strItem.Length > 0)
					Headers[strItem[0].Trim()] = strItem[1].Trim();
			}
			// check if the page should be transfered to another location
			if( matches.Count > 0 && (
				matches[0].Value.IndexOf(" 302 ") != -1 || 
				matches[0].Value.IndexOf(" 301 ") != -1))
				// check if the new location is sent in the "location" header
				if(Headers["Location"] != null)
				{
					try		{	ResponseUri = new Uri(Headers["Location"]);		}
					catch	{	ResponseUri = new Uri(ResponseUri, Headers["Location"]);		}
				}
			ContentType = Headers["Content-Type"];
			if(Headers["Content-Length"] != null)
				ContentLength = int.Parse(Headers["Content-Length"]);
			KeepAlive = (Headers["Connection"] != null && Headers["Connection"].ToLower() == "keep-alive") ||
				(Headers["Proxy-Connection"] != null && Headers["Proxy-Connection"].ToLower() == "keep-alive");
		}
		public void Close()
		{
			socket.Close();
		}
		public Uri ResponseUri;
		public string ContentType;
		public int ContentLength;
		public WebHeaderCollection Headers; 
		public string Header;
		public Socket socket;
		public bool KeepAlive;
	}
}
