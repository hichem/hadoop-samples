## Question
You have been given below table structure, with the data. Please find the 
  Table Name: region
  r_regionkey smallint,
  r_name string,
  r_nations array<struct<n_nationkey:smallint,n_name:string,n_comment:string>>

1. Count all the Nation keys
2. Count the Nation keys for each region.
3. Distinct Nation keys count for each region.

## Solutions
### Q1
~~~
select count(r_nations.item.n_nationkey) as count from region, region.r_nations as r_nations;
~~~
### Q2
~~~
select count(*) from HadoopExamint group by property;
~~~
or
~~~
select r_name, count(r_nations.item.n_nationkey) as count from region, region.r_nations as r_nations group by r_name;
~~~
### Q3
~~~
select r_name, count(distinct r_nations.item.n_nationkey) as count from region, region.r_nations as r_nations group by r_name;
~~~
