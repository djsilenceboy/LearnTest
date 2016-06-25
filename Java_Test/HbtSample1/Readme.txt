This Hibernate + Maven sample:
* Under "/resources" folder, define "database.properties" and "*.hbm.xml".
* Class names in "*.hbm.xml" must be with package name.
* In "pom.xml", use "hbm2cfgxml" goal to generate "hibernate.cfg.xml" based on "database.properties" and "*.hbm.xml".
* In "pom.xml", use "hbm2java" goal to generate "*.java" based on generated "hibernate.cfg.xml" and predefined "*.hbm.xml".
