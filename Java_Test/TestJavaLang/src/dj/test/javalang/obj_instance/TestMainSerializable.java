
package dj.test.javalang.obj_instance;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.util.Arrays;

public class TestMainSerializable
{
	public static byte[] getBytesFromObject(Object obj) throws IOException{
		if (obj != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);

			oos.writeObject(obj);
			oos.close();

			return baos.toByteArray();
		} else {
			return null;
		}
	}

	public static Object getObjectFromBytes(byte[] data) throws IOException, ClassNotFoundException{
		if (data != null) {
			ByteArrayInputStream bais = new ByteArrayInputStream(data);
			ObjectInputStream ois = new ObjectInputStream(bais);

			return ois.readObject();
		} else {
			return null;
		}
	}

	public static void main(String[] args){
		try {
			URI uri = new URI("http://localhost");

			System.out.println("Src URI = " + uri);

			byte[] data = getBytesFromObject(uri);

			System.out.println("Obj URI (" + data.length + ") = " + Arrays.toString(data));

			Object obj = getObjectFromBytes(data);

			System.out.println("Obj is URI = " + (obj instanceof URI));
			System.out.println("Dst URI = " + obj);

			/*
			 * Src URI = http://localhost
			 * Obj URI (83) = [-84, -19, 0, 5, 115, 114, 0, 12, 106, 97, 118, 97, 46, 110, 101, 116, 46, 85, 82, 73, -84, 1, 120, 46, 67, -98, 73, -85, 3, 0, 1,
			 * 76, 0, 6, 115, 116, 114, 105, 110, 103, 116, 0, 18, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 120, 112,
			 * 116, 0, 16, 104, 116, 116, 112, 58, 47, 47, 108, 111, 99, 97, 108, 104, 111, 115, 116, 120]
			 * Obj is URI = true
			 * Dst URI = http://localhost
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
