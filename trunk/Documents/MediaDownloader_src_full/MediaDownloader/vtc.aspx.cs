using System;
using System.Text.RegularExpressions;
using System.Web.UI;

namespace MediaDownloader
{
	/// <summary>
	/// Summary description for vtc.
	/// </summary>
	public class vtc : Page
	{
		private void Page_Load(object sender, EventArgs e)
		{
			string url = Request["url"];
			Response.Write(ProcessVtc(url));
		
		}
		public string ProcessVtc(string url)
		{
			//href="#" onclick="MPPlay('/news/truyenhinhtructuyen/detail3278.asx')
			String content="";
			string link="";
			String content_temp="";
			if (url.ToLower().IndexOf("http://www.vtc.com.vn")==0)
			{
				content = kikicoco.GetHtmlContent(url);
				content_temp=content;
				try
				{
					string regCode ="href=\"#\"\\s+onclick=\"MPPlay(?<=).*(?=\\;)";
					Regex theRegex = new Regex(regCode,RegexOptions.IgnoreCase);
					MatchCollection MatchResult = theRegex.Matches(content);
					foreach (Match match in MatchResult)
					{
						String temp = match.Value;
						String replacement="";
						link = GetLinkMPPlay(temp);
						replacement ="href="+kikicoco.GetHtmlContent(link)+" onclick=\"MPPlay('"+link+"')";
						Regex.Replace(content_temp,temp,replacement);
					}
				}
				catch 
				{
					content="Không thể kết nới tới www.vtc.com.vn";
				}
			}
			else
			{
				content ="Úi úi! Xin đại ka tha cho em !!!";
			}
			return content;
		}
		public string GetLinkVtc(string URL)
		{
			//MPPlay('/news/chuongtrinhvtv1/tintuc-sukien/sukiennoibat/detail5503.asx');
			String link="";
			try
			{
				link = kikicoco.GetHtmlContent(URL);
			}
			catch 
			{
			}
			return link;
		}
		public string GetLinkMPPlay(string input)
		{
			//href="#" onclick="MPPlay('/news/truyenhinhtructuyen/detail3278.asx')
			string link="";
			try
			{
				string regCode ="(?<=MpPlay\\(').*(?=\\'\\))";
				Regex theRegex = new Regex(regCode,RegexOptions.IgnoreCase);
				MatchCollection MatchResult = theRegex.Matches(input);
				foreach (Match match in MatchResult)
				{
					link ="http://www.vtc.com.vn/" +match.Value;
					break;
				}
			}
			catch 
			{

			}
			return link;
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
			this.Load += new EventHandler(this.Page_Load);
		}
		#endregion
	}
}
