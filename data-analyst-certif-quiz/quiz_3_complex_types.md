## Questions
Please accomplish following actvities:
1. Create a table region with fololowing structure. The underline file format should be Parquet in HDFS
  r_regionkey smallint,
  r_name string,
  r_comment string,
  r_nations array<struct<n_nationkey:smallint,n_name:string,n_comment:string >>

2. Once table is created, load data in this table from region.csv
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

3. Now calculate number of nations keys, total of nations keys, average of nations keys, minimum and maximum of nation name also find count of distinct nations. This calculation should be region specific

## Solution
### Q1
~~~
create table region (
r_regionkey smallint,
r_name string,
r_comment string,
r_nations array<struct<n_nationkey : smallint, n_name : string, n_comment : string>>)
row format delimited
fields terminated by '|'
lines terminated by '\n'
stored as parquet file;
~~~
### Q2
Create a temptable first.
Create table tempregion(data string);
Now load data in this table.
Load data inpath Vusericloudera/hadoopexam_101/region.csv' into table tempregion 
Now create a select statement, which will split the data for each row in requested format.

~~~
SELECT split(data,'\\|')[0] r_regionkey 
, split(data,'\\|')[1] r_name
, split(data,'\\|')[2] r_comment 
, split(split(data.'\\|'[3],",")[0] n_nationkey 
, split(split(data,'\\|'[3].",")[1] n_name
, split(split(data,'\\|'),",")[2] n_comment 
From tempregion;
~~~
Now create an insert statement, which will load data in this table.(It will take sometime based on volume of data)
~~~
insert overwrite table region
SELECT split(data,'\\|')[0] r_regionkey
, split(data,'\\|')[1] r_name
, split(data,'\\|')[2] r_comment
, array(named_struct( "n_nationkey". cast(split(split(data,'\\|')[3],",")[0] as smallint) , "n_name" , split(split(data,'\\|')[3],",")[1] , "n_comment" , split(split(data,'\\|')[3],",")[2]
~~~
Check whether data have been loaded or not.
~~~
SELECT * from region;
Now use impala-shell or Hue to refrech metadata
invalidate metadata;
~~~
### Q3
~~~
select
r_name,
count(r_nations.item.n_nationkey) as count,
sum(r_nations.item.n_nationkey) as total,
avg(r_nations.item.n_nationkey) as average,
min(r_nations.item.n_name) as minimum,
max(r_nations.item.n_name) as maximum,
ndv(r_nations.item.n_nationkey) as nations_count
from region, region.r_nations as r_nations
group by r_name
order by r_name
~~~
