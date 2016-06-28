
package dj.test.javalang.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import dj.test.common.Utils;

public class TestMainObjectIo
{
	public void testObjectOutStream1(){
		SampleData obj = new SampleData();
		obj.setJ(456);
		obj.getSampleData2().setJ(456);
		SampleData.setK(789);
		SampleData2.setK(789);
		System.out.println("Object = " + obj);
		byte[] objRawData = null;

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(baos)) {
			oos.writeObject(obj);
			objRawData = baos.toByteArray();
			System.out.println("Content Hex = " + Utils.arrayToHexString(objRawData));
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		SampleData.setK(0);
		SampleData2.setK(0);

		try (ByteArrayInputStream bais = new ByteArrayInputStream(objRawData); ObjectInputStream ois = new ObjectInputStream(bais)) {
			SampleData obj2 = (SampleData)ois.readObject();
			System.out.println("Object2 = " + obj2);
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}
	}

	public static void main(String[] args){
		TestMainObjectIo test = new TestMainObjectIo();

		System.out.println("========================================");

		test.testObjectOutStream1();

		System.out.println("========================================");

		System.out.println("========================================");
	}

	@Override
	public String toString(){
		return "TestMainObject [toString()=" + super.toString() + "]";
	}
}

class SampleData implements Serializable
{
	int i = 123;
	String s = "Hello";
	transient int j = 0;
	static int k = 0;

	SampleData2 sampleData2 = new SampleData2();

	// Exception = java.io.NotSerializableException: dj.test.javalang.io.SampleData3
	// SampleData3 sampleData3 = new SampleData3();

	public int getI(){
		return i;
	}

	public void setI(int i){
		this.i = i;
	}

	public String getS(){
		return s;
	}

	public void setS(String s){
		this.s = s;
	}

	public int getJ(){
		return j;
	}

	public void setJ(int j){
		this.j = j;
	}

	public static int getK(){
		return k;
	}

	public static void setK(int k){
		SampleData.k = k;
	}

	public SampleData2 getSampleData2(){
		return sampleData2;
	}

	public void setSampleData2(SampleData2 sampleData2){
		this.sampleData2 = sampleData2;
	}

	@Override
	public String toString(){
		return "SampleData [i=" + i + ", s=" + s + ", j=" + j + ", k=" + k + ", sampleData2=" + sampleData2 + "]";
	}
}

class SampleData2 implements Serializable
{
	int i = 123;
	String s = "Hello";
	transient int j = 0;
	static int k = 0;

	public int getI(){
		return i;
	}

	public void setI(int i){
		this.i = i;
	}

	public String getS(){
		return s;
	}

	public void setS(String s){
		this.s = s;
	}

	public int getJ(){
		return j;
	}

	public void setJ(int j){
		this.j = j;
	}

	public static int getK(){
		return k;
	}

	public static void setK(int k){
		SampleData2.k = k;
	}

	@Override
	public String toString(){
		return "SampleData [i=" + i + ", s=" + s + ", j=" + j + ", k=" + k + "]";
	}
}

class SampleData3
{
	int i = 123;
	String s = "Hello";
	transient int j = 0;
	static int k = 0;

	public int getI(){
		return i;
	}

	public void setI(int i){
		this.i = i;
	}

	public String getS(){
		return s;
	}

	public void setS(String s){
		this.s = s;
	}

	public int getJ(){
		return j;
	}

	public void setJ(int j){
		this.j = j;
	}

	public static int getK(){
		return k;
	}

	public static void setK(int k){
		SampleData.k = k;
	}

	@Override
	public String toString(){
		return "SampleData [i=" + i + ", s=" + s + ", j=" + j + ", k=" + k + "]";
	}
}
