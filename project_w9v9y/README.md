# My Personal Project

## Budget Tracker 

**Proposal**

For this Project, I wanted to create a budget tracker, this tracker will track
all the living expenses every day such as necessities, food, clothing e.t.c. It will need you
to enter the information of the item you entered such as the name and the amount of money it costs. 
Some features could include the type of item that is being added, the amount of money you decided to use for the budget. 
For this application, anyone can use it especially for those who are bad at handling their money where this application will allow you to keep track 
and know how much money you have left. This project is an interest to me where I personally know that I'm very bad at handling money and will always 
go over the limit I set every month, thus making me interested in creating this project.

## User stories
- As a User, I want to be able to add a budget in the beginning of the budget tracker
- As a User, I want to be able to add an item that contains the name, price and the amount of this item to my tracker. 
- As a User, I want to be able to view the list of items that are added in the tracker.
- As a User, I want to be able to remove the item from the tracker.
- As a User, I want to be able to see the budget increase or decrease when the item is added or removed.
- As a User, I want to be able to select the item in tracker and change the amount when the same item is bought another day.
- As a User, I would have to option to save the entire budget tracker application including the list of Items to file.
- As a User, when I start up the application again It would have the option to load the listOfItems from last time.

# Instructions for Grader

- There is a button called "Add Item" which when pressed a first JOPtionPanel comes up asking for the name then the price then the quantity.
- There is a button called "View Items" that when pressed it shows a list of items as a string in a JTextArea in JOption Panel.
- There is a visual component right after you enter the budget, At the top of the budgetTracker GUI you can see an image of money.
- Save the state of the application from a press of a button called "save"
- Load the state of the application from a press of a button called "load"

**Phase 4: Task 2**

-Sample:

Events Logged when App started: 

bread of price: 2.0, and Quantity: 3 added to Budget Tracker.

milk of price: 3.0, and Quantity: 6 added to Budget Tracker.

cups of price: 4.0, and Quantity: 5 added to Budget Tracker.

cups removed from Budget Tracker.

**Phase 4: Task 3**

If I had more time with this project, I would first fix the issues within Budget Tracker where the addItems methods has multiple functionality within its method. I would remove the change in price within the addItems and make a new method that changes the price of the item by the quantity. It also lacks exception handling when what if the item added and the budget goes below positive and the case where when removing the item from the tracker, what if there is no such item within the tracker. Thus exceptions could be added within budget tracker. Another fix could be where currently budgetTracker manipulates both the budget and the items list. Refactoring could involve budget and item management into separate classes, such as BudgetManager and ItemManager. 