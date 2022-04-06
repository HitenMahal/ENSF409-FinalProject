Our UML has the following 13 Classes:
    Order
    OrderForm
    CalculateHamper
    Inventory
    FoodItem
    Hamper
    Nutrition
    NutritionContent
    Client
    FormatMethods
    FileAccessException
    InsufficientFoodException
    DatabaseConnectionError

To make it more readable we have split our tests into various files.
Below we specify which Classes are tested in which file.

TestOrders.java:
    FileAccessException
    Order
    OrderForm

TestNutrition.java:
    Nutrition
    NutritionContent
    InsufficientFoodException

TestHamperAndFormatMethods.java:
    Hamper
    FormatMethods
    DatabaseConnectionError

TestInventory.java:
    Inventory

TestFoodItem.java:
    FoodItem

TestCalculateHamper.java:
    CalculateHamper

TestCalculateClient.java:
    Client
