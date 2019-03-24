## Q1
You have been given MySql retail db database. Please import all the tables in HADOOPEXAMDB using Sqoop

### A1
sqoop import-all-tables jdbc:mysql://localhost/retaildb --username user --password password --target-dir HADOOPEXAMDB

## Q2 
You have following table structure in hive/impala: Table Name: he_order_items; columns: order_item_id int order_item_product int order_item_quantity tinyint order_item_subtotal double order_item_product_price double

1-Calculate the minimum, maximum and average of order_item_product_price.
2-Now find approximately the median of 'order_item_product_price'.
3-Count the all values higher than appx_median value
4-Count the all values lower than appx_median value.
5-Calculate appx_median of order_item_product_price where order_item_product_price between 20 and 80

### A2
1- select min(order_item_product_price), max(order_item_product_price), avg(order_item_product_price) from he_order_items;
2- select appx_median(order_item_product_price) from he_order_items;
3- select count(order_item_product_price) from he_order_items where order_item_product_price >= (select appx_median(order_item_product_price) from he_order_items);
4- select count(order_item_product_price) from he_order_items where order_item_product_price < (select appx_median(order_item_product_price) from he_order_items);
5- select appx_median(order_item_product_price) from he_order_items where order_item_product_price between 20 and 80 

## Q3
Please accomplish following actvities:
1- Create a table region with fololowing structure. The underline file format should be Parquet in HDFS
r_regionkey smallint,
r_name string,
r_comment string,
r_nations array<struct<n_nationkey:smallint,n_name:string,n_comment:string >>

2-Once table is created, load data in this table from region.csv
Create data file in the hdfs path : cloudera/eliteformation_101/region.csv (Exclude header). Use hue to do this activity. 
r_regionkey|r_name|r_comment|r_nations(inferieur)n_nationkey,n_name,n_comment(superieur) 
1|AFRICA|Good Business Region for eliteformation.fr|0,Cameroon,Reference site http://www.QuickTechie.com
1|AFRICA|Good Business Region for Trainning4Exam.com|5,Egypt,Reference site http://www.eliteformation.fr
1|AFRICA|Good Business Region for eliteformation.fr|14,Namibia, Reference site http://www.QuickTechie.com
1|AFRICA|Good Business Region for Trainning4Exam.com|15,Zimbabwe, Reference site http://www.QuickTechie.com
1|AFRICA|Good Business Region for eliteformation.fr|16,Uganda, Reference site http://www.QuickTechie.com
2|AMERICA|Average Business Region for eliteformation.fr|1, United States, Reference site http://www.eliteformation.fr
2|AMERICA|Average Business Region for Trainning4Exam.com|2, Canada, Reference site http://www.eliteformation.fr
2|AMERICA|Average Business Region for eliteformation.fr|3,Cuba, Reference site http://www.QuickTechie.com
2|AMERICA|Average Business Region for Trainning4Exam.com|17,Costa Rica, Reference site http://www.eliteformation.fr
2|AMERICA|Average Business Region for eliteformation.fr|24, Panama, Reference site http://www.eliteformation.fr
3|ASIA|Best Business Region for Trainning4Exam.com|8,India,Reference site http://www.QuickTechie.com
3|ASIA|Best Business Region for eliteformation.fr|9, China, Reference site http://www.eliteformation.fr
3|ASIA|Best Business Region for Trainning4Exam.com|12, Japan, Reference site http://www.QuickTechie.com
3|ASIA|Best Business Region for eliteformation.fr|18, Russia, Reference site http://www.eliteformation.fr
3|ASIA|Best Business Region for Trainning4Exam.com|21, Israel, Reference site http://www.QuickTechie.com
4|EUROPE|Low sale Business Region for eliteformation.fr|6, Austria, Reference site http://www.eliteformation.fr
4|EUROPE|Low sale Business Region for Trainning4Exam.com|7,Bulgaria, Reference site http://www.QuickTechie.com
4|EUROPE|Low sale Business Region for eliteformation.fr|19, Belgium, Reference site http://www.eliteformation.fr
4|EUROPE|Low sale Business Region for Trainning4Exam.com|22, Croatia, Reference site http://www.QuickTechie.com
4|EUROPE|Low sale Business Region for eliteformation.fr|23, Denmark, Reference site http://www.eliteformation.fr
5|MIDDLE EAST|OK OK Sale Business Region for eliteformation.fr|4, Saudi Arabia, Reference site http://www.QuickTechie.com
5|MIDDLE EAST|OK OK Sale Business Region for Trainning4Exam.com|10, Yemen, Reference site http://www.eliteformation.fr
5|MIDDLE EAST|OK OK Sale Business Region for eliteformation.fr|11, Oman , Reference site http://www.QuickTechie.com
5|MIDDLE EAST|OK OK Sale Business Region for Trainning4Exam.com|13, kuwait, Reference site http://www.eliteformation.fr
5|MIDDLE EAST|OK OK Sale Business Region for eliteformation.fr|20, Qatar , Reference site http://www.QuickTechie.com

3- Now calculate number of nations keys, total of nations keys, average of nations keys, minimum and maximum of nation name also find count of distinct nations. This calculation should be region specific

