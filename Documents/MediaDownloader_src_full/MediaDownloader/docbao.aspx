<%@ Register TagPrefix="kikicoco" TagName="Header" src="Skins/HeaderDb.ascx" %>
<%@ Register TagPrefix="kikicoco" TagName="Footer" src="Skins/Footer.ascx" %>
<%@ Page language="c#" Codebehind="docbao.aspx.cs" AutoEventWireup="false" Inherits="MediaDownloader.docbao" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<HTML>
	<HEAD>
		<title>Download báo theo số tại docbao.dec.vn</title>
		<meta name="description" content="Download báo theo số tại docbao.dec.vn">
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
				<span class="white"><span id="info_1" class="chooser_inst"><b>Download báo theo số tại <a href="http://docbao.dec.vn">
								docbao.dec.vn</a>
							<br>Bạn copy địa chỉ của số báo và điền vào ô dưới đây.</span> </span>
				<div id="chooser_set_1">
					<form id="Form1" method="post" runat="server">
						<br>
						<div class="campo_entrada_url_out">
							<asp:TextBox ID="txtUrl" Runat="server" class="campo_entrada_url"></asp:TextBox>
						</div>
						<asp:ImageButton ImageUrl="botdl2.gif" Runat="server" ID="cmdDownload"></asp:ImageButton>
					</form>
				</div>
				<br>
				<small id="docbao" class="selector_info">Ví dụ: <u>http://docbao.dec.vn/viewer.aspx?nid=7b7f844408fc434facf94999f92b2b08</u></small><br>			
			</div>
		</div>
		<br>
		<div class="body_container">
			<div class="contenedor_principal_inner">
				<asp:Label ID="lblLink" Runat="server">Kết quả sẽ hiển thị ở đây!</asp:Label>
			</div>
		</div>
		<div align="center">
			<br>
		</div>
		<div align="center" class="body_container">
			<kikicoco:Footer id="Footer" Runat="Server"></kikicoco:Footer>
		</div>
		</B>
	</body>
</HTML>
