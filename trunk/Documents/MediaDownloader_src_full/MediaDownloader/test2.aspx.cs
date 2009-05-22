using System;
using System.Text.RegularExpressions;
using System.Web.UI;

namespace MediaDownloader
{
	/// <summary>
	/// Summary description for test2.
	/// </summary>
	public class test2 : Page
	{
		private void Page_Load(object sender, EventArgs e)
		{
			String test1 ="Cookie: PHPSESSID=3345df7da0f34009f30939cd5b913b0e";
			String url ="http://muzic9.com/index.php?c=song&songid=193117";
			Response.Write(GetLinkMuzic9(url,test1)); 
			//String test1 ="Cookie: Active=; Forum=LV=2006%2D11%2D17+08%3A33%3A22&SID=8fa4d16b8cd72e78fd1f2z995fbzdzb3&UID=giangtatdatC287DDAD7623EC1; __utma=207194510.130811125.1163411054.1163673136.1163727216.5; __utmz=207194510.1163664106.3.3.utmccn=(referral)|utmcsr=mail.google.com|utmcct=/mail/|utmcmd=referral; ASPSESSIONIDASBDCQAQ=LBONINOBEOGNPPGNIPPIDHBP; __utmb=207194510; __utmc=207194510";
			//Response.Write(kikicoco.GetHtmlContent3("http://loitraitim.com/music/player.asp?id=3544",test1)); 
			//String test1 ="Cookie:__utmz=67528257.1163499743.6.5.utmccn=(referral)|utmcsr=media.ajaxviet.com|utmcct=/|utmcmd=referral; __utma=67528257.1626810265.1153401116.1162519792.1163499743.6; __utma=143770342.1173687654.1153378286.1163659941.1163725467.219; t11235v3=RS0:1213148138171217193|DM0:blog.ajaxviet.com; homepage=http://blog.ajaxviet.com; .DASBLOGAUTH=8A5D514ADCD517FD05D844881A2DBF85AA8D6A7CB2E388ADC59D47340317668BF682C4DA80CDA130D32A0D32527781B64165BF851235A005749854200E8801CB3DF020CA403247E4; __utmz=143770342.1163725467.219.70.utmccn=(referral)|utmcsr=70.87.192.34:32000|utmcct=/mail/view.html|utmcmd=referral; name=Pham Duc Hai; email=duchaikhtn@gmail.com; ASP.NET_SessionId=snhac3343kluhg45uv0pcg55; __utmb=143770342; __utmc=143770342";
			//Response.Write(kikicoco.GetHtmlContent3("http://blog.ajaxviet.com/default.aspx",test1)); 
		}
		private string GetLinkMuzic9(String URL,String headers)
		{
			//http://muzic9.com/index.php?c=song&songid=193117
			String link="";
			link = kikicoco.GetHtmlContent3(URL,headers);
			try
			{
				string regCode ="(?<=play_wma\\(').*(?=\\'\\))";
				Regex theRegex = new Regex(regCode,RegexOptions.IgnoreCase);
				MatchCollection MatchResult = theRegex.Matches(link);
				foreach (Match match in MatchResult)
				{
					link =match.Value;
					break;
				}
			}
			catch 
			{
				link ="Có lỗi xảy ra hoặc địa chỉ không đúng.";
				return link;
			}
			try
			{
				link = kikicoco.GetHtmlContent3(link,headers);
				string regCode ="(?<=href\\=\\\").*(?=\\\")";
				Regex theRegex = new Regex(regCode,RegexOptions.IgnoreCase);
				MatchCollection MatchResult = theRegex.Matches(link);
				foreach (Match match in MatchResult)
				{
					link =match.Value;
					break;
				}
			}
			catch 
			{
				link ="Có lỗi xảy ra hoặc địa chỉ không đúng.";
				return link;
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
