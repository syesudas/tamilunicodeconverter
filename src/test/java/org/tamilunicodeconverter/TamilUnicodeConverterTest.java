package org.tamilunicodeconverter;

import java.io.File;
import static org.junit.Assert.*;

import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

public class TamilUnicodeConverterTest
{
	private TamilUnicodeConverter converter = new TamilUnicodeConverter();
	private File outputDir = new File("target/temp/output");
	private File sourceFile = new File("src/test/resources/ppt/A-001.PPT");
	private String sourceFileBaseName = FilenameUtils.getBaseName(sourceFile
			.getName());

	@Test
	public void testGetRawOutputFile()
	{
		File rawOutputFile = converter.getRawOutputFile(outputDir,
				sourceFileBaseName);
		System.out.println("Raw output file: " + rawOutputFile);
		assertEquals(new File(outputDir, "raw" + File.separator
				+ sourceFileBaseName + ".txt"), rawOutputFile);
	}

	@Test
	public void testGetUnicodeOutputFile()
	{
		File unicodeOutputFile = converter.getUnicodeOutputFile(outputDir,
				sourceFileBaseName);
		System.out.println("Unicode output file: " + unicodeOutputFile);
		assertEquals(new File(outputDir, "unicode" + File.separator
				+ sourceFileBaseName + ".txt"), unicodeOutputFile);
	}

	@Test
	public void testGetFileNameFromContent_1()
	{
		String content = "இயேசு ராஜா முன்னே செல்கிறார்";
		String expected = "இயேசு ராஜா முன்னே செல்கிறார்.txt";
		String result = converter.getFileNameFromContent(content);
		assertEquals(expected, result);
		System.out.println("File name from content: " + result);
	}

	@Test
	public void testGetFileNameFromContent_2()
	{
		String content = "இயேசு ராஜா முன்னே";
		String expected = "இயேசு ராஜா முன்னே.txt";
		String result = converter.getFileNameFromContent(content);
		assertEquals(expected, result);
		System.out.println("File name from content: " + result);
	}

	@Test
	public void testConvertFile()
	{
		converter.convertFile(sourceFile, outputDir);
		assert converter.getRawOutputFile(outputDir, sourceFileBaseName)
				.exists();
		assert converter.getUnicodeOutputFile(outputDir, sourceFileBaseName)
				.exists();
	}

	@Test
	public void testConvertFile_createOutputFileNameFromContent()
	{		
		converter.setCreateOutputFileNameFromContent(true);
		converter.resetConvertedFilesCount();
		converter.convertFile(sourceFile, outputDir);
		assert converter.getConvertedFilesCount() > 0;
	}
	
	@Test
	public void testConvert()
	{
		converter.convert(sourceFile.getParentFile(), outputDir);
		assert converter.getConvertedFilesCount() > 0;
	}
}
