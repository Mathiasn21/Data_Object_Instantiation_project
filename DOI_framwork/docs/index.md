# DOIF - DataObject Instantiation Framework with statistics

## Introduction
The project’s primary goal is to alleviate redundant work related to reading and interacting
with the information contained inside different resources. Like files or from GET requests.
This also includes support for various data formats like JSON or CSV.

Its secondary goal aims to execute statistical calculation and analysis on a given dataset.
These include things like:
Calculating different averages over a data column

This assumes the user of the framework will specify necessary rules and settings in order
to allow the framework to optimally handle any given resource and its content.
Necessary rules will involve utilizing annotations when required and/or initializing a
class with specific constructor parameters or parameter lists. Which further is a compromise that aims
to displease everyone equally whilst allowing the framework to handle a broader aspect of responsibilities.
Like allowing for more file formats and/or other types of resources, or even internal data structures.
