RANDOOP_JAR=/Users/rabdulov/dev/tools/randoop-4.2.1
java -Xmx3000m -classpath /Users/rabdulov/IdeaProjects/HackerRank/out/production/HackerRank:$RANDOOP_JAR/randoop-all-4.2.1.jar randoop.main.Main gentests --testclass=com.company.symmetrical_points.SymmetricalPoints --output-limit=100

java -Xmx3000m -classpath /Users/rabdulov/IdeaProjects/HackerRank/out/production/HackerRank:$RANDOOP_JAR/randoop-all-4.2.1.jar randoop.main.Main gentests --testclass=com.company.test.RandoopTestingCase --output-limit=100