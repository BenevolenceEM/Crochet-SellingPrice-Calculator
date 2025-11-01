package CSPCPackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CalcServlet")
public class CalcServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        double yarnCost = Double.parseDouble(request.getParameter("ycost"));
        String acostParam = request.getParameter("acost");
        double additionalCost = (acostParam == null || acostParam.isEmpty()) ? 0.0 : Double.parseDouble(acostParam);

        //double additionalCost = Double.parseDouble(request.getParameter("acost"));
        double hoursSpent = Double.parseDouble(request.getParameter("hours"));
        double minWage = Double.parseDouble(request.getParameter("mwage"));
        
        double laborCost = hoursSpent * minWage;
        double totalCost = yarnCost + additionalCost + laborCost;
        
        double markupPercentage = Double.parseDouble(request.getParameter("profit"));
        double markupRate = markupPercentage / 100.0;
        double markupPrice = totalCost * (1 + markupRate);
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><head><title>Price Breakdown</title>");
        out.println("<link rel='stylesheet' href='main.css'>");
        out.println("</head><body>");
        out.println("<main class='container'><section class='textbox'>");
        out.println("<h3>Price Breakdown</h3>");
        out.println("<p>&#129526; Yarn Cost: $" + String.format("%.2f", yarnCost) + "</p>");
        out.println("<p>&#128230; Additional Cost: $" + String.format("%.2f", additionalCost) + "</p>");
        out.println("<p>&#9202;&#65039; Labor Cost (" + hoursSpent + " hrs @ $" + minWage + "/hr): $" + String.format("%.2f", laborCost) + "</p>");
        out.println("<p>&#128176; Total Cost: $" + String.format("%.2f", totalCost) + "</p>");
        out.println("<p>&#128184; Suggested Selling Price (" + (markupRate * 100) + " %markup): $" + String.format("%.2f", markupPrice) + "</p>");
        out.println("<br><a href='index.html'>&#128281; Back to Calculator</a>");
        out.println("</section></main></body></html>");
    }
}