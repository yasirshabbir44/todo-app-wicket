
-- A script to initiate the database with some data.
 INSERT INTO person(id, name, imageUrl, createdat, updatedat, version) VALUES ('0ca6271d-a96e-4864-9abc-15980c0de842', 'Yasir Shabbir', 'https://randomuser.me/api/portraits/men/33.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
 -- A script to initiate the database with some data.
  INSERT INTO person(id, name, imageUrl, createdat, updatedat, version) VALUES ('cd5cd458-294d-4ef9-99eb-0a15848433c7', 'Mohit Manrai', 'https://randomuser.me/api/portraits/men/78.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
 -- A script to initiate the database with some data.
  INSERT INTO person(id, name, imageUrl, createdat, updatedat, version) VALUES ('cd5cd458-294d-4ef9-99eb-0a15841133c7', 'John Smith', 'https://randomuser.me/api/portraits/men/85.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);


  INSERT INTO task(id, title,description,priority,status,duedate, updatedat, version,person_id) VALUES ('0ca6271d-a96e-4864-9abc-15980c0de842', 'Lorem ipsum dolor sit amet', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua','HIGH','PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0,'0ca6271d-a96e-4864-9abc-15980c0de842');
   INSERT INTO task(id, title,description,priority,status,duedate, updatedat, version,person_id) VALUES ('a275575a-b766-4d63-8d75-695a1e6dfe68', 'Lorem ipsum dolor sit amet', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua','MEDIUM','PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0,'0ca6271d-a96e-4864-9abc-15980c0de842');

  INSERT INTO task(id, title,description,priority,status,duedate, updatedat, version,person_id) VALUES ('0ca6271d-a96e-4864-9abc-15980c0de811', 'Lorem ipsum dolor sit amet', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua','LOW','COMPLETED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0,'cd5cd458-294d-4ef9-99eb-0a15848433c7');
   INSERT INTO task(id, title,description,priority,status,duedate, updatedat, version,person_id) VALUES ('a7258134-a62e-4c8a-bc65-179a08930b2f', 'Lorem ipsum dolor sit amet', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua','MEDIUM','PENDING', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0,'cd5cd458-294d-4ef9-99eb-0a15848433c7');

COMMIT;