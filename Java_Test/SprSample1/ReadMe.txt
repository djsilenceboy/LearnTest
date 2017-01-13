Create Spring project with Spring Tool Suite IDE
http://www.codejava.net/frameworks/spring/spring-mvc-beginner-tutorial-with-spring-tool-suite-ide


To add Web content, there are several options, in order from best:

1. Copy all contents inside "WebContent" to "src/main/webapp/".

2.
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-war-plugin</artifactId>
				<version>2.4</version>
				<configuration>
					<webResources>
						<resource>
							<directory>WebContent</directory>
						</resource>
					</webResources>
				</configuration>
			</plugin>
		</plugins>

3.
	<build>
		<resources>
			<resource>
				<directory>WebContent</directory>
			</resource>
		</resources>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-war-plugin</artifactId>
				<version>2.4</version>
				<configuration>
					<webXml>WebContent\WEB-INF\web.xml</webXml>
				</configuration>
			</plugin>
		</plugins>
	</build>


When running this project from a shared disk in VirtualBox, it must use version 2.4 for WAR plugin.
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-war-plugin</artifactId>
				<version>2.4</version>
Otherwise, it can use higher version, such as "2.6", "3.0.0".
