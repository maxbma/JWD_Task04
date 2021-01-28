package by.tc.task04.factory.factories;

import by.tc.task04.server.service.*;
import by.tc.task04.server.service.impl.Task01;
import by.tc.task04.server.service.impl.Task02;
import by.tc.task04.server.service.impl.Task03;
import by.tc.task04.server.service.impl.Task04;
import by.tc.task04.server.service.impl.Task05;
import by.tc.task04.server.service.impl.Task06;
import by.tc.task04.server.service.impl.Task07;
import by.tc.task04.server.service.impl.Task08;
import by.tc.task04.server.service.impl.Task09;
import by.tc.task04.server.service.impl.Task10;
import by.tc.task04.server.service.impl.Task11;
import by.tc.task04.server.service.impl.Task12;
import by.tc.task04.server.service.impl.Task13;
import by.tc.task04.server.service.impl.Task14;
import by.tc.task04.server.service.impl.Task15;
import by.tc.task04.server.service.impl.Task16;

public class FileOperationFactory {
	public FileOperation createFileOperation(int taskNumber) {
		switch(taskNumber) {
		case 1:
			return new Task01();
		case 2:
			return new Task02();
		case 3:
			return new Task03();
		case 4: 
			return new Task04();
		case 5:
			return new Task05();
		case 6:
			return new Task06();
		case 7:
			return new Task07();
		case 8:
			return new Task08();
		case 9:
			return new Task09();
		case 10:
			return new Task10();
		case 11:
			return new Task11();
		case 12:
			return new Task12();
		case 13:
			return new Task13();
		case 14:
			return new Task14();
		case 15:
			return new Task15();
		case 16:
			return new Task16();	
		default:
			return null;
		}
	}
}
