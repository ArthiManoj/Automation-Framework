# Automation-Framework
Testing few functionalities of Wikipedia web application using junit

Goals of the project:

-Test few functionalities of Wikipedia web application using junit(a unit testing framework) which is used 
for developing a test driven development.

-Test framework needs to be integerated with Jenkins(a continuous integeration tool) and produce reports using Surefire.


Project Requirements:

-Wikipedia application needs to be tested across different browsers(Firefox,Chrome,IE,Safari) and platforms(Windows,MacOS and Linux).

-Positive test scenarios:

   1.Click on any link like 'English' from the home page and assert the title of the navigated page is matching the expected text.
   
   2.Sign in to the application and assert if the user name is matching as expected
   
   3.Test search functionality 
   
   4.Log out from the application and have a assertion check
   
   5.Return to main page and assert on page title again

-Negative test scenario:

   Enter invalid user name and password and assert on error message
   

-Automation Framework Business Requirements:

   1.Framework should support cross-browser testing by reusing same scripts
   
   2.Flexibility in setting test run priority
   
   3.Tests needs to be triggered automatically when use of Continuous Integeration Tool.
   
   4.Test reports needs to be accurate
   
   5.Framework must be easy to maintain and expansible
   
   6.Framework code needs to be checked in to git.
