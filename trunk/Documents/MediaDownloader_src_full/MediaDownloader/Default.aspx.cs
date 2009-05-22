using System;
using System.Configuration;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Text.RegularExpressions;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using System.Xml;

namespace MediaDownloader
{
	/// <summary>
	/// Summary description for media.
	/// </summary>
	public class media : Page
	{
		protected ImageButton cmdDownload;
		protected System.Web.UI.WebControls.Label lblLink;
		protected System.Web.UI.WebControls.Label lblNews;
		protected System.Web.UI.WebControls.TextBox txtUrl;
		protected HtmlForm Form1;
	
		private void Page_Load(object sender, EventArgs e)
		{
			// Put user code to initialize the page here
			lblNews.Text=GetHtmlContent("http://media.ajaxviet.com/hotnews.html");
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
			this.cmdDownload.Click += new System.Web.UI.ImageClickEventHandler(this.cmdDownload_Click);
			this.Load += new System.EventHandler(this.Page_Load);

		}
		#endregion

		private void cmdDownload_Click(object sender, ImageClickEventArgs e)
		{
			String type="";//nhacso,i4vn,sonic
			lblLink.Text ="";
			if(txtUrl.Text=="")
			{
				lblLink.Text="Ô địa chỉ rỗng hoặc không hợp lệ.";
			}
			else
			{
				if (txtUrl.Text.ToLower().IndexOf("nhacso.net")>0) type="nhacso";
				if (txtUrl.Text.ToLower().IndexOf("i4vn.com.vn")>0) type="i4vn";
				if (txtUrl.Text.ToLower().IndexOf("sonic.vn")>0) type="sonic";
				if (txtUrl.Text.ToLower().IndexOf("bennhac.com")>0) type="bennhac";
				if (txtUrl.Text.ToLower().IndexOf("vnmusic.com.vn")>0) type="vnmusic";
				if (txtUrl.Text.ToLower().IndexOf("docbao.dec.vn")>0) type="docbao";
				if (txtUrl.Text.ToLower().IndexOf("giaidieu.net")>0) type="giaidieu";
				if (txtUrl.Text.ToLower().IndexOf("vuilen.com")>0) type="vuilen";
				if (txtUrl.Text.ToLower().IndexOf("baokhanh.biz")>0) type="baokhanh";
				if (txtUrl.Text.ToLower().IndexOf("nhacyeu.info")>0) type="nhacyeu";
				if (txtUrl.Text.ToLower().IndexOf("enhac.net")>0) type="enhac.net";
				if (txtUrl.Text.ToLower().IndexOf("nhacnen.net")>0) type="nhacnen";
				if (txtUrl.Text.ToLower().IndexOf("tuoitho.net")>0) type="tuoitho";
				if (txtUrl.Text.ToLower().IndexOf("muzic9.com")>0) type="muzic9";
				if (txtUrl.Text.ToLower().IndexOf("vietvoice.net")>0) type="vietvoice";
				if (txtUrl.Text.ToLower().IndexOf("cuasoamnhac.net")>0) type="cuasoamnhac";
				if (txtUrl.Text.ToLower().IndexOf("hoathachthao.info")>0) type="hoathachthao";
				if (txtUrl.Text.ToLower().IndexOf("tialia.net")>0) type="tialia";
				if (txtUrl.Text.ToLower().IndexOf("notnhactre.net")>0) type="notnhactre";
				if (txtUrl.Text.ToLower().IndexOf("nhacviet.vietnamnet.vn")>0) type="vietnamnet";
				if (txtUrl.Text.ToLower().IndexOf("gmetal.net")>0) type="gmetal";
				if (txtUrl.Text.ToLower().IndexOf("mtv.mtvhalong.com")>0) type="mtvhalong";
				if (txtUrl.Text.ToLower().IndexOf("music.trongdongvn.com")>0) type="trongdongvn";
				if (txtUrl.Text.ToLower().IndexOf("nghenhacso.net")>0) type="nghenhacso.net";
				if (txtUrl.Text.ToLower().IndexOf("amnhac.timnhanh.com")>0) type="timnhanh.com";
			}
			if (type=="")
			{
				lblLink.Text="Địa chỉ không hợp lệ.";
			}
			String inputUrl = txtUrl.Text.Trim();
			//String muzic9 ="Cookie: PHPSESSID=3345df7da0f34009f30939cd5b913b0e";
			//String muzic9 ="Cookie: PHPSESSID=5b2e72ddd323b2bf068215f024d444b1 \n Expires: Thu, 19 Nov 3000 08:52:00 GMT";
			switch (type)
			{
				case "nhacso":
					lblLink.Text=GetLinkNhacso(inputUrl);
					break;
				case "i4vn":
						lblLink.Text=GetLinkI4vn(inputUrl);
					break;
				case "sonic":
					{
						if (txtUrl.Text.ToLower().IndexOf("front/music")>0)
							lblLink.Text=GetLinksonicNew(inputUrl);
						if (txtUrl.Text.ToLower().IndexOf("front/game")>0)
							lblLink.Text=GetLinkGamesonic(inputUrl);
					break;
					}
				case "bennhac":
					lblLink.Text=GetLinkBennhac(inputUrl);
					break;
				case "vnmusic":
					lblLink.Text=GetLinkVnmusic(inputUrl);
					break;
				case "docbao":
					lblLink.Text=GetLinkDocbao(inputUrl);
					break;
				case "giaidieu":
					lblLink.Text=GetLinkGiaidieu(inputUrl);
					break;
				case "vuilen":
					if (txtUrl.Text.ToLower().IndexOf("music")>0)
						lblLink.Text=GetLinkMusicpPuonghong(inputUrl);
					else 
						lblLink.Text=GetLinkComicbookPuonghong(inputUrl);
					break;
				case "baokhanh":
					lblLink.Text=GetLinkBaokhanh(inputUrl);
					break;
				case "nhacyeu":
					lblLink.Text=GetLinkNhacyeu(inputUrl);
					break;
				case "enhac.net":
					lblLink.Text=GetLinkEnhac(inputUrl);
					break;
				case "nghenhacso.net":
					lblLink.Text=GetLinkNghenhacso(inputUrl);
					break;
				case "nhacnen":
					lblLink.Text=GetLinkNhacnen(inputUrl);
					break;
				case "tuoitho":
					lblLink.Text=GetLinkTuoitho(inputUrl);
					break;
				case "muzic9":
					lblLink.Text=GetLinkMuzic9New(inputUrl);
					break;
				case "vietvoice":
					lblLink.Text=GetLinkVietvoice(inputUrl);
					break;
				case "cuasoamnhac":
					lblLink.Text=GetLinkCuasoamnhac(inputUrl);
					break;
				case "hoathachthao":
					lblLink.Text=GetLinkHoathachthao(inputUrl);
					break;
				case "tialia":
					lblLink.Text=GetLinkTialia(inputUrl);
					break;
				case "notnhactre":
					lblLink.Text=GetLinkNotnhactre(inputUrl);
					break;
				case "vietnamnet":
					if (txtUrl.Text.ToLower().IndexOf("videoclip")>0)
						lblLink.Text = GetLinkVietnamnetVideo(inputUrl);
					else
						lblLink.Text=GetLinkVietnamnet(inputUrl);
					break;
				case "gmetal":
					if (txtUrl.Text.ToLower().IndexOf("songs")>0)
						lblLink.Text=GetLinkGmetal(inputUrl);
					else 
						lblLink.Text=GetLinkGmetalSinger(inputUrl);
					break;
				case "mtvhalong":
					lblLink.Text=GetLinkMtvhalong(inputUrl);
					break;
				case "timnhanh.com":
					lblLink.Text=GetLinkTimnhanh(inputUrl);
					break;
				default:
					lblLink.Text="Địa chỉ không hợp lệ.";
					break;
			}
		}
		public String GetHtmlHeader(string inputUrl)
		{
			string outputString="";
			try
			{
				WebRequest myRequest = WebRequest.Create(inputUrl);
				WebResponse myResponse = myRequest.GetResponse();
				outputString = myResponse.Headers.ToString();
			}
			catch
			{
				outputString="Có lỗi xảy ra!";
			}
			return outputString;
		}
		public String GetHtmlContent(string inputUrl)
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
				outputString="";
			}
			//Response.Write(outputString);
			return outputString;
		}
		public String GetHtmlContent2(string inputUrl)
		{
			HttpWebRequest req;
			try
			{
				req = (HttpWebRequest) HttpWebRequest.Create(inputUrl);
				req.UserAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.5) Gecko/20041107 Firefox/1.0";
				//Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US; rv:1.8.1) Gecko/20061010 Firefox/2.0
			}
			catch 
			{
				return "";
			}
			HttpWebResponse resp;
			try
			{			
				resp = (HttpWebResponse) req.GetResponse();
			}
			catch 
			{
				return "";
			}
			StreamReader streamReader=null;
            String content ="";
			streamReader =new StreamReader(resp.GetResponseStream(),Encoding.UTF8);
			content = streamReader.ReadToEnd( );
			resp.Close();
			return content;
		}
		public string GetLinkNhacso(string URL)
		{
			return AsxReader(GetAsxUrl(URL));
		}
		public string GetLinkMusicpPuonghong(string URL)
		{
			//http://music.vuilen.com/play.php?albumid=159&songid=1876
			//-->player.php?playertype=c29uZw==&id=MTg3Ng==
			String link="";
			try
			{
				string regCode ="(?<=songid=)[0-9]+";
				Regex theRegex = new Regex(regCode);
				MatchCollection MatchResult = theRegex.Matches(URL);
				foreach (Match match in MatchResult)
				{
					byte[] data=System.Text.UnicodeEncoding.UTF8.GetBytes(match.Value);
					Base64Encoder myEncoder=new Base64Encoder(data);
					StringBuilder sb=new StringBuilder();
					sb.Append(myEncoder.GetEncoded());
					link ="http://music.vuilen.com/player.php?playertype=c29uZw==&id=" + sb.ToString();
					break;
				}
			}
			catch 
			{
			}
			if (link!="") link = "<b>Download link:</b><br><a href=\""+link+"\" target=blank>"+link+"</a><br><br>Bạn dùng Firefox để chạy link này,sau đó \"Save as\" lại với chế độ \"Complete\" file nhạc sẽ nằm trong thư mục cùng tên với tên file bạn vừa lưu.<br>Có một số cách nữa, tôi sẽ cập nhật sau.";
			else link="Địa chỉ không đúng.";
			return link;
		}
		public string GetLinkComicbookPuonghong(string URL)
		{
			//http://comicbook.phuonghong.org/view_book_detail.php?bookid=128&chapterid=1915
			//http://comicbook.phuonghong.org/data/truyentranh/dragonball/tap1/index.php?s=2
			//http://comic.vuilen.com/view_book_detail.php?bookid=128&chapterid=1915
			String baselink = "";
			String link="";
			String content="";
			try
			{
				baselink = GetHtmlContent(URL);
				//string regCode ="http://comicbook\\.phuonghong\\.org(?<=http\\://comicbook\\.phuonghong\\.org).*(?=index.php\\?s=2)";
				string regCode ="http://comic\\.vuilen\\.com(?<=http\\://comic\\.vuilen\\.com).*(?=index.php\\?s=2)";
				Regex theRegex = new Regex(regCode);
				MatchCollection MatchResult = theRegex.Matches(baselink);
				foreach (Match match in MatchResult)
				{
					baselink = match.Value;
					link =match.Value+"config.xml";
					break;
				}
			}
			catch 
			{
				
			}
			try
			{
				link = GetHtmlContent(link);
				string regCode ="(?<=\\<page\\>).*(?=\\</page\\>)";
				Regex theRegex = new Regex(regCode);
				MatchCollection MatchResult = theRegex.Matches(link);
				foreach (Match match in MatchResult)
				{
					content +="<a target=_blank href="+baselink + match.Value.Trim()+">"+baselink + match.Value.Trim()+"</a><br>";
				}
			}
			catch
			{
				content ="Hiện tại chúng tôi không cung cấp lấy link trang comic.vuilen.com qua web.</br>Bạn download phần mềm này về chạy trên máy của bạn là được <a href=\"http://media.ajaxviet.com/phuonghong.zip\">Phần mềm download truyện tranh Phuonghong (comic.vuilen.com)</a>";
			}
			if ((link=="")||(content==""))
				content ="Hiện tại chúng tôi không cung cấp lấy link trang comic.vuilen.com qua web.</br>Bạn download phần mềm này về chạy trên máy của bạn là được <a href=\"http://media.ajaxviet.com/phuonghong.zip\">Phần mềm download truyện tranh Phuonghong (comic.vuilen.com)</a>";
			return content;
		}
		public string GetLinkI4vn(string URL)
		{
			//http://www.i4vn.com.vn/&music/index.php?i4vn=choinhac&id=1473
			//-->http://www.i4vn.com.vn/&music/play.php?id=1473
			String link="";
			try
			{
				string regCode ="id=[0-9]+";
				Regex theRegex = new Regex(regCode);
				MatchCollection MatchResult = theRegex.Matches(URL);
				foreach (Match match in MatchResult)
				{
					link ="http://www.i4vn.com.vn/&music/play.php?" +match.Value;
					break;
				}
			}
			catch 
			{
			}
			try
			{
				WebRequest myRequest = WebRequest.Create(link);
                WebResponse myResponse = myRequest.GetResponse();
				link = "http://"+myResponse.ResponseUri.Host.ToString()+myResponse.ResponseUri.LocalPath.ToString();
			}
			catch 
			{
				link ="Có lỗi xảy ra hoặc không kết nối được tới I4vn.";
				return link;
			}	
			if (link!="") link = "<a href=\""+link+"\" target=blank>"+link+"</a><br><br>Bạn dùng Flashget hoặc Firefox để download,sau khi lấy về nếu file nào ko có đuôi, bạn đổi tên file thành .mp3,hoặc 1 đuôi khác là có thể nghe được";
			else link="Địa chỉ không đúng.";
			return link;
		}
		public string GetLinkNotnhactre(string URL)
		{
			//http://notnhactre.net/view_Program.aspx?pid=170
			//http://notnhactre.net/view_media.aspx?lpid=170
			String link="";
			try
			{
				string regCode ="(?<=pid\\=)[0-9]+";
				Regex theRegex = new Regex(regCode,RegexOptions.IgnoreCase);
				MatchCollection MatchResult = theRegex.Matches(URL);
				foreach (Match match in MatchResult)
				{
					link ="http://notnhactre.net/view_media.aspx?lpid=" +match.Value;
					break;
				}
			}
			catch 
			{
				link ="Có lỗi xảy ra hoặc không kết nối được tới Notnhactre.";
				return link;
			}
			try
			{
				link = GetHtmlContent(link);
				string regCode ="\\b(https?|ftp|mms)://[-A-Z0-9+&@#/%?=~_|!:,.;]*[-A-Z0-9+&@#/%=~_|]";
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
				link ="Có lỗi xảy ra hoặc không kết nối được tới Notnhactre.";
				return link;
			}	
			if ((link!="")&(link.IndexOf("://")<10)) link = "<b>Download link : </b><a href=\""+link+"\" target=blank>"+link+"</a><br>";
			else link="Có lỗi xảy ra hoặc địa chỉ không đúng định dạng, bạn nên tham khảo ở phía dưới.";		
			return link;
		}
		public string GetLinkVietnamnet(string URL)
		{
			//http://nhacviet.vietnamnet.vn/vn/nhacpham/chitiet/704/index.aspx
			String content="";
			String links="";
			String link="";
			String baselink="";
			try
			{
				content = GetHtmlContent(URL);
				string regCode ="(?<=var\\s+imgArray\\=\\[\\').*(?=\\'\\])";
				Regex theRegex = new Regex(regCode,RegexOptions.IgnoreCase);
				MatchCollection MatchResult = theRegex.Matches(content);
				foreach (Match match in MatchResult)
				{
					links =match.Value;
					links = Regex.Replace(links,"[0-9]+\\'\\,\\'",String.Empty);
					break;
				}
				regCode ="(?<=PARAM\\s+NAME=\"URL\"\\s+VALUE=\\\").*(?=\\\"\\>)";
				theRegex = new Regex(regCode,RegexOptions.IgnoreCase);
				MatchResult = theRegex.Matches(content);
				foreach (Match match in MatchResult)
				{
					link =match.Value;
					if (link != "") break;
				}
				string[] SplitArray = null;
				SplitArray = Regex.Split(links, "\\$\\$\\$",RegexOptions.IgnoreCase);
				for(int i=0;i<SplitArray.Length;i++)
				{
					if(link.IndexOf(SplitArray[i])>0)
					{
						baselink = link.Replace(SplitArray[i],String.Empty);
						break;
					}
				}
				content="<b>Download link:</b><br>";
				for(int i=0;i<SplitArray.Length-1;i=i+2)
				{
					content += SplitArray[i]+": <a target=_blank href="+baselink+Regex.Replace(SplitArray[i+1]," ","%20")+">"+baselink+SplitArray[i+1]+"</a>"+"<br>";
				}

			}
			catch 
			{
				content ="Có lỗi xảy ra hoặc không kết nối được tới nhacviet.vietnamnet.vn.";
				return content;
			}
			if (content=="<b>Download link:</b><br>")
				content ="Có lỗi xảy ra hoặc không kết nối được tới nhacviet.vietnamnet.vn.";
			return content;
		}
		public string GetLinkVietnamnetVideo(string URL)
		{
			//http://nhacviet.vietnamnet.vn/vn/videoclip/0/2/index.aspx
			String link="";
			try
			{
				link = GetHtmlContent(URL);
				string regCode ="(?<=Playfile\\(\\').*(?=\\'\\))";
				Regex theRegex = new Regex(regCode,RegexOptions.IgnoreCase);
				MatchCollection MatchResult = theRegex.Matches(link);
				link="<b>Download link:</b><br>";
				foreach (Match match in MatchResult)
				{
					link +="<a href="+match.Value+" target=blank>"+match.Value+"</a><br>";
				}
			}
			catch 
			{
				link ="Có lỗi xảy ra hoặc không kết nối được tới nhacviet.vietnamnet.vn.";
				return link;
			}
			if (link=="<b>Download link:</b><br>")
				link ="Có lỗi xảy ra hoặc không kết nối được tới nhacviet.vietnamnet.vn.";
			return link;
		}
		public string GetLinkTimnhanh(string URL)
		{
			//http://amnhac.timnhanh.com//playalbum.php?i=425
			//http://amnhac.timnhanh.com/listsong.php?act=album&id=427
			String link="";
			try
			{
				string regCode ="(?<=\\?i\\=)[0-9]+";
				Regex theRegex = new Regex(regCode);
				MatchCollection MatchResult = theRegex.Matches(URL);
				foreach (Match match in MatchResult)
				{
					link ="http://amnhac.timnhanh.com/listsong.php?act=album&id=" +match.Value;
					break;
				}
			}
			catch 
			{
			}
			try
			{
				link = GetHtmlContent4(link,"Windows-Media-Player/11.00.00.4715");
				string regCode ="(?<=HREF=\\\").*(?=\\\"/>)";
				Regex theRegex = new Regex(regCode);
				MatchCollection MatchResult = theRegex.Matches(link);
				link="<b>Download link :</b><br>";
				foreach (Match match in MatchResult)
				{
					if (match.Value.StartsWith("http://giaitri2.timnhanh.com/musicuploads/images/company/soundtrack")==false)
						link +="<a href="+match.Value+" target=blank>"+match.Value+"</a>"+"\n";
				}

			}
			catch 
			{
				return link;
			}	
			return link;
		}
		public String GetHtmlContent4(string inputUrl,string agent)
		{
			HttpWebRequest req;
			try
			{
				req = (HttpWebRequest) HttpWebRequest.Create(inputUrl);
				//req.UserAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.5) Gecko/20041107 Firefox/1.0";
				req.UserAgent=agent;
				//req.ContentType="video/x-ms-asx";
			}
			catch 
			{
				return "Có lỗi xảy ra!";
			}
			HttpWebResponse resp;
			try
			{			
				resp = (HttpWebResponse) req.GetResponse();
			}
			catch 
			{
				return "Có lỗi xảy ra!";
			}
			StreamReader streamReader=null;
			String content ="";
			streamReader =new StreamReader(resp.GetResponseStream(),Encoding.UTF8);
			content = streamReader.ReadToEnd( );
			resp.Close();
			return content;
		}
		public string GetLinkGmetal(string URL)
		{
			//http://gmetal.net/songs/3476/
			String link="";
			try
			{
				link = GetHtmlContent(URL);
				string regCode ="(?<=embed\\s+autostart=true\\s+src=\").*?(?=\\\"\\s)";
				Regex theRegex = new Regex(regCode,RegexOptions.IgnoreCase);
				MatchCollection MatchResult = theRegex.Matches(link);
				link="<b>Download link:</b><br>";
				foreach (Match match in MatchResult)
				{
					link +="<a href="+match.Value+" target=blank>"+match.Value+"</a><br>";
					break;
				}
			}
			catch 
			{
				link ="Có lỗi xảy ra hoặc không kết nối được tới gmetal.net.";
				return link;
			}
			if (link=="<b>Download link:</b><br>")
				link ="Có lỗi xảy ra hoặc không kết nối được tới gmetal.net.";
			return link;
		}
		public string GetLinkGmetal2(string URL)
		{
			//http://gmetal.net/songs/3476/
			String link="";
			try
			{
				link = GetHtmlContent(URL);
				string regCode ="(?<=embed\\s+autostart=true\\s+src=\").*?(?=\\\"\\s)";
				Regex theRegex = new Regex(regCode,RegexOptions.IgnoreCase);
				MatchCollection MatchResult = theRegex.Matches(link);
				link ="";
				foreach (Match match in MatchResult)
				{
					link +="<a href="+match.Value+" target=blank>"+match.Value+"</a><br>";
					break;
				}
			}
			catch 
			{
				link ="";
				return link;
			}
			return link;
		}
		public string GetLinkGmetalSinger(string URL)
		{
			//http://gmetal.net/songs/3476/
			String link="";
			String content="<b>Download link:</b><br>";
			try
			{
				link = GetHtmlContent(URL);
				string regCode ="(?<=<li><a\\s+href=\\\").*?(?=\\\"\\>)";
				Regex theRegex = new Regex(regCode,RegexOptions.IgnoreCase);
				MatchCollection MatchResult = theRegex.Matches(link);
				foreach (Match match in MatchResult)
				{
					link ="http://gmetal.net/"+match.Value;
					content += GetLinkGmetal2(link);
				}
			}
			catch 
			{
				content ="Có lỗi xảy ra hoặc không kết nối được tới gmetal.net.";
				return content;
			}
			if (content=="<b>Download link:</b><br>")
				content ="Có lỗi xảy ra hoặc không kết nối được tới gmetal.net.";
			return content;
		}
		public string GetLinkMtvhalong(string URL)
		{
			string url_param = ConfigurationSettings.AppSettings["mtvhalong"];
			//http://mtv.mtvhalong.com/nghenhac.php?mtvhalong.com,157,40
			//http://mtv.mtvhalong.com/nghenhac.php?guitangbaihat,157,24
			//http://mtv.mtvhalong.com/hinhanh.php?mtvhalong.com,157,24
			String link="";
			try
			{
				string regCode ="[0-9]+.[0-9]+";
				Regex theRegex = new Regex(regCode,RegexOptions.IgnoreCase);
				MatchCollection MatchResult = theRegex.Matches(URL);
				foreach (Match match in MatchResult)
				{
					//link ="http://mtv.mtvhalong.com/hinhanh.php?mtvhalong.com,"+match.Value;
					link =url_param+match.Value;
					break;
				}
			}
			catch 
			{
				link ="";
				return link;
			}
			try
			{
				link = GetHtmlContent(link);
				string regCode ="(?<=<Ref\\s*href\\s*=\\s*\\\").*(?=\\\"\\s*)";
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
				link ="";
				return link;
			}
			if (link.IndexOf("sonic.vn")>0)
			{
				String content = GetHtmlContent2(link);
				content = Regex.Replace(content,"<script(?<=<script).+(?=</script>)</script>",String.Empty);
				try
				{
					string regCode ="(?<=<ref\\s+href\\s*=\\\").+(?=\\\"\\s*\\/\\>)";
					Regex theRegex = new Regex(regCode);
					MatchCollection MatchResult = theRegex.Matches(content);
					foreach (Match match in MatchResult)
					{
						link =match.Value;
						if (link!="") 
							break;
					}
				}
				catch 
				{
					link ="Có lỗi xảy ra hoặc không kết nối được tới sonic.";
					return link;
				}
				//link = Regex.Replace(link," ","%20");
			}
			if (link=="")
				link ="Có lỗi xảy ra hoặc không kết nối được tới mtv.mtvhalong.com.";
			else 
				link = "<b>Download link:</b><br>" + "<a href=\""+link+"\" target=blank>"+link+"</a><br>";;
			return link;
		}
		public string GetLinkTuoitho(string URL)
		{
			//http://www.tuoitho.net/diendan/index.php?action=musiclink&act=view&id=3641
			//http://tuoitho.net/z.php?L=3645
			String link="";
			try
			{
				string regCode ="(?<=id\\=)[0-9]+";
				Regex theRegex = new Regex(regCode);
				MatchCollection MatchResult = theRegex.Matches(URL);
				foreach (Match match in MatchResult)
				{
					link ="http://tuoitho.net/z.php?L=" +match.Value;
					break;
				}
			}
			catch 
			{
			}
			try
			{
				WebRequest myRequest = WebRequest.Create(link);
				WebResponse myResponse = myRequest.GetResponse();
				link = "http://"+myResponse.ResponseUri.Host.ToString()+myResponse.ResponseUri.LocalPath.ToString();
			}
			catch 
			{
				link ="Có lỗi xảy ra hoặc không kết nối được tới Tuoitho.net.";
				return link;
			}	
			if (link!="") link = "<b>Download link :</b><br><a href=\""+link+"\" target=blank>"+link+"</a>";
			else link="Có lỗi xảy ra hoặc địa chỉ không đúng định dạng, bạn nên tham khảo ở phía dưới.";
			return link;
		}
		public string GetLinkAlbumI4vn(string URL)
		{
			//Album : http://www.i4vn.com.vn/&music/index.php?i4vn=album&id=410
			//http://www.i4vn.com.vn/&music/index.php?i4vn=choinhac&id=6135
			
			String content=GetHtmlContent(URL);
			String link="";
			try
			{
				string regCode ="(?<=href\\=\\\")\\?i4vn=choinhac&id=[0-9]*(?=\\\"><B><font\\scolor=\\\"#316EB9\\\">)";
				Regex theRegex = new Regex(regCode);
				MatchCollection MatchResult = theRegex.Matches(content);
				foreach (Match match in MatchResult)
				{
					link +=GetLinkI4vn("http://www.i4vn.com.vn/&music/index.php"+match.Value)+"<br>";
				}
			}
			catch 
			{
			}
			return link;
		}
		public string GetLinkVnmusic(string URL)
		{
			//http://www.vnmusic.com.vn/music/index.php?aid=nghenhac&id=773
			String link="";
			try
			{
				link = GetHtmlContent(URL);
				string regCode ="(?<=\\<param\\sname\\=\\\"filename\"\\svalue\\s=\\\").+?(?=\\\"\\>)";
				Regex theRegex = new Regex(regCode);
				MatchCollection MatchResult = theRegex.Matches(link);
				foreach (Match match in MatchResult)
				{
					link =match.Value;
					break;
				}
			}
			catch 
			{
				link ="Có lỗi xảy ra hoặc không kết nối được tới Vnmmusic.";
				return link;
			}
			if (link!="") link = "<b>Download link : </b><a href=\""+link+"\" target=blank>"+link+"</a><br>";
			else link="Có lỗi xảy ra hoặc địa chỉ không đúng định dạng, bạn nên tham khảo ở phía dưới.";
			return link;
		}
		public string GetLinkEnhac(string URL)
		{
			//http://www.enhac.net/music/index.php?eNhaC=play&id=53
			String link="";
			try
			{
				link = GetHtmlContent(URL);
				string regCode ="(?<=play\\.php\\?str\\=)[a-zA-Z0-9]*(?=)";
				Regex theRegex = new Regex(regCode);
				MatchCollection MatchResult = theRegex.Matches(link);
				foreach (Match match in MatchResult)
				{
					link ="http://www.enhac.net/music/play.php?str="+match.Value;
					break;
				}
			}
			catch 
			{
				link ="Có lỗi xảy ra hoặc không kết nối được tới Enhac.";
				return link;
			}
			try
			{
				link = GetHtmlContent(link);
				string regCode ="(?<=<REF\\s*href\\s*=\\s*\").*(?=\\\"\\s*)";
				Regex theRegex = new Regex(regCode);
				MatchCollection MatchResult = theRegex.Matches(link);
				foreach (Match match in MatchResult)
				{
					link =match.Value;
					break;
				}
			}
			catch 
			{
				link ="Có lỗi xảy ra hoặc không kết nối được tới Enhac.";
				return link;
			}
			if (link!="") link = "<b>Download link : </b><a href=\""+link+"\" target=blank>"+link+"</a><br>";
			else link="Có lỗi xảy ra hoặc địa chỉ không đúng định dạng, bạn nên tham khảo ở phía dưới.";
			return link;
		}
		public string GetLinkNghenhacso(string URL)
		{
			//http://www.nghenhacso.net/music/index.php?eNhaC=play&id=53
			String link="";
			try
			{
				link = GetHtmlContent(URL);
				string regCode ="(?<=play\\.php\\?str\\=)[a-zA-Z0-9]*(?=)";
				Regex theRegex = new Regex(regCode);
				MatchCollection MatchResult = theRegex.Matches(link);
				foreach (Match match in MatchResult)
				{
					link ="http://www.nghenhacso.net/play.php?str="+match.Value;
					break;
				}
			}
			catch 
			{
				link ="Có lỗi xảy ra hoặc không kết nối được tới nghenhacso.net.";
				return link;
			}
			try
			{
				link = GetHtmlContent(link);
				string regCode ="(?<=<REF\\s*href\\s*=\\s*\").*(?=\\\"\\s*)";
				Regex theRegex = new Regex(regCode);
				MatchCollection MatchResult = theRegex.Matches(link);
				foreach (Match match in MatchResult)
				{
					link =match.Value;
					break;
				}
			}
			catch 
			{
				link ="Có lỗi xảy ra hoặc không kết nối được tới nghenhacso.net.";
				return link;
			}
			if (link!="") link = "<b>Download link : </b><a href=\""+link+"\" target=blank>"+link+"</a><br>";
			else link="Có lỗi xảy ra hoặc địa chỉ không đúng định dạng, bạn nên tham khảo ở phía dưới.";
			return link;
		}
		public string GetLinkNhacnen(string URL)
		{
			//http://www.nhacnen.net/index.php?action=album&id=274
			//http://www.nhacnen.net/index.php?action=addlyrics&song=2760
			//http://www.nhacnen.net/index.php?action=song&array=yes&id=125,2557,
			String link="";
			if(URL.ToLower().IndexOf("song=")>0)
			{
				try
				{
					string regCode ="(?<=song\\=)[0-9]+(?=)";
					Regex theRegex = new Regex(regCode);
					MatchCollection MatchResult = theRegex.Matches(URL);
					link ="http://www.nhacnen.net/index.php?action=song&array=yes&id=";
					foreach (Match match in MatchResult)
					{
						link +=match.Value+",";
						break;
					}
				}
				catch 
				{
					link ="Có lỗi xảy ra hoặc không kết nối được tới Nhacnen.net. Server nhacnen thường chậm, bạn nên thử lại.";
					return link;
				}
			}
			else
			{
				try
				{
					link = GetHtmlContent(URL);
					string regCode ="(?<=\\<input.*song_id.*value=\\')[0-9]*(?=)";
					Regex theRegex = new Regex(regCode);
					MatchCollection MatchResult = theRegex.Matches(link);
					link ="http://www.nhacnen.net/index.php?action=song&array=yes&id=";
					foreach (Match match in MatchResult)
					{
						link +=match.Value+",";
					}
				}
				catch 
				{
					link ="Có lỗi xảy ra hoặc không kết nối được tới Nhacnen.net. Server nhacnen thường chậm, bạn nên thử lại.";
					return link;
				}
			}
			try
			{
				link = GetHtmlContent(link);
				string regCode ="(?<=SRC=\").*(?=\\\"\\s+NAME=\"MediaPlayer)";
				Regex theRegex = new Regex(regCode);
				MatchCollection MatchResult = theRegex.Matches(link);
				foreach (Match match in MatchResult)
				{
					link =match.Value;
					break;
				}
			}
			catch 
			{
				link ="Có lỗi xảy ra hoặc không kết nối được tới Nhacnen.net. Server nhacnen thường chậm, bạn nên thử lại.";
				return link;
			}
			if (link!="") link = "<b>Download link : </b>"+GetHtmlContent2(link)+"<br>";
			else link="Có lỗi xảy ra hoặc địa chỉ không đúng định dạng, bạn nên tham khảo ở phía dưới.";
			return link;
		}
		public string GetLinkBennhac(string URL)
		{
			//http://music.bennhac.com/NhacPham/VN/15640/Ai-Dua-Em-Ve/review/new-file-sharing-server.html
			//http://music.bennhac.com/streaming/15640/64/
			//http://music.bennhac.com/streaming/15640/32/
			String link64="";
			String link32="";
			String link="";
			try
			{
				string regCode ="(?<=\\/)[0-9]+?(?=\\/)";
				Regex theRegex = new Regex(regCode);
				MatchCollection MatchResult = theRegex.Matches(URL);
				foreach (Match match in MatchResult)
				{
					link64 ="http://music.bennhac.com/streaming/" +match.Value+"/64/";
					link32 ="http://music.bennhac.com/streaming/" +match.Value+"/32/";
					break;
				}
			}
			catch 
			{
			}
			try
			{
				MyUri uri = new MyUri(link64);
				MyWebRequest request = null;
				// create web request
				request = MyWebRequest.Create(uri, request, false);
				// retrieve response from web request
				MyWebResponse response = request.GetResponse();
				if(response.ResponseUri.Equals(uri) == false)
				{
					link64 = response.ResponseUri.ToString();
					//link64 = "http://music.bennhac.com"+link64.Substring(link64.IndexOf("bennhac.com")+11);
					request = null;
				}
			}
			catch
			{
				link64="";
			}
			try
			{
				MyUri uri = new MyUri(link32);
				MyWebRequest request = null;
				// create web request
				request = MyWebRequest.Create(uri, request, false);
				// retrieve response from web request
				MyWebResponse response = request.GetResponse();
				if(response.ResponseUri.Equals(uri) == false)
				{
					link32 = response.ResponseUri.ToString();
					//link32 = "http://music.bennhac.com"+link32.Substring(link32.IndexOf("bennhac.com")+11);
					request = null;
				}
			}
			catch
			{
				link32="";
			}
			link64 = Regex.Replace(link64," ","%20");
			link32 = Regex.Replace(link32," ","%20");
			if (link64!="") link64 = "<b>Download link nhạc 64k bits : </b><a href="+link64+" target=blank>"+link64+"</a><br>";
			if (link32!="") link32 = "<b>Download link nhạc 32k bits : </b><a href="+link32+" target=blank>"+link32+"</a><br>";			
			link ="<b>Hướng dẫn:</b> Bạn copy link này, rồi mở WindowMedia Player (tested WMP 11), sau đó mở \"File->Open URL\" hoặc bấm phím tắt Ctrl+U,";
			link +="sau đó paste địa chỉ vừa copy điền vào đó,bấm Ok,tiếp theo bấm \"File->Save as\".<br>";
			link +="Hoặc dùng Real Player, mở \"File->Open\" hoặc bấm phím nóng Ctrl+O.Sau đó save file lại.<br>";
			link +="Có thể hơi chậm 1 chút, nếu nó báo lỗi bạn thử lại lần nữa xem sao.<br>";
			link += link64 + link32;
			if (link=="") link = "Có lỗi xảy ra hoặc địa chỉ không đúng hoặc không kết nối được với Bennhac.";
			return link;
		}
		public string GetLinkGamesonic(string URL)
		{
			//http://www.sonic.vn/front/game/play.aspx?GameID=26&GameGenreID=1
			String link="";
			try
			{
				link = GetHtmlContent(URL);
				string regCode ="(?<=<param\\s+name=\"movie\"\\s+value=').*(?=\\'\\>)";
				Regex theRegex = new Regex(regCode,RegexOptions.IgnoreCase);
				MatchCollection MatchResult = theRegex.Matches(link);
				foreach (Match match in MatchResult)
				{
					link ="http://www.sonic.vn/"+match.Value;
					break;
				}
			}
			catch 
			{
				link ="Có lỗi xảy ra hoặc không kết nối được tới Enhac.";
				return link;
			}
			if (link!="") link = "<b>Download link : </b><a href=\""+link+"\" target=blank>"+link+"</a>.<br>Bạn dùng Flashget, IDM ... để download hoặc đơn giản bấm chuột phải vào link chọn \"Save target as\" để download về máy.<br>";
			else link="Có lỗi xảy ra hoặc địa chỉ không đúng định dạng, bạn nên tham khảo ở phía dưới.";
			return link;
		}
		public string GetLinksonicNew(string URL)
		{
			//http://sonic.vn/front/music/play.aspx?songid=18920&musicgenreid=33
			//hoac http://sonic.vn/front/music/play.aspx?songid=18920

			String link="";
			String link_temp="";
			try
			{
				string regCode ="(?<=songid=)[0-9]+(?=)";
				Regex theRegex = new Regex(regCode);
				MatchCollection MatchResult = theRegex.Matches(URL);
				foreach (Match match in MatchResult)
				{
					link_temp ="http://sonic.vn/front/music/listening.aspx?zumi=" +match.Value;
					break;
				}
			}
			catch 
			{
				link ="Có lỗi xảy ra hoặc địa chỉ không đúng.";
				return link;
			}
			String content = GetHtmlContent2(link_temp);
			content = Regex.Replace(content,"<script(?<=<script).+(?=</script>)</script>",String.Empty);
			try
			{
				string regCode ="(?<=<ref\\s+href\\s*=\\\").+(?=\\\"\\s*\\/\\>)";
				Regex theRegex = new Regex(regCode);
				MatchCollection MatchResult = theRegex.Matches(content);
				foreach (Match match in MatchResult)
				{
					link =match.Value;
					if (link!="") 
						break;
				}
			}
			catch 
			{
				link ="Có lỗi xảy ra hoặc không kết nối được tới sonic.";
				return link;
			}
			//link = Regex.Replace(link," ","%20");
			if (link!="") link = "<b>Download link : </b><a href=\""+link+"\" target=blank>"+link+"</a><br>Bạn dùng phần mềm Wellget để download. Bạn hãy tìm bài hướng dẫ chi tiết ở phía trên.</a>";
			else link="Có lỗi xảy ra hoặc không kết nối được tới sonic.";
			return link;
		}
		public string GetLinkTrongdongvn(string URL)
		{
			String link="";
			String link_temp="";
			try
			{
				string regCode ="(?<=&id\\=)[0-9]+";
				Regex theRegex = new Regex(regCode);
				MatchCollection MatchResult = theRegex.Matches(URL);
				foreach (Match match in MatchResult)
				{
					link_temp ="http://music.trongdongvn.com/index.php?act=listen&id=" +match.Value;
					break;
				}
			}
			catch 
			{
				link ="Có lỗi xảy ra hoặc địa chỉ không đúng.";
				return link;
			}
			String content = GetHtmlContent(link_temp);
			try
			{
				string regCode ="(?<=<ref\\s+href\\s*=\\\").+(?=\\\"\\s*\\/\\>)";
				Regex theRegex = new Regex(regCode);
				MatchCollection MatchResult = theRegex.Matches(content);
				content="<b>Download link : </b>";
				foreach (Match match in MatchResult)
				{
					link =match.Value;
					//link = Regex.Replace(link," ","%20");
					if (link!="") 
						content+="<a href=\""+link+"\" target=blank>"+link+"</a><br>";
						
				}
			}
			catch 
			{
				link ="Có lỗi xảy ra hoặc không kết nối được tới sonic.";
				return link;
			}
			//link = Regex.Replace(link," ","%20");
			if (link!="") link =content + "<br>Nếu là file mms:// bạn nên dùng Flashget để download.";
			else link="Có lỗi xảy ra hoặc không kết nối được tới music.trongdongvn.com.";
			return link;
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
			if (link!="") link = "<b>Download link : </b><a href=\""+link+"\" target=blank>"+link+"</a><br>";
			else link="Có lỗi xảy ra hoặc địa chỉ không đúng định dạng, bạn nên tham khảo ở phía dưới.";
			if (link.IndexOf("wma.muzic9.com")>0)
				link = "Hệ thống đang bảo trì, mong các bạn thông cảm. Chúng tôi sẽ cập nhật lại trong thời gian tới!";
			return link;
		}
		private string GetLinkMuzic9New(String URL)
		{
			//http://muzic9.com/index.php?c=song&songid=193164
			String link="";
			String header="";
			try
			{
				header = GetHtmlHeader(URL);
				string regCode ="(?<=Set-Cookie:\\s+).*(?=\\;)";
				Regex theRegex = new Regex(regCode,RegexOptions.IgnoreCase);
				MatchCollection MatchResult = theRegex.Matches(header);
				foreach (Match match in MatchResult)
				{
					header ="Cookie: "+match.Value;
					break;
				}
			}
			catch 
			{
				header ="Có lỗi xảy ra hoặc địa chỉ không đúng.";
				return header;
			}
			try
			{
				link = kikicoco.GetHtmlContent3(URL,header);
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
				link = kikicoco.GetHtmlContent3(link,header);
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
			if (link!="") link = "<b>Download link : </b><a href=\""+link+"\" target=blank>"+link+"</a><br>";
			else link="Có lỗi xảy ra hoặc địa chỉ không đúng định dạng, bạn nên tham khảo ở phía dưới.";
			if (link.IndexOf("wma.muzic9.com")>0)
				link = "Phần này đang được bảo trì, mong các bạn thông cảm. Chúng tôi sẽ cập nhật lại trong thời gian tới!";
			return link;
		}
		public string GetLinkHoathachthao(string URL)
		{
			//http://hoathachthao.info/tacpham.php?htt=nghe&baiso=13283
			String link="";
			try
			{
				link = GetHtmlContent(URL);
				string regCode ="(?<=UTF8\\(\\')[0-9a-zA-Z]+(?=\\')";
				Regex theRegex = new Regex(regCode,RegexOptions.IgnoreCase);
				MatchCollection MatchResult = theRegex.Matches(link);
				foreach (Match match in MatchResult)
				{
					link =match.Value;
					break;
				}
				link = hoathachthao_decode(link);
			}
			catch 
			{
				link ="Có lỗi xảy ra hoặc không kết nối được tới Enhac.";
				return link;
			}
			if ((link!="")&(link.IndexOf("://")<10)) link = "<b>Download link : </b><a href=\""+link+"\" target=blank>"+link+"</a>.<br>Bạn dùng Flashget, IDM ... để download hoặc đơn giản bấm chuột phải vào link chọn \"Save target as\" để download về máy.<br>";
			else link="Có lỗi xảy ra hoặc địa chỉ không đúng định dạng, bạn nên tham khảo ở phía dưới.";
			return link;
		}
		public string hoathachthao_decode(String KEY)
		{  
			String s = KEY;
			s = s.Replace("8367367287456657874",".");  
			s = s.Replace("372145","%20");  
			s = s.Replace("97636455735","(");  
			s = s.Replace("01454262562656",")");   
			s = s.Replace("561447562656","&");  
			s = s.Replace("45147842",":");  
			s = s.Replace("8489785545352","_");  
			s = s.Replace("997644564321","-");  
			s = s.Replace("69467262","/");   
			s = s.Replace("026454456893486","a");  
			s = s.Replace("0474565463","b");  
			s = s.Replace("067456957","c");  
			s = s.Replace("08745685","d");  
			s = s.Replace("012456545875","e");  
			s = s.Replace("014456548695","f");  
			s = s.Replace("01645645532","g");  
			s = s.Replace("018456457583","h");  
			s = s.Replace("022545645823","i");  
			s = s.Replace("045682358","j");  
			s = s.Replace("0564562385783","k");  
			s = s.Replace("028272456765","l");  
			s = s.Replace("0326945649684","m");  
			s = s.Replace("0344456454564","n");  
			s = s.Replace("03612456452","o");  
			s = s.Replace("03852456375823","p");  
			s = s.Replace("04456543999","q");  
			s = s.Replace("0444564594","r");  
			s = s.Replace("04456456454","s");  
			s = s.Replace("0486456452544","t");  
			s = s.Replace("05245645682","u");  
			s = s.Replace("054565424","v");  
			s = s.Replace("05667890673","w");  
			s = s.Replace("05787897875","x");  
			s = s.Replace("0687978763","y");  
			s = s.Replace("064587978978","z");
			s = s.Replace("07455978978","=");
			s = s.Replace("0644555478","?");
			s = s.Replace("06457887978","$");  
			return s;
		}
		private string GetLinkVietvoice(String URL)
		{
			//http://music.vietvoice.net/song_details.php?lang=Vietnamese&ID=4676
			//http://music.vietvoice.net/play.php?iSongID=4676
			String link="";
			String header="";
			try
			{
				header = GetHtmlHeader(URL);
				string regCode ="(?<=Set-Cookie:\\s+).*(?=\\;)";
				Regex theRegex = new Regex(regCode,RegexOptions.IgnoreCase);
				MatchCollection MatchResult = theRegex.Matches(header);
				foreach (Match match in MatchResult)
				{
					header ="Cookie: "+match.Value;
					break;
				}
			}
			catch 
			{
				header ="Có lỗi xảy ra hoặc địa chỉ không đúng.";
				return header;
			}
			try
			{
				string regCode ="(?<=ID=)[0-9]+(?=)";
				Regex theRegex = new Regex(regCode,RegexOptions.IgnoreCase);
				MatchCollection MatchResult = theRegex.Matches(URL);
				foreach (Match match in MatchResult)
				{
					link ="http://music.vietvoice.net/play.php?iSongID="+match.Value;
					break;
				}
				link = kikicoco.GetHtmlContent3(link,header);
			}
			catch 
			{
				link ="Có lỗi xảy ra hoặc địa chỉ không đúng.";
				return link;
			}
			if ((link!="")&(link.IndexOf("://")<10)) link = "<b>Download link : </b><a href=\""+link+"\" target=blank>"+link+"</a><br>";
			else link="Có lỗi xảy ra hoặc địa chỉ không đúng định dạng, bạn nên tham khảo ở phía dưới.";		
			return link;
		}
		private string GetLinkCuasoamnhac(String URL)
		{
			//http://cuasoamnhac.net/#Play/1169
			//http://cuasoamnhac.net/index.php?m=Play&id=1169
			//http://cuasoamnhac.net/thanhphan/_aspx_bh.php?id=1
			String link="";
			String header="";
			try
			{
				string regCode ="(?<=\\/)[0-9]+";
				Regex theRegex = new Regex(regCode);
				MatchCollection MatchResult = theRegex.Matches(URL);
				foreach (Match match in MatchResult)
				{
					link ="http://cuasoamnhac.net/index.php?m=Play&id=" +match.Value;
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
				header = GetHtmlHeader(link);
				string regCode ="(?<=Set-Cookie:\\s+).*(?=\\;)";
				Regex theRegex = new Regex(regCode,RegexOptions.IgnoreCase);
				MatchCollection MatchResult = theRegex.Matches(header);
				foreach (Match match in MatchResult)
				{
					header ="Cookie: "+match.Value;
					break;
				}
			}
			catch 
			{
				header ="Có lỗi xảy ra hoặc địa chỉ không đúng.";
				return header;
			}
			link="http://cuasoamnhac.net/thanhphan/_aspx_bh.php?id=1";
			try
			{
				link = kikicoco.GetHtmlContent3(link,header);
				string regCode ="(?<=ref\\s+href\\=\\\").*(?=\\\")";
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
			if (link!="") link = "<b>Download link : </b><a href=\""+link+"\" target=blank>"+link+"</a><br>";
			else link="Có lỗi xảy ra hoặc địa chỉ không đúng định dạng, bạn nên tham khảo ở phía dưới.";		
			return link;
		}
		private string GetLinkTialia(String URL)
		{
			//http://www.tialia.net/media.php?mediaid=50272
			//http://www.tialia.net/play.php
			String link="";
			String header="";
			try
			{
				header = GetHtmlHeader(URL);
				string regCode ="PHPSESSID=[0-9a-z]{32}";
				Regex theRegex = new Regex(regCode,RegexOptions.IgnoreCase);
				MatchCollection MatchResult = theRegex.Matches(header);
				foreach (Match match in MatchResult)
				{
					header ="Cookie: "+match.Value;
					break;
				}
			}
			catch 
			{
				header ="Có lỗi xảy ra hoặc địa chỉ không đúng.";
				return header;
			}
			link="http://www.tialia.net/play.php";
			try
			{
				link = kikicoco.GetHtmlContent3(link,header);
				string regCode ="(?<=ref\\s+href\\s*=\\s*\\\").*(?=\\\")";
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
			if ((link!="")&(link.IndexOf("://")<10)) link = "<b>Download link : </b><a href=\""+link+"\" target=blank>"+link+"</a><br>";
			else link="Có lỗi xảy ra hoặc địa chỉ không đúng.";		
			return link;
		}
		public string GetLinkGiaidieu(string URL)
		{
			//http://www.giaidieu.net/cgi-bin/musiconline.run?v=song&a=3270
			//http://www.giaidieu.net/cgi-bin/mediaplayer.run?p=giaidieu&s=3270
			//http://www.giaidieu.net/cgi-bin/mediaplayer.run?a=play

			String link="";
			try
			{
				string regCode ="(?<=a=)[0-9]+(?=)";
				Regex theRegex = new Regex(regCode);
				MatchCollection MatchResult = theRegex.Matches(URL);
				foreach (Match match in MatchResult)
				{
					link ="http://www.giaidieu.net/cgi-bin/mediaplayer.run?p=giaidieu&s=" +match.Value;
					break;
				}
			}
			catch 
			{
				link ="Có lỗi xảy ra hoặc địa chỉ không đúng.";
				return link;
			}
			String content="";
			if (link!="")
			{
				content = "<b>Hướng dẫn :</b><br>";
				content += "Bạn phải dùng trình duyệt Firefox - <a target=blank href=\"http://download.mozilla.org/?product=firefox-2.0&os=win&lang=en-US\">Download Firefox</a><br>";
				content +="Bạn chạy lần lượt 2 link sau: (nhớ là chạy lần lượt)<br>";
				content +="Link 1: <a target=blank href=" + link + ">"+link+"</a><br>";
				content +="Link 2: <a target=blank href=\"http://www.giaidieu.net/cgi-bin/mediaplayer.run?a=play\">http://www.giaidieu.net/cgi-bin/mediaplayer.run?a=play</a><br>";
                content +="Sau đó bạn Save as thành tên là gì tùy bạn, nhớ thêm đuôi .mp3 cho file nhạc, đuôi nhạc khác cũng được.";

			}
			else content="Có lỗi xảy ra hoặc địa chỉ không đúng định dạng, bạn nên tham khảo ở phía dưới.";
			return content;
		}
		public string GetLinkDocbao(string URL)
		{
			//http://docbao.dec.vn/show_image.aspx?pid=9f1f9b2c1bd34377ab79c97d579afb0d&type=d
			String content=GetHtmlContent(URL);
			String link="";
			link = "Hướng dẫn : Bạn có thể download từng link 1 cũng được, tôi khuyên bạn nên dùng Flashget để download tất cả cùng 1 lúc,<br> sau đó dùng Tool <a href=\"http://nhacso.ajaxviet.com/14aren.zip\">Rename</a> để đổi tên tất cả các file thành đuôi .jpeg.<br>";
			try
			{
				string regCode ="(?<=\\\")[0-9a-z]{32}(?=\\\">[0-9]+)";
				Regex theRegex = new Regex(regCode);
				MatchCollection MatchResult = theRegex.Matches(content);
				foreach (Match match in MatchResult)
				{
					link +="<a target=\"blank\" href=http://docbao.dec.vn/show_image.aspx?pid="+match.Value+"&type=d>http://docbao.dec.vn/show_image.aspx?pid="+match.Value+"&type=d</a><br>";
				}
			}
			catch 
			{
				link = "Có lỗi xảy ra !";
			}
			return link;
		}
		public string GetLinkBaokhanh(string URL)
		{
			//http://www.baokhanh.biz/index.php?v=nghe&id=6579&trang=1
			//http://www.baokhanh.biz/download.php?id=6579
			String link="";
			try
			{
				string regCode ="(?<=id\\=)[0-9]+(?=)";
				Regex theRegex = new Regex(regCode);
				MatchCollection MatchResult = theRegex.Matches(URL);
				foreach (Match match in MatchResult)
				{
					link ="http://www.baokhanh.biz/download.php?id=" +match.Value;
					break;
				}
			}
			catch 
			{
				link ="";
				return link;
			}
			try
			{
				MyUri uri = new MyUri(link);
				MyWebRequest request = null;
				// create web request
				request = MyWebRequest.Create(uri, request, false);
				// retrieve response from web request
				MyWebResponse response = request.GetResponse();
				link="";
				if(response.ResponseUri.Equals(uri) == false)
				{
					link = response.ResponseUri.ToString();
					request = null;
				}
			}
			catch 
			{
				link ="Có lỗi xảy ra hoặc không kết nối được tới Baokhanh.biz.";
				return link;
			}
			//link = Regex.Replace(link," ","%20");
			if (link!="") link = "<b>Download link : </b><a href=\""+link+"\" target=blank>"+link+"</a><br>";
			else link="Có lỗi xảy ra hoặc địa chỉ không đúng định dạng, bạn nên tham khảo ở phía dưới.<br>Trang này đã chuyển domain thành <b>www.nhacyeu.info</b> rồi bạn ạ. Bạn vào trang <b>www.nhacyeu.info</b> để lấy link hoặc tham khảo bên dưới.";
			return link;
		}
		public string GetLinkNhacyeu(string URL)
		{
			String link="";
			try
			{
				string regCode ="(?<=id\\=)[0-9]+(?=)";
				Regex theRegex = new Regex(regCode);
				MatchCollection MatchResult = theRegex.Matches(URL);
				foreach (Match match in MatchResult)
				{
					link ="http://www.nhacyeu.info/download.php?id=" +match.Value;
					break;
				}
			}
			catch 
			{
				link ="";
				return link;
			}
			try
			{
				WebRequest myRequest = WebRequest.Create(link);
				WebResponse myResponse = myRequest.GetResponse();
				link = "http://"+myResponse.ResponseUri.Host.ToString()+myResponse.ResponseUri.LocalPath.ToString();
			}
			catch 
			{
				link ="Có lỗi xảy ra hoặc không kết nối được tới www.nhacyeu.info.";
				return link;
			}
			//link = Regex.Replace(link," ","%20");
			if (link!="") link = "<b>Download link : </b><a href=\""+link+"\" target=blank>"+link+"</a><br>";
			else link="Có lỗi xảy ra hoặc địa chỉ không đúng định dạng, bạn nên tham khảo ở phía dưới.";
			return link;
		}
		public string GetAsxUrl(string URL)
		{
			//input : http://nhacso.net/Music/Album/2006/02/05F604ED/
			//Nghe si : http://nhacso.net/Music/Artist/2005/09/05F5E10A/ (Hong Nhung)
			//Bai hat :http://nhacso.net/Music/Song/Thieu-Nhi/2005/12/05F60169/ -->out :mms link
			//out : asx url
			int start,stop;
			string outurl="";
			StreamReader streamReader =null;
			//http://nhacso.net/Music/Album/2006/02/05F604ED/?playAlbum=1
			if (URL.IndexOf("Music/Album")>0)
			{
				string url= URL.Trim()+"?playAlbum=1";
				try
				{
					WebRequest myRequest = WebRequest.Create(url);
					WebResponse myResponse = myRequest.GetResponse();

					streamReader =new StreamReader(myResponse.GetResponseStream(),Encoding.UTF8);
			
					string outputString;
					outputString = streamReader.ReadToEnd( );
					//Response.Write("<p>"+outputString + "</p>");
					//tim doan "showPLayer("
					start =outputString.IndexOf("showPLayer")+ "showPLayer".Length+2;
					//Response.Write("<p>"+start + "</p>");
					stop =outputString.IndexOf("</script>",start)-3;
					//Response.Write("<p>"+stop + "</p>");
					outurl = "http://nhacso.net"+outputString.Substring(start,stop-start);
					//Response.Write("<p>"+outurl + "</p>");
					return outurl;
			
				}
				catch
				{
					//streamReader.Close( );
					//Response.Write("<p>Có lỗi rồi ! Không tìm thấy kết quả!</p>");
					lblLink.Text="Có lỗi rồi ! Không tìm thấy kết quả!";
				}
			}
			if (URL.IndexOf("Music/Artist")>0)
			{
				//hxxp://nhacso.net/Music/nghe_artist.asp?id=
				string url= URL.Trim();
				try
				{
					WebRequest myRequest = WebRequest.Create(url);
					WebResponse myResponse = myRequest.GetResponse();

					streamReader =new StreamReader(myResponse.GetResponseStream(),Encoding.UTF8);
			
					string outputString;
					outputString = streamReader.ReadToEnd( );
					//Response.Write("<p>"+outputString + "</p>");
					//tim doan "play_artist.gif"
					start =outputString.IndexOf("play_artist.gif");
					start =outputString.IndexOf("Artist",start) + "Artist".Length+1;
					//Response.Write("<p>"+start + "</p>");
					stop =outputString.IndexOf("</div>",start)-3;
					//Response.Write("<p>"+stop + "</p>");
					outurl = "http://nhacso.net/Music/nghe_artist.asp?id="+outputString.Substring(start,stop-start);
					//Response.Write("<p>"+outurl + "</p>");
					return outurl;
			
				}
				catch
				{
					//streamReader.Close( );
					lblLink.Text="Có lỗi rồi ! Không tìm thấy kết quả!";
				}
			}
			if (URL.IndexOf("Music/Song")>0)
			{
				//http://nhacso.net/Music/Song/Thieu-Nhi/2005/12/05F60167/
				int key=0;
				string url= URL.Trim();
				try
				{
					WebRequest myRequest = WebRequest.Create(url);
					WebResponse myResponse = myRequest.GetResponse();

					streamReader =new StreamReader(myResponse.GetResponseStream(),Encoding.UTF8);
			
					string outputString;
					outputString = streamReader.ReadToEnd( );
					//Response.Write("<p>"+outputString + "</p>");
					
					//Tim key = so cap the <span> trong div co id = vietkar9
					//start =outputString.IndexOf("Music/Vietkar9");
					start =outputString.IndexOf("vietkar9")+"vietkar9".Length+4;
					//Response.Write("<p>start = "+start + "</p>");
					stop =outputString.IndexOf("script",start)-3;
					//Response.Write("<p>stop = "+stop + "</p>");
					/* test xem du lieu lay dung ko
					outurl =outputString.Substring(start,stop-start);
					outurl = outurl.Replace("</","1");
					outurl = outurl.Replace("<","1");
					outurl = outurl.Replace(">","");
					Response.Write("<p>outurl="+outurl + "</p>");*/
					key =(stop-start)/13;
					//Response.Write("<p>key = "+key + "</p>");
					
					//tim doan "showPLayerz"
					start =outputString.IndexOf("showPLayerz")+ "showPLayerz".Length+2;;
					//Response.Write("<p>"+start + "</p>");
					stop =outputString.IndexOf("))",start)-2;
					//Response.Write("<p>"+stop + "</p>");
					outurl =outputString.Substring(start,stop-start);
					outurl = decode(outurl,key);
					//Response.Write("<p>"+outurl + "</p>");
					return outurl;
			
				}
				catch
				{
					//streamReader.Close( );
					lblLink.Text="Có lỗi rồi ! Không tìm thấy kết quả!";
				}
			}
			else
			{
				lblLink.Text="Sai URL rồi !";
			}
			return outurl;
		}
		public string AsxReader(string ASX_URL)
		{
			//vd :http://nhacso.net/Music/nghe_album.asp?id=100009197
			string html="";
			string ALBUM = "";//song
			if(ASX_URL.IndexOf("mms://")>=0)
			{
				html=html+="<p>Bạn copy link dưới đây đề download bài hát.</br><a href='" + ASX_URL + "' target='new'>" + ASX_URL + "</a><br/>";	
				return html;
			}
			else
			{
				html="";
				try
				{
					WebRequest myRequest = WebRequest.Create(ASX_URL);
					WebResponse myResponse = myRequest.GetResponse();	
					//Stream rssStream = myResponse.GetResponseStream();
					StreamReader streamReader =new StreamReader(myResponse.GetResponseStream(),Encoding.UTF8);
					string temp =streamReader.ReadToEnd();
					temp = temp.Replace("&"," ");
					XmlDocument rssDoc = new XmlDocument();
					//rssDoc.Load(rssStream);
					rssDoc.LoadXml(temp);
					XmlNodeList rssItems = rssDoc.SelectNodes("ASX/ENTRY");
					XmlNode node=null;
					node =rssDoc.SelectSingleNode("ASX/TITLE");
					ALBUM =node.InnerText;
					//Response.Write("<p><b>ALBUM : " + ALBUM + "</b><br/>");
					html+="<p><b>Album/Ca sĩ : " + ALBUM + "</b><br/>";
					string TITLE = "";//song
					string HREF = "";//url
					for (int i = 0; i < rssItems.Count; i++)
					{
						XmlNode rssDetail;
						rssDetail = rssItems.Item(i).SelectSingleNode("TITLE");
						if (rssDetail != null)
						{
							TITLE = rssDetail.InnerText;
						}
						else
						{
							TITLE = "";
						}
						node = rssItems.Item(i).SelectSingleNode("REF");
						rssDetail = node.Attributes["HREF"];	
						if (rssDetail != null)
						{
							HREF = rssDetail.InnerText;
						}
						else
						{
							HREF = "";
						}
						//Response.Write("<p><a href='" + HREF + "' target='new'>" + TITLE + "</a><br/>");
						html+="<p><a href='" + HREF + "' target='new'>" + TITLE + "</a><br/>";	
					}
				}
				catch
				{
					//Response.Write("Dữ liệu từ nhạc số có chỗ bị sai định dạng-->Không đọc được");
				}
			}
			
			return html;

		}
		public string decode(string codeString,int key)
		{
			Regex theRegex = new Regex(" |` |`");
			string link="";
			int temp;
			try
			{
				foreach (string subString in theRegex.Split(codeString))
				{
					//sBuilder.AppendFormat("{0}: {1}\t", id++, subString);
					temp = Convert.ToInt32(subString);
					link = link +Convert.ToChar(temp-key); 
				}	
			}
			catch
			{
				
			}
			return link;
		}
	}
	public class MyUri : System.Uri
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
