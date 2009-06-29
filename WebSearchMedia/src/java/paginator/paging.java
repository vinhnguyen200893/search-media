package paginator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */
public class paging {

    public static String make_paging(int rows_Per_Page, int cur_Page, long total_Rows, int max_page, String self) {

        String nav = "";
        if (rows_Per_Page >= total_Rows * rows_Per_Page) {
            return "";
        }

        int total =(int) total_Rows / rows_Per_Page;
        if (total_Rows - total * rows_Per_Page > 0) {
            total += 1;
        }

        int start = ((cur_Page - max_page) > 0) ? cur_Page - (max_page - 1) : 1;
        int end = ((cur_Page + max_page) < total) ? (cur_Page + max_page) : total;


        for (int p = start; p <= end; p++) {
            if (p == cur_Page) {
                nav += "&nbsp;"+p; // khong can tao link cho trang hien hanh
            } else {
                nav += " <a href='" + self + "?page=" + p + "'>" + p + "</a>";
            }
        }
        // tao lien ket den trang truoc & trang sau, trang dau, trang cuoi
        String prev = "&nbsp;";
        String first = "&nbsp;";
        if (cur_Page > 1) {
            int pag = cur_Page - 1;
            if (pag > 1) {
                prev = "<a class='white' href=\"" + self + "?page=" + pag + "\"> <img src='images/back.png' alt='trang trước'/> </a>";
            }
            first = "<a class='white' href=\"" + self + "?page=" + 1 + "\"><img src='images/first.png' alt='trang đầu'/></a>";
        }
        String next = "&nbsp;"; // dang o trang cuoi, khong can in lien ket trang ke
        String last = "&nbsp;"; // va lien ket trang cuoi
        if (cur_Page < total) {
            int pa = cur_Page + 1;
            if (pa < total) {
                next = "<a class='white' href=\"" + self + "?page=" + pa + "\"><img src='images/forward.png' alr='trang tiếp'/></a>";
            }

            last = "<a class='white' href=\"" + self + "?page=" + total + "\"><img src='images/last.png' alt='trang cuối'/></a>";
        }
        // hien thi cac link lien ket trang
        return first + prev + nav + next + last;

    }
}
