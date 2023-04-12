## Floor Supply Stores 
This is a software design for chain of stores that specialise in floor tiles.

### Assumptions
- Floor - each product has a unique name represented by attribute NAME.
- Store - each store has at least 1 product in it.
- Store - each store also contains a list of all the products it has. 
- User - each user has a unique ID, represented by attribute ID.

### Meeting Requirements
1.As a user of the system I want to be able to see each store's offering and the amount of product in stock by square feet.

To meet this requirement I designed a class STORE with attributes: STOREID, PRODUCTLIST. This class will keep a list of all the products available in that store.
In addition, I designed a class USER with attributes: ISEMPLOYEE, ID, and NAME. 
USER also methods: checkStock(Int) and checkAllProducts(Int). 
The first method allows a user to check the amount of product in stock in terms of sq. ft. This method uses the method getAmount() in the FLOOR class.
The second method allows them to browse through all the products a store has. 

2.The application must allow employees to add new products to the system. As well as delete and edit them.

To meet this requirement I made an EMPLOYEE class. It has methods addProduct(Floor), editProduct(Floor), and removeProduct(Floor).
These methods take in a paramter of the Floor type. 

3.The different categories of floors available are tile, stone, wood, laminate and vinyl

To meet this requirement I made classes TILE, STONE, WOOD, LAMINATE, and VINYL. 
These classes inherit the FLOOR class. 
These classes all contain the attribute TYPE and the method getType() which returns a string representing the type of that floor. 
TILE and STONE have an attribute MATERIAL.
WOOD has an attribute SPECIES. 

4.The application must contain a database (DB) of floors.

This requirement was not modeled as its out of the scope of the class diagram. 

5.Users must be able to search for products by picking from a hierarchical list, where the first level is the floor category, and the second level is the floor type.

To implement this the class USER has the method: browseProducts()). The first method allows a user to search for a product, first through its category then its type. This method will call the method getCategory() in FLOOR and getType() in TILE,STONE,WOOD,LAMINATE,and VINYL. 

6.Users must also be able to specify an item by typing its name (search functionality).

To implement this the class USER has the method searchProduct(String). This method takes in a String parameter. This method allows a user to search for a specific product using its name. 

7.All floors regardless of their category have an associated color, size, brand, type and price.

To implement this I created a FLOOR class with contains the attributes: CATEGORY,COLOR,SIZE,BRAND, and PRICE. The classes that inherit from FLOOR: TILE,STONE,WOOD,LAMINATE,and VINYL contain the attribute TYPE. 

8.Categories tile and stone have different materials they are made of, eg. Tile - porcelain, ceramic, resin; Stone - marble, pebble, 

To implement this the classes TILE and STONE have the attribute MATERIAL. 

9.Wood floors have both a type (solid, engineered, bamboo, etc) and species (oak,hickory, maple, etc.)

To implement this the class WOOD has the attributes SPECIES and TYPE.

10.Laminate can be regular laminate or water resistant, whereas vinyl can be water resistant or waterproof.

To implement this the class LAMINATE has a boolean attribute ISWATERRESISTANT. VINYL has a String list attribute ISWATERPROOF. The decision to use a boolean for LAMINATE is due to the fact that LAMINATE is either its normal form or water resistant compared to VINYL which has 2 distinct variants. 

11.The User Interface (UI) must be intuitive and responsive.

This requirement was not implemented as it doesnt directly contribute to the design of the system. 