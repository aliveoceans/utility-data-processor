package com.aliveoceans.dataprocessor;

import java.util.List;

import com.aliveoceans.dataprocessor.converter.FileConverter;
import com.aliveoceans.dataprocessor.converter.SupportedTypes;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.DefaultParser;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class MainApp {
	private static final List<String> SUPPORTED_TYPES = List.of("csv", "json", "xlsx", "yml", "xml");

	public static void main(String[] args) throws Exception {
		Terminal terminal = TerminalBuilder.builder().system(true).build();
		DefaultParser parser = new DefaultParser();
		parser.setEscapeChars(null); // Fixes backslash stripping!

		LineReader reader = LineReaderBuilder.builder()
				.terminal(terminal)
				.parser(parser)
				.build();
		//LineReader reader = LineReaderBuilder.builder().terminal(terminal).parser(new DefaultParser()).build();

		terminal.writer().println("\u001B[36m╔══════════════════════════════════════════╗");
		terminal.writer().println("║   Welcome to Utility Data Processor CLI  ║");
		terminal.writer().println("╚══════════════════════════════════════════╝\u001B[0m");
		terminal.flush();

		String inputPath = reader.readLine("\u001B[33m📂 Enter path to input file:\u001B[0m ");

		String inputType = promptFileType(terminal, reader, "📄 Select input file type:");
		String outputType = promptFileType(terminal, reader, "📝 Select output file type:");

		try {
			FileValidator.validate(inputPath, inputType);
			FileConverter.convert(inputPath, SupportedTypes.get(inputType), SupportedTypes.get(outputType));
			terminal.writer().println("\u001B[32m✅ Conversion completed successfully!\u001B[0m");
		} catch (Exception e) {
			terminal.writer().println("\u001B[31m❌ Error: " + e.getMessage() + "\u001B[0m");
		}

		terminal.flush();
	}

	private static String promptFileType(Terminal terminal, LineReader reader, String title) {
		terminal.writer().println("\n\u001B[34m" + title + "\u001B[0m");

		for (int i = 0; i < SUPPORTED_TYPES.size(); i++) {
			String type = SUPPORTED_TYPES.get(i);
			terminal.writer().println(String.format("\u001B[36m  %d) %s\u001B[0m", i + 1, type.toUpperCase()));
		}
		terminal.flush();

		while (true) {
			String input = reader.readLine("\u001B[33m➡ Choose one of the above options from 1 to 5 (default 1): \u001B[0m");
			if (input.trim().isEmpty()) {
				return SUPPORTED_TYPES.get(0); // default to first
			}

			try {
				int choice = Integer.parseInt(input.trim());
				if (choice >= 1 && choice <= SUPPORTED_TYPES.size()) {
					return SUPPORTED_TYPES.get(choice - 1);
				}
			} catch (NumberFormatException ignored) {}

			terminal.writer().println("\u001B[31m⚠️  Invalid input. Please enter a number between 1 and 5.\u001B[0m");
			terminal.flush();
		}
	}
}