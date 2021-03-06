package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ConnectionListener implements ServletContextListener {

	// Server 啟動時要執行的程式
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Tomcat 啟動中 ...");
		// 資歷庫放置位置
		String db_path = "C:\\Users\\student\\eclipse-workspace-web\\JavaWeb-20220418\\db";
		// 資歷庫名稱
		String db_name = "webdb.db";
		// 建立資料庫連線參數
		String db_url = "jdbc:sqlite:" + db_path + "\\" + db_name;
		// 資料庫連線物件
		Connection conn = null;
		try {
			// 建立資料庫 driver
			Class.forName("org.sqlite.JDBC"); // new 的意思
			conn = DriverManager.getConnection(db_url);
			// 將 conn 物件放到 context(Application) scope 變數中
			sce.getServletContext().setAttribute("conn", conn);
			System.out.println("已將 conn 物件放到 context(Application) scope 變數中");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Server 關閉時要執行的程式
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Tomcat 關閉中 ...");
		Object connObj = sce.getServletContext().getAttribute("conn");
		if (connObj != null) {
			try {
				if (connObj instanceof Connection) {
					((Connection) connObj).close();
					System.out.println("conn 物件 已關閉");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
