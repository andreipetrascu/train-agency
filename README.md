# train-agency

Application description:
Develop (analyze, design, implement) a desktop application that can be used by a train ticket booking agency.
The application will have 3 types of users: passenger, employee and administrator.

Passenger users can perform the following operations without authentication:
❖ Viewing the list of trains by departure station, destination, duration;
❖ Viewing the list of trains between 2 locations, including price and availability of free seats;
❖ Search for a train by number.

Employee-type users can perform the following operations after authentication:
❖ All operations allowed to passenger users;
❖ CRUD operations regarding the persistence of trains and tickets sold;
❖ Selling a ticket to a traveler;
❖ Viewing statistics related to tickets sold: percentages by departure station, destination, price using graphs (radial structure, ring structure, column type, etc.);
❖ Save reports / lists with information about trains in several formats: csv, json.

Administrator users can perform the following operations after authentication:
❖ CRUD operations for information related to employee-type users.
