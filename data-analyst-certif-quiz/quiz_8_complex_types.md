## Question
You have been given a below table definition:
~~~
describe region;
+.........+................+........+
|name|type||
+.........+................+........+
r_regionkey|smallint|
r_name|string|
r_comment|string|
r_nations|array<struct<
n_nationkey:smallint,
n_name:string,
n_comment:string
>>
+.........+................+........+
~~~

Write a Query report, which can generate following as an output.
- For each region, how many nation keys.
- For each region sum of all the nation keys.
- For each region avg of all the nation keys.
- For each region calculate minimum and maximum nation name value.
- Calculate for each region, how many distinct nation keys using ndv function.

## Solutions
~~~
select
r_name,
count(r_nations.item.n_nationkey) as count.
sum(r_nations.item.n_nationkey) as sum,
avg(r_nations.item.n_nationkey) as avg.
min(r_nations.item.n_name) as minimum.
max(r_nationsitem.n_name) as maximum,
ndv(r nationsitem.n nationkey) as distinct vals
from
region, region.r_nations as r_nations
group by r_name 
order by r_name;
~~~
