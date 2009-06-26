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
        <script type="text/javascript" src="javascripts/acc/jquery-1.3.2.js"></script>
        <script type="text/javascript" src="javascripts/acc/ui.core.js"></script>
        <script type="text/javascript" src="javascripts/acc/ui.accordion.js"></script>

        <link type="text/css" rel="stylesheet"  href="css/acc/ui.all.css" />
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <!-- END INCLUDE JAVASCRIPT AND CSS -->

        <title>Search Media</title>
    </head>
    <body >
        <form action="search.jsp"  method="post">
            <!--ADVANCE SEARCH
        <input type="hidden" id="song" name="song" value="" style="display:none"/>
        <input type="hidden" id="singer" name="singer" value="" style="display:none"/>
        <input type="hidden" id="album" name="album" value="" style="display:none"/>
        <input type="hidden" id="lyric" name="lyric" value="" style="display:none"/>
        <input type="hidden" id="site" name="site" value="" style="display:none"/>
        <!--END ADVANCE SEARCH -->
            <input type="hidden" id="field" name="field" value="mp3" style="display:none"/>
            <input type="hidden" id="typesearch" name="typesearch" value="normal" style="display:none"/>

            <!--END SEARCH -->
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
                                            <img src="images/logo.png">
                                        </td>
                                    </tr>
                                </table>

                                <div class="frame_search">
                                    <table cellspacing="0" cellpadding="0" border="0">
                                        <tr>
                                            <td  valign="top">
                                                <table cellspacing="0" cellpadding="2">
                                                    <tr>
                                                        <td>
                                                            <input type="image" id="music" src="images/music_on.jpg" align="right" onClick="return chooseMusic();">
                                                        </td>
                                                        <td>
                                                            <input type="image" id="film" src="images/film.jpg" align="left" onClick="return chooseFilm();">
                                                        </td>

                                                    </tr>
                                                    <tr>
                                                        <td colspan= "2">
                                                            <input id="query" name="query" type="text" size="40"></input>
                                                        </td>
                                                        <td>
                                                            <!--	<input type="image" src="images/search.jpg" align="right" id="search" onClick="search()">
                                                -->
                                                            <input type="image" src="images/search.jpg"  align="right" onclick="search('normal')">
                                                        </td>
                                                    </tr>

                                                    <tr >
                                                        <td  align="right" >
                                                            <input type="image" id="search_adv"  src="images/search_adv_o.jpg"  onclick="search_adv()" >
                                                        </td>
                                                        <td  align="left" >
                                                            <input type="image" id="search_tc"  src="images/search_tc_o.jpg"  onclick="search_tc()" >
                                                        </td>
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
                                                Tên ca sĩ/ diễn viên
                                            </td>
                                            <td width="50%">
                                                <input type="text" align="right" id="singer" name="singer"  />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="left">
                                                Tên bài  hát / tên bộ phim
                                            </td>
                                            <td width="50%">
                                                <input type="text" align="right" id="song" name="song"  />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="left">
                                                Lời bài hát/ phụ đề phim
                                            </td>
                                            <td width="50%">
                                                <input type="text" align="right" id="lyric" name="lyric"  />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="left">
                                                Nguồn
                                            </td>
                                            <td width="50%">
                                                <input type="text" align="right" id="site" name="site"  />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="left">
                                                <input type="button" value="Tìm kiếm" id="bt_search_avd" onclick="search('advance')" >
                                            </td>
                                            <td width="50%">
                                                <input type="button" value="Đóng lại" id="bt_close" onClick="bt_close()" />
                                            </td>
                                        </tr>

                                    </table>
                                </div>
                                <div class="search_tc" id= "tc" name="tc" style="visibility: hidden ; height:0px" >
                                    <table border="0"  cellpadding="2" cellspacing="0"  align="center" style="font-size:10px">
                                        <tr>
                                            <td width="50%"><input id="tc_m_song" name="tc_m" type="radio" value="" checked="checked" onClick="setField('song')"/> Tên bài hát/bộ phim </td>
                                        </tr>

                                        <tr>
                                            <td width="50%"><input id="tc_m_song" name="tc_m" type="radio" value="" checked="checked" onClick="setField('singer')"/> Tên ca sĩ/ diễn viên</td>
                                        </tr>
                                        <tr>
                                            <td width="50%"><input id="tc_m_singer" name="tc_m" type="radio" value="" onClick="setField('album')"/> Tên album </td>
                                        </tr>
                                        <tr>
                                            <td width="50%"><input id="tc_m_lyric" name="tc_m" type="radio" value="" onClick="setField('lyric')"/> Lời bài hát/ phụ đề phim </td>
                                        </tr>
                                        <tr>
                                            <td width="50%"><input id="tc_m_src" name="tc_m" type="radio" value=""  onclick="setField('site')"/> Nguồn</td>
                                        </tr>

                                    </table>
                                </div>

                            </div>
                        </td>
                        <td></td>
                    </tr>
                    <tr width="100%" >
                        <td  align="center"  colspan="3" style="color:red">
                           Có lỗi xãy ra !<br>
                           Mã lỗi : 
                           <%
                            request.setCharacterEncoding("utf-8");
                            session = request.getSession();
                            out.print(session.getAttribute("error"));
                            %>
                        </td>
                    </tr>
                    <tr width="100%" >
                        <td class="foo" align="center" valign="bottom" colspan="3">
                        </td>
                    </tr>
                </table>
            </div>
        </form>
    </body>
</html>
