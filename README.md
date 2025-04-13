This project is a Library Management System. It is a GUI application made using Java for the backend and Java FX (FXML) for the frontend as well as writing queries to connect
to databases, create tables, and perform various other operations. This app is meant to be like a virtual library platform, allowing users to access books and search for them
and for admins to add books, search books, and do various other advanced operaitons.

When the application starts, there is an automatic window for the user asking for the user to log in, which then redirects them to where they can access different tools.
It asks for the username and password and there is also a submit button, which if clicked validates the user's log in credentials by cross referencing it using the database.

![Screenshot 2025-04-12 220741](https://github.com/user-attachments/assets/f74071a4-fc3d-4a9b-89b7-179a019e56c5)
This is a picture of how the application looks when first opened.

However, if there is a new person wanting to signup, then there is a button called New Signup which will then redirect you to another page creating a new user (either a regular user
or an admin).

![image](https://github.com/user-attachments/assets/bd0744fc-14cf-423c-b751-8a91983e7573)
This is a picture of how the application looks on the signup page.

This creates a new username. It asks for a username, a password, and a password confirmation. Then, depending on if the person wants to be a user or an admin, there are two options.
After the respective user is created and added to the database, it returns back to the user login page or the person can go back anytime they want using the back button.

If the user does log in, it takes them to a seperate page where they can do various functions of their own.

![image](https://github.com/user-attachments/assets/f0131bf1-d7e3-46e2-bb0a-f0c501fefaa8)
This is a picture of the page of the user functions.

There are two entry fields for the ISBN of a book, either for the user to borrow a book with an ISBN they have or to return a book they have borrowed.
Clicking the borrow book or return book button will validate the ISBN and then do the function if it works.

There are also two more buttons: show books and filter book.

The show books button will lead you to another page where it displays a table which shows all the books that are in the system. It displays their ID, title, author, and the quantity.

![image](https://github.com/user-attachments/assets/fc5eac1a-11f0-4abb-baec-d9c8333b1df5)
This picture shows the page of the table of all the books.

There was also a filter book button. This button will also lead you to another book where it also displays a table, but depending on which filter the user fills in, the title or the author of the book,
the table will display the books that fill the criteria.

![image](https://github.com/user-attachments/assets/2b298a9e-e6a8-41a8-8507-75a60b1a8cc6)

These are all the functions for the user.

Back to the starting page, there is also an admin login page. If clicked, this redirects you to another page for the login of an admin user, which is exactly like how the user login works.

![image](https://github.com/user-attachments/assets/dfcf829b-6a8f-4a3a-a5be-2eb1c335c2c4)

After the login is validated, the admin user is taken to a page for their functions.

![image](https://github.com/user-attachments/assets/fb343911-a779-4fbd-97a2-198d1b021d2f)
This is a picture of the page of the admin book management.

The first button is called Borrow Book History. As the name says, when clicked, it leads the admin to another page where they can see which user borrowed (or returned) what book at what time.
![image](https://github.com/user-attachments/assets/36888332-f191-4306-a53d-5e1cae48bd6d)

The next button is called add book. If clicked, it also redirects the admin to another page where they have to fill out 3 fields (ISBN, Title, and Author), and if the add book button is clicked,
then the database and all other tables using book will now include this information.

![image](https://github.com/user-attachments/assets/3715975d-1f1b-4230-8172-f9ce58b35bd6)

The next button is called update book. If clicked, it redirects the admin to another page where they can update the information of a book. They have to enter the ISBN, and then they can enter
a new book title or author, and if the update book button is clicked, it will grab the book with that ISBN and update the corresponding information.

![image](https://github.com/user-attachments/assets/1d70d4a6-c56b-493f-8cc1-fe069f08f85a)

The next button is called remove book. If clicked, it redirects the admin to another page where they have to enter the ISBN of a book, and if it is correct, the book will be removed from all tables
and databases.

![image](https://github.com/user-attachments/assets/ed8ef1d0-4c5b-4c12-9130-1deed3452aa5)

The final button for the admin is called search book. If the admin enters the ISBN of the book and clicks the search book button, it will display the title and the author of the book in two uneditable
text fields.

![image](https://github.com/user-attachments/assets/5137715f-b41b-4535-a479-0503e95c12cd)

These are all the functions for the admin user, and this is what this Library Management System application does.









