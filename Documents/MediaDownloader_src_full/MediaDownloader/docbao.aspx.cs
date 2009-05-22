using System;
using System.IO;
using System.Net;
using System.Text;
using System.Text.RegularExpressions;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;

namespace MediaDownloader
{
	/// <summary>
	/// Summary description for docbao.
	/// </summary>
	public class docbao : Page
	{
		protected TextBox txtUrl;
		protected ImageButton cmdDownload;
		protected Label lblLink;
		protected HtmlForm Form1;
	
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
			this.cmdDownload.Click += new ImageClickEventHandler(this.cmdDownload_Click);
			this.Load += new EventHandler(this.Page_Load);

		}
		#endregion

		private void cmdDownload_Click(object sender, ImageClickEventArgs e)
		{
			lblLink.Text = GetLinkDocbao(txtUrl.Text.Trim());
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
			catch (Exception ex)
			{
				link = "Có lỗi xảy ra !";
			}
			return link;
		}
	}
}
