# Use Case Model
**Author**: Team 2

## 1 Use Case Diagram

![](../Resources/use-caseDiagram.png)

## 2 Use Case Descriptions

1. Browse Products: 
    - Requirements: allow a user to browse all of the different products a store offers. 
    - Pre-conditions: the user must be either be a customer or a employee. In addition, there should be products to display.
    - Post-conditions: the user has detailed information on the product they browsed for. 
    - Scenarios: 
        - normal: 
            1. Log into app
            2. All products available are displayed on the home page.
2. Search for product:
    -   Requirements: allow a user to lookup a specific product by name or select a category and type.
    -   Pre-conditions: the user must be a customer or employee and the number of products in the store should not be zero. 
    -   Post-conditions: the user will have detailed information on the specific product they searched for. 
    -   Scenarios: 
        - normal: 
            1. Log into app. 
            2. Click on search bar and type in name of product. 
            3. Information about that specific product is dislayed if found. 
        - alternate:
            1. Log into app.
            2. User clicks on list icon.
            3. User selects a specific category from the list.
            4. App displays the types of floors under the selected category. 
            5. User selects a specific type of floor. 
            6. Detailed information about the selected floor is displayed.
        - exceptional: 
            1. Log into app.
            2. Click on search bar and type in name. 
            3. App displays an error message stating a product with that name was not found. 
3. Delete product: 
    -   Requirements: allow an employee to delete a product from the store. 
    -   Pre-conditions: the user trying to carrying out the operation is an employee and the product being deleted exists in the store.
    -   Post-conditions: the product is removed from the store and is no longer displayed in the home page or can be searched for by a user. 
    -   Scenarios: 
        - normal: 
            1. Log into app. 
            2. User browses for the product on the homepage.
            3. User clicks on delete icon. 
            4. User has to confirm deletion by clicking on button. 
        - alternate:
            1. Log into app. 
            2. User searches for the product by its name.
            3. User clicks on delete icon. 
            4. User has to confirm deletion by clicking on button. 
4. Modifying product: 
    -   Requirements: allow an employee to delete a modfy a product in the store.
    -   Pre-conditions: the user carrying out the operation is an employee and the product being modified exists in the store. 
    -   Post-conditions: the product's information is modified.
    -   Scenarios: 
        - normal: 
                1. Log into app. 
                2. User browses for the product on the homepage.
                3. User clicks on modify icon. 
                4. User modfies the values they want.
                4. User has to confirm modification by clicking on button. 
            - alternate:
               1. Log into app. 
                2. User searches for the product by its name.
                3. User clicks on modify icon. 
                4. User modfies the values they want.
                4. User has to confirm modification by clicking on button. 
5. Adding product: 
    -   Requirements: allow an employee to add a product to the store.
    -   Pre-conditions: the user carrying out this operation is an employee. The floor being added should not already exist. 
    -   Post-conditions: a new product is added to the store. 
    -   Scenarios: 
        - normal :
            1. Log into app.
            2. Click on add button.
            3. App displays a form to enter information of the new product. 
            4. User confirms addition of product by clicking button. 
        - exceptional: 
            1. Log into app.
            2. Click on add button.
            3. App displays a form to enter information of the new product. 
            4. A toast will popup warning the user. 
        - exceptional: 
            1. Log into app.
            2. Click on add button.
            3. App displays a form to enter information of the new product. 
            4. The add button cannot be clicked. 

