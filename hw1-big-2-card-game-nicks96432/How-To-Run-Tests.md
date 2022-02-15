# How To Run Tests

There are two ways to run the tests: 

1. Run the test via shell script
Run `bash test.sh`
2. Run each test manually
3. `cd src`
4. `javac TestSuite.java -d ../out/`
5. `cd ..`
6. `java -cp out/ TestSuite <testcase's name>`

> Replace `<testcase's name>` with the testcase's name you can find under the testcases/ directory. 
> For example `java -cp out/ TestSuite always-play-first-card`
