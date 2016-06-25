This Hibernate + Maven sample:
* Under "/resources/<package>" folder, define "*.hbm.xml".
* Under "/resources" folder, define "hibernate.cfg.xml".
* Class names in "*.hbm.xml" must be with package name.
* In "pom.xml", use "hbm2java" goal to generate "*.java" based on "hibernate.cfg.xml" and "*.hbm.xml".
