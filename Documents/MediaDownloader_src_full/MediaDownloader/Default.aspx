<%@ Page language="c#" Codebehind="Default.aspx.cs" AutoEventWireup="false" Inherits="MediaDownloader.media" %>
<%@ Register TagPrefix="kikicoco" TagName="Header" src="Skins/HeaderM.ascx" %>
<%@ Register TagPrefix="kikicoco" TagName="Footer" src="Skins/Footer.ascx" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<HTML>
	<HEAD>
		<title>Download music from nhacso.net, i4vn.com.vn, sonic200.com, bennhac.com, 
			vnmusic.com.vn, Giaidieu.net, Music.phuonghong.org,baokhanh.biz,Enhac.net,
			Nhacnen.net,Tuoitho.net,music.vuilen.com,muzic9.com(coolrip.com),music.vietvoice.net,
			cuasoamnhac.net,hoathachthao.info,tialia.net,notnhactre.net,nhacviet.vietnamnet.vn,gmetal.net,
			mtv.mtvhalong.com,nhacyeu.info,nghenhacso.net,sonic.vn</title>
		<meta name="description" content="Download music from nhacso.net,i4vn.com.vn,sonic200.com,sonic.vn, bennhac.com, vnmusic.com.vn, Giaidieu.net, Music.phuonghong.org, baokhanh.biz, enhac.net,nhacnen.net,tuoitho.net,music.vuilen.com,muzic9.com(coolrip.com),music.vietvoice.net,cuasoamnhac.net,hoathachthao.info,tialia.net,notnhactre.net,nhacviet.vietnamnet.vn,gmetal.net,mtv.mtvhalong.com,nhacyeu.info,nghenhacso.net">
		<meta name="CODE_LANGUAGE" Content="C#">
		<meta name="vs_defaultClientScript" content="JavaScript">
		<meta name="vs_targetSchema" content="http://schemas.microsoft.com/intellisense/ie5">
		<link rel="stylesheet" href="styleN.css">
	</HEAD>
	<body MS_POSITIONING="GridLayout">
		<div align="center" class="body_container">
			<kikicoco:Header id="ctlHeader" Runat="Server"></kikicoco:Header>
		</div>
		<div align="center">
			<br>
		</div>
		<div class="body_container">
			<div class="contenedor_principal_inner">
				<span class="white">
				<span id="info_1" class="chooser_inst">
					<b>Download file nhạc từ các site:</b> 
					<a target="_blank" href="http://nhacso.net">nhacso.net</a>, 
					<a target="_blank" href="http://www.i4vn.com.vn/&amp;music">i4vn.com.vn</a>, 
					<a target="_blank" href="http://sonic.vn/front/music/">sonic.vn</a>,
					<a target="_blank" href="http://music.bennhac.com">bennhac.com</a>, 
					<a target="_blank" href="http://www.vnmusic.com.vn">vnmusic.com.vn</a>, 
					<a target="_blank" href="http://www.giaidieu.net">giaidieu.net</a>,
					<a target="_blank" href="http://music.vuilen.com">music.vuilen.com</a>,
					<a target="_blank" href="http://baokhanh.biz">baokhanh.biz</a>,
					<a target="_blank" href="http://www.nhacyeu.info">nhacyeu.info</a>,
					<a target="_blank" href="http://Nhacnen.net">nhacnen.net</a>,
					<a target="_blank" href="http://tuoitho.net">tuoitho.net</a>,
					<a target="_blank" href="http://enhac.net">enhac.net</a>,
					<a target="_blank" href="http://music.vietvoice.net">music.vietvoice.net</a>,
					<a target="_blank" href="http://cuasoamnhac.net">cuasoamnhac.net</a>,
					<a target="_blank" href="http://hoathachthao.info">hoathachthao.info</a>,
					<a target="_blank" href="http://tialia.net">tialia.net</a>,
					<a target="_blank" href="http://notnhactre.net">notnhactre.net</a>,
					<a target="_blank" href="http://nhacviet.vietnamnet.vn">nhacviet.vietnamnet.vn</a>,
					<a target="_blank" href="http://mtv.mtvhalong.com">mtv.mtvhalong.com</a>,
					<a target="_blank" href="http://www.nghenhacso.net">nghenhacso.net</a>,
					<a target="_blank" href="http://gmetal.net">gmetal.net</a>,
					<a target="_blank" href="http://muzic9.com">muzic9.com</a><br>
					<b>Download game từ các site:</b>
					<a target="_blank" href="http://sonic.vn/front/game/">sonic.vn</a><br>
					<b>Download file báo,truyện tranh từ các site:</b> 
					<a target="_blank" href="http://docbao.dec.vn">docbao.dec.vn</a>, 
					<a target="_blank" href="http://comic.vuilen.com">comic.vuilen.com</a><br>
					<b>Hướng dẫn : </b>Bạn vào các trang trên tìm bài hát, truyện, báo mà mình yêu 
						thích rồi copy link điền vào ô dưới đây.<br>
					<b><a target="_blank" href="http://media.ajaxviet.com/huong_dan_download_bao_va%20_truyen_tranh.htm">
								Hướng dẫn download truyện tranh và báo tại comic.vuilen.com và docbao.dec.vn </a></b><img src="http://media.ajaxviet.com/new.gif" alt="Mới" border="0"></span>
				</span>
				<div id="chooser_set_1">
					<form id="Form1" method="post" runat="server">
						<br>
						<div class="campo_entrada_url_out">
							<asp:TextBox ID="txtUrl" Runat="server" class="campo_entrada_url"></asp:TextBox>
						</div>
						<asp:ImageButton ImageUrl="botdl2.gif" Runat="server" ID="cmdDownload"></asp:ImageButton>
					</form>
				</div>
				
			</div>
		</div>
		<div class="body_container">
			<div class="contenedor_principal_inner">
				<asp:Label ID="lblNews" Runat="server"></asp:Label>
			</div>
		</div>
		<div class="body_container">
			<div class="contenedor_principal_inner">
				<asp:Label ID="lblLink" Runat="server">Kết quả sẽ hiển thị ở đây!</asp:Label>
			</div>
		</div>
		<div class="body_container">
			<div class="contenedor_principal_inner">
				<small id="huongdanchitiet" class="selector_info"><b>Hướng dẫn chi tiết cho từng site:</b>(Để chắc chắn download được tất cả các trang các bạn nên dùng <b><a href="http://www.amazesoft.com/download.htm" target="_blank">Flashget</a></b> để download nhạc.)</small><br>
				<small id="vidu" class="selector_info"><b>Ví dụ</b> (bạn nên xem kỹ định dạng link cho từng trang ở đưới đây):</small><br>
				<small id="nhacso" class="selector_info"><b>Nhacso.net:</b> <u>http://nhacso.net/Music/Song/Nhac%2DTrinh/2005/09/05F5E2CC/</u><br>Lưu ý là nhacso.net bạn có thể down theo cả link album và link nghệ sĩ.(Bạn nên tham khảo ở đây <a href="http://nhacso.ajaxviet.com">http://nhacso.ajaxviet.com</a>)</small><br>
				<small id="I4vn" class="selector_info"><b>I4vn.com.vn:</b> <u>http://www.i4vn.com.vn/&amp;music/index.php?i4vn=choinhac&amp;id=1473</u></small><br>
				<small id="sonic200" class="selector_info"><b>sonic.vn:</b> <u>http://sonic.vn/front/music/play.aspx?songid=35689&amp;musicgenreid=63</u>
					hoặc <u>http://sonic.vn/front/music/play.aspx?songid=35689</u><br>Link game: <u>http://www.sonic.vn/front/game/play.aspx?GameID=26&GameGenreID=1</u></small><br>
				<small id="bennhac" class="selector_info"><b>Bennhac.com:</b> <u>http://music.bennhac.com/NhacPham/VN/15640/Ai-Dua-Em-Ve/review/new-file-sharing-server.html</u></small><br>
				<small id="vnmusic" class="selector_info"><b>Vnmusic.com.vn:</b> <u>http://www.vnmusic.com.vn/music/index.php?aid=nghenhac&amp;id=773</u></small><br>
				<small id="giaidieu" class="selector_info"><b>Giaidieu.net:</b> <u>http://www.giaidieu.net/cgi-bin/musiconline.run?v=song&amp;a=3270</u></small><br>
				<small id="mphuonghong" class="selector_info"><b>Music.phuonghong.net:</b> <u>http://music.phuonghong.org/play.php?albumid=159&amp;songid=1876</u></small><br>
				<small id="baokhanh" class="selector_info"><b>Baokhanh.biz:</b> <u>http://www.baokhanh.biz/index.php?v=nghe&id=6579&trang=1</u></small><br>
				<small id="nhacyeu" class="selector_info"><b>Nhacyeu.info:</b> <u>http://www.nhacyeu.info/index.php?v=nghe&id=6579&trang=1</u></small><br>
				<small id="enhac" class="selector_info"><b>Enhac.net:</b> <u>http://www.enhac.net/music/index.php?eNhaC=play&id=53</u> - Tìm link chỗ ngôi sao màu vàng bên cạnh bài hát nếu không thấy</small><br>
				<small id="nhacnen" class="selector_info"><b>Nhacnen.net:</b> Album : <u>http://www.nhacnen.net/index.php?action=album&id=274 </u>,
				Bài hát : <u>http://www.nhacnen.net/index.php?action=addlyrics&song=2760</u> (bạn copy link này ở chỗ "Add Lyrics" cạnh bài hát đó)
				</small><br>
				<small id="vietvoice" class="selector_info"><b>Music.vietvoice.net:</b> <u>http://music.vietvoice.net/song_details.php?lang=Vietnamese&ID=4154</u></small><br>
				<small id="cuasoamnhac" class="selector_info"><b>Cuasoamnhac.net:</b> <u>http://cuasoamnhac.net/#Play/1169</u></small><br>
				<small id="hoathachthao" class="selector_info"><b>Hoathachthao.info:</b> <u>http://hoathachthao.info/tacpham.php?htt=nghe&baiso=13283</u></small><br>
				<small id="tialia" class="selector_info"><b>Tialia.net:</b> <u>http://www.tialia.net/media.php?mediaid=50272</u> hoặc <u>http://tialia.net/media.php?s=62ee1c9b80e42dbfaf94d46216f8d02b&mediaid=123205</u></small><br>
				<small id="notnhactre" class="selector_info"><b>Notnhactre.net:</b> <u>http://notnhactre.net/view_Program.aspx?pid=170</u></small><br>
				<small id="vietnamnet" class="selector_info"><b>Nhacviet.vietnamnet.vn:</b> Link nhạc :<u>http://nhacviet.vietnamnet.vn/vn/nhacpham/chitiet/2041/index.aspx</u><br>Link Video lấy theo trang, ví dụ trang 2 :<u>http://nhacviet.vietnamnet.vn/vn/videoclip/0/2/index.aspx</u></small><br>
				<small id="tuoitho" class="selector_info"><b>Tuoitho.net:</b> <u>http://www.tuoitho.net/diendan/index.php?action=musiclink&act=view&id=3645</u></small><br>
				<small id="mtvhalong" class="selector_info"><b>Mtv.mtvhalong.com:</b> <u>http://mtv.mtvhalong.com/nghenhac.php?mtvhalong.com,157,40</u> hoặc <u>http://mtv.mtvhalong.com/nghenhac.php?guitangbaihat,157,40</u></small><br>
				<small id="nghenhacso" class="selector_info"><b>Nghenhacso.net:</b> <u>http://www.nghenhacso.net/index.php?eNhaC=play&id=423</u></small><br>
				<small id="gmetal" class="selector_info"><b>Gmetal.net:</b> <u>http://gmetal.net/songs/3476/</u> hoặc theo ca sĩ : <u>http://gmetal.net/singers/196/</u></small><br>
				<small id="muzic9" class="selector_info"><b>Muzic9.com:</b> <u>http://muzic9.com/index.php?c=song&songid=193117</u> - Trang nhạc nước ngoài tuyệt hảo - tạm dừng</small><br>
				<small id="docbao" class="selector_info"><b>docbao.dec.vn:</b> <u>http://docbao.dec.vn/viewer.aspx?nid=7b7f844408fc434facf94999f92b2b08</u>- 
					link số báo cần lấy</small><br>
				<small id="cphuonghong" class="selector_info"><b>Comic.vuilen.com:</b> <u>http://comic.vuilen.com/view_book_detail.php?bookid=128&chapterid=1915</u>--&gt;Bảy 
					Viên ngọc rồng đấy
			</div>
		</div>
		<div class="body_container">
			<div class="contenedor_principal_inner">
				<!-- BEGIN CBOX - http://www.cbox.ws -->
				<div align="center" id="cboxdiv">
				<iframe frameborder="0" width="730" height="200" src="http://www.cbox.ws/box/?boxid=887401&amp;boxtag=6104&amp;sec=main" marginheight="2" marginwidth="2" scrolling="auto" allowtransparency="yes" name="cboxmain" style="border:#ababab 1px solid;" id="cboxmain"></iframe><br>
				<iframe frameborder="0" width="730" height="75" src="http://www.cbox.ws/box/?boxid=887401&amp;boxtag=6104&amp;sec=form" marginheight="2" marginwidth="2" scrolling="no" allowtransparency="yes" name="cboxform" style="border:#ababab 1px solid;border-top:0px" id="cboxform"></iframe>
				</div>
				<!-- END CBOX -->				
			</div>
		</div>
		<div align="center" class="body_container">
			<kikicoco:Footer id="Footer" Runat="Server"></kikicoco:Footer>
		</div>
		</SMALL>
	</body>
</HTML>
