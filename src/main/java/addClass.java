import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class addClass extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res){
        int i = Integer.parseInt(req.getParameter("num1"));
        int  j= Integer.parseInt(req.getParameter("num2"));

        int k= i+j;
        System.out.println("Result is "+ k);
    }
}
