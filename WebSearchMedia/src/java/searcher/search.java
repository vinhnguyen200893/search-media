/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package searcher;

import lucene.search.media.searcher.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.lucene.document.Document;

/**
 *
 * @author Thanh Nga
 */
public class search extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
//        try {
//            Hit h = new Hit();
//            int page=Integer.parseInt(request.getParameter("page"));
//            int row_per_page=Integer.parseInt(request.getParameter("row_per_page"));
//            if(getSession(request)==null)
//            {
//                //get all parameter to make search
//                request.setCharacterEncoding("utf-8");
//                String type = request.getParameter("type");//video or mp3
//                String field = request.getParameter("field");//song,artist or lyric,album
//                String query = request.getParameter("query");
//
//                //get Directory to search
//                String path = System.getProperty("user.dir") + "/data";
//                if (type.equals("video")) {
//                    path += "/video";
//                } else if (type.equals("mp3")) {
//                    path += "/mp3";
//                }
//                File f = new File(path);
//
//                //make search
//
//                Searcher s = new Searcher();
//                h = s.search(f, field, query);
//
//                //set session and save to Database
//                setSession(request, h);
//                //combine all result and print out
//                //result as time]total]song}singer}link}media}...]
//            }
//            else
//                h=(Hit)getSession(request);
//            StringBuffer sb=new StringBuffer();
//            sb.append(h.getTime()+"]");
//            sb.append(h.getTotaldocs()+"]");
//            for(int i=page*row_per_page;i<h.getTotaldocs();i++)
//            {
//                Document doc=h.getHits().doc(i);
//                sb.append(doc.getField("songvn").stringValue()+"}");
//                sb.append(doc.getField("songen").stringValue()+"}");
//                sb.append(doc.getField("singervn").stringValue()+"}");
//                sb.append(doc.getField("singeren").stringValue()+"}");
//                sb.append(doc.getField("linkobject").stringValue()+"}");
//                sb.append(doc.getField("linksource").stringValue()+"}");
//                sb.append(doc.getField("date").stringValue()+"}");
//                sb.append(doc.getField("linkmedia").stringValue()+"}");
//                sb.append(doc.getField("service").stringValue()+"}");
//                sb.append(doc.getField("albumen").stringValue()+"}");
//                sb.append(doc.getField("albumvn").stringValue()+"}");
//                sb.append(doc.getField("lyric").stringValue()+"}");
//                sb.append("]");
//            }
//            out.print(sb.toString());
//                //return result
//
//            }
//
//            catch  (Exception e) {
//                out.print("0");
//
//
//        } finally {
//            out.close();
//        }
    }
//    private void setSession( HttpServletRequest request,Hit h)
//    {
//         HttpSession session=request.getSession();
//         if(session.getAttribute("hits")==null)
//         {
//             session.setAttribute("hits",h);
//         }
//
//    }
//    private Hit getSession( HttpServletRequest request)
//    {
//         HttpSession session=request.getSession();
//         if(session.getAttribute("hits")==null)
//             return (Hit)session.getAttribute("hits");
//         return null;
//
//    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
