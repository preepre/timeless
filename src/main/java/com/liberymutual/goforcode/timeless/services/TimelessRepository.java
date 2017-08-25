package com.liberymutual.goforcode.timeless.services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.liberymutual.goforcode.timeless.models.Timesheet;

@Service
public class TimelessRepository {

	private int listSize;

	public void create(Timesheet item) {

		if (listSize != 0) {
			item.setId(listSize + 1);
		} else if (listSize == 0) {
			item.setId(1);
		}

		String file = "timeless.csv";

		CSVFormat format = CSVFormat.DEFAULT.withHeader();

		try (FileWriter writer = new FileWriter(file, true); CSVPrinter printer = CSVFormat.DEFAULT.print(writer)) {

			String[] fullTimesheet = { Integer.toString(item.getId()), item.getWeekOf(),
					Double.toString(item.getMondayHours()), Double.toString(item.getTuesdayHours()),
					Double.toString(item.getWednesdayHours()), Double.toString(item.getThursdayHours()),
					Double.toString(item.getFridayHours()), Boolean.toString(item.getIsComplete()) };

			printer.printRecord(fullTimesheet);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Timesheet> getAll() {

		List<Timesheet> items = new ArrayList<Timesheet>();

		try (Reader in = new FileReader("timeless.csv")) {
			Iterable<CSVRecord> records = null;
			records = CSVFormat.DEFAULT.parse(in);

			for (CSVRecord record : records) {

				Timesheet newItem = new Timesheet();
				newItem.setId(Integer.parseInt(record.get(0)));
				newItem.setWeekOf(record.get(1));
				newItem.setMondayHours(Double.parseDouble(record.get(2)));
				newItem.setTuesdayHours(Double.parseDouble(record.get(3)));
				newItem.setWednesdayHours(Double.parseDouble(record.get(4)));
				newItem.setThursdayHours(Double.parseDouble(record.get(5)));
				newItem.setFridayHours(Double.parseDouble(record.get(6)));
				newItem.setComplete(Boolean.parseBoolean(record.get(7)));

				items.add(newItem);
			}

			listSize = items.size();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		return items;

		// return Collections.emptyList();
	}

	public Timesheet getById(int id) {

		List<Timesheet> existingList = getAll();

		for (Timesheet findItem : existingList) {
			if (findItem.getId() == id) {
				return findItem;
			}
		}

		return null;
	}
	
    public Timesheet getCurrentTimeSheet() {
        List<Timesheet> list = getAll();
        for (Timesheet item : list) {
            if (!item.getIsComplete()) {
                return item;
            }
        }
        return null;
    }

//	public void update(Timesheet item) {
//
//		List<Timesheet> existingList = getAll();
//		int idIncrementer = 1;
//		String file = "timeless.csv";
//
//		try (FileWriter writer = new FileWriter(file, false); CSVPrinter printer = CSVFormat.DEFAULT.print(writer)) {
//
//			for (Timesheet findItem : existingList) {
//
//				if (findItem.getId() == item.getId()) {
//
//					findItem.setComplete(true);
//
//				}
//
//				findItem.setId(idIncrementer);
//				idIncrementer += 1;
//
//				String[] fullTimesheet = { Integer.toString(item.getId()), item.getWeekOf(),
//						Double.toString(item.getMondayHours()), Double.toString(item.getTuesdayHours()),
//						Double.toString(item.getWednesdayHours()), Double.toString(item.getThursdayHours()),
//						Double.toString(item.getFridayHours()), Boolean.toString(item.getIsComplete()) };
//
//				printer.printRecord(fullTimesheet);
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}

}
