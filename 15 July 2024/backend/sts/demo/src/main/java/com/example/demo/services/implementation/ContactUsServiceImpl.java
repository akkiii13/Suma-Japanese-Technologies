package com.example.demo.services.implementation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.ContactUs;
import com.example.demo.repositories.ContactUsRepo;
import com.example.demo.services.ContactUsService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ContactUsServiceImpl implements ContactUsService {

	@Autowired
	ContactUsRepo contactUsRepository;

	@Override
	public List<ContactUs> getContactUsInquiriesByDate(LocalDate date) {
		if (date == null)
			throw new IllegalArgumentException("Date must be provided.");
		List<ContactUs> contactUsList = this.contactUsRepository.findByDateSubmitted(date);
		List<ContactUs> collect = contactUsList.stream().sorted(Comparator.comparing(ContactUs::getDateSubmitted)
				.reversed().thenComparing(ContactUs::getTimeSubmitted).reversed()).collect(Collectors.toList());
		createNewSheet(collect, date, null);
		return collect;
	}

	@Override
	public List<ContactUs> getContactUsInquiriesByRangeOfDate(LocalDate startDate, LocalDate endDate) {
		if (startDate == null || endDate == null)
			throw new IllegalArgumentException("Start date and end date must be provided.");
		List<ContactUs> contactUsList = this.contactUsRepository.findByDateSubmittedBetween(startDate, endDate);
		List<ContactUs> collect = contactUsList.stream().sorted(Comparator.comparing(ContactUs::getDateSubmitted)
				.reversed().thenComparing(ContactUs::getTimeSubmitted).reversed()).collect(Collectors.toList());
		createNewSheet(collect, startDate, endDate);
		return collect;
	}

	@Override
	public List<ContactUs> getInquiryListBeforeDate(LocalDate date) {
		if (date == null)
			throw new IllegalArgumentException("Date must be provided.");
		List<ContactUs> contactUsList = this.contactUsRepository.findByDateSubmittedBefore(date);
		List<ContactUs> collect = contactUsList.stream()
				.sorted(Comparator.comparing(ContactUs::getDateSubmitted).thenComparing(ContactUs::getTimeSubmitted))
				.collect(Collectors.toList());
		createNewSheet(collect, date, null);
		return collect;
	}

	@Override
	public List<ContactUs> getInquiryListAfterDate(LocalDate date) {
		if (date == null)
			throw new IllegalArgumentException("Date must be provided.");
		List<ContactUs> contactUsList = this.contactUsRepository.findByDateSubmittedAfter(date);
		List<ContactUs> collect = contactUsList.stream().sorted(Comparator.comparing(ContactUs::getDateSubmitted)
				.reversed().thenComparing(ContactUs::getTimeSubmitted).reversed()).collect(Collectors.toList());
		createNewSheet(collect, date, null);
		return collect;
	}

	private void createNewSheet(List<ContactUs> list, LocalDate startDate, LocalDate endDate) {

		String filePath = null;

		if (endDate != null)
			filePath = "D:\\Data\\Akshay N\\ExtractedData\\Contact_Us____" + startDate.getYear() + "-"
					+ startDate.getMonth() + "-" + startDate.getDayOfMonth() + "____" + endDate.getYear() + "-"
					+ endDate.getMonth() + "-" + endDate.getDayOfMonth() + "____" + LocalDateTime.now().getYear() + "-"
					+ LocalDateTime.now().getMonth() + "-" + LocalDateTime.now().getDayOfMonth() + "____"
					+ LocalDateTime.now().getHour() + "-" + LocalDateTime.now().getMinute() + "-"
					+ LocalDateTime.now().getSecond() + ".xlsx";
		else
			filePath = "D:\\Data\\Akshay N\\ExtractedData\\Contact_Us____" + startDate.getYear() + "-"
					+ startDate.getMonth() + "-" + startDate.getDayOfMonth() + "____" + LocalDateTime.now().getYear()
					+ "-" + LocalDateTime.now().getMonth() + "-" + LocalDateTime.now().getDayOfMonth() + "____"
					+ LocalDateTime.now().getHour() + "-" + LocalDateTime.now().getMinute() + "-"
					+ LocalDateTime.now().getSecond() + ".xlsx";

		// Create a new workbook and sheet
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();

		int rowIndex = 0;

		// Create header row
		Row headerRow = sheet.createRow(rowIndex++);

		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 16);
		font.setBold(true);
		headerStyle.setFont(font);

		headerRow.createCell(0).setCellValue("Date submitted");
		headerRow.createCell(1).setCellValue("Time submitted");
		headerRow.createCell(2).setCellValue("Id");
		headerRow.createCell(3).setCellValue("Full name");
		headerRow.createCell(4).setCellValue("Mobile number prefix");
		headerRow.createCell(5).setCellValue("Mobile number");
		headerRow.createCell(6).setCellValue("Email");
		headerRow.createCell(7).setCellValue("Subject");
		headerRow.createCell(8).setCellValue("Message");

		for (ContactUs obj : list) {
			Row row = sheet.createRow(rowIndex++);
			if (obj.getDateSubmitted() instanceof LocalDate) {
				LocalDate date = obj.getDateSubmitted(); // Assuming you have a LocalDateTime object
				DateTimeFormatter databaseFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Match your database
																								// format
				String formattedDateTime = date.format(databaseFormat);
				row.createCell(0).setCellValue(formattedDateTime);
			}
			if (obj.getTimeSubmitted() instanceof LocalTime) {
				LocalTime time = obj.getTimeSubmitted();
				DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
				String formattedTime = time.format(timeFormat);
				row.createCell(1).setCellValue(formattedTime);
			}
			row.createCell(2).setCellValue(obj.getId());
			row.createCell(3).setCellValue(obj.getFullName());
			row.createCell(4).setCellValue(obj.getMobileNumberPrefix());
			row.createCell(5).setCellValue(obj.getMobileNumber());
			row.createCell(6).setCellValue(obj.getEmail());
			row.createCell(7).setCellValue(obj.getSubject());
			row.createCell(8).setCellValue(obj.getMessage());
		}

		try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
			workbook.write(fileOut);
			System.out.println("Excel file created successfully!");
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void writeHeaderLine(ResultSet result, XSSFSheet sheet) throws SQLException {
		// write header line containing column names
		ResultSetMetaData metaData = result.getMetaData();
		int numberOfColumns = metaData.getColumnCount();

		XSSFRow headerRow = sheet.createRow(0);

		// exclude the first column which is the ID field
		for (int i = 2; i <= numberOfColumns; i++) {
			String columnName = metaData.getColumnName(i);
			XSSFCell headerCell = headerRow.createCell(i - 2);
			headerCell.setCellValue(columnName);
		}
	}

	private void writeDataLines(ResultSet result, XSSFWorkbook workbook, XSSFSheet sheet) throws SQLException {
		ResultSetMetaData metaData = result.getMetaData();
		int numberOfColumns = metaData.getColumnCount();

		int rowCount = 1;

		while (result.next()) {
			XSSFRow row = sheet.createRow(rowCount++);

			for (int i = 2; i <= numberOfColumns; i++) {
				Object valueObject = result.getObject(i);

				Cell cell = row.createCell(i - 2);

				if (valueObject instanceof Boolean) {
					cell.setCellValue((Boolean) valueObject);
				} else if (valueObject instanceof Double) {
					cell.setCellValue((double) valueObject);
				} else if (valueObject instanceof Float) {
					cell.setCellValue((float) valueObject);
				} else if (valueObject instanceof Integer) {
					cell.setCellValue((int) valueObject);
				} else if (valueObject instanceof Long) {
					cell.setCellValue((long) valueObject);
				} else if (valueObject instanceof Date) {
					cell.setCellValue((Date) valueObject);
					formatDateCell(workbook, cell);
				} else {
					cell.setCellValue(valueObject.toString());
				}

			}

		}
	}

	private void formatDateCell(XSSFWorkbook workbook, Cell cell) {
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFCreationHelper creationHelper = workbook.getCreationHelper();
		cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH-mm-ss"));
		cell.setCellStyle(cellStyle);
	}

}
