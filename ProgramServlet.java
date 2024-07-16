package mainpackage.users.web;

import mainpackage.utils.dao.ProgramDao;
import mainpackage.utils.model.Program;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/ProgramServlet")
public class ProgramServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProgramDao programDao;
    private static final Logger LOGGER = Logger.getLogger(ProgramServlet.class.getName());

    public void init() {
        programDao = new ProgramDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        int minutes = Integer.parseInt(request.getParameter("minutes"));
        double baseCharge = Double.parseDouble(request.getParameter("baseCharge"));
        double additionalCharge = Double.parseDouble(request.getParameter("additionalCharge"));

        Program newProgram = new Program(0, name, minutes, baseCharge, additionalCharge);
        programDao.insertProgram(newProgram);
        LOGGER.info("Program inserted successfully: " + newProgram.getName());

        response.sendRedirect("CreatePrograms.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("edit")) {
            int programId = Integer.parseInt(request.getParameter("programId"));
            Program program = programDao.getProgramById(programId);

            if (program != null) {
                request.setAttribute("program", program); // Set program as request attribute
                request.getRequestDispatcher("/EditPrograms.jsp").forward(request, response);
            } else {
                // Handle case where program with given ID was not found
                LOGGER.warning("Program not found with ID: " + programId);
                response.sendRedirect("CreatePrograms.jsp");
            }
        } else {
            // Handle other actions or redirect appropriately
        }
    }

}
