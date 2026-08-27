# Electricity Price Analyzer

## Description
A client application for analysing and calculating electricity prices throughout
the day based on a selected electricity area using API fetching.
Min, max and average prices are included, as well as sorting prices
from lowest to highest and finding the cheapest 4 consecutive charging hours.

## Implementation
The app is built using the different classes Main,
ElectricityPriceClient and ElectricityPriceService.
Main handles the console menu and user input.
ElectricityPriceClient handles API requests for
fetching the daily electricity prices and converts
the JSON data with Jackson into usable objects,
mapped to an array containing the ElectricityPrice model/record.

ElectricityPriceService uses the converted API data to calculate
min, max and average prices.
It also sorts prices from lowest to highest
and finds the best consecutive
charging period (4 hours) using
a sliding window algorithm.

To prevent invalid input,
necessary guard clauses have been implemented in Main.

## Reflection
The biggest difference when programming with Java
compared to JavaScript was the syntax that
I'm not used to yet. It was a challenge figuring
out the different method signatures and what differs
between them. It was also a bit confusing
to understand how to access methods between
classes since I'm used to being able to write
"export" in JavaScript.

Sliding window was also something
new, but something that I found very useful.
All in all, I enjoyed the experience
and I can see the benefits of this language.
I look forward to learning more about it
and being able to understand and master it

