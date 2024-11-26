CREATE TABLE IF NOT EXISTS students(
       id SERIAL,
       name VARCHAR (150) NOT NULL,
       dni VARCHAR(50) NOT NULL UNIQUE,
        rol VARCHAR(250),
       PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS  course(
      id SERIAL,
      name VARCHAR(100),
      link VARCHAR(255) ,
      code VARCHAR(150) UNIQUE ,
      aim VARCHAR(600) ,
      contents VARCHAR(900),
      students_id INT,
      FOREIGN KEY (students_id) REFERENCES students(id),
      PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS trainer(
      id SERIAL,
      name VARCHAR(250) NOT NULL,
      dni VARCHAR(50) NOT NULL UNIQUE,
      area VARCHAR(150) NOT NULL,
      burden VARCHAR(150) NOT NULL ,
      course_id INT,
      FOREIGN KEY (course_id) REFERENCES course(id),
      PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS  certificate(
       id SERIAL,
       hours VARCHAR(25) NOT NULL,
       date VARCHAR(150) NOT NULL UNIQUE,
       trainer_id INT,
       FOREIGN KEY (trainer_id) REFERENCES trainer(id),
       PRIMARY KEY (id)
);



