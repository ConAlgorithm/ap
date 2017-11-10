package catfish.plugins.pdfgenerator;

import java.io.InputStream;
import java.io.OutputStream;

public interface IMimeGenerator {
	OutputStream generate(InputStream ins);
}
