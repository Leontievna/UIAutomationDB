This is a Test project for a DB https://www.bahn.de/. 

There are two types of test: 
Smoke test (check login button and search button) and e2e scenario without credit card data input.

You can upload the latest Allure report in the artifacts of the workflow run. [example](https://leontievna.github.io/UIAutomationDB/67/)

For generating the Allure report locally, follow the instructions below:

1. Install Alllure Commandline

    ```brew install allure```
2. Unzip the folder

3. Open a terminal in the directory where your folder is stored e.g.:  /Downloads
    
    ```allure serve```