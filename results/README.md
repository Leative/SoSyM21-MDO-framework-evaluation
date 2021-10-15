# Explanation of collected data #

## Archive files ##
The 7z archives contain the raw output data of MDEOptimiser. Despite their small size the extracted data needs about a gigabyte for each use case.
For each evolutionary computation (called **batch**) a file **batch-\<batchNumber\>-data-steps.csv** is provided containing data about the elapsed time, the population and the approximation set of each iteration of the batch.
A file **batch-\<batchNumber\>-outcome.txt** contains a summary including more inforation about the approximation set of the last iteration. 
For all evolutionary computations performed for a problem instance, the EMF instance models (xmi files) of the approximation sets of the last iterations are available in the main folder of that problem instance.

## Analysis folder ##
The analysis folder of each use case contains data collected by an analytical tool from the output of MDEOptimiser.
The folder itself contains a summary report for all problem instances which gives an overview over the most important metrics:
- the mean number of iterations,
- different aggregations of the normalized hypervolume,
- the standard deviation for the mean normalized hypervolume,
- the overall contributions of a specific algorithmic variant to the union of all cumulative approximation sets of all variants for the respective problem instance, and
- the BSR which is the percentage of these contributions.
Additionally, for each problem instance an overview over some statistical values is provided in a separate file. They need to be read as row x column, i.e., 
a positive value for Cliff's delta means that the variant in the header of the respective row performs better than the variant in the header of the respective column.

A separate folder for each problem instance contains more in depth information about the performed evolutionary computations. The names of the files are chosen to be self-explanatory.

## Charts folder ##
Based on the data in the analysis folder, charts contains visualizations of the progress of different metrics.
