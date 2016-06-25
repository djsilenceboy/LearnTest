This Hibernate + Maven sample:
* Under "/resources/<package>" folder, define "*.hbm.xml".
* Under "/resources" folder, define "hibernate.cfg.xml".
* Class names in "*.hbm.xml" must be with package name.
* In "pom.xml", use "hbm2java" goal to generate "*.java" based on "hibernate.cfg.xml" and "*.hbm.xml".
* In "pom.xml", use "hbm2dll" goal to create tables based on "hibernate.cfg.xml" and "*.hbm.xml".
  Alternatively to use "hibernate.hbm2ddl.auto" in "hibernate.cfg.xml" to create tables, instead of using "hbm2dll" in "pom.xml".
