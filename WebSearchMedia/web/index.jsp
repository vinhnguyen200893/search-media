<%-- 
    Document   : index
    Created on : Jun 10, 2009, 11:33:01 AM
    Author     : Thanh Nga
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- INCLUDE JAVASCRIPT AND CSS-->
        <script type="text/javascript"  src="javascripts/index.js"></script>
        <script type="text/javascript"  src="javascripts/prototype.js"></script>

        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <script src="js/index.js"  type="text/javascript"></script>

        <link rel="stylesheet" type="text/css"  href="css/style_mac.css" />
        <script src="javascripts/jquery_mac.js" type="text/javascript" ></script>
        <script src="javascripts/interface_mac.js" type="text/javascript" ></script>

        <script type="text/javascript" src="javascripts/sclqcn6.js"></script>

        <!-- END INCLUDE JAVASCRIPT AND CSS -->

        <title>Search Media</title>
    </head>
    <body >

        <form action="search.jsp" method="post">
		   <input type="hidden" id="type" name="type" value="mp3" style="display:none"/>      
            <input type="hidden" id="type_search" name="type_search" value="normal" style="display:none"/>

            <div class="bg">
                <table width="100%" border="0"  cellspacing="0" cellpadding="0">
                    <tr width="100%" >
                        <td>&nbsp;</td>
                        <td  valign="top" height="350">
                            <div class="bgHeader" >
                                <div class= "login">
                                    <table cellpadding="0" cellspacing="0" border="0">
                                        <tr>
                                            <td align="right">
    
                                            </td>
                                        </tr>
                                    </table>

                                </div>
                                <table cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td class="logo">
                                            <a href="#" onClick="return false;"><img src="images/logo.png"/> </a>
                                        </td>
                                    </tr>
                                </table>

                                <div class="frame_search">
                                    <table cellspacing="0" cellpadding="0" border="0">
                                        <tr>
                                            <td  valign="top">
                                                <table cellspacing="0" cellpadding="2">
                                                    <tr>
                                                        <td align="center">
                                                          <a href="#"><input type="image" id="music" src="images/music_on.jpg" align="left" ></a>
                                                            <a href="index_video.jsp">
                                                            <input name="image" type="image" id="film" src="images/film.jpg" align="left">
                                                            </a></td>
                                                        <td><a href="search_advance.jsp" style="font-size:10px">Tìm kiếm nâng cao</a></td>
                                                    </tr>
                                                    <tr>
                                                        <td >
                                                            <input id="query" name="query" type="text" size="40"></input>                                                        </td>
														<td>
                                                            <select id="field" name="field" >
															<option value="song">Tên bài hát</option>
															<option value="singer">Tên nghệ sĩ</option>
															<option value="album">Tên album</option>
															<option value="lyric">Lời bát hát</option>
															</select>                  
														</td>
                                                        <td>                                                          
                                                            <table id="vista-buttons.com:idlqcn6" width=0 cellpadding=0 cellspacing=0 border=0><tr><td style="padding-right:0px" title ="Tìm  ">
                                                            <a href="search.jsp" onMouseOver='xpe("lqcn6o");' onMouseOut='xpe("lqcn6n");' onMouseDown='xpe("lqcn6c");'><img id="xpi_lqcn6" src="images/btlqcn6_0.gif" name=vblqcn6 width="82" height="30" border=0 alt="Tìm"></a></td></tr></table>                                                        </td>
                                                    </tr>                                                   
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                          </div></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td width="780" height="70">


                            <!-- This is main content -->
                <!-- /This is main content -->
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr width="100%" >
                        <td align="center" valign="top" colspan="3">
                          <div class="foo">
                                <table  border="0"  cellpadding="0" cellspacing="2">
                                    <tr height="20px">
                                    </tr>
                                    <tr width="100%"  >
                                        <td   align="center" valign="top" >
                                            <div class="dock" id="dock2" >
                                                <div class="dock-container2">
                                                    <a class="dock-item2" href="#" ><span style="color:#FF0000" >Home</span><img src="images/mac/home.png" alt="home" /></a>
                                                    <a class="dock-item2" href="#"><span style="color:#FF0000">Contact</span><img src="images/mac/email.png" alt="contact" /></a>
                                                    <a class="dock-item2" href="#"><span style="color:#FF0000">Music</span><img src="images/mac/music.png" alt="music" /></a>
                                                    <a class="dock-item2" href="#"><span style="color:#FF0000">Video</span><img src="images/mac/video.png" alt="video" /></a>
                                                    <a class="dock-item2" href="#"><span style="color:#FF0000">Top nhạc</span><img src="images/mac/rss.png" alt="rss" /></a>
                                                    <a class="dock-item2" href="#"><span style="color:#FF0000" >Top phim</span><img src="images/mac/rss2.png" alt="rss" /></a>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                                <p>
								<div class="fooCopyright" style=" padding:30px ; width: 600px; height: 50px;">

                                    Chương trình Search Media phát triển bởi 0512230 - 0512286 <br/>
                                    dựa trên thư viện Lucene 					<br/>
                                    © Copyright 2009 <br/>

                                </div>
								</p>
                            </div>
                            
                        </td>
                    </tr>
                </table>
            </div>

        </form>

        <script type="text/javascript">
            $(document).ready(
            function()
            {
                $('#dock').Fisheye(
                {
                    maxWidth: 50,
                    items: 'a',
                    itemsText: 'span',
                    container: '.dock-container',
                    itemWidth: 40,
                    proximity: 90,
                    halign : 'center'
                }
            )
                $('#dock2').Fisheye(
                {
                    maxWidth: 60,
                    items: 'a',
                    itemsText: 'span',
                    container: '.dock-container2',
                    itemWidth: 40,
                    proximity: 80,
                    alignment : 'left',
                    valign: 'bottom',
                    halign : 'center'
                }
            )
            }
        );
        </script>
    </body>
</html>
