package github.chorman0773.sentry.save;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Represents the Raw Structure of The Legacy Sentry Save Format.
 * @author connor
 * @deprecated The Sentry Save format is deprecated. Prefer ShadeNBT and CryptoShade to This format)
 */
@Deprecated(forRemoval=true)
public class RawSaveData {
	
	@Deprecated(forRemoval=true)
	public static class ListNode {
		String key;
		List<ListNode> listnodes;
		List<IntNode> intnodes;
		List<StringNode> stringnodes;
		public ListNode(String key) {
			this.key = key;
			listnodes = new LinkedList<>();
			intnodes = new LinkedList<>();
			stringnodes = new LinkedList<>();
		}
		public void appendIntNode(String key, int value){
			intnodes.add(new IntNode(key,value));
		}
		public void appendStringNode(String key, String value){
			stringnodes.add(new StringNode(key,value));
		}
		public void appendListNode(ListNode element){
			listnodes.add(element);
		}
		public String getString(String key) {
			for(StringNode s:stringnodes)
				if(s.key.equals(key))
					return s.value;
			return "";
		}
		public int getInt(String key){
			for(IntNode i:intnodes)
				if(i.key.equals(key))
					return i.value;
			return 0;
		}
		public ListNode getList(String key){
			for(ListNode l:listnodes)
				if(l.key.equals(key))
					return l;
			return new ListNode(key);
		}
		public int countPairs(){
			return stringnodes.size()+intnodes.size()+listnodes.size();
		}

	}
	@Deprecated(forRemoval=true)
	public static class StringNode {
		String key;
		String value;
		StringNode(String key, String value) {
			this.key =key;
			this.value = value;
		}

	}
	@Deprecated(forRemoval=true)
	public static class IntNode {
		String key;
		int value;
		IntNode(String key,int value) {
			this.key = key;
			this.value = value;
		}

	}
	ListNode ln = new ListNode("");
	 String name;
	 String ver;
	 String id;
	public RawSaveData(String gameName,String gameVer,UUID gameId) {
	this.name = gameName;
	this.ver = gameVer;
	this.id = gameId.toString();
	}
	public void save(Saveable s)
	{
		s.save(ln);
	}
	public void load(Saveable s){
		s.load(ln);
	}

	public static ListNode createListNode(String key){
		return new ListNode(key);
	}

	public void saveGame(OutputStream p) {


	try {
		Document d = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

		DOMSource s = new DOMSource(d);
		StreamResult r = new StreamResult(p);
		try {
			TransformerFactory.newInstance().newTransformer().transform(s, r);
		} catch (TransformerException | TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
	public void loadGame(InputStream in){
		StreamSource s = new StreamSource(in);
		DOMResult r = new DOMResult();
		try {
			TransformerFactory.newInstance().newTransformer().transform(s, r);
			Document d = r.getNode().getOwnerDocument();
			Node data = d.getElementsByTagName("data").item(0);
			decodeListNode(data,ln);
		} catch (TransformerException | TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void decodeListNode(Node data, ListNode node) {
		// TODO Auto-generated method stub
		NodeList list = data.getChildNodes();
		for(int i = 0;i <list.getLength();i++){
			Node n = list.item(i);
			String name = n.getLocalName();
			switch(name){
			case "intmapping":
				Node key = n.getFirstChild();
				Node value = n.getLastChild();
				node.appendIntNode(key.getNodeValue(), Integer.parseInt(key.getNodeName()));
			break;
			case "stringmapping":
				Node key1 = n.getFirstChild();
				Node value1 = n.getLastChild();
				node.appendStringNode(key1.getNodeValue(), value1.getNodeValue());
			break;
			case "listmapping":
				Node key2 = n.getFirstChild();
				Node value2 = n.getLastChild();
				ListNode target = new ListNode(key2.getNodeValue());
				decodeListNode(value2,target);
				node.appendListNode(target);
			break;
			}
		}
	}






}
