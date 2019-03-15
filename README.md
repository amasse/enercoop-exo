# enercoop-exo
Technical exercise for Enercoop interview

**Basic Tip**

The fastest way to answer the exercise a shell command, you can run the following in the logs directory, that will do the count (without taking care of ex-aequo)

`grep id * | sed -e 's/.*id=//g' | sed -e 's/\&.*//g' | sort | uniq -c | sort -r | head -n 5 | awk '{print $1}'`

**To build the program and run the tests, from the root directory of the project** :

`./gradlew clean test`

**To run the program,from the root directory of the project** :

Make sure you have built the jar !!:

`./gradlew fatJar`

Then run the following command

`java -jar build/libs/enercoop-exo-all-1.0-SNAPSHOT.jar src/test/resources/logs/`

The output should be :

~~~~
RankedId{rank=1, id=413, count=105, exAequo=false}
RankedId{rank=2, id=505, count=102, exAequo=false}
RankedId{rank=3, id=473, count=97, exAequo=false}
RankedId{rank=4, id=512, count=96, exAequo=false}
RankedId{rank=5, id=561, count=95, exAequo=true}
RankedId{rank=5, id=753, count=95, exAequo=true}
RankedId{rank=5, id=991, count=95, exAequo=true}
~~~~
