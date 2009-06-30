<%-- 
    Document   : search_2
    Created on : Jun 24, 2009, 10:10:25 PM
    Author     : Thanh Nga
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="lucene.search.media.searcher.*" %>
<%@ page import="lucene.search.media.objects.*" %>
<%@ page import="lucene.search.media.parsehtml.HtmlHandler"%>
<%@ page import="org.apache.lucene.document.Document"%>
<%@ page import="java.io.File" %>
<%@ page import="paginator.paging" %>
<%@ page import="org.apache.poi.util.StringUtil" %>
<%@ page import="java.util.*" %>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- INCLUDE JAVASCRIPT AND CSS-->
    <script type="text/javascript"  src="javascripts/index.js"></script>
    <script type="text/javascript"  src="javascripts/public_functions.js"></script>
    <script type="text/javascript" src="javascripts/acc/jquery-1.3.2.js"></script>
    <script type="text/javascript" src="javascripts/acc/ui.core.js"></script>
    <script type="text/javascript" src="javascripts/acc/ui.accordion.js"></script>
    <script type="text/javascript" src="javascripts/clipboard.js"></script>
    <link type="text/css" rel="stylesheet"  href="css/acc/ui.all.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <!-- END INCLUDE JAVASCRIPT AND CSS -->
    <script type="text/javascript">
        $(document).ready(function(){
            $("#accordion").accordion('option','height',"auto");
            $("#accordion").accordion();
        });
    </script>
    <script type="text/javascript" src="javascripts/tooltip/BubbleTooltips.js"></script>
    <script type="text/javascript">
        window.onload=function(){enableTooltips()};
    </script>

    <!--GREY BOX-->
    <script type="text/javascript">
        var url="http://localhost:8080/WebSearchMedia/javascripts/";
        var GB_ROOT_DIR = url+"greybox/";
    </script>

    <script type="text/javascript" src="javascripts/greybox/AJS.js"></script>
    <script type="text/javascript" src="javascripts/greybox/AJS_fx.js"></script>
    <script type="text/javascript" src="javascripts/greybox/gb_scripts.js"></script>
    <link href="javascripts/greybox/gb_styles.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="javascripts/sclqcn6.js"></script>
    <title>Search Media</title>
    <body onLoad="onload_acc();" >
        <%
        System.gc();
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
            String host = request.getRequestURL().toString();
            host = host.substring(0, host.lastIndexOf("/"));

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
                String type = "";
                if (request.getParameter("type") != null) {
                    type = request.getParameter("type");//video or mp3
                    session.setAttribute("type", type);
                } else {
                    type = session.getAttribute("type").toString();
                }

                //get typesearch= normail/advance
                String type_search = "";
                if (request.getParameter("type_search") != null) {
                    type_search = request.getParameter("type_search");//video or mp3
                    session.setAttribute("type_search", type_search);
                } else {
                    type_search = session.getAttribute("type_search").toString();
                }

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
                if (request.getParameter("page") != null) {
                    pageNum = Integer.parseInt(request.getParameter("page")) - 1;
                }

                Searcher s = new Searcher();
                if (type_search.equals("normal"))//normal search
                {
                    //song,artist or lyric,album
                    String field = "";
                    if (request.getParameter("field") != null) {
                        field = request.getParameter("field");//video or mp3
                        session.setAttribute("field", field);
                    } else {
                        field = session.getAttribute("field").toString();
                    }

                    if (!field.equals("lyric")) {
                        if (unicode == 1) {
                            field += "vn";
                        } else {
                            field += "en";
                        }
                    }
                    h = s.search(f, field, query, pageNum, max_page);
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
                    h = s.searchAdvance(f, q, pageNum, max_page);

                }
                String begin = "";
                if (pageNum == 0) {
                    begin = String.valueOf(pageNum + 1);
                } else {
                    begin = String.valueOf(pageNum * max_page + 1);
                }

                String end = String.valueOf((pageNum * max_page + max_page) > h.getTotaldocs() ? (pageNum * max_page + h.getTotaldocs() % pageNum) : (pageNum * max_page + max_page));

                sb.append("Kết quả <b>" + begin + " - " + end + "</b>, Tổng <b>" + h.getTotaldocs() + "</b> tài liệu cho <b>" + query + "</b> - (" + (float) h.getTime() / 1000 + " giây)");

                total = h.getTotaldocs();
                //int offset = (pageNum - 1) * rowsPerPage;
                String self = "search.jsp";
                Str_paging = paging.make_paging(rowsPerPage, pageNum + 1, total, max_page, self);
            }
        %>
        <form  action="search.jsp" method="post" >
            <input type="hidden" id="type" name="type" value="video" style="display:none"/>
            <input type="hidden" id="type_search" name="type_search" value="advance" style="display:none"/>

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
                                            <td align="right" style="font-size:12px">
                                                <a href="index.jsp">Trang chủ</a> | <a href="contact.jsp">Liên hệ</a>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div class= "topdiv">
                                    <table cellpadding="0" cellspacing="5" border="0">
                                        <tr >
                                            <td width="70" align="center" >
                                            <a class="awhite" href="topmusic.jsp"><img class="topimg" src="images/mac/music.png"></a>                                            </td>
                                            <td width="70" align="center" >
                                            <a class="awhite" href="topfilm.jsp"><img class="topimg" src="images/mac/video.png"></a>                                            </td>
                                            <td width="70" align="center" >
                                            <a class="awhite" href="topsinger.jsp"><img class="topimg" src="images/singer.jpg"></a>                                            </td>
                                        </tr>
                                        <tr style="font-size:12px;font-weight:bold" >
                                            <td align="center" ><a href="topmusic.jsp">Top nhạc</a></td>
                                            <td align="center" ><a href="topfilm.jsp">Top phim</a></td>
                                            <td align="center" ><a href="topsinger.jsp">Top nghệ sỹ</a></td>
                                        </tr>
                                    </table>
                                </div>
                                <table cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td class="logo">
                                            <a href="index.jsp"><img src="images/logo.png"></a>
                                        </td>
                                    </tr>
                                </table>

                                <div class="frame_search">
                                    <table cellspacing="0" cellpadding="0" border="0">
                                        <tr>
                                            <td  valign="top">
                                                <table border="0"  cellpadding="2" cellspacing="0"  align="right" style="font-size:12px;color:#006699">
                                                    <tr>
                                                        <td align="left">
                                                        Nhạc / Phim                                        </td>
                                                        <td width="25%"><label>
                                                                <select name="select" style="width:145px"  >
                                                                    <option value="mp3">Nhạc</option>
                                                                    <option value="video">Phim</option>
                                                                </select>
                                                        </label></td>
                                                        <td width="25%" rowspan="5">
                                                            <table id="vista-buttons.com:idlqcn6" width=0 cellpadding=0 cellspacing=0 border=0><tr><td style="padding-right:0px" title ="Tìm  ">
                                                            <a href="search.jsp" onMouseOver='xpe("lqcn6o");' onMouseOut='xpe("lqcn6n");' onMouseDown='xpe("lqcn6c");'><img id="xpi_lqcn6" src="images/btlqcn6_0.gif" name=vblqcn6 width="82" height="30" border=0 alt="Tìm"></a></td></tr></table>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td align="left">
                                                        Tên ca sĩ/ diễn viên                                            </td>
                                                        <td width="25%">
                                                        <input type="text" align="right" id="singer" name="singer"  />                                            </td>
                                                    </tr>
                                                    <tr>
                                                        <td align="left">
                                                        Tên bài  hát / tên bộ phim                                            </td>
                                                        <td width="25%">
                                                        <input type="text" align="right" id="song" name="song"  />                                            </td>
                                                    </tr>
                                                    <tr>
                                                        <td align="left">
                                                        Lời bài hát/ phụ đề phim                                            </td>
                                                        <td width="25%">
                                                        <input type="text" align="right" id="lyric" name="lyric"  />                                            </td>
                                                    </tr>
                                                    <tr>
                                                        <td align="left">
                                                        Nguồn                                            </td>
                                                        <td width="25%">
                                                        <input type="text" align="right" id="site" name="site"  />                                            </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                </div>

                                <div id="ssb">
                                    <p><%=sb.toString()%> </p>
                                </div>
                                <div class="result">
                                    <img id="result" src="images/kq.jpg" align="left" height="28">
                                </div>

                            </div>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td width="780" height="100">
                            <!-- This is main content -->
                            <div id="accordion" >
                                <%
            ArrayList arr = new ArrayList();
            arr = h.getHits();

            for (int i = 0; i < arr.size(); i++) {
                Document doc = (Document) arr.get(i);
                String r_id_service = "";
                if (doc.getField("idservice") != null) {
                    r_id_service = doc.getField("idservice").stringValue();
                }
                String r_source = "";
                if (doc.getField("linksource") != null) {
                    r_source = doc.getField("linksource").stringValue();
                }
                String r_service = "";
                if (doc.getField("service") != null) {
                    r_service = doc.getField("service").stringValue();
                }
                String r_media = "";
                if (doc.getField("linkmedia") != null) {
                    r_media = doc.getField("linkmedia").stringValue();
                }

                //SONG
                String r_song = "";
                if (doc.getField("songvn") != null) {
                    r_song = doc.getField("songvn").stringValue();
                } else if (doc.getField("songen") != null) {
                    r_song = doc.getField("songen").stringValue();
                }
                //SINGER
                String r_singer = "";
                if (doc.getField("singervn") != null) {
                    r_singer = doc.getField("singervn").stringValue();
                } else if (doc.getField("singeren") != null) {
                    r_singer = doc.getField("singeren").stringValue();
                }
                //OBJECT
                String r_object = "";
                if (doc.getField("linkobject") != null) {
                    r_object = doc.getField("linkobject").stringValue();
                }
                //ALBUM
                String r_album = "";
                if (doc.getField("albumvn") != null) {
                    r_album = doc.getField("albumvn").stringValue();
                } else if (doc.getField("albumen") != null) {
                    r_album = doc.getField("albumen").stringValue();
                }
                String r_lyric = "Lyric:đang cập nhật";
                if (doc.getField("lyric").stringValue().replaceAll(" ", "") != "") {
                    r_lyric = doc.getField("lyric").stringValue();
                }
                                %>
                                <h3 style="max-height:50px">
                                    <a href="#"  onClick="play_acc(this);return false;" title='
                                       <%
                                    out.print(r_lyric.substring(0, r_lyric.length() > 200 ? 200 : r_lyric.length()) + " ...");
                                       %>
                                       '>
                                        <img  src="images/play.jpg" height="40" width="40" name="imgacc" border="0" >
                                        <label id ="rs_song"  style="font-size:10px" > <b   style="color:#FF0000;font-size:15px">   <%=r_song%> </b> </label>
                                        <label id="rs_src" style="font-size:14px;color:#0000FF" > - <%=r_singer%> </label>
                                    </a>
                                </h3>
                                <div style="max-height:200px" >
                                    <table width="700px" border="0" align="center" cellpadding="0" cellspacing="2">
                                        <tr>
                                            <td valign="top">
                                                <div class="result" >
                                                    <table border="0" align="center" cellpadding="0" cellspacing="0">
                                                        <tr>
                                                            <td>
                                                                <%=r_object%>
                                                            </td>
                                                        </tr>
                                                    </table>

                                                </div>
                                            </td>
                                            <td valign="top" align="center"  >
                                                <div class="result" >
                                                    <table width="350px" border="0" align="center" cellpadding="2" cellspacing="3">
                                                        <tr >
                                                            <td align="left" class="aa" width="100px">Nguồn từ
                                                            </td>
                                                            <td align="left" ><a href="<%="http://" + r_source%>" style="color:blue"><%=r_id_service.toLowerCase()%></a>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left" class="aa">Album</td>
                                                            <td style="color:blue"> <%=r_album%></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left" class="aa">Mã nhúng</td>
                                                            <td><input type="text" name="embed" id="embed" readonly="readonly" style="background: rgb(227, 227, 227);font-size: 12px; width:200px" value='   <%=r_object.replaceAll("'", "\"")%>' onClick="select(this.id);copyToClipboard(this.value);" />
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td align="left" class="aa">Download</td>
                                                            <td><a href="javascript:;" onClick="show_lightbox('<%=r_song%>','<%=host%>/download.jsp?media=<%=r_media%>&id_service=<%=r_id_service%>&service=<%=r_service%>','','300');"><img src="images/down.gif" title="Download" width="29" height="25" align="middle"></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td height="30px" colspan="3" style="max-width:250px">
                                                                <h5 style="max-height:10px" class="aa">Lyric  <img name="imgLyric" src="images/move-down.png" onClick="divLyric()"  title="Click để mở rộng vùng xem lời bài hát"></h5>
                                                                <div class="box-loibaihat" name="divLyric" style="overflow: hidden; height: 40px;"  title="Double Click để mở rộng vùng xem lời bài hát" onDblClick="divLyric()" >
                                                                    <%=r_lyric%>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <%}%>
                            </div>
                            <div class="fooLink" align="center" style="font-weight:bolder;color:red">
                                <a  >   <%=Str_paging%> </a>
                            </div>
                            <!-- /This is main content -->
                        </td>
                        <td >&nbsp;</td>
                    </tr>
                    <tr width="100%" >
                        <td align="center" valign="top" colspan="3" >
                            <div class="foo">
                                <div class="fooCopyright" style="margin: 10pt auto; width: 600px; height: 50px;">

                                    Chương trình Search Media phát triển bởi 0512230 - 0512286 <br/>
                                    dựa trên thư viện Lucene 					<br/>
                                    © Copyright 2009 <br/>

                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
            <script language="JavaScript">
                function doAction ( b){
                    if (b.value=="Order now"){
                        checkoutFrm.action="saveorder.jsp";
                        checkoutFrm.submit();
                    }
                    if (b.value=="Change"){
                        checkoutFrm.action="shoppingcart.jsp";
                        checkoutFrm.submit();
                    }
                    if (b.value=="Continue shopping"){
                        checkoutFrm.action="default.jsp";
                        checkoutFrm.submit();
                    }
                }
            </script>
            <%

        } catch (Exception e) {
            out.close();
            out.clear();
            response.sendRedirect("error.jsp?error=" + e.getMessage());
        } finally {
            //out.close();
        }
            %>

        </form>
    </body>

</html>
