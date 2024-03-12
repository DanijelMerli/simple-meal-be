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

INSERT INTO extras (id_extra, extra_type, price) VALUES
        (6, 1, 2.0),
         (7, 0, 1.5),
         (8, 0, 2.0),
         (9, 1, 3.0),
         (10, 1, 3.5);


INSERT INTO regular_meals (id_regular, small_price, large_price) VALUES
           (1, 15.0, 30.0),
           (3, 16.0, 32.0),
           (5, 20.0, 40.0);

INSERT INTO fit_meals (id_fit, price, should_Order_Early) VALUES
             (2, 15.0, 1),
             (4, 16.0, 0);