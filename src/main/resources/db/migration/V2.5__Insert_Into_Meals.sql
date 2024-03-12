INSERT INTO meals (name, description) VALUES
      ('Spaghetti Bolognese', 'Classic Italian pasta with meat sauce'),
      ('Chicken Caesar Salad','Fresh romaine lettuce topped with grilled chicken and Caesar dressing'),
      ('Margherita Pizza', 'Traditional Italian pizza with tomato sauce, mozzarella cheese, and fresh basil'),
      ('Grilled Salmon', 'Grilled salmon fillet served with roasted vegetables'),
      ('Beef Burger', 'Juicy beef patty topped with lettuce, tomato, and cheese, served with fries'),
      ('Homemade Cookie', 'Your grandmother made this for ya'),
      ('Caesar Dressing Soup', 'Creamy Caesar dressing for salads'),
      ('Capri Cake', 'Tastes like strawberries..on the summer evening..'),
      ('Avocado Soup', 'Fresh slices of avocado in soup'),
      ('Watermelon sugar', 'Your fave dessert you have never tried');

INSERT INTO extras (extra_id, extra_type, price) VALUES
        (6, 1, 2.0),
         (7, 0, 1.5),
         (8, 0, 2.0),
         (9, 1, 3.0),
         (10, 1, 3.5);


INSERT INTO main_courses (large_price, main_course_id, should_Order_Early, small_price, type_menu) VALUES
           (18.0, 1, 0, 15.0, 0),
           (28.0, 2, 1, 22.0, 1),
           (20.0, 3, 0, 16.0, 0),
           (30.0, 4, 1, 25.0, 1),
           (35.0, 5, 1, 20.0, 1);