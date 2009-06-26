<%-- 
    Document   : download
    Created on : Jun 22, 2009, 10:59:18 PM
    Author     : Thanh Nga
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="service.media_link"  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
       <%
          boolean error=false;
          request.setCharacterEncoding("utf-8");
          String link="";
          if(request.getParameter("r_media")!=null)
              link=request.getParameter("r_media");
          String Id="";
          if(request.getParameter("r_id_service")!=null)
              Id=request.getParameter("r_id_service");
          String xml="";
          if(request.getParameter("r_service")!=null)
              xml=request.getParameter("r_service");
          
          String result="Xin lỗi,tạm thời chúng tôi chưa hỗ trợ download từ site này!";
          result+="<br>Gợi ý: Bạn có thể dùng IDM hoặc FlashGet có hỗ trợ download trong khi nghe nhạc";
          result+="<br>Hoặc có thể vào trang gốc đẻ download.";
          result+="<br>Xin cảm ơn!";
          if(link.equals(""))
          {              
              if(!xml.equals(""))
                  {
                    try {

                            media_link media = new media_link();
                            request.setCharacterEncoding("utf-8");                        
                            media.setFile_xml(xml);
                            if (Id.equals("NHACCUATUI.COM")) {
                                media.getLinkNhacCuaTui(1, "LOCATION");
                            } else if (Id.equals("CHACHA.VN")) {
                                media.getLinkNhacCuaTui(0, "LOCATION");
                            } else if (Id.equals("AMNHACTIMNHANH.VN") || Id.equals("MUZIC9.COM")) {
                                media.getLinkNhacCuaTui(0, "ENTRY");
                            }

                            link=media.getLink();
                        } catch (Exception e) {
                           error=true;
                        } finally {
                            out.close();
                        }
                  }
              

          }
          if(error)
          {
               result="Có lỗi xảy ra tại server,Xin vui lòng thử lại sau!";
              
          }
          else if(!link.equals(""))
            {
              result="Bạn hãy copy link bên dưới và paste vào trình duyệt đê download!";
              result+="<br>Hoặc dùng chương trình hỗ trờ download!<br>";
              result+=link;
            }
          out.print(result);
       %>
       
    </body>
</html>
