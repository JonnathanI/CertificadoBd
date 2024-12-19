CREATE TABLE IF NOT EXISTS students(
       id SERIAL,
       name VARCHAR (150) NOT NULL,
       dni VARCHAR(50) NOT NULL UNIQUE,
       PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS trainer(
      id SERIAL,
      name VARCHAR(300),
      area VARCHAR(300),
      PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS  course(
      id SERIAL,
      name VARCHAR(100),
      rol VARCHAR(255),
      link VARCHAR(255) ,
      code VARCHAR(150) UNIQUE ,
      aim VARCHAR(600) ,
      contents VARCHAR(900),
      students_id INT,
      trainer_id INT,
      FOREIGN KEY (students_id) REFERENCES students(id),
      FOREIGN KEY (trainer_id) REFERENCES trainer(id),
      PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS  certificate(
       id SERIAL,
       hours VARCHAR(25),
       date VARCHAR(150),
       course_id INT,
       FOREIGN KEY (course_id) REFERENCES course(id),
       PRIMARY KEY (id)
);



