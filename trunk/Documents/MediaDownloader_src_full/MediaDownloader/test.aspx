<%@ Page language="c#" Codebehind="test.aspx.cs" AutoEventWireup="false" Inherits="MediaDownloader.Test.test" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<HTML>
	<HEAD>
		<title>test</title>
		<meta name="GENERATOR" Content="Microsoft Visual Studio .NET 7.1">
		<meta name="CODE_LANGUAGE" Content="C#">
		<meta name="vs_defaultClientScript" content="JavaScript">
		<meta name="vs_targetSchema" content="http://schemas.microsoft.com/intellisense/ie5">
	</HEAD>
	<body MS_POSITIONING="GridLayout">
		<form id="Form1" method="post" runat="server">
			<asp:TextBox id="txtUrl" runat="server" Width="80%"></asp:TextBox>
			<asp:Button id="cmdGetContent" runat="server" Text="Get Content" Width="96px"></asp:Button><br>
			<asp:TextBox id="txtContent" runat="server" Width="80%" Height="352px" TextMode="MultiLine"></asp:TextBox>
		</form>
	</body>
</HTML>
