package chanhchung.vn.ChatApp.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import chanhchung.vn.ChatApp.model.Account;

public class XmlFileFactory {
	
	static final String ACC_FILE_NAME = "Account.xml";
	
	
	public static ArrayList<Account> readListAccount() 
	{
		ArrayList<Account> res = new ArrayList<Account>();
		
		try {
			File obj = new File(ACC_FILE_NAME);
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			if(obj.exists()) {
				Document doc =  documentBuilder.parse(obj);
				Element root = doc.getDocumentElement();
				NodeList _list = root.getElementsByTagName("account");
				
				int length = _list.getLength();
				if (length == 0) {
					System.out.println("Danh sach trong");
				} else {
					for(int i = 0 ; i < length ; i++) {
						// username vs password
						String info = _list.item(i).getTextContent().trim();
						String []arr = info.split("\n");
						String username = arr[0];
						String pass = arr[1];
						Account model = new Account(username, pass);
						res.add(model);
					}
				}
				
				
		    } else {
		    	obj.createNewFile();
		    	Document doc =  documentBuilder.newDocument();
		    	//Tao root
		    	Element root = doc.createElement("accounts");
		    	doc.appendChild(root);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
		        Transformer transformer = transformerFactory.newTransformer();
		        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
		        transformer.setOutputProperty(OutputKeys.METHOD,"xml");
		        transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
		        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");
		        DOMSource source = new DOMSource(doc);
		        StreamResult result = new StreamResult(new File(ACC_FILE_NAME));
		        transformer.transform(source, result);
		    }			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
		
	}

	public static boolean isExsist(String _username) {
		ArrayList<Account> arr = readListAccount();
		for(Account item: arr) {
			if(item.get_username().compareToIgnoreCase(_username) == 0) {
				return true;
			}
		}
		return false;
	}
	
	public static void addNewAccount(String _username,String _pass) {
		
		try {
			
			if(isExsist(_username)) {
				JOptionPane jop = new JOptionPane();
				jop.showMessageDialog(null, "Tên đăng nhập đã tồn tại trong hệ thống! Vui lòng chọn tên khác");
			} else {
			
			File obj = new File(ACC_FILE_NAME);
			Document doc = (Document) DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(obj);
			// lay goc
			NodeList rootList = doc.getElementsByTagName("accounts");
			Node root = rootList.item(0);
			
			Element account = doc.createElement("account");
			Element user = doc.createElement("username");
			user.appendChild(doc.createTextNode(_username));
			Element pass = doc.createElement("password");
			pass.appendChild(doc.createTextNode(_pass));
			account.appendChild(user);
			account.appendChild(pass);
			
			root.appendChild(account);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
	        transformer.setOutputProperty(OutputKeys.METHOD,"xml");
	        transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
	       // transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");
	        DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(new File(ACC_FILE_NAME));
	        transformer.transform(source, result);
	        
	        JOptionPane.showMessageDialog(null, "Đăng kí thành công!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	
//
//	public static void delete_Vie(String word) {
//		try {
//			
//			File obj = new File(VA_FILE_NAME);
//			Document doc = (Document) DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(obj);
//			// lay goc
//			NodeList records = doc.getElementsByTagName("record");
//			
//			Set<Element> targetElements = new HashSet<Element>();
//
//		    List<Record> list = readAV();
//		    int index_search = SearchBinary.binarySearch(list, word);
//		    if(index_search != -1) {
//		    	Element nNode = (Element) records.item(index_search);
//		    	targetElements.add(nNode);
//		    }
//		    else {
//				// tim kiem tuan tu
//				for(int i = list.size() - 1; i >= 0 ; i--) {
//					if(list.get(i).getWord().compareToIgnoreCase(word) == 0) {
//						Element nNode = (Element) records.item(i);
//						targetElements.add(nNode);
//					}
//				}
//		    	
//		    }
//		    for(Element item : targetElements) {
//		    	item.getParentNode().removeChild(item);
//		    }
//		    
//		    if(targetElements.size() > 0) {
//		    	System.out.println("Xoa thanh cong!");
//		    } else {
//		    	System.out.println("Tu " + word + " khong tim thay de xoa!");
//		    }
//
//			
//			TransformerFactory transformerFactory = TransformerFactory.newInstance();
//	        Transformer transformer = transformerFactory.newTransformer();
//	        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
//	        transformer.setOutputProperty(OutputKeys.METHOD,"xml");
//	        transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
//	        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");
//	        DOMSource source = new DOMSource(doc);
//	        StreamResult result = new StreamResult(new File(VA_FILE_NAME));
//	        transformer.transform(source, result);
//	        
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//
//	public static void delete_Eng(String word) {
//			try {
//			
//			File obj = new File(AV_FILE_NAME);
//			Document doc = (Document) DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(obj);
//			// lay goc
//			NodeList records = doc.getElementsByTagName("record");
//			
//			Set<Element> targetElements = new HashSet<Element>();
//
//		    List<Record> list = readAV();
//		    int index_search = SearchBinary.binarySearch(list, word);
//		    if(index_search != -1) {
//		    	Node nNode = records.item(index_search);
//		    	Element e = (Element) nNode;
//		    	targetElements.add(e);
//		    }
//		    else {
//				// tim kiem tuan tu
//				for(int i = list.size() - 1; i >= 0 ; i--) {
//					if(list.get(i).getWord().compareToIgnoreCase(word) == 0) {
//						Element nNode = (Element) records.item(i);
//						targetElements.add(nNode);
//				    	//nNode.getParentNode().removeChild(nNode);   	
//					}
//				}
//		    	
//		    }
//		    if(targetElements.size() > 0) {
//		    	System.out.println("Xoa thanh cong!");
//		    } else {
//		    	System.out.println("Tu " + word + " khong tim thay de xoa!");
//		    }
//		    for(Element item : targetElements) {
//		    	item.getParentNode().removeChild(item);
//		    }
//
//			
//			TransformerFactory transformerFactory = TransformerFactory.newInstance();
//	        Transformer transformer = transformerFactory.newTransformer();
//	        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
//	        transformer.setOutputProperty(OutputKeys.METHOD,"xml");
//	        transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
//	        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");
//	        DOMSource source = new DOMSource(doc);
//	        StreamResult result = new StreamResult(new File(AV_FILE_NAME));
//	        transformer.transform(source, result);
//	        
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//



}
