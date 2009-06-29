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

        <form action="show_result.htm" method="post">
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
                                                <!--
                                        <div class="dock" id="dock">
                                          <div class="dock-container">
                                              <a class="dock-item" href="#"><img src="images/mac/home.png"  /><span style="color:#FF0000">Home</span></a>
                                              <a class="dock-item" href="#"><img src="images/mac/singin.png" /><span style="color:#FF0000">Đăng nhập</span></a>
                                              <a class="dock-item" href="#"><img src="images/mac/singp1.png" /><span style="color:#FF0000">Đăng ký</span></a>
                                         </div>
                                        </div>
                                        -->
                                            </td>
                                        </tr>
                                    </table>

                                </div>
                                <table cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td class="logo">
                                            <a href="#"><img src="images/logo.png"/> </a>
                                        </td>
                                    </tr>
                                </table>

                                <div class="frame_search">
                                    <table cellspacing="0" cellpadding="0" border="0">
                                        <tr>
                                            <td  valign="top">
                                                <table cellspacing="0" cellpadding="2">
                                                    <tr>
                                                        <td width="71"><a><input type="image" name="objSearch" src="images/t_mp3.jpg" width="71" height="26" onClick="chooseObj(this);return false;" /></a></td>
                                                        <td width="71"><a><input type="image" name="objSearch" src="images/t_video.jpg" width="71" height="26" onClick="chooseObj(this);return false;" /></a></td>
                                                        <td width="71"><a><input type="image" name="objSearch" src="images/t_album.jpg" width="71" height="26" onClick="chooseObj(this);return false;" /></a></td>
                                                        <td width="71"><a><input type="image" name="objSearch" src="images/t_lyric_o.jpg" width="71" height="26" onClick="chooseObj(this);return false;" /></a></td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan= "4">
                                                            <input id="textbox_search" type="text" size="45"></input>                                                        </td>
                                                        <td width="120">
                                                            <!--	<input type="image" src="images/search.jpg" align="right" id="search" onClick="search()">

                                                            <input type="image" src="images/search.jpg" id="query" align="right">
                                                            -->

                                                            <table id="vista-buttons.com:idlqcn6" width=0 cellpadding=0 cellspacing=0 border=0><tr><td style="padding-right:0px" title ="Tìm  ">
                                                            <a href="search.jsp" onMouseOver='xpe("lqcn6o");' onMouseOut='xpe("lqcn6n");' onMouseDown='xpe("lqcn6c");'><img id="xpi_lqcn6" src="images/btlqcn6_0.gif" name=vblqcn6 width="82" height="30" border=0 alt="Tìm  "></a></td></tr></table>                                                        </td>
                                                    </tr>
                                                    <tr >
                                                        <td colspan="4"  align="center" >
                                                            <input type="image" id="search_adv"  src="images/search_adv_o.jpg"  onclick="search_adv();return false;" >                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="search_adv" id= "adv" name="adv" style="visibility: hidden ; height:0px"  >
                                    <table border="0"  cellpadding="2" cellspacing="0"  align="right" style="font-size:10px">
                                        <tr>
                                            <td align="left">
                                            Tên ca sĩ/ diễn viên									</td>
                                            <td width="50%" colspan="2">
                                            <input type="text" align="right" id="singer" name="song"  />									</td>
                                        </tr>
                                        <tr>
                                            <td align="left">
                                            Tên album / tên bộ phim									</td>
                                            <td width="50%" colspan="2">
                                            <input type="text" align="right" id="album" name="song"  />									</td>
                                        </tr>
                                        <tr>
                                            <td align="left">
                                            Lời bài hát/ phụ đề phim									</td>
                                            <td width="50%" colspan="2">
                                            <input type="text" align="right" id="lyric" name="song"  />									</td>
                                        </tr>
                                        <tr>
                                            <td align="left">
                                            Nguồn									</td>
                                            <td width="50%" colspan="2">
                                            <input type="text" align="right" id="site_music" name="song"  />									</td>
                                        </tr>
                                        <tr>
                                            <td align="left">&nbsp;</td>
                                            <td align="left">
                                                <input name="button" type="button" id="bt_search_avd" onClick="return false;" value="Tìm kiếm"  align="left">
                                            </td>
                                            <td align="left">
                                                <input type="button" value="Đóng lại" id="bt_close" onClick="return btclose();" align="left" />
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </td>
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
                                                    <a class="dock-item2" href="#"><span style="color:#FF0000">Contact</span><img src="images/mac/email.png" alt="contact" /></a>
                                                    <a class="dock-item2" href="#"><span style="color:#FF0000">Top Music</span><img src="images/mac/music.png" alt="music" /></a>
                                                    <a class="dock-item2" href="#"><span style="color:#FF0000">Top Video</span><img src="images/mac/video.png" alt="video" /></a>
                                                    <a class="dock-item2" href="#"><span style="color:#FF0000">Top Album</span><img src="images/mac/rss.png" alt="rss" /></a>
                                                    <a class="dock-item2" href="#"><span style="color:#FF0000" >Top Comment</span><img src="images/mac/rss2.png" alt="rss" /></a>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
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
