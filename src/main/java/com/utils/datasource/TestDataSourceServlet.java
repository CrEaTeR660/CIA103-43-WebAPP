package com.utils.datasource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet("/TestDataSource")
public class TestDataSourceServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		//序列化
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain; charset=UTF-8");
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
            try (Connection conn = ds.getConnection()) {
                resp.getWriter().println("連線成功：" + conn);
            }
        } catch (Exception e) {
            e.printStackTrace(resp.getWriter());
        }
    }
}
