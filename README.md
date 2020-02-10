## Table of contents
* [General info](#general-info)
* [Links](#links)
* [Paths](#paths)
* [Examples](#examples)

## General info
This project is simple service which fetches data from data.cityofnewyork.us and returns it processed.

## Links
Raw data set:

https://data.cityofnewyork.us/resource/5rq2-4hqu.json

Description of data set:
https://data.cityofnewyork.us/Environment/2015-Street-Tree-Census-Tree-Data/uvpi-gqnh

		
### Paths


|  URL |  Method |  Description |
|----------|--------------|----------------|
|`http://localhost:8080/serivce/nearby-trees?x=*y=*radius=*`  | GET | Returns all trees in the radius|

## Examples

Input:
  - X and Y of the center point, radius in meters for the look up.

Output:
  - Aggregation by common name

Example of the expected output:
http://localhost:8080/service/nearby-trees?X=1052594.02767&Y=181149.472069&radius=1000
```json
{
	"silver maple": 1,
	"honeylocust": 1,
	"Norway maple": 2,
	"swamp white oak": 1
}
```
