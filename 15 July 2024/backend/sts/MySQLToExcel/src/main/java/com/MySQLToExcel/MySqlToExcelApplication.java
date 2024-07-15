package com.MySQLToExcel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication 
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.MySQLToExcel.crud"})
@EnableJpaRepositories(basePackages="com.MySQLToExcel.repositories")
@EnableTransactionManagement
@EntityScan(basePackages="com.MySQLToExcel.entities")
public class MySqlToExcelApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySqlToExcelApplication.class, args);
//		MySqlToExcelApplication exporter = new MySqlToExcelApplication();
//		exporter.export("contactus");
	}

//	public void export(String table) {
//		String jdbcURL = "jdbc:mysql://localhost:3306/nodeJsBackend";
//		String username = "root";
//		String password = "suma_japanese_technologies_0005";
//
//		String excelFilePath = getFileName(table.concat("_Export"));
//		System.out.println(excelFilePath);
//
//		try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
//			String sql = "SELECT * FROM ".concat(table);
//
//			Statement statement = connection.createStatement();
//
//			ResultSet result = statement.executeQuery(sql);
//
//			XSSFWorkbook workbook = new XSSFWorkbook();
//			XSSFSheet sheet = workbook.createSheet(table);
//
//			writeHeaderLine(result, sheet);
//
//			writeDataLines(result, workbook, sheet);
//
//			FileOutputStream outputStream = new FileOutputStream(excelFilePath);
//			workbook.write(outputStream);
//			workbook.close();
//
//			statement.close();
//
//		} catch (SQLException e) {
//			System.out.println("Datababse error:");
//			e.printStackTrace();
//		} catch (IOException e) {
//			System.out.println("File IO error:");
//			e.printStackTrace();
//		}
//	}

}
