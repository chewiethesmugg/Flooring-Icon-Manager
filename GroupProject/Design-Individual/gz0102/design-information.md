### UML class diagram design information

1. As a user of the system I want to be able to see each store's offering and the amount of product in stock by square feet.

   * To realize this requirement, I provided a getInStoreStock() function that user can utilize with each store's unique storeId to view all the floor stocks within that store by return a list of floors. 

     

2. The application must allow employees to add new products to the system. As well as delete and edit them.

   * To realize this requirement, I provided three functions which includes add/edit/delete products inside the employee class. An employee will be able to add/edit/delete products with his/her unique id, the storeId, and the item that needs to be modified. And these functions will return a boolean which indicates if the operation is successful.

     

3. The different categories of floors available are tile, stone, wood, laminate and vinyl

   * To realize this requirement, I created an abstract class called Floor and all the categories of floors are child classes which extends from the Floor class.

     

4. The application must contain a database (DB) of floors.

   * Not considered turing this phases of design, DB will be designed with ER diagram.

     

5. Users must be able to search for products by picking from a hierarchical list, where the first level is the floor category, and the second level is the floor type.

   * To realize this requirement, I placed a searchByCategory function in the Floor class which will return a list of different categories of floor. Then, based on the each category, a searchByType function can be called by provding differentt parameters based on that category. And a list of floor with specific type will be returned. 

   

6. Users must also be able to specify an item by typing its name (search functionality).

   * To realize this requirement, I created a searchByname function in the Floor class which will be inherited by all the categories. When user enter a specific name for that floor, a list of floor that are associated with the name will be returned. 

   

7. All floors regardless of their category have an associated color, size, brand, type and price

   * To realize this requirement, I placed all these attributes in the abstract Floor class so that all categories of floor will have all these attributes as well.

     

8. Categories tile and stone have different materials they are made of, e.g. Tile - porcelain, ceramic, resin; Stone - marble, pebble, slate

   * To realize this requirement, I added material attribute to both Tile and Stone class so that Tile and Stone floor class can set and get material by using the getter and setter for this attribute.

   

9. Wood floors have both a type (solid, engineered, bamboo, etc) and species (oak, hickory, maple, etc.)

   * To realize this requirement, I added species attribute to Wood class so that Wood floor class can set and get species as well as type by using the getter and setter for these attributes.

   

10. Laminate can be regular laminate or water resistant, whereas vinyl can be water resistant or waterproof

    * To realize this requirement, I added isWaterResistant attribute to the Laminate floor class, and isWaterProof to the Vinyl floor class. And these attributes can be set by the setType function in these two classes.

    

11. The User Interface (UI) must be intuitive and responsive.

    * Not considered during this phase of design. UI does not affect the classes and their relations directly. 





