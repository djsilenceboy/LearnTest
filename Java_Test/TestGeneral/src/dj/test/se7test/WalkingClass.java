
package dj.test.se7test;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;

public class WalkingClass
{
	public static void main(String[] args){
		String a = "etc";
		Path p = FileSystems.getDefault().getPath(a).toAbsolutePath();
		A sfv = new A();

		try {
			walk(p, sfv);
		} catch (IOException e) {
			System.out.println("error: " + e.toString());
		}
	}

	static void walk(Path p, A a) throws IOException{
		Files.walkFileTree(p, a);
	}
}

class A extends SimpleFileVisitor<Path>
{
	@Override
	public FileVisitResult postVisitDirectory(Path p, IOException e) throws IOException{
		System.out.println(p);
		return CONTINUE;
	}
}
