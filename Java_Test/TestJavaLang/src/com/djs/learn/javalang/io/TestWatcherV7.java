
package com.djs.learn.javalang.io;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class TestWatcherV7
{
	String directoryName = "etc/data";

	public void testWatcher1(){
		WatchService watcher = null;
		Path path = null;

		try {
			watcher = FileSystems.getDefault().newWatchService();
			path = Paths.get(directoryName);

			WatchKey watchKey = path.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);
		} catch (Exception e) {
			System.err.println("Exception = " + e);
		}

		for (;;) {
			// wait for key to be signaled
			WatchKey key;

			try {
				key = watcher.take();
			} catch (InterruptedException x) {
				return;
			}

			for (WatchEvent<?> event : key.pollEvents()) {
				WatchEvent.Kind<?> kind = event.kind();

				// This key is registered only
				// for ENTRY_CREATE events,
				// but an OVERFLOW event can
				// occur regardless if events
				// are lost or discarded.
				if (kind == StandardWatchEventKinds.OVERFLOW) {
					continue;
				}

				// The filename is the
				// context of the event.
				WatchEvent<Path> ev = (WatchEvent<Path>)event;
				Path filename = ev.context();

				// Verify that the new
				//  file is a text file.
				try {
					// Resolve the filename against the directory.
					// If the filename is "test" and the directory is "foo",
					// the resolved name is "test/foo".
					Path child = path.resolve(filename);
					if (!Files.probeContentType(child).equals("text/plain")) {
						System.err.format("New file '%s'" + " is not a plain text file.%n", filename);
						continue;
					}
				} catch (IOException x) {
					System.err.println(x);
					continue;
				}

				// Email the file to the
				//  specified email alias.
				System.out.format("Emailing file %s%n", filename);
				//Details left to reader....
			}

			// Reset the key -- this step is critical if you want to
			// receive further watch events.  If the key is no longer valid,
			// the directory is inaccessible so exit the loop.
			boolean valid = key.reset();
			if (!valid) {
				break;
			}
		}
	}

	public static void main(String[] args){
		TestWatcherV7 test = new TestWatcherV7();

		System.out.println("========================================");

		test.testWatcher1();

		System.out.println("========================================");
	}
}
