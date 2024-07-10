#ESS Simulation Project
##_CSCI 331 - Prof. Borrelli_
##_Alex Iacob ai9388@rit.edu_

To compile and run, simply compile ESSSimulation.java inside of the src folder and run
using ./ESSSimulation popSize [percentHawks] [resourceAmt] [costHawk-Hawk], where only
the first parameter is required. All of the other values have a default.

For this project, we were assigned to read an article about the logic 
of animal conflict. In this particular scenario, we had to learn about doves and hawks.
Every animal has their own number, status, and resource value. For each animal encounter,
we can have a Dove-Dove, Dove-Hawk, Hawk-Dove, or Hawk-Hawk.

* A Dove-Hawk or Hawk-Dove have essentially the same outcome. The Hawk takes all of the
resources for itself while the Dove flies away without any resources.
 * A Dove-Dove interaction has both animals split the resources and each take half
before flying away.
* A Hawk-Hawk interaction is different. The resources go to the first hawk while both
suffering from the Hawk-Hawk cost interaction. If either of the hawk's resource value
falls below 0, it dies.

In regards to Evolutionary Stable Strategies, we can see a direct closed system application
with the hawks and doves. In all of these situations we can already deduce what can happen
before it happens because of our closed system. This provides a gateway to our knowledge
in Intelligent Systems while using Game Theory as a platform of understanding. Consequently,
Game theory is used to find a strategy that can withstand the test of time and strive for a
longer period of time; even if there might be better strategies for a current instance, this ESS
will be the most stable for the most amount of time.

I chose to use Java for this project because of my prior experience and familiarity with the
language. I also considered that an object oriented design would assist with sorting and creating
of each individual animal.