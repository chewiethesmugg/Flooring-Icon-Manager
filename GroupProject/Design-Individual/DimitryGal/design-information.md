1 - to implement this requirement I included a Quantity Attribute in the FloorStore class

2-  To implement this requirement, a class FloorStore is needed to be added to the design. The FloorStore class will have all the Floors that the store has. As well as an employee class that will manipulate the floor products of that store
Class: FloorStore 
Attributes: store_ID, product_ID, Quantity
Operations: addFloor(), deleteFloor(), editFloor()

Class: Employee
Attributes: employee_ID
Operations: AddEmployee(), ModifyEmployee() 

3, 4, 7 -  for these requirements, I will create a class Floor and add it to the Design.
class: Floor
Attributes: FloorID, color, size, brand, type, price
Operations: addFloor(), deleteFloor(), editFloor()

5 -  This requirement is a relation between User and Floor. I'll add a User class to the design. A Relationship needs to be added for User and Floor. by requirement 2 the relationship already exists by StoreFloors. this requirement also wants us to group the floors by its categories. So we need the FloorCategory class:

Class Name: User
Attributes: UserID, First_Name, Last_Name, Address
Operations: AddUser(), ModifyUser()

class: Floor_Category
Attributes: CategoryID, Name, Description 
Operations: addCategory(), removeCategory(), addFloor(), editFloor(), deleteFloor()

6- to implement this requirement I added a searchFloor() operation to the FloorStore class 

8- To realize this requirement we'll add the Attribute Material to Floor_Category class
9- To realize this requirement we'll add the Attribute Species to Floor class
10- This requirement is implemented in the Description attribute of the Floor_Category class
11-  This is not a class design requirement, but a UI requirement. So it's not considered because it doesn't affect the design directly