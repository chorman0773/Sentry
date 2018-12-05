package github.chorman0773.sentry.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import github.chorman0773.sentry.text.I18N;

public class Resource {
	private static final JsonParser parser = new JsonParser();
	protected final ResourcePack owner;
	private ResourceType type;

	public Resource(ResourcePack owner,ResourceType type) {
		this.owner = owner;
		this.type = type;
	}

	public Map<String,Resource> getSubResources() {
		return null;
	}
	public ResourceType getType() {
		return type;
	}
	private JsonObject getJsonObject() {
		InputStream in = owner.getResourceStream(this);
		Reader r = new InputStreamReader(in,StandardCharsets.UTF_8);
		return parser.parse(r).getAsJsonObject();
	}

	public JsonObject getAsJson() {
		if(type!=ResourceType.JSON)
			throw new IllegalStateException("This is not a Json Resource");
		return getJsonObject();
	}

	public BufferedImage getAsImage()throws IOException {
		if(type!=ResourceType.GRAPHIC)
			throw new IllegalStateException("This is not an image Resource");
		return ImageIO.read(owner.getResourceStream(this));
	}

	public Clip getAsAudio() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		if(type!=ResourceType.AUDIO)
			throw new IllegalStateException("This is not an audio resource");
		AudioInputStream ain = AudioSystem.getAudioInputStream(owner.getResourceStream(this));
		Clip c = AudioSystem.getClip();
		c.open(ain);
		return c;
	}

	public Node getAsXML() throws TransformerConfigurationException, TransformerException, TransformerFactoryConfigurationError {
		StreamSource src = new StreamSource(owner.getResourceStream(this));
		DOMResult res = new DOMResult();
		TransformerFactory.newInstance().newTransformer().transform(src, res);
		return res.getNode();
	}

	public I18N getAsLanguage()
	{
		if(type!=ResourceType.LANGUAGE)
			throw new IllegalStateException("This is not a language resource");
		return new I18N(getJsonObject());
	}


}
