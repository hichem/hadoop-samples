## Questions
You have been given MySql retail db database. Please import all the tables in HADOOPEXAMDB using Sqoop

## Solution
sqoop import-all-tables jdbc:mysql://localhost/retaildb --username user --password password --target-dir HADOOPEXAMDB
