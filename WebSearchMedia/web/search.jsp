<%--
    Document   : index
    Created on : Jun 10, 2009, 11:33:01 AM
    Author     : Thanh Nga
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="lucene.search.media.searcher.*" %>
<%@ page import="lucene.search.media.objects.*" %>
<%@ page import="lucene.search.media.parsehtml.HtmlHandler"%>
<%@ page import="org.apache.lucene.document.Document"%>
<%@ page import="java.io.File" %>
<%@ page import="paginator.Paging" %>
<%@ page import="org.apache.poi.util.StringUtil" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- INCLUDE JAVASCRIPT AND CSS-->
        <script type="text/javascript"  src="javascripts/index.js"></script>

        <script type="text/javascript" src="javascripts/acc/jquery-1.3.2.js"></script>
        <script type="text/javascript" src="javascripts/acc/ui.core.js"></script>
        <script type="text/javascript" src="javascripts/acc/ui.accordion.js"></script>

        <link type="text/css" rel="stylesheet"  href="css/acc/ui.all.css" />
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <!-- END INCLUDE JAVASCRIPT AND CSS -->
        <script type="text/javascript">
            $(document).ready(function(){
                $("#accordion").accordion('option','height',"auto");
                $("#accordion").accordion();
            });
        </script>
        <title>Search Media</title>
    </head>
    <body >
        <%
        String Str_paging = "";
        String query = "";
        StringBuffer sb = new StringBuffer();
        //PARAMETER CONFIG
        //PAGING RESULT
        int rowsPerPage = 5;
        int pageNum = 0;
        long total = 0;
        int max_page = 5;
        Hit h = new Hit();
        h.setTotaldocs(0);
        int unicode = 0;//unicode or no
        try {

            request.setCharacterEncoding("utf-8");
            session = request.getSession();

            //GET QUERY PARAMETER TO SEARCH
            if (request.getParameter("query") != null) {
                query = request.getParameter("query");
                if (query != "") {
                    session.setAttribute("query", query);
                }
            } else if (request.getParameter("page") != null) {
                query = session.getAttribute("query").toString();
            }
            if (query != "") {

                //get all parameter to make search
                String type="";
                if(request.getParameter("type")!=null)
                {
                       type = request.getParameter("type");//video or mp3
                       session.setAttribute("type", type);
                }
                else
                    type = session.getAttribute("type").toString();

                //get typesearch= normail/advance
                String type_search="";
                if(request.getParameter("type_search")!=null)
                {
                       type_search = request.getParameter("type_search");//video or mp3
                       session.setAttribute("type_search", type_search);
                }
                else
                    type_search = session.getAttribute("type_search").toString();
                
                if (StringUtil.isUnicodeString(query)) {
                    unicode = 1;//unicode or no
                }
                //get Directory to search
                ServletContext context = this.getServletContext();
                String path = context.getRealPath("data");
                //String path = "data";
                if (type.equals("video")) {
                    path += "/video";
                } else if (type.equals("mp3")) {
                    path += "/mp3";
                }
                File f = new File(path);

                //make search
                Searcher s = new Searcher();
                if (type_search.equals("normal"))//normal search
                {
                    //song,artist or lyric,album
                    String field="";
                    if(request.getParameter("field")!=null)
                    {
                           field = request.getParameter("field");//video or mp3
                           session.setAttribute("field", field);
                    }
                    else
                        field = session.getAttribute("field").toString();

                    if (!field.equals("lyric")) {
                        if (unicode == 1) {
                            field += "vn";
                        } else {
                            field += "en";
                        }
                    }
                    h = s.search(f, field, query);
                } else//advance search
                {
                    HtmlHandler html = new HtmlHandler();
                    QueryObject q = new QueryObject();
                    //get all parameter in hidden field
                    if (request.getParameter("song") != null) {
                        if (StringUtil.isUnicodeString(request.getParameter("song"))) {
                            unicode = 1;
                        } else {
                            unicode = 0;
                        }
                        if (unicode == 1) {
                            q.set_Map("songvn", request.getParameter("song"));
                        } else {
                            q.set_Map("songen", request.getParameter("song"));
                        }

                    }
                    if (request.getParameter("singer") != null) {
                        if (StringUtil.isUnicodeString(request.getParameter("singer"))) {
                            unicode = 1;
                        } else {
                            unicode = 0;
                        }
                        if (unicode == 1) {
                            q.set_Map("singervn", request.getParameter("singer"));
                        } else {
                            q.set_Map("singeren", request.getParameter("singer"));
                        }

                    }
                    if (request.getParameter("album") != null) {
                        if (StringUtil.isUnicodeString(request.getParameter("album"))) {
                            unicode = 1;
                        } else {
                            unicode = 0;
                        }
                        if (unicode == 1) {
                            q.set_Map("albumvn", request.getParameter("album"));
                        } else {
                            q.set_Map("albumen", request.getParameter("album"));
                        }

                    }
                    if (request.getParameter("site") != null) {
                        q.set_Map("idservice", request.getParameter("site").replaceAll("[' ','-','_']", "").toUpperCase());
                    }
                    if (request.getParameter("lyric") != null) {
                        q.set_Map("lyric", request.getParameter("lyric").toUpperCase());
                    }
                    h = s.searchAdvance(f, q);

                }

                sb.append("Tìm thấy  <b>" + h.getTotaldocs() + "</b> tài liệu cho <b>" + query + "</b> - (" + (float) h.getTime() / 1000 + " giây)");

                if (request.getParameter("page") != null) {
                    pageNum = Integer.parseInt(request.getParameter("page"))-1;
                }
                total = h.getTotaldocs();
                //int offset = (pageNum - 1) * rowsPerPage;
                String self = "search.jsp";
                Str_paging = Paging.make_paging(rowsPerPage, pageNum+1, total, max_page, self);
            }

        %>
        <form  method="post">
            <!--ADVANCE SEARCH
        <input type="hidden" id="song" name="song" value="" style="display:none"/>
        <input type="hidden" id="singer" name="singer" value="" style="display:none"/>
        <input type="hidden" id="album" name="album" value="" style="display:none"/>
        <input type="hidden" id="lyric" name="lyric" value="" style="display:none"/>
        <input type="hidden" id="site" name="site" value="" style="display:none"/>
            <!--END ADVANCE SEARCH -->
            <input type="hidden" id="search_again" name="search_again" value="" style="display:none"/>
            <input type="hidden" id="type" name="type" value="mp3" style="display:none"/>
            <input type="hidden" id="field" name="field" value="song" style="display:none"/>
            <input type="hidden" id="type_search" name="type_search" value="normal" style="display:none"/>

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
                                                            <input type="image" id="music" src="images/music_on.jpg" align="right" onClick="chooseMusic()">
                                                        </td>
                                                        <td>
                                                            <input type="image" id="film" src="images/film.jpg" align="left" onClick="chooseFilm()">
                                                        </td>

                                                    </tr>
                                                    <tr>
                                                        <td colspan= "2">
                                                            <input id="query" name="query" type="text" size="40" value="<%=query%>"></input>
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
                                <!-- KET QUA :THOI GIAN TIM KIEM+TONG KET QUA!-->
                                <div id="ssb">
                                    <p><%=sb.toString()%>	</p>
                                </div>
                                <!-- END KET QUA :THOI GIAN TIM KIEM+TONG KET QUA!-->

                                <div class="result">
                                    <img id="result" src="images/kq.jpg" align="left" height="28">
                                </div>

                            </div>
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td> </td>
                        <td align="center" width="780" height="75">
                            <form id="show_result" >

                                <!-- This is main content -->
                                <div id="accordion" >
                                    <%

            for (int i = pageNum * rowsPerPage; i < (pageNum * rowsPerPage + rowsPerPage) && i < h.getTotaldocs(); i++) {
                Document doc = h.getHits().doc(i);
                                    %>
                                    <h3>
                                        <a href="#">
                                            <img src="images/media.jpg" height="40" width="40">
                                            <label id ="rs_song"  style="font-size:10px" ><b style="color:#FF0000;font-size:15px" >
                                                    <%
                                                                        //SONG
                                                                        if (doc.getField("songvn") != null) {
                                                                            out.print(doc.getField("songvn").stringValue());
                                                                        } else if (doc.getField("songen") != null) {
                                                                            out.print(doc.getField("songen").stringValue());
                                                                        }
                                                    %>
                                            </b> </label>
                                        </a>
                                    </h3>
                                    <div>
                                        <table border="0"  cellpadding="2" cellspacing="0"  align="left" style="font-size:12px">
                                            <tr align="left">
                                                <td align="left" rowspan="4">
                                                    <%
                                                                        if (doc.getField("linkobject") != null) {
                                                                            System.out.println(doc.getField("linkobject").stringValue());
                                                                        }

                                                    %>
                                                </td>
                                            </tr>
                                            <tr >
                                                <td width="160px">Tác giả:<br>
                                                    <label id="rs_song" style="font-size:14px;color:#0000FF" >
                                                        <%
                                                                        if (doc.getField("singervn") != null) {
                                                                            System.out.println(doc.getField("singervn").stringValue());
                                                                        } else if (doc.getField("singeren") != null) {
                                                                            System.out.println(doc.getField("singeren").stringValue());
                                                                        }
                                                        %>
                                                    </label>
                                                </td>
                                                <td width="60px">Link download<br>
                                                    <label id="rs_src"style="font-size:14px;color:#0000FF" > nhaccuatui.com </label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td width="60px">Album<br>
                                                    <label id="rs_song" style="font-size:14px;color:#0000FF" >
                                                        <%
                                                                        if (doc.getField("albumvn") != null) {
                                                                            System.out.println(doc.getField("albumvn").stringValue());
                                                                        } else if (doc.getField("albumen") != null) {
                                                                            System.out.println(doc.getField("albumen").stringValue());
                                                                        }
                                                        %>
                                                    </label>
                                                </td>
                                                <td width="60px">Mã nhúng blog<br>
                                                    <input name="" type="text" value="">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td width="60px">Nguồn:<br>
                                                    <%
                                                                        if (doc.getField("idservice") != null) {
                                                                            System.out.println(doc.getField("idservice").stringValue().toLowerCase().replaceAll("_", " "));
                                                                        }
                                                    %>
                                                </td>

                                                <td width="60px">Mã nhúng <br>
                                                    <input name="" type="text" value="
                                                           <%
                                                                        if (doc.getField("object") != null) {
                                                                            System.out.println(doc.getField("linkobject").stringValue());
                                                                        }
                                                           %>">
                                                </td>
                                            </tr>
                                        </table>

                                    </div>
                                    <!--END FOR-->
                                    <%}%>
                                </div>

                                <!-- /This is main content -->

                            </form>
                        </td>
                        <td></td>
                    </tr>
                    <tr width="100%" >
                        <td class="paging"  colspan="3" >
                            <%=Str_paging%>
                        </td>
                    </tr>
                    <tr width="100%" >
                        <td class="foo" align="center" valign="bottom" colspan="3">

                        </td>
                    </tr>

                </table>
            </div>
            
            <%

        } catch (Exception e) {
            response.sendRedirect("error.jsp?error="+e.getMessage());
        } finally {
        }
            %>



        </form>

    </body>
</html>
