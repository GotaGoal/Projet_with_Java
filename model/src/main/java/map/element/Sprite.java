package map.element;

import java.io.StringWriter;

public class Sprite extends StringWriter {
	public Sprite(final String c) {
		this.write(c);
	}
}
